package code.powers.nestpowers;

import code.powers.AbstractEasyPower;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class DelayedTempStrPower extends AbstractEasyPower {

    public static final String POWER_ID = makeID("DelayedTempStrPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public DelayedTempStrPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        loadRegion("backAttack");
    }

    @Override
    public void atStartOfTurn(){
        flash();
        applyToSelf(new StrengthPower(adp(), amount));
        applyToSelf(new LoseStrengthPower(adp(), amount));
        atb(new RemoveSpecificPowerAction(adp(), adp(),POWER_ID));
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    };
}
