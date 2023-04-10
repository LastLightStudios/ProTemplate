package code.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class FavoriteToyAction extends AbstractGameAction {
    private DamageInfo info;
    private int cardPlayCount = 0;
    private int energyGain = 1;

    public FavoriteToyAction(AbstractCreature target, DamageInfo info, int cardPlayCount) {
        this.info = info;
        this.target = target;
        this.cardPlayCount = cardPlayCount;
    }

    @Override
    public void update() {
        addToBot(new DamageAction(target, info, AttackEffect.SLASH_HORIZONTAL));
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() - 1 < cardPlayCount)
            addToTop(new GainEnergyAction( energyGain));
        isDone = true;
    }
}
