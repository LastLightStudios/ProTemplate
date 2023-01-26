package code.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class StretchYourWings extends AbstractEasyCard {
    public final static String ID = makeID("StretchYourWings");

    private final static int ENERGY_GAIN = 4;

    public StretchYourWings() {
        super(ID, 2, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        magicNumber = baseMagicNumber = ENERGY_GAIN;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new GainEnergyAction(magicNumber));
    }

    public void upp() {
        exhaust = false;
    }
}