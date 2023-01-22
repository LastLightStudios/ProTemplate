package code.actions.deprecated;


import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;

import static code.util.Wiz.adp;

public class TransformCardInHandAction extends AbstractGameAction {
    private AbstractCard replacement;
    private int handIndex;

    public TransformCardInHandAction(int index, AbstractCard replacement) {
        this.handIndex = index;
        this.replacement = replacement;
        if (Settings.FAST_MODE) {
            this.startDuration = 0.05F;
        } else {
            this.startDuration = 0.15F;
        }
        this.duration = this.startDuration;
    }

    @Override
    public void update() {
        if (this.duration == this.startDuration) {
            AbstractCard target = (AbstractCard)adp().hand.group.get(this.handIndex);
            this.replacement.current_x = target.current_x;
            this.replacement.current_y = target.current_y;
            this.replacement.target_x = target.target_x;
            this.replacement.target_y = target.target_y;
            this.replacement.drawScale = 1.0F;
            this.replacement.targetDrawScale = target.targetDrawScale;
            this.replacement.angle = target.angle;
            this.replacement.targetAngle = target.targetAngle;
            this.replacement.superFlash(Color.WHITE.cpy());
            adp().hand.group.set(this.handIndex, this.replacement.makeSameInstanceOf());
            adp().hand.glowCheck();
        }
        this.tickDuration();
    }
}
