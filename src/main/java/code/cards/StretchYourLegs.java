package code.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import static code.ModFile.makeID;
import static code.util.Wiz.applyToSelf;
import static code.util.Wiz.atb;

public class StretchYourLegs extends AbstractEasyCard {
    public final static String ID = makeID("StretchYourLegs");

    private final static int ENERGY_GAIN = 2;
    private final static int WEAK_GAIN = 2;
    private final static int UPGRADE_WEAK_GAIN = -1;

    public StretchYourLegs() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        magicNumber = baseMagicNumber = ENERGY_GAIN;
        secondMagic = baseSecondMagic = WEAK_GAIN;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new GainEnergyAction(magicNumber));
        applyToSelf(new WeakPower(p, secondMagic, false));
    }

    public void upp() {
        //upgradeMagicNumber();
        upgradeSecondMagic(UPGRADE_WEAK_GAIN);
    }
}