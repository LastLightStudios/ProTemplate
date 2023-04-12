package code.actions;

import code.powers.DrawLessNextTurnPower;
import code.powers.EmberPower;
import code.powers.LoseBurningScalesPower;
import code.powers.NoEnergyPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;

public class ShedAction extends AbstractGameAction {
    private AbstractCreature c;

    public ShedAction(AbstractCreature c, int amount) {
        this.c = c;
        this.amount = amount;
    }

    @Override
    public void update() {
        for (AbstractPower p : c.powers){
            if (p.type == PowerType.DEBUFF) {
                if ((p instanceof EmberPower) || (p instanceof StrengthPower) || (p instanceof DexterityPower) || (p instanceof FocusPower)) {
                    if (p.amount < 0 && Math.abs(p.amount) <= this.amount) {
                        this.addToTop(new RemoveSpecificPowerAction(this.c, this.c, p));
                    } else {
                        this.addToTop(new AbstractGameAction() {
                            @Override
                            public void update() {
                                p.stackPower(ShedAction.this.amount);
                                p.updateDescription();
                                AbstractDungeon.onModifyPower();
                                this.isDone = true;
                            }
                        });
                    }
                } else if ((p instanceof LoseStrengthPower) || (p instanceof LoseDexterityPower) || (p instanceof LoseBurningScalesPower) ||
                        (p instanceof WeakPower) || (p instanceof VulnerablePower) || (p instanceof FrailPower) ||
                        (p instanceof DrawReductionPower) || (p instanceof DrawLessNextTurnPower) || (p instanceof NoBlockPower) || (p instanceof NoEnergyPower)){
                    this.addToTop(new ReducePowerAction(this.c, this.c, p.ID, amount));
                }
            }
        }
        this.isDone = true;
    }
}
