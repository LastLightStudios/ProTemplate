package code.powers;

import code.cards.ScorchingFang;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static code.ModFile.makeID;

public class CauterizePower extends AbstractEasyPower {

    public static final String POWER_ID = makeID("CauterizePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public CauterizePower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.DEBUFF, false, owner, amount);
        loadRegion("nirvana");
    }

    @Override
    public void stackPower(int stackAmount){
        amount += stackAmount;
        updateDescription();
    }

    @Override
    public int onHeal(int healAmount){
        flash();
        return 0;
    }

    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        int multiplier = 1;
        if (card instanceof ScorchingFang){
            multiplier = ((ScorchingFang)card).getCauterizeMultiplier();
        }
        return type == DamageInfo.DamageType.NORMAL ? damage + ((float)this.amount * multiplier) : damage;
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    };
}
