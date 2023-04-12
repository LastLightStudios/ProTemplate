package code.powers;

import code.cards.sparkbreaths.AbstractSparkBreathCard;
import code.util.DragonUtils;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class FavoriteToyPower extends AbstractEasyPower {

    public static final String POWER_ID = makeID("FavoriteToyPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private AbstractCard card;

    public FavoriteToyPower(AbstractCreature owner, int amount, AbstractCard copyMe) {
        super(POWER_ID, NAME, PowerType.BUFF, true, owner, amount);
        loadRegion("nightmare");
        priority = DragonUtils.PowerPriorities.NO_ENERGY_PRIORITY;
        this.card = copyMe.makeStatEquivalentCopy();
        this.card.resetAttributes();
        updateDescription();
    }

    @Override
    public void atStartOfTurn(){
        if (card instanceof AbstractSparkBreathCard){
            ((AbstractSparkBreathCard) card).checkEmberTriggerTop();
        }
        makeInHand(card);
        atb(new ReducePowerAction(adp(), adp(), POWER_ID, 1));
    }

    @Override
    public void updateDescription() {
        if (card != null){ //this is a fix for when the super calls updateDescription() before card has been assigned
            if (amount == 1){
                description = DESCRIPTIONS[0] + FontHelper.colorString(card.name, "y") + DESCRIPTIONS[1];
            } else {
                description = DESCRIPTIONS[0] + FontHelper.colorString(card.name, "y") + DESCRIPTIONS[2] + amount + DESCRIPTIONS[3];
            }

        }
    }
}
