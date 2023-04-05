package code.powers.nestpowers;

import code.powers.AbstractEasyPower;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class DelayedGainMiraclePower extends AbstractEasyPower {

    public static final String POWER_ID = makeID("DelayedGainMiraclePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public DelayedGainMiraclePower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        loadRegion("backAttack");
    }

    @Override
    public void atStartOfTurn(){
        flash();
        makeInHand(new Miracle(), amount);
        atb(new RemoveSpecificPowerAction(adp(), adp(),POWER_ID));
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    };
}
