package code.powers;

import code.actions.HoardCardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class SmolderingFurriesPower extends AbstractEasyPower {

    public static final String POWER_ID = makeID("SmolderingFurries");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public SmolderingFurriesPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        updateDescription();
        loadRegion("evolve");
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        if (card instanceof Burn && !this.owner.hasPower("No Draw")) {
            flash();
            atb(new DrawCardAction(this.owner, this.amount));
            atb(new HoardCardAction(card));
            applyToSelf(new EmberPower(owner, amount));
        }
    }

    @Override
    public void updateDescription() {
        if (this.amount == 1) {
            this.description = powerStrings.DESCRIPTIONS[0];
        } else {
            this.description = powerStrings.DESCRIPTIONS[1] + this.amount + powerStrings.DESCRIPTIONS[2] + this.amount + powerStrings.DESCRIPTIONS[3];
        }
    }
}
