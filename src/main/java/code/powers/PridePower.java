package code.powers;

import code.actions.PridePowerAction;
import code.powers.nestpowers.ExtravagantNestPower;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static code.ModFile.makeID;
import static code.util.Wiz.atb;

public class PridePower extends AbstractEasyPower {

    public static final String POWER_ID = makeID("PridePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public PridePower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void stackPower(int stackAmount){
        amount += stackAmount;
        updateDescription();
    }

    @Override
    public void updateDescription() {
        if (amount == 1){
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1] + "1" + DESCRIPTIONS[2];
        } else {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1] + (amount / 2) + DESCRIPTIONS[2];
        }
    };

    public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
        // only trigger if the player does not have the Nest power b/c the Nest power will trigger the PridePowerAction
        if (!owner.hasPower(ExtravagantNestPower.POWER_ID)){
            atb(new PridePowerAction(this));
        }
    }
}
