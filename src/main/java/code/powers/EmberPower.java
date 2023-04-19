package code.powers;


import code.actions.CheckEmberBreakpointAction;
import code.cards.sparkbreaths.AbstractSparkBreathCard;
import code.util.DragonUtils;
import code.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static code.ModFile.makeID;
import static code.util.DragonUtils.getEmberBreakpoint;
import static code.util.Wiz.*;

public class EmberPower extends AbstractEasyPower {

    public static final String POWER_ID = makeID("EmberPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public EmberPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        priority = DragonUtils.PowerPriorities.EMBER_PRIORITY;
        loadRegion("vigor");
        if(!owner.hasPower(FirepowerPower.POWER_ID)){
            applyToSelf(new FirepowerPower(owner));
        }
        updateDescription();
    }

    // need to make all of these also trigger not just in hand
    // probably need to extract into a change to spark and change to breath method
    @Override
    public void onInitialApplication(){
        if (amount >= getEmberBreakpoint()){
            swapSparkCards();
        } else {
            swapBreathCards();
        }
    }

    @Override
    public void stackPower(int stackAmount){
        super.stackPower(stackAmount);
        if (amount >= getEmberBreakpoint()){
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
        if (amount >= getEmberBreakpoint()){
            swapSparkCards();
        } else {
            swapBreathCards();
        }
    }

    public void swapSparkCards(){ // Swap Sparks to Breaths
        for (AbstractCard c : Wiz.getAllCardsInCardGroups(true, true)){
            if (c instanceof AbstractSparkBreathCard){
                att(new CheckEmberBreakpointAction((AbstractSparkBreathCard)c));
            }
        }
        loadRegion("doubleDamage");
    }

    public void swapBreathCards(){ // Swap Breaths to Sparks
        for (AbstractCard c : Wiz.getAllCardsInCardGroups(true, true)){
            if (c instanceof AbstractSparkBreathCard){
                att(new CheckEmberBreakpointAction((AbstractSparkBreathCard)c));
            }
        }
        loadRegion("vigor");
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
        //I don't think the next 3 lines are neccesary but at this point I'm kinda afraid to take them out and find out it doesn't work later
        //the previous line should be the one that's actually fixing it, though.
        AbstractGameAction hailMary = new RemoveSpecificPowerAction(owner, owner, POWER_ID);
        hailMary.actionType = AbstractGameAction.ActionType.DAMAGE;
        atb(hailMary);
    }
}