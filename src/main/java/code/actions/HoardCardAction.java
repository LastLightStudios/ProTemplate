package code.actions;

import basemod.ReflectionHacks;
import code.powers.PridePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static code.util.Wiz.*;

public class HoardCardAction extends AbstractGameAction {
    private AbstractPlayer p = adp();

    // These are for "Hoard this card"
    private AbstractCard playedCard;
    boolean hoardThisCard = false;

    // These are to Hoard other cards
    private boolean isRandom;
    private boolean anyNumber;
    private boolean canPickZero;

    public HoardCardAction(AbstractCard playedCard) {
        this.playedCard = playedCard;
        hoardThisCard = true;
    }

    public HoardCardAction(int amount, boolean isRandom, boolean anyNumber, boolean canPickZero){
        this.amount = amount;
        this.isRandom = isRandom;
        this.anyNumber = anyNumber;
        this.canPickZero = canPickZero;
    }

    // This will be called when you want to pick a number without a cap
    public HoardCardAction(boolean anyNumber, boolean canPickZero){
        this(99, false, anyNumber, canPickZero);
    }

    // This will be called when you want to pick a number with a cap
    public HoardCardAction(int amount, boolean anyNumber, boolean canPickZero){
        this(amount, false, anyNumber, canPickZero);
    }

    // This is called when you want to Hoard a random number of cards
    public HoardCardAction(int amount){
        this(amount, true, false, false);
    }

    @Override
    public void update() {
        if (hoardThisCard) {
            //probably need to add a check in here to see if its a duplicated card from double tap/burst...b/c I dont think the player would expect those to Hoard
            applyToSelfTop(new PridePower(adp(), 1));
            setCardToExhaust(playedCard);
            this.isDone = true;
        } else if (isRandom) {
            for (int i = 0; i < this.amount; i++){

            }
        } else {

        }
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
