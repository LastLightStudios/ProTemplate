package code.util;

import code.cards.gems.*;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.ArrayList;
import java.util.Arrays;

public class DragonUtils {

    public static AbstractCard getRandomGem(){
        return Wiz.getRandomItem(gemList);
    }

    static ArrayList<AbstractCard> gemList = new ArrayList<AbstractCard>(Arrays.asList(
            new Aquamarine(),
            new Emerald(),
            new Rhodonite(),
            new Ruby(),
            new Amethyst(),
            new Garnet(),
            new Quartz(),
            new Citrine()
    ));

    public static class CustomTags {
        @SpireEnum
        public static AbstractCard.CardTags SPARK;
        @SpireEnum
        public static AbstractCard.CardTags BREATH;
        @SpireEnum
        public static AbstractCard.CardTags DOUBLE_SIDED;
        @SpireEnum
        public static AbstractCard.CardTags GEM;
    }
}
