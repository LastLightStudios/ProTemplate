package code.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;

import static code.ModFile.makeID;
import static code.util.Wiz.applyToSelf;
import static code.util.Wiz.atb;

public class StretchYourTail extends AbstractEasyCard {
    public final static String ID = makeID("StretchYourTail");

    private final static int ENERGY_GAIN = 2;
    private final static int FRAIL_GAIN = 2;
    private final static int UPGRADE_FRAIL_GAIN = -1;

    public StretchYourTail() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        magicNumber = baseMagicNumber = ENERGY_GAIN;
        secondMagic = baseSecondMagic = FRAIL_GAIN;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new GainEnergyAction(magicNumber));
        applyToSelf(new FrailPower(p, secondMagic, false));
    }

    public void upp() {
        //upgradeMagicNumber();
        upgradeSecondMagic(UPGRADE_FRAIL_GAIN);
    }
}