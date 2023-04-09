package code.cards;

import code.powers.NoEnergyPower;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.DragonCharacterFile.Enums.DRAGON_COLOR;
import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class INeedItNow extends AbstractEasyCard {
    public final static String ID = makeID("INeedItNow");

    private final static int ENERGY_GAIN = 2;
    private final static int TURN_PENALTY = 2;
    private final static int UPGRADE_TURN_PENALTY = -1;


    public INeedItNow() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, DRAGON_COLOR);
        baseMagicNumber = magicNumber = ENERGY_GAIN;
        baseSecondMagic = secondMagic = TURN_PENALTY;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new GainEnergyAction(magicNumber));
        applyToSelf(new NoEnergyPower(p, secondMagic));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_TURN_PENALTY);
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
    }
}