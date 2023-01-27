package code.util;

import code.cards.gems.*;
import code.cards.nests.*;
import code.cards.sparkbreaths.*;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.ArrayList;
import java.util.Arrays;

import static code.util.Wiz.adp;

public class DragonUtils {

    public static AbstractCard getRandomGem(){
        return Wiz.getRandomItem(gemList);
    }

    public static AbstractCard getRandomSpark(){
        return Wiz.getRandomItem(sparkList);
    }

    public static AbstractCard getRandomNest(){
        return Wiz.getRandomItem(nestList);
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

    static ArrayList<AbstractCard> sparkList = new ArrayList<AbstractCard>(Arrays.asList(
            new BarrageSpark(),
            new CrystallineSpark(),
            new FocusedSpark(),
            new ProfaneSpark(),
            new PureSpark(),
            new RadiantSpark(),
            new SwiftSpark(),
            new ToxicSpark()
    ));

    static ArrayList<AbstractCard> nestList = new ArrayList<AbstractCard>(Arrays.asList(
            new AbyssNest(),
            new AncientNest(),
            new DesertNest(),
            new ForestNest(),
            new HeavenlyNest(),
            new OceanNest(),
            new TundraNest(),
            new VolcanicNest(),
            new NestingForm()
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
        @SpireEnum
        public static AbstractCard.CardTags NEST;
    }
}
