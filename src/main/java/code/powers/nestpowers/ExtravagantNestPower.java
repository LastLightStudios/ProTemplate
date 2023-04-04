package code.powers.nestpowers;

import code.actions.PridePowerAction;
import code.actions.TriggerPridePowerAction;
import code.powers.AbstractEasyPower;
import code.powers.PridePower;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class ExtravagantNestPower extends AbstractEasyPower {

    public static final String POWER_ID = makeID("ExtravagantNestPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public ExtravagantNestPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void atEndOfTurnPreEndTurnCards(boolean isPlayer){
        if (EnergyPanel.getCurrentEnergy() > 0){
            applyToSelf(new PridePower(adp(), amount));
        }
        atb(new TriggerPridePowerAction());
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    };
}
