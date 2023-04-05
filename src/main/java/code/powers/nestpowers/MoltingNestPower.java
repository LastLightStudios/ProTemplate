package code.powers.nestpowers;

import code.actions.ReduceDebuffsAction;
import code.powers.AbstractEasyPower;
import code.util.DragonUtils;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import static code.ModFile.makeID;
import static code.util.Wiz.atb;

public class MoltingNestPower extends AbstractEasyPower {

    public static final String POWER_ID = makeID("ImposingNestPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public MoltingNestPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        priority = DragonUtils.PowerPriorities.EARLY_NEST_PRIORITY;
        loadRegion("closeUp");
    }

    @Override
    public void atEndOfTurnPreEndTurnCards(boolean isPlayer){
        if (EnergyPanel.getCurrentEnergy() > 0){
            flash();
            atb(new ReduceDebuffsAction(owner, amount));
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    };
}
