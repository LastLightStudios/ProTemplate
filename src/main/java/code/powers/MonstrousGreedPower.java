package code.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import java.util.List;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class MonstrousGreedPower extends AbstractEasyPower implements HoardingPowerInterface {

    public static final String POWER_ID = makeID("MonstrousGreedPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public MonstrousGreedPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        loadRegion("darkembrace");
        updateDescription();
    }

    @Override
    public void onHoard(List<AbstractCard> hoardedCardsList){
        for (AbstractCard card : hoardedCardsList){
            atb(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(amount, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE, true));
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    };
}
