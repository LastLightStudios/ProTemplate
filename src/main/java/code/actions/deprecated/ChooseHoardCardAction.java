package code.actions.deprecated;

import code.powers.PridePower;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class ChooseHoardCardAction extends AbstractGameAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("Hoard"));
    private static final AbstractPlayer p = adp();

    public ChooseHoardCardAction(int amount) {
        this.amount = amount;
    }

    @Override
    public void update() {

        if (amount == 1){
            att(new SelectCardsInHandAction(uiStrings.TEXT[1], selectedCard -> selectedCard.forEach(card -> {
                applyToSelfTop(new PridePower(p, 1));
                att(new ExhaustSpecificCardAction(card, p.hand));
            })));
        } else if (amount > 1) {
            att(new SelectCardsInHandAction(amount, uiStrings.TEXT[2], selectedCards -> {
                for (int i = 0; i < selectedCards.size(); ++i) {
                    applyToSelfTop(new PridePower(p, 1));
                }
                selectedCards.forEach(card -> {
                    att(new ExhaustSpecificCardAction(card, p.hand));
                });
            }));
        } else if (amount == -1) { // this lets you choose any number
            att(new SelectCardsInHandAction(amount, uiStrings.TEXT[2], true, true, c -> true, selectedCards -> {
                for (int i = 0; i < selectedCards.size(); ++i) {
                    applyToSelfTop(new PridePower(p, 1));
                }
                selectedCards.forEach(card -> {
                    att(new ExhaustSpecificCardAction(card, p.hand));
                });
            }));
        } else {
            //this should not trigger
        }
        this.isDone = true;
    }
}
