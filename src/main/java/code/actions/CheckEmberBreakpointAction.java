package code.actions;


import code.cards.AbstractSparkBreathCard;
import code.powers.EmberPower;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static code.powers.EmberPower.getEmberBreakpoint;
import static code.util.Wiz.adp;
import static code.util.Wiz.att;

public class CheckEmberBreakpointAction extends AbstractGameAction {
    private AbstractSparkBreathCard changingCard;
    public CheckEmberBreakpointAction(AbstractSparkBreathCard originalCard){
        changingCard = originalCard;
    }

    @Override
    public void update() {
        AbstractPower ember = adp().getPower(EmberPower.POWER_ID);
        if (ember != null) {
            if (ember.amount >= getEmberBreakpoint() && changingCard.isFront()){
                // if it is a Spark and ember is at or above the breakpoint, transform to Breath
                att(new TransformTwoSidedCardAction(changingCard, true, Color.valueOf("cc5500")));
            } else if (ember.amount < getEmberBreakpoint() && changingCard.isFront()) {
                // if it is a Breath and below the breakpoint, transform to a Spark
                att(new TransformTwoSidedCardAction(changingCard, false, Color.valueOf("cc5500")));
            }
        } else if (ember == null && !changingCard.isFront()){
            // if it is a Breath and there is no ember, transform to a Spark
            att(new TransformTwoSidedCardAction(changingCard, false, Color.valueOf("cc5500")));
        }
        this.isDone = true;
    }
}
