package code.actions;

import code.cards.sparkbreaths.AbstractSparkBreathCard;
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

public class PlayAllSparksAction extends AbstractGameAction {

    public PlayAllSparksAction(AbstractCreature target) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.WAIT;
        this.source = adp();
        this.target = target;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST){
            ArrayList<AbstractCard> tempList = new ArrayList<>();
            for (AbstractCard c : adp().hand.group) {
                if (DragonUtils.isSpark(c)){
                    tempList.add(c);
                    c.exhaustOnUseOnce = true;
                    adp().limbo.group.add(c);
                    att(new NewQueueCardAction(c, this.target, false, true));
                    att(new UnlimboAction(c));
                } else if (DragonUtils.isBreath(c)){
                    tempList.add(c);
                    ((AbstractSparkBreathCard) c).changeToBack(false);
                    c.exhaustOnUseOnce = true;
                    adp().limbo.group.add(c);
                    att(new NewQueueCardAction(c, this.target, false, true));
                    att(new UnlimboAction(c));
                }
            }
            adp().hand.group.removeAll(tempList);
            tempList.clear();
            for (AbstractCard c : adp().drawPile.group) {
                if (DragonUtils.isSpark(c)){
                    tempList.add(c);
                    c.exhaustOnUseOnce = true;
                    adp().limbo.group.add(c);
                    att(new NewQueueCardAction(c, this.target, false, true));
                    att(new UnlimboAction(c));
                }
            }
            adp().drawPile.group.removeAll(tempList);
            tempList.clear();
            for (AbstractCard c : adp().discardPile.group) {
                if (DragonUtils.isSpark(c)){
                    tempList.add(c);
                    c.exhaustOnUseOnce = true;
                    adp().limbo.group.add(c);
                    att(new NewQueueCardAction(c, this.target, false, true));
                    att(new UnlimboAction(c));
                }
            }
            adp().discardPile.group.removeAll(tempList);
            tempList.clear();
            this.isDone = true;
        }
    }
}
