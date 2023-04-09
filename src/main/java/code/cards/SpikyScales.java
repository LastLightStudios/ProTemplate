package code.cards;

import code.powers.BurningScalesPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;
import static code.util.Wiz.applyToSelf;

public class SpikyScales extends AbstractEasyCard {
    public final static String ID = makeID("SpikyScales");
    private final static int BURNING_SCALES = 3;
    private final static int UPGRADE_BURNING_SCALES = 2;

    public SpikyScales() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = BURNING_SCALES;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new BurningScalesPower(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_BURNING_SCALES);
    }
}