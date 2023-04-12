package code.powers;

import code.interfaces.HoardingPowerInterface;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import java.util.List;

import static code.ModFile.makeID;

public class MoreMoreMorePower extends AbstractEasyPower implements HoardingPowerInterface {

    public static final String POWER_ID = makeID("MoreMoreMorePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public MoreMoreMorePower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        loadRegion("deva2");
        updateDescription();
    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            description = powerStrings.DESCRIPTIONS[0] + amount + powerStrings.DESCRIPTIONS[1];
        } else {
            description = powerStrings.DESCRIPTIONS[0] + amount + powerStrings.DESCRIPTIONS[2];
        }
    }

    @Override
    public void onHoard(List<AbstractCard> hoardedCardsList) {
        if (!owner.hasPower("No Draw")){
            for (AbstractCard c : hoardedCardsList){
                flash();
                addToBot(new DrawCardAction(owner, amount));
            }
        }
    }
}
