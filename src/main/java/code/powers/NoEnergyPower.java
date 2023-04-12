package code.powers;

import code.util.DragonUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static code.ModFile.makeID;
import static code.util.Wiz.adp;
import static code.util.Wiz.atb;

public class NoEnergyPower extends AbstractEasyPower {

    public static final String POWER_ID = makeID("NoEnergyPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private boolean allowEnergyGain = false;

    public NoEnergyPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.DEBUFF, true, owner, amount);
        priority = DragonUtils.PowerPriorities.NO_ENERGY_PRIORITY;
        loadRegion("no_stance");
    }

    @Override
    public void atStartOfTurnPostDraw() {
        addToBot(new AbstractGameAction() {
            public void update() {
                addToBot(new AbstractGameAction() {
                    public void update() {
                        allowEnergyGain = false;
                        this.isDone = true;
                    }
                });
                this.isDone = true;
            }
        });
    }

    @Override
    public void atEndOfRound() {
        allowEnergyGain = true;
        if (amount == 0) {
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        } else {
            addToBot(new ReducePowerAction(this.owner, this.owner, POWER_ID, 1));
        }
    }

    @Override
    public void updateDescription() {
        if (amount == 1){
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        } else {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
        }
    }

    public boolean isAllowEnergyGain(){
        return allowEnergyGain;
    }
}
