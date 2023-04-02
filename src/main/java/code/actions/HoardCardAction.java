package code.actions;

import basemod.ReflectionHacks;
import code.powers.PridePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

/*
If I ever want to add card filters, then reference
https://github.com/kiooeht/StSLib/blob/master/src/main/java/com/evacipated/cardcrawl/mod/stslib/actions/common/SelectCardsInHandAction.java
 */

public class HoardCardAction extends AbstractGameAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("Hoard"));
    private static final AbstractPlayer p = adp();

    public static final int IS_RANDOM = 2;
    public static final int CAN_PICK_ZERO = 4;
    public static final int ANY_NUMBER = 8;

    private static int prideGainPerHoardedCard = 1;

    private boolean firstCall = true;
    private boolean hooksTriggered = false;
    private boolean completed = false;

    // These are for "Hoard this card"
    private AbstractCard playedCard;
    boolean hoardThisCard = false;

    // These are to Hoard other cards
    private boolean isRandom;
    private boolean canPickZero;
    private boolean anyNumber;

    public HoardCardAction(AbstractCard playedCard) {
        this.playedCard = playedCard;
        hoardThisCard = true;
    }

    public HoardCardAction(int amount, int flags){
        this.amount = amount;
        isRandom = (flags & IS_RANDOM) == IS_RANDOM;
        canPickZero = (flags & CAN_PICK_ZERO) == CAN_PICK_ZERO;
        anyNumber = (flags & ANY_NUMBER) == ANY_NUMBER;
    }

    @Override
    public void update() {
        if (completed){
            // if (effect.isDone || !AbstractDungeon.topLevelEffects.contains(effect))  where effect is some `private HoardCardEffect effect` I haven't figured out yet
            isDone = true;
            // can add some wrap-up/finish method call here if necessary in the future, such as checking for if powers have some type of onHoard effect.
            return;
        }
        if (!hooksTriggered){
            hooksTriggered = true;
            // this is where stuff that would modify the Hoard effect go, like increasing the amount of Pride gained or something idk...
            return;
        }
        if (firstCall){
            if (hoardThisCard) {
                // "Hoard this card." means that none of the other branches apply.
                //TODO ? add a check in here to see if its a duplicated card from double tap/burst...but maybe not, I think the card would still give Pride, just not end up in Exhaust pile
                applyToSelfTop(new PridePower(adp(), prideGainPerHoardedCard));
                setCardToExhaust(playedCard);
            } else if (p.hand.size() == 0) {
                // if the hand is empty then there's nothing to Hoard
            } else if (!anyNumber && p.hand.size() <= amount) {
                // if the player is required to Hoard more than the remaining cards in their hand
                amount = p.hand.size();
                int tmp = p.hand.size();
                for (int i = 0; i < tmp; i++){
                    p.hand.moveToExhaustPile(p.hand.getTopCard());
                }
                CardCrawlGame.dungeon.checkForPactAchievement();
                for (int i = 0; i < tmp; i++){
                    // this happens in a second loop so that all the cards exhaust in a row, then the Pride adds up for each card Hoarded
                    applyToSelfTop(new PridePower(adp(), prideGainPerHoardedCard));
                }
            } else if (isRandom){
                if (amount > p.hand.size()){
                    amount = p.hand.size();
                }
                for (int i = 0; i < amount; i++){
                    p.hand.moveToExhaustPile(p.hand.getRandomCard(AbstractDungeon.cardRandomRng));
                    CardCrawlGame.dungeon.checkForPactAchievement();
                    applyToSelfTop(new PridePower(adp(), prideGainPerHoardedCard));
                }
            } else {
                // This branch should be for conditions that require the Card Select screen
                if (amount == 1){
                    AbstractDungeon.handCardSelectScreen.open(uiStrings.TEXT[1], amount, anyNumber, canPickZero);
                } else {
                    AbstractDungeon.handCardSelectScreen.open(uiStrings.TEXT[2], amount, anyNumber, canPickZero);
                }
                firstCall = false;
                return;
            }
            completed = true;
            firstCall = false;
        } else {
            // this branch is only reached if firstCall = false && completed = false, so it should only be the branch that opened the Card Select Screen
            if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved){
                int tmp = AbstractDungeon.handCardSelectScreen.selectedCards.group.size();
                for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group)
                    p.hand.moveToExhaustPile(c);
                CardCrawlGame.dungeon.checkForPactAchievement();
                for (int i = 0; i < tmp; i++) {
                    applyToSelfTop(new PridePower(p, prideGainPerHoardedCard));
                }
                AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
                AbstractDungeon.handCardSelectScreen.selectedCards.clear();
                AbstractDungeon.player.hand.refreshHandLayout();
                AbstractDungeon.player.hand.applyPowers();
                completed = true;
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
}
