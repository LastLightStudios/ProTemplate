package code.cards;

import code.powers.HoardingInstinctPower;
import code.powers.KindlePower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;
import static code.util.Wiz.applyToSelf;

public class HoardingInstinct extends AbstractEasyCard {
    public final static String ID = makeID("HoardingInstinct");
    private final static int UPGRADED_COST = 0;

    public HoardingInstinct() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new HoardingInstinctPower(p, 1));
    }

    public void upp() {
        upgradeBaseCost(UPGRADED_COST);
    }
}