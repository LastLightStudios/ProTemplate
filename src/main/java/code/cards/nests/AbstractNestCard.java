package code.cards.nests;

import basemod.helpers.TooltipInfo;
import code.DragonCharacterFile;
import code.cards.AbstractEasyCard;
import code.util.DragonUtils.CustomTags;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.ArrayList;
import java.util.List;

import static code.ModFile.makeID;

public abstract class AbstractNestCard extends AbstractEasyCard {
    protected static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("Nest"));

    public AbstractNestCard(final String cardID, final int cost, final CardRarity rarity) {
        super(cardID, cost, CardType.POWER, rarity, CardTarget.SELF, DragonCharacterFile.Enums.DRAGON_COLOR);
        tags.add(CustomTags.NEST);
    }

    @Override
    public List<String> getCardDescriptors(){
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(uiStrings.TEXT[0]);
        return retVal;
    }
}