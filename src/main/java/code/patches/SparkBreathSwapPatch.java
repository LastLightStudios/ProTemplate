package code.patches;

import basemod.ReflectionHacks;
import code.cards.AbstractSwappableCard;
import code.util.DragonUtils.CustomTags;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.powers.AbstractPower;
import javassist.CtBehavior;

import code.powers.EmberPower;
import static code.util.Wiz.adp;


/*
* HOW ReflectionHacks WORKS:
*
* All ReflectionHacks methods work essentially the same way. The parameters are:
* 1st, the actual object you want to pull a private field from
* 2nd, the class where the private thing is, with .class at the end
* 3rd, a string that's the name of the field you want to get
* And 4th, if you're setting something and not just reading it, the value you want to set it to [AND ALSO USE setPrivate() not getPrivate()]
*
* The methods in ReflectionHacks themselves have a pretty explicit name, you can probably figure out which one you need by looking at which exist by looking in the ReflectionHacks class file (ofc ask if you're not sure)
* Oh, and also methods that get a static field/method skip the first parameter
* For example, ReflectionHacks.getPrivate(screen, CardRewardScreen.class, "draft"); will get the field called "draft" from the object 'screen', which here is a CardRewardScreen
*
*
*/

public class SparkBreathSwapPatch {

    @SpirePatch(clz = UseCardAction.class, method = "update")
    public static class SwapSparkBreathCardHook {
        @SpireInsertPatch(locator = StrangeSpoonFinder.class)
        public static void SwapSparkBreathCardMethod(UseCardAction __instance) {
            AbstractCard tempTargetCard = ReflectionHacks.getPrivate(__instance, UseCardAction.class, "targetCard");
            if (tempTargetCard instanceof AbstractSwappableCard){
                AbstractPower ember = adp().getPower(EmberPower.POWER_ID);
                if (ember != null){
                    if (ember.amount >= EmberPower.getEmberBreakpoint()){
                        if (tempTargetCard.hasTag(CustomTags.SPARK)){
                            ReflectionHacks.setPrivate(__instance, UseCardAction.class, "targetCard", tempTargetCard.cardsToPreview.makeSameInstanceOf());
                        }
                    } else {
                        if (tempTargetCard.hasTag(CustomTags.BREATH)){
                            ReflectionHacks.setPrivate(__instance, UseCardAction.class, "targetCard", tempTargetCard.cardsToPreview.makeSameInstanceOf());
                        }
                    }
                } else {
                    if (tempTargetCard.hasTag(CustomTags.BREATH)){
                        ReflectionHacks.setPrivate(__instance, UseCardAction.class, "targetCard", tempTargetCard.cardsToPreview.makeSameInstanceOf());
                    }

                }
            }
        }

        private static class StrangeSpoonFinder extends SpireInsertLocator{
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractPlayer.class, "hasRelic");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }
}
