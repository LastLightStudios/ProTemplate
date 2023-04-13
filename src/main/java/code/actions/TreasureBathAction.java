package code.actions;

import code.util.DragonUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static code.util.Wiz.*;

public class TreasureBathAction extends AbstractGameAction {

    public TreasureBathAction(AbstractCreature source, int amount) {
        this.source = source;
        this.amount = amount;
    }

    @Override
    public void update() {
        if (amount > adp().hand.group.size()){
            for (int i = 0; i < (amount - adp().hand.group.size()); i++){
                att(new MakeTempCardInHandAction(DragonUtils.returnTrulyRandomCardWithTagInCombat(DragonUtils.CustomTags.GEM).makeCopy()));
            }
        }
        isDone = true;
    }
}
