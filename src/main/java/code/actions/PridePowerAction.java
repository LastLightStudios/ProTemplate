package code.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static code.util.Wiz.*;

public class PridePowerAction extends AbstractGameAction {
    private AbstractPower power;

    public PridePowerAction(AbstractPower power) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.WAIT;
        this.source = adp();
        this.power = power;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST){
            if (isAnEnemyAttacking()){
                power.flash();
                if (power.amount == 1){
                    att(new ReducePowerAction(power.owner, power.owner, power, 1));
                } else {
                    att(new ReducePowerAction(power.owner, power.owner, power, (power.amount / 2)));
                }
                att(new GainBlockAction(power.owner, power.owner, power.amount));
            }
        }
        this.isDone = true;
    }

    public boolean isAnEnemyAttacking(){
        for (AbstractMonster mon : getEnemies()){
            if (mon != null && mon.getIntentBaseDmg() >= 0){
                return true;
            }
        }
        return false;
    }
}
