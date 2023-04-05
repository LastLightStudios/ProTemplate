package code.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.evacipated.cardcrawl.mod.stslib.patches.NeutralPowertypePatch;

import static code.ModFile.makeID;

public class OutscalePower extends AbstractEasyPower {

    public static final String POWER_ID = makeID("OutscalePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public OutscalePower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, NeutralPowertypePatch.NEUTRAL, false, owner, amount);
        loadRegion("deva");
    }
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    };
}
