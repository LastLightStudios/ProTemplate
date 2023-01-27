package code.cards;

import code.powers.EmberPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.DragonCharacterFile.Enums.DRAGON_COLOR;
import static code.ModFile.makeID;
import static code.util.Wiz.applyToSelf;

public class BurningScales extends AbstractEasyCard {
    public final static String ID = makeID("BurningScales");

    private final static int BLOCK = 7;
    private final static int UPGRADE_BLOCK = 3;
    private final static int EMBER_GAIN = 2;
    private final static int UPGRADE_EMBER_GAIN = 1;


    public BurningScales() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, DRAGON_COLOR);
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = EMBER_GAIN;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToSelf(new EmberPower(p, magicNumber));
    }

    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
        upgradeMagicNumber(UPGRADE_EMBER_GAIN);
    }
}