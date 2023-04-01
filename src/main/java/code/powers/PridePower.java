package code.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class PridePower extends AbstractEasyPower {

    public static final String POWER_ID = makeID("PridePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public PridePower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void stackPower(int stackAmount){
        amount += stackAmount;
        updateDescription();
    }

    public boolean isAnEnemyAttacking(){
        for (AbstractMonster mon : getEnemies()){
            if (mon != null && mon.getIntentBaseDmg() >= 0){
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateDescription() {
        if (this.amount == 1){
            description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + "1" + DESCRIPTIONS[2];
        } else {
            description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + (this.amount / 2) + DESCRIPTIONS[2];
        }
    };

    public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
        if (isAnEnemyAttacking()){
            flash();
            atb(new GainBlockAction(this.owner, this.owner, this.amount));
            if (this.amount == 1){
                atb(new ReducePowerAction(this.owner, this.owner, this, 1));
            } else {
                atb(new ReducePowerAction(this.owner, this.owner, this, (this.amount / 2)));
            }
        }
    }
}
