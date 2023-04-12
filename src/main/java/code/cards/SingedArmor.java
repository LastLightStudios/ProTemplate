package code.cards;

import code.interfaces.HoardingCardInterface;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.DragonCharacterFile.Enums.DRAGON_COLOR;
import static code.ModFile.makeID;
import static code.util.Wiz.att;

public class SingedArmor extends AbstractEasyCard implements HoardingCardInterface {
    public final static String ID = makeID("SingedArmor");

    private final static int BLOCK = 5;
    private final static int UPGRADE_BLOCK = 3;
    private final static int ENERGY_GAIN = 2;
    private final static int UPGRADE_ENERGY_GAIN = 1;


    public SingedArmor() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, DRAGON_COLOR);
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = ENERGY_GAIN;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    @Override
    public void triggerOnHoard(){
        att(new GainEnergyAction(magicNumber));
    }

    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
        upgradeMagicNumber(UPGRADE_ENERGY_GAIN);
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}