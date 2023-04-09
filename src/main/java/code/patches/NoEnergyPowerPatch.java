package code.patches;

import code.powers.NoEnergyPower;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import javassist.CtBehavior;

public class NoEnergyPowerPatch {
    @SpirePatch(clz = GainEnergyAction.class, method = "update")
    public static class NoEnergyPowerHook {
        @SpireInsertPatch(locator = GainEnergyLocator.class)
        public static SpireReturn<Void> NoEnergyCheckMethod(GainEnergyAction __instance) {
            AbstractPower noEnergy = AbstractDungeon.player.getPower(NoEnergyPower.POWER_ID);
            if (noEnergy != null){
                if (!((NoEnergyPower) noEnergy).isAllowEnergyGain()){
                    __instance.isDone = true;
                    return SpireReturn.Return();
                }
            }
            return SpireReturn.Continue();
        }
        private static class GainEnergyLocator extends SpireInsertLocator{
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractPlayer.class, "gainEnergy");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }
}