package code.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import static code.ModFile.makeID;
import static code.util.Wiz.applyToSelf;
import static code.util.Wiz.atb;

public class StretchYourBelly extends AbstractEasyCard {
    public final static String ID = makeID("StretchYourBelly");

    private final static int ENERGY_GAIN = 2;
    private final static int VULN_GAIN = 2;
    private final static int UPGRADE_VULN_GAIN = -1;

    public StretchYourBelly() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        magicNumber = baseMagicNumber = ENERGY_GAIN;
        secondMagic = baseSecondMagic = VULN_GAIN;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new GainEnergyAction(magicNumber));
        applyToSelf(new VulnerablePower(p, secondMagic, false));
    }

    public void upp() {
        //upgradeMagicNumber();
        upgradeSecondMagic(UPGRADE_VULN_GAIN);
    }
}