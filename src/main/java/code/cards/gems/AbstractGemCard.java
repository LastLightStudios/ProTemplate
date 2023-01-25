package code.cards.gems;

import basemod.helpers.TooltipInfo;
import code.DragonCharacterFile;
import code.cards.AbstractEasyCard;
import code.util.DragonUtils.CustomTags;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.ArrayList;
import java.util.List;

import static code.ModFile.makeID;

public abstract class AbstractGemCard extends AbstractEasyCard {
    protected static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("Gem"));

    public AbstractGemCard(final String cardID, final CardType type, final CardRarity rarity, final CardTarget target) {
        super(cardID, 0, type, rarity, target, DragonCharacterFile.Enums.DRAGON_COLOR);
        exhaust = true;
        tags.add(CustomTags.GEM);
    }

    @Override
    public List<String> getCardDescriptors(){
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(uiStrings.TEXT[0]);
        return retVal;
    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        ArrayList<TooltipInfo> retVal = new ArrayList<>();
        retVal.add(new TooltipInfo(uiStrings.TEXT[0], uiStrings.TEXT[1]));
        return retVal;
    }
}