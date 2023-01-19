package code.cards;


import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;

import code.DragonCharacterFile;

public abstract class AbstractSparkTestCard extends AbstractEasyCard{

    protected final CardStrings altCardStrings;

    public AbstractSparkTestCard(final String cardID, final String otherCardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target){
        super(cardID, cost, type, rarity, target, DragonCharacterFile.Enums.DRAGON_COLOR);
        altCardStrings = CardCrawlGame.languagePack.getCardStrings(otherCardID);
    }

    @Override
    public void applyPowers(){

    }
}
