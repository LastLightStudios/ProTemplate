package code.actions;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import static code.util.Wiz.adp;

public class TransformCardInExhaustPileAction extends AbstractGameAction {
    private AbstractCard replacement;
    private int handIndex;

    public TransformCardInExhaustPileAction(int index, AbstractCard replacement) {
        this.handIndex = index;
        this.replacement = replacement;
    }

    @Override
    public void update() {
        adp().exhaustPile.group.set(this.handIndex, this.replacement.makeSameInstanceOf());
        this.isDone = true;
    }
}
