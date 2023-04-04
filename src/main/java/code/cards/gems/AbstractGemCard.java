package code.cards.gems;

import basemod.helpers.TooltipInfo;
import code.DragonCharacterFile;
import code.actions.HoardCardAction;
import code.cards.AbstractEasyCard;
import code.util.DragonUtils.CustomTags;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.ArrayList;
import java.util.List;

import static code.ModFile.makeID;
import static code.util.Wiz.atb;
import static code.util.Wiz.getEnemies;

public abstract class AbstractGemCard extends AbstractEasyCard {
    protected static final UIStrings gemUIStrings = CardCrawlGame.languagePack.getUIString(makeID("Gem"));
    protected static final UIStrings hoardUIStrings = CardCrawlGame.languagePack.getUIString(makeID("Hoard"));

    public AbstractGemCard(final String cardID, final CardType type, final CardRarity rarity, final CardTarget target) {
        super(cardID, 0, type, rarity, target, DragonCharacterFile.Enums.DRAGON_COLOR);
        selfRetain = true;
        tags.add(CustomTags.GEM);
    }

    public void hoardThisGem(){
        atb(new HoardCardAction(this));
    }

    @Override
    public List<String> getCardDescriptors(){
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(gemUIStrings.TEXT[0]);
        return retVal;
    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        ArrayList<TooltipInfo> retVal = new ArrayList<>();
        retVal.add(new TooltipInfo(gemUIStrings.TEXT[0], gemUIStrings.TEXT[1]));
        retVal.add(new TooltipInfo(hoardUIStrings.TEXT[3], hoardUIStrings.TEXT[4]));
        return retVal;
    }
}