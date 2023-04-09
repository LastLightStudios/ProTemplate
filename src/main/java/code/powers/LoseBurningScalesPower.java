package code.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class LoseBurningScalesPower extends AbstractEasyPower {

    public static final String POWER_ID = makeID("LoseBurningScalesPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public LoseBurningScalesPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.DEBUFF, false, owner, amount);
        loadRegion("bias");
    }

    @Override
    public void atStartOfTurn(){
        flash();
        atb(new ReducePowerAction(adp(), adp(), BurningScalesPower.POWER_ID, amount));
        atb(new RemoveSpecificPowerAction(adp(), adp(), POWER_ID));
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    };
}
