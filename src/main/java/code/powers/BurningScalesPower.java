package code.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class BurningScalesPower extends AbstractEasyPower {

    public static final String POWER_ID = makeID("BurningScalesPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public BurningScalesPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void stackPower(int stackAmount){
        amount += stackAmount;
        updateDescription();
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount){
        if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != this.owner) {
            flash();
            int damage = amount;
            if (info.owner.hasPower(CauterizePower.POWER_ID)) {
                damage += info.owner.getPower(CauterizePower.POWER_ID).amount;
            }
            att(new DamageAction(info.owner, new DamageInfo(AbstractDungeon.player, damage, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
        }
        return damageAmount;
    }

    @Override
    public void atStartOfTurn(){
        atb(new RemoveSpecificPowerAction(adp(), adp(), POWER_ID));
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    };
}
