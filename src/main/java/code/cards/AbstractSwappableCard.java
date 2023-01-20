package code.cards;

public abstract class AbstractSwappableCard extends AbstractEasyCard{

    private static boolean looping;

    public AbstractSwappableCard(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target, CardColor color){
        super(cardID, cost, type, rarity, target, color);
    }

    protected void setLinkedCard(AbstractSwappableCard linkedCard) {
        if (linkedCard != null) {
            this.cardsToPreview = linkedCard;
            this.cardsToPreview.cardsToPreview = this;
        }
    }

    @Override
    public void upgrade() {
        if (cardsToPreview != null && !looping) {
            looping = true;
            cardsToPreview.upgrade();
            looping = false;
        }
    }
}
