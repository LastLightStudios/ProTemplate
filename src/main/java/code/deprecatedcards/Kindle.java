package code.deprecatedcards;

import code.cards.AbstractEasyCard;
import code.powers.dep.KindlePower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;
import static code.util.Wiz.applyToSelf;

public class Kindle extends AbstractEasyCard {
    public final static String ID = makeID("Kindle");

    private final static int EMBER_GAIN = 2;
    private final static int UPGRADE_EMBER_GAIN = 1;

    public Kindle() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = EMBER_GAIN;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new KindlePower(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_EMBER_GAIN);
    }
}