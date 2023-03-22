package code.cards.gems;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static code.ModFile.makeID;
import static code.util.Wiz.applyToSelf;

public class Ruby extends AbstractGemCard {
    public final static String ID = makeID("Ruby");

    private final static int TEMP_STR_GAIN = 2; //temp str gain
    private final static int UPGRADE_TEMP_STR_GAIN = 2; //increase temp str gain

    public Ruby() {
        super(ID, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        baseMagicNumber = magicNumber = TEMP_STR_GAIN;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new StrengthPower(p, magicNumber));
        applyToSelf(new LoseStrengthPower(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_TEMP_STR_GAIN);
    }
}