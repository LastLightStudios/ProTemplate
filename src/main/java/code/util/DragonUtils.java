package code.util;

import code.cards.NestingForm;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static code.util.Wiz.*;

public class DragonUtils {
    private static HashMap<AbstractCard.CardTags, ArrayList<AbstractCard>> tagsWithLists = new HashMap<>();

    public static int countRaresInDeck(){
        int count = 0;
        for (AbstractCard c : getAllCardsInCardGroups(true, true)){
            if (c.rarity == AbstractCard.CardRarity.RARE){
                count++;
            }
        }
        return count;
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
        public static final int NESTING_FORM_PRIORITY = 2;
        public static final int EARLY_NEST_PRIORITY = 3;
        public static final int CAUTERIZE_NEST_PRIORITY = 4;
        public static final int DEFAULT = 5;
    }
}
