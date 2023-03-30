package code.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;

import static code.util.Wiz.*;

public class HoardThisCardAction extends AbstractGameAction {
    private AbstractPlayer p;
    private AbstractCard c;

    public HoardThisCardAction(AbstractPlayer p, AbstractCard c) {
        this.p = p;
        this.c = c;
    }

    @Override
    public void update() {
        applyToSelfTop(new PlatedArmorPower(adp(), 1));
        att(new ExhaustSpecificCardAction(c, p.hand));
        this.isDone = true;
    }
}
