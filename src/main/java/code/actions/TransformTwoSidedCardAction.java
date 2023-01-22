package code.actions;


import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;

import code.cards.AbstractTwoSidedCard;

public class TransformTwoSidedCardAction extends AbstractGameAction {
    private AbstractTwoSidedCard changingCard;
    private boolean changeToBack;
    public TransformTwoSidedCardAction(AbstractTwoSidedCard originalCard, boolean _changeToBack){
        changingCard = originalCard;
        changeToBack = _changeToBack;
    }

    @Override
    public void update() {
        changingCard.changeSide(changeToBack);
        changingCard.superFlash(Color.valueOf("cc5500"));
        this.isDone = true;
    }
}
