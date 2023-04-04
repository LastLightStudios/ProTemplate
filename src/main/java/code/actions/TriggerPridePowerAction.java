package code.actions;

import code.powers.PridePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static code.util.Wiz.adp;
import static code.util.Wiz.att;

public class TriggerPridePowerAction extends AbstractGameAction {

    public TriggerPridePowerAction() {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.WAIT;
        this.source = adp();
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST){
            AbstractPower pride = adp().getPower(PridePower.POWER_ID);
            if (pride == null){
                System.out.println("make sure to create the Pride Power before calling TriggerPridePowerAction()");
            } else {
                att(new PridePowerAction(pride));
            }
        }
        this.isDone = true;
    }
}
