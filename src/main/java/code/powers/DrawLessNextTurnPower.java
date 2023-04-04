package code.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class DrawLessNextTurnPower extends AbstractEasyPower {

    public static final String POWER_ID = makeID("DrawLessNextTurnPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public DrawLessNextTurnPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.DEBUFF, true, owner, amount);
        this.updateDescription();
    }

    @Override
    public void onInitialApplication() {
        adp().gameHandSize -= amount;
    }

    @Override
    public void stackPower(int stackAmount) {
        adp().gameHandSize -= stackAmount;
        super.stackPower(stackAmount);
    }

    @Override
    public void onRemove() {
        adp().gameHandSize += amount;
    }

    @Override
    public void reducePower(int reduceAmount) {
        adp().gameHandSize += Math.min(reduceAmount,amount);
        super.reducePower(reduceAmount);
    }

    @Override
    public void atStartOfTurnPostDraw() {
        atb(new RemoveSpecificPowerAction(owner, owner, this));
    }

    @Override
    public void updateDescription() {
        if (amount == 1){
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        } else {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
        }
    };
}
