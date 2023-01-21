package code.cards;


import basemod.AutoAdd;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;

import code.DragonCharacterFile;

public abstract class AbstractTwoSidedCard extends AbstractEasyCard{

    //protected final CardStrings altCardStrings;

    public AbstractTwoSidedCard(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target){
        super(cardID, cost, type, rarity, target, DragonCharacterFile.Enums.DRAGON_COLOR);
        // altCardStrings = CardCrawlGame.languagePack.getCardStrings(otherCardID);
    }

    @Override
    public void applyPowers(){

    }

    public abstract void changeToFront();

    public abstract void changeToBack();
}
