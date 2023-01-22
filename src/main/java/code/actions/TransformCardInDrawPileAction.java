package code.actions;


import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;

import static code.util.Wiz.adp;

public class TransformCardInDrawPileAction extends AbstractGameAction {
    private AbstractCard replacement;
    private int handIndex;

    public TransformCardInDrawPileAction(int index, AbstractCard replacement) {
        this.handIndex = index;
        this.replacement = replacement;
    }

    @Override
    public void update() {
        adp().drawPile.group.set(this.handIndex, this.replacement.makeSameInstanceOf());
        this.isDone = true;
    }
}
