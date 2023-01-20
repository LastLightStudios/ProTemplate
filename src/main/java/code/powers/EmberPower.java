package code.powers;


import org.apache.logging.log4j.Logger;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import code.actions.SwapCardsAction;
import code.cards.AbstractSwappableCard;
import code.util.CustomTags;

import static code.ModFile.makeID;
import static code.util.Wiz.adp;
import static code.util.Wiz.att;

public class EmberPower extends AbstractEasyPower {

    public static final String POWER_ID = makeID("EmberPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final int EMBER_BREAKPOINT = 5;

    public EmberPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        if (this.amount >= 9999)
            this.amount = 9999;

        updateDescription();
    }

    // need to make all of these also trigger not just in hand
    // probably need to extract into a change to spark and change to breath method
    @Override
    public void onInitialApplication(){
        if (amount >= EMBER_BREAKPOINT){
            swapSparkCards();
        }
    }

    @Override
    public void stackPower(int stackAmount){
        super.stackPower(stackAmount);
        if (amount >= EMBER_BREAKPOINT){
            swapSparkCards();
        }
    }

    @Override
    public void onRemove(){
        if (amount <= EMBER_BREAKPOINT ) {
            swapBreathCards();
        }
    }

    @Override
    public void reducePower(int reduceAmount){
        super.reducePower(reduceAmount);
        if (amount <= EMBER_BREAKPOINT ) {
            swapBreathCards();
        }
    }

    public void swapSparkCards(){
        for (AbstractCard c : adp().hand.group){
            if (isSpark(c) && c instanceof  AbstractSwappableCard){
                att(new SwapCardsAction((AbstractSwappableCard)c, (AbstractSwappableCard)c.cardsToPreview, adp().hand));
            }
        }for (AbstractCard c : adp().drawPile.group){
            if (isSpark(c) && c instanceof  AbstractSwappableCard){
                att(new SwapCardsAction((AbstractSwappableCard)c, (AbstractSwappableCard)c.cardsToPreview, adp().drawPile));
            }
        }
        for (AbstractCard c : adp().discardPile.group){
            if (isSpark(c) && c instanceof  AbstractSwappableCard){
                att(new SwapCardsAction((AbstractSwappableCard)c, (AbstractSwappableCard)c.cardsToPreview, adp().discardPile));
            }
        }for (AbstractCard c : adp().exhaustPile.group){
            if (isSpark(c) && c instanceof  AbstractSwappableCard){
                att(new SwapCardsAction((AbstractSwappableCard)c, (AbstractSwappableCard)c.cardsToPreview, adp().exhaustPile));
            }
        }
    }

    public void swapBreathCards(){
        for (AbstractCard c : adp().hand.group){
            if (isBreath(c) && c instanceof  AbstractSwappableCard){
                att(new SwapCardsAction((AbstractSwappableCard)c, (AbstractSwappableCard)c.cardsToPreview, adp().hand));
            }
        }for (AbstractCard c : adp().drawPile.group){
            if (isBreath(c) && c instanceof  AbstractSwappableCard){
                att(new SwapCardsAction((AbstractSwappableCard)c, (AbstractSwappableCard)c.cardsToPreview, adp().drawPile));
            }
        }
        for (AbstractCard c : adp().discardPile.group){
            if (isBreath(c) && c instanceof  AbstractSwappableCard){
                att(new SwapCardsAction((AbstractSwappableCard)c, (AbstractSwappableCard)c.cardsToPreview, adp().discardPile));
            }
        }for (AbstractCard c : adp().exhaustPile.group){
            if (isBreath(c) && c instanceof  AbstractSwappableCard){
                att(new SwapCardsAction((AbstractSwappableCard)c, (AbstractSwappableCard)c.cardsToPreview, adp().exhaustPile));
            }
        }
    }

    public static boolean isSpark(AbstractCard c){
        return c.hasTag(CustomTags.SPARK);
    }

    public static boolean isBreath(AbstractCard c){
        return c.hasTag(CustomTags.BREATH);
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    };
}
