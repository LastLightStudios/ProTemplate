package code.powers.nestpowers;

import code.powers.AbstractEasyPower;
import code.powers.CauterizePower;
import code.util.DragonUtils;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import static code.ModFile.makeID;
import static code.util.Wiz.*;
import static code.util.Wiz.applyToEnemy;

public class VolcanicNestPower extends AbstractEasyPower {

    public static final String POWER_ID = makeID("VolcanicNestPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public VolcanicNestPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        priority = DragonUtils.PowerPriorities.CAUTERIZE_NEST_PRIORITY;
        loadRegion("firebreathing");
    }

    @Override
    public void atEndOfTurn(boolean isPlayer){
        if (EnergyPanel.getCurrentEnergy() > 0){
            for (AbstractMonster monster : getEnemies()){
                applyToEnemy(monster, new CauterizePower(monster, amount));
            }
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    };
}
