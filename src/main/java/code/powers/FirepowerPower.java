package code.powers;

import code.util.DragonUtils;
import com.evacipated.cardcrawl.mod.stslib.patches.NeutralPowertypePatch;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static code.ModFile.makeID;

public class FirepowerPower extends AbstractEasyPower {

    public static final String POWER_ID = makeID("FirepowerPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public FirepowerPower(AbstractCreature owner) {
        super(POWER_ID, NAME, NeutralPowertypePatch.NEUTRAL, false, owner, DragonUtils.DEFAULT_EMBER_BREAKPOINT);
        priority = DragonUtils.PowerPriorities.FIREPOWER_PRIORITY;
        loadRegion("attackBurn");
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    };
}
