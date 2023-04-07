package code.actions;

import basemod.ReflectionHacks;
import code.powers.HoardingPowerInterface;
import code.powers.PridePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

/*
If I ever want to add card filters, then reference
https://github.com/kiooeht/StSLib/blob/master/src/main/java/com/evacipated/cardcrawl/mod/stslib/actions/common/SelectCardsInHandAction.java
 */

public class HoardCardAction extends AbstractGameAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("Hoard"));

    public static final int IS_RANDOM = 1;
    public static final int CAN_PICK_ZERO = 2;
    public static final int ANY_NUMBER = 4;

    private static final int basePrideGainPerHoardedCard = 1;
    private static int prideGainPerHoardedCard = 1;

    private boolean firstCall = true;
    private boolean hooksTriggered = false;
    private boolean completed = false;

    private Consumer<List<AbstractCard>> callback;
    private ArrayList<AbstractCard> tempHand;

    // These are for "Hoard this card"
    private AbstractCard playedCard;
    boolean hoardThisCard = false;

    // These are to Hoard other cards
    private boolean isRandom = false;
    private boolean canPickZero = false;
    private boolean anyNumber = false;

    public HoardCardAction(AbstractCard playedCard) {
        this.playedCard = playedCard;
        hoardThisCard = true;

        tempHand = new ArrayList<>();
    }

    public HoardCardAction(int amount){
        this(amount, 0);
    }

    public HoardCardAction(int amount, int flags){
        this.amount = amount;
        isRandom = (flags & IS_RANDOM) != 0;
        canPickZero = (flags & CAN_PICK_ZERO) != 0;
        anyNumber = (flags & ANY_NUMBER) != 0;

        tempHand = new ArrayList<>();
    }

    // Hey, don't add random effects to randomly Hoarded cards, cuz it doesn't work right now and you'll need to fix it!
    // Probably needs a different callback set up, a single card multiple times, potentially...or maybe it does work as it? it's just gonna
    // be multiple lists of a single card? idk you didn't design one for it yet so don't worry about it.
    // Also remember that the callback will be called before the cards are Exhausted, but if its an atb not att in the use function it should be fine?
    public HoardCardAction(int amount, int flags, int prideGainIncrease, Consumer<List<AbstractCard>> callback){
        this(amount, flags);
        prideGainPerHoardedCard += prideGainIncrease;
        this.callback = callback;
    }

    @Override
    public void update() {
        if (completed){
            // if (effect.isDone || !AbstractDungeon.topLevelEffects.contains(effect))  where effect is some `private HoardCardEffect effect` I haven't figured out yet
            isDone = true;
            // can add some wrap-up/finish method call here if necessary in the future, such as checking for if powers have some type of onHoard effect.
            prideGainPerHoardedCard = basePrideGainPerHoardedCard;
            return;
        }
        if (!hooksTriggered){
            hooksTriggered = true;
            // this is where we would check for powers that would modify the Hoard effect
            return;
        }
        if (firstCall){
            if (hoardThisCard) {
                // "Hoard this card." means that none of the other branches apply.
                //TODO ? add a check in here to see if its a duplicated card from double tap/burst...but maybe not, I think the card would still give Pride, just not end up in Exhaust pile
                applyToSelfTop(new PridePower(adp(), prideGainPerHoardedCard));
                setCardToExhaust(playedCard);
                for (AbstractPower power : adp().powers) {
                    if (power instanceof HoardingPowerInterface) {
                        tempHand.add(playedCard);
                        ((HoardingPowerInterface) power).onHoard(tempHand);
                    }
                }
            } else if (adp().hand.size() == 0) {
                // if the hand is empty then there's nothing to Hoard
            } else if (!anyNumber && adp().hand.size() <= amount) {
                // if the player is required to Hoard more than the remaining cards in their hand
                amount = adp().hand.size();
                int tmp = adp().hand.size();
                if (callback != null){
                    callback.accept(adp().hand.group);
                }
                hoardCards(new ArrayList<>(adp().hand.group));
            } else if (isRandom){
                //
                if (amount > adp().hand.size()){
                    amount = adp().hand.size();
                }
                if (callback != null){
                    callback.accept(adp().hand.group);
                }
                for (int i = 0; i < amount; i++){
                    AbstractCard card = adp().hand.getRandomCard(AbstractDungeon.cardRandomRng);
                    tempHand.add(card);
                    adp().hand.moveToExhaustPile(card);
                    CardCrawlGame.dungeon.checkForPactAchievement();
                    applyToSelfTop(new PridePower(adp(), prideGainPerHoardedCard));
                }
                for (AbstractPower power : adp().powers) {
                    if (power instanceof HoardingPowerInterface) {
                        ((HoardingPowerInterface) power).onHoard(tempHand);
                    }
                }
            } else {
                // This branch should be for conditions that require the Card Select screen
                if (amount == 1){
                    AbstractDungeon.handCardSelectScreen.open(uiStrings.TEXT[0], amount, anyNumber, canPickZero);
                } else {
                    AbstractDungeon.handCardSelectScreen.open(uiStrings.TEXT[0], amount, anyNumber, canPickZero);
                }
                firstCall = false;
                return;
            }
            completed = true;
            firstCall = false;
        } else {
            // this branch is only reached if firstCall = false && completed = false, so it should only be the branch that opened the Card Select Screen
            if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved){
                if (callback != null){
                    callback.accept(AbstractDungeon.handCardSelectScreen.selectedCards.group);
                }
                hoardCards(AbstractDungeon.handCardSelectScreen.selectedCards.group);
                AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
                AbstractDungeon.handCardSelectScreen.selectedCards.clear();
                AbstractDungeon.player.hand.refreshHandLayout();
                AbstractDungeon.player.hand.applyPowers();
                completed = true;
            } else {
                //
            }
        }
        return;
    }

    private void setCardToExhaust(AbstractCard card) {
        for (AbstractGameAction action : AbstractDungeon.actionManager.actions) {
            if (action instanceof UseCardAction){
                AbstractCard targetCard = ReflectionHacks.getPrivate(action, UseCardAction.class, "targetCard");
                if (targetCard == card){
                    ((UseCardAction) action).exhaustCard = true;
                }
            }
        }
    }

    private void hoardCards(List<AbstractCard> cardList){
        int tmp = cardList.size();
        for (int i = 0; i < tmp; i++) {
            // the following for loop doesn't actually do anything xd
            for (AbstractPower power : adp().powers){
                if (power instanceof HoardingPowerInterface){
                    ((HoardingPowerInterface) power).onHoard(cardList);
                }
            }
                /*
                if (card instanceof cardWithOnHoard){
                card.OnHoard();}
                 */
        }
        for (AbstractCard c : cardList){
            adp().hand.moveToExhaustPile(c);
        }
        CardCrawlGame.dungeon.checkForPactAchievement();
        for (int i = 0; i < tmp; i++) {
            // this happens in a second loop so that all the cards exhaust in a row, then the Pride adds up for each card Hoarded
            applyToSelfTop(new PridePower(adp(), prideGainPerHoardedCard));
        }
    }
}
