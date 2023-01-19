package code.cards;

import code.DragonCharacterFile;

public abstract class AbstractSparkCard extends AbstractEasyCard{

    public static String otherCardID;

    public AbstractSparkCard(final String cardID, final String otherCardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target){
        super(cardID, cost, type, rarity, target, DragonCharacterFile.Enums.DRAGON_COLOR);
        this.otherCardID = otherCardID;
    }

    @Override
    public void applyPowers(){

    }
}
