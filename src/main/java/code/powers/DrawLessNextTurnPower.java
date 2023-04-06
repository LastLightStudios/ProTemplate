package code.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
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
        loadRegion("lessdraw");
    }

    @Override
    public void onInitialApplication() {
        adp().gameHandSize--;
    }

    @Override
    public void onRemove() {
        adp().gameHandSize++;
    }

    @Override
    public void reducePower(int reduceAmount) {
        super.reducePower(reduceAmount);
    }

    @Override
    public void atStartOfTurnPostDraw() {
        if (amount == 0){
            atb(new RemoveSpecificPowerAction(owner, owner, this));
        } else {
            atb(new ReducePowerAction(owner, owner, this, 1));
        }
    }

    @Override
    public void updateDescription() {
        if (amount == 1){
            description = DESCRIPTIONS[0] + DESCRIPTIONS[1];
        } else {
            description = DESCRIPTIONS[0] + DESCRIPTIONS[2] + amount + DESCRIPTIONS[3];
        }
    };
}
