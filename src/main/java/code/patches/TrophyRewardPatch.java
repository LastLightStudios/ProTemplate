package code.patches;

import code.powers.TrophyRewardPower;
import code.rewards.TrophyReward;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.screens.CombatRewardScreen;
import com.megacrit.cardcrawl.ui.buttons.ProceedButton;
import javassist.CtBehavior;

public class TrophyRewardPatch {
    @SpireEnum
    public static RewardItem.RewardType DRAGONMOD_TROPHYREWARD;

    @SpirePatch(clz = AbstractRoom.class, method = "update")
    public static class AddRewardPatch {
        @SpireInsertPatch(locator = Locator.class)
        public static void addRewards(AbstractRoom __instance) {
            if (AbstractDungeon.player.hasPower(TrophyRewardPower.POWER_ID)) {
                for (int i = 0; i < AbstractDungeon.player.getPower(TrophyRewardPower.POWER_ID).amount; i++)
                AbstractDungeon.getCurrRoom().rewards.add(new TrophyReward());
            }
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractRoom.class, "addPotionToRewards");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }

    @SpirePatch(clz = CombatRewardScreen.class, method = "setupItemReward")
    public static class PopulateCardsPatch {
        @SpireInsertPatch(locator = Locator.class)
        public static void populateCards(CombatRewardScreen __instance) {
            for (int i = 0; i < __instance.rewards.size(); i++) {
                RewardItem rewardItem = __instance.rewards.get(i);
                if (rewardItem instanceof TrophyReward) {
                    ((TrophyReward)rewardItem).populateCards();
                }
            }
            AbstractDungeon.combatRewardScreen.positionRewards();
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(ProceedButton.class, "show");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }
}