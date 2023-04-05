package code.powers;

import code.util.DragonUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class HoardingInstinctPower extends AbstractEasyPower {

    public static final String POWER_ID = makeID("HoardingInstinctPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public HoardingInstinctPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        if (this.amount >= 9999)
            this.amount = 9999;
        loadRegion("deva2");
        updateDescription();
    }

    @Override
    public void atStartOfTurn(){
        for (int i = 0; i < this.amount; i++){
            AbstractCard c = DragonUtils.returnTrulyRandomCardWithTagInCombat(DragonUtils.CustomTags.GEM).makeCopy();
            makeInHand(c);
        }
    }

    @Override
    public void updateDescription() {
        if (this.amount > 1) {
            description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
        } else {
            description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        }
    }
}
