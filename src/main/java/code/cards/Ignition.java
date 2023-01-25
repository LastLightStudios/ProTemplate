package code.cards;

import code.powers.EmberPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.DragonCharacterFile.Enums.DRAGON_COLOR;
import static code.ModFile.makeID;
import static code.util.Wiz.applyToSelf;

public class Ignition extends AbstractEasyCard {
    public final static String ID = makeID("Ignition");

    private final static int EMBER_GAIN = 5;

    public Ignition() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, DRAGON_COLOR);
        baseMagicNumber = magicNumber = EMBER_GAIN;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new EmberPower(p, magicNumber));
    }

    public void upp() {
        exhaust = false;
    }
}