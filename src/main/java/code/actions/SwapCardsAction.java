package code.actions;

import code.cards.AbstractSwappableCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.CardGroup;

import static code.util.Wiz.adp;
import static code.util.Wiz.att;

public class SwapCardsAction extends AbstractGameAction {
    private AbstractSwappableCard card1;
    private AbstractSwappableCard card2;
    private CardGroup currentLocation;

    public SwapCardsAction(AbstractSwappableCard originalCard, AbstractSwappableCard swapCard, CardGroup location){
        card1 = originalCard;
        card2 = swapCard;
        currentLocation = location;
    }

    @Override
    public void update() {
        if (currentLocation == adp().hand){
            int index = -1;
            for (int i = 0; i < adp().hand.group.size(); i++) {
                if (card1 == adp().hand.group.get(i)) {
                    index = i;
                }
            }
            if (index != -1) {
                updateNewSwappedCard(card2);
                att(new TransformCardInHandAction(index, card2));
            } else {
                System.out.println("Somehow the card in hand is not in hand");
            }
        }
        if (currentLocation == adp().drawPile){
            int index = -1;
            for (int i = 0; i < adp().drawPile.group.size(); i++) {
                if (card1 == adp().drawPile.group.get(i)) {
                    index = i;
                }
            }
            if (index != -1) {
                updateNewSwappedCard(card2);
                att(new TransformCardInDrawPileAction(index, card2));
            } else {
                System.out.println("Somehow the card in drawPile is not in drawPile");
            }
        }
        if (currentLocation == adp().discardPile){
            int index = -1;
            for (int i = 0; i < adp().discardPile.group.size(); i++) {
                if (card1 == adp().discardPile.group.get(i)) {
                    index = i;
                }
            }
            if (index != -1) {
                updateNewSwappedCard(card2);
                att(new TransformCardInDiscardPileAction(index, card2));
            } else {
                System.out.println("Somehow the card in discardPile is not in discardPile");
            }
        }
        if (currentLocation == adp().exhaustPile){
            int index = -1;
            for (int i = 0; i < adp().exhaustPile.group.size(); i++) {
                if (card1 == adp().exhaustPile.group.get(i)) {
                    index = i;
                }
            }
            if (index != -1) {
                updateNewSwappedCard(card2);
                att(new TransformCardInExhaustPileAction(index, card2));
            } else {
                System.out.println("Somehow the card in exhaustPile is not in exhaustPile");
            }
        }
        if (currentLocation == adp().limbo){
            int index = -1;
            for (int i = 0; i < adp().limbo.group.size(); i++) {
                if (card1 == adp().limbo.group.get(i)) {
                    index = i;
                }
            }
            if (index != -1) {
                updateNewSwappedCard(card2);
                att(new TransformCardInLimboAction(index, card2));
            } else {
                System.out.println("Somehow the card in Limbo is not in Limbo");
            }
        }
        this.isDone = true;
    }

    private void updateNewSwappedCard(AbstractSwappableCard card){
        card.applyPowers();
        card.cardsToPreview.applyPowers();
        this.addToTop(new UpdateAfterTransformAction(card));
    }
}
