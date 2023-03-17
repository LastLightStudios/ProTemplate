package code.actions;

import code.util.DragonUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;

import java.util.ArrayList;

import static code.util.Wiz.adp;
import static code.util.Wiz.att;

public class IncreaseCardCostAction extends AbstractGameAction {
    private AbstractCard card;

    public IncreaseCardCostAction(AbstractCard card, int amount) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.WAIT;
        this.source = adp();
        this.card = card;
        this.amount = amount;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST){
            this.card.updateCost(amount);
        }
        this.isDone = true;
    }
}
