package code.actions;

import code.cards.AbstractSparkCard;
import com.megacrit.cardcrawl.cards.CardGroup;

import static code.util.Wiz.adp;

public class TransformSparkCardAction {
    public TransformSparkCardAction(AbstractSparkCard originalCard, AbstractSparkCard swapCard, CardGroup.CardGroupType cardGroup){
        switch(cardGroup){
            case HAND:
                int index = -1;
                for (int i = 0; i < adp().hand.group.size(); i++) {
                    if (originalCard == adp().hand.group.get(i)) {
                        index = i;
                    }
                }
                if (index != -1) {
                    adp().hand.group.remove(index);
                    adp().hand.group.add(index, swapCard);
                } else {
                    System.out.println("Somehow the card in hand is not in hand");
                }
                break;
            default:
                ;
        }
    }
}
