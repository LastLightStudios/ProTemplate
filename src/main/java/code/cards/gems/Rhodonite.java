package code.cards.gems;

import code.powers.EmberPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static code.ModFile.makeID;
import static code.util.Wiz.applyToSelf;

public class Rhodonite extends AbstractGemCard {
    public final static String ID = makeID("Rhodonite");

    private final static int EMBER_GAIN = 2; //Ember gain
    private final static int UPGRADE_EMBER_GAIN = 2; //increase Ember gain

    public Rhodonite() {
        super(ID, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        baseMagicNumber = magicNumber = EMBER_GAIN;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new EmberPower(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_EMBER_GAIN);
    }
}