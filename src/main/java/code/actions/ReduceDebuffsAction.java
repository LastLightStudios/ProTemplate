package code.actions;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;
import java.util.Iterator;

public class ReduceDebuffsAction extends AbstractGameAction {
    private AbstractCreature c;

    public ReduceDebuffsAction(AbstractCreature c, int amount) {
        this.c = c;
        this.amount = amount;
    }

    @Override
    public void update() {

        Iterator var1 = this.c.powers.iterator();

        while(var1.hasNext()) {
            AbstractPower p = (AbstractPower)var1.next();
            boolean isTurnBased = ReflectionHacks.getPrivate(p, AbstractPower.class, "isTurnBased");
            if ((p.type == PowerType.DEBUFF) && isTurnBased) {
                this.addToTop(new ReducePowerAction(this.c, this.c, p.ID, amount));
            }
        }
        this.isDone = true;
    }
}
