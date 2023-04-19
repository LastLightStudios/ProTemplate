package code.util;

import basemod.helpers.TooltipInfo;
import code.cards.sparkbreaths.AbstractSparkBreathCard;
import code.powers.FirepowerPower;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.relics.PrismaticShard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class DragonUtils {
    protected static final UIStrings shedUIStrings = CardCrawlGame.languagePack.getUIString(makeID("Shed"));
    private static HashMap<AbstractCard.CardTags, ArrayList<AbstractCard>> tagsWithLists = new HashMap<>();

    public static final int DEFAULT_EMBER_BREAKPOINT = 5;

    public static int getEmberBreakpoint(){
        if (adp() != null){
            if (adp().hasPower(FirepowerPower.POWER_ID)){
                return adp().getPower(FirepowerPower.POWER_ID).amount;
            }
        }
        return DEFAULT_EMBER_BREAKPOINT;
    }

    public static boolean isSpark(AbstractCard c){
        return (c instanceof AbstractSparkBreathCard && ((AbstractSparkBreathCard) c).isFront());
    }

    public static boolean isBreath(AbstractCard c){
        return (c instanceof AbstractSparkBreathCard && !((AbstractSparkBreathCard) c).isFront());
    }

    public static int countRaresInDeck(){
        int count = 0;
        for (AbstractCard c : adp().masterDeck.group){
            if (c.rarity == AbstractCard.CardRarity.RARE){
                count++;
            }
        }
        return count;
    }

    public static List<TooltipInfo> getSheddableDebuffTooltips(){
        ArrayList<TooltipInfo> retVal = new ArrayList<>();
        if (adp() != null && adp().hasRelic(PrismaticShard.ID)){
            retVal.add(new TooltipInfo(shedUIStrings.TEXT[2], shedUIStrings.TEXT[4]));
        } else {
            retVal.add(new TooltipInfo(shedUIStrings.TEXT[2], shedUIStrings.TEXT[3]));
        }
        return retVal;
    }

    public static AbstractCard returnTrulyRandomCardWithTagInCombat(AbstractCard.CardTags tag){
        return returnTrulyRandomCardWithTagInCombat(tag, false);
    }

    public static AbstractCard returnTrulyRandomCardWithTagInCombat(AbstractCard.CardTags tag, boolean includeHealing) {
        if (tagsWithLists.get(tag) == null) {
            ArrayList<AbstractCard> list = new ArrayList<>();
            for (Map.Entry<String, AbstractCard> potentialCard : CardLibrary.cards.entrySet()) {
                AbstractCard card = potentialCard.getValue();
                if (includeHealing){
                    if (card.hasTag(tag)) {
                        list.add(card.makeCopy());
                    }
                } else {
                    if (card.hasTag(tag) && !card.hasTag(AbstractCard.CardTags.HEALING)) {
                        list.add(card.makeCopy());
                    }
                }
            }
            tagsWithLists.put(tag, list);
        }
        ArrayList<AbstractCard> list = tagsWithLists.get(tag);
        return list.get(AbstractDungeon.cardRandomRng.random(list.size() - 1));
    }

    public static class CustomTags {
        @SpireEnum
        public static AbstractCard.CardTags SPARK;
        @SpireEnum
        public static AbstractCard.CardTags BREATH;
        @SpireEnum
        public static AbstractCard.CardTags DOUBLE_SIDED;
        @SpireEnum
        public static AbstractCard.CardTags GEM;
        @SpireEnum
        public static AbstractCard.CardTags NEST;
    }

    public static class PowerPriorities {
        public static final int FIREPOWER_PRIORITY = 0; // I want this in front of Ember
        public static final int EMBER_PRIORITY = 1; // I want Ember to be on the far left for visibility
        public static final int NESTING_FORM_PRIORITY = 2;
        public static final int EARLY_NEST_PRIORITY = 3; // Imposing Nest wants to debuff enemies before Cauterize
        public static final int CAUTERIZE_NEST_PRIORITY = 4;
        public static final int DEFAULT = 5;
        public static final int NO_ENERGY_PRIORITY = 20;
    }
}
