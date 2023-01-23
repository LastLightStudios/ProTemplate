package code.actions;


import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;

import code.cards.AbstractTwoSidedCard;

public class TransformTwoSidedCardAction extends AbstractGameAction {
    private AbstractTwoSidedCard changingCard;
    private boolean changeToBack;
    private Color color;
    public TransformTwoSidedCardAction(AbstractTwoSidedCard originalCard, boolean _changeToBack, Color _color){
        changingCard = originalCard;
        changeToBack = _changeToBack;
        color = _color;
    }

    @Override
    public void update() {
        changingCard.changeSide(changeToBack);
        changingCard.superFlash(color);
        this.isDone = true;
    }
}
