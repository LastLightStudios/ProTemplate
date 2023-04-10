package code.powers;


import code.actions.TransformTwoSidedCardAction;
import code.cards.AbstractTwoSidedCard;
import code.cards.sparkbreaths.AbstractSparkBreathCard;
import code.util.DragonUtils;
import code.util.Wiz;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import code.util.DragonUtils.CustomTags;
import com.megacrit.cardcrawl.powers.PhantasmalPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class EmberPower extends AbstractEasyPower {

    public static final String POWER_ID = makeID("EmberPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final int EMBER_BREAKPOINT = 5;

    public EmberPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        priority = DragonUtils.PowerPriorities.EMBER_PRIORITY;
        if (this.amount >= 9999)
            this.amount = 9999;
        loadRegion("vigor");
        canGoNegative = true;
        updateDescription();
    }

    // need to make all of these also trigger not just in hand
    // probably need to extract into a change to spark and change to breath method
    @Override
    public void onInitialApplication(){
        if (amount >= EMBER_BREAKPOINT){
            swapSparkCards();
        } else {
            swapBreathCards();
        }
    }

    @Override
    public void stackPower(int stackAmount){
        super.stackPower(stackAmount);
        if (amount >= EMBER_BREAKPOINT){
            swapSparkCards();
        } else {
            swapBreathCards();
        }
    }

    @Override
    public void onRemove(){
        swapBreathCards();
    }

    @Override
    public void reducePower(int reduceAmount){
        super.reducePower(reduceAmount);
        if (amount >= EMBER_BREAKPOINT){
            swapSparkCards();
        } else {
            swapBreathCards();
        }
    }

    public void swapSparkCards(){ // Swap Sparks to Breaths
        for (AbstractCard c : Wiz.getAllCardsInCardGroups(true, true)){
            if (DragonUtils.isSpark(c) && c instanceof AbstractTwoSidedCard){
                atb(new TransformTwoSidedCardAction((AbstractTwoSidedCard) c, true, Color.valueOf("cc5500")));
            }
        }
        loadRegion("doubleDamage");
    }

    public void swapBreathCards(){ // Swap Breaths to Sparks
        for (AbstractCard c : Wiz.getAllCardsInCardGroups(true, true)){
            if (DragonUtils.isBreath(c) && c instanceof AbstractTwoSidedCard){
                atb(new TransformTwoSidedCardAction((AbstractTwoSidedCard) c, false, Color.valueOf("cc5500")));
            }
        }
        loadRegion("vigor");
    }

    public static int getEmberBreakpoint(){
        return EMBER_BREAKPOINT;
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        if (amount > 0){
            type = PowerType.BUFF;
        } else {
            type = PowerType.DEBUFF;
        }
    };

    @Override
    public void onVictory(){
        swapBreathCards();
        this.amount = 0;
        AbstractGameAction hailMary = new RemoveSpecificPowerAction(owner, owner, POWER_ID);
        hailMary.actionType = AbstractGameAction.ActionType.DAMAGE;
        atb(hailMary);
    }
}