package code.actions;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import static code.util.Wiz.adp;

public class TransformCardInLimboAction extends AbstractGameAction {
    private AbstractCard replacement;
    private int handIndex;

    public TransformCardInLimboAction(int index, AbstractCard replacement) {
        this.handIndex = index;
        this.replacement = replacement;
    }

    @Override
    public void update() {
        adp().limbo.group.set(this.handIndex, this.replacement);
        this.isDone = true;
    }
}
