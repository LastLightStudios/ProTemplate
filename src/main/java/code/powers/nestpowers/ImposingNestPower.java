package code.powers.nestpowers;

import code.powers.AbstractEasyPower;
import code.util.DragonUtils;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import static code.ModFile.makeID;
import static code.util.Wiz.*;
import static code.util.Wiz.applyToEnemy;

public class ImposingNestPower extends AbstractEasyPower {

    public static final String POWER_ID = makeID("ImposingNestPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public ImposingNestPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        priority = DragonUtils.PowerPriorities.EARLY_NEST_PRIORITY;
    }

    @Override
    public void atEndOfTurn(boolean isPlayer){
        if (EnergyPanel.getCurrentEnergy() > 0){
            flash();
            for (AbstractMonster mon : getEnemies()){
                applyToEnemy(mon, new StrengthPower(mon, -amount));
            }
            for (AbstractMonster mon : getEnemies()){
                if (!mon.hasPower(ArtifactPower.POWER_ID)){
                    applyToEnemy(mon, new GainStrengthPower(mon, amount));
                }
            }
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    };
}
