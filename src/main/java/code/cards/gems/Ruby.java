package code.cards.gems;

import code.powers.EmberPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static code.ModFile.makeID;
import static code.util.Wiz.applyToSelf;

public class Ruby extends AbstractGemCard {
    public final static String ID = makeID("Ruby");

    private final static int MAGIC_NUMBER = 2; //Ember gain
    private final static int UPGRADE_MAGIC_NUMBER = 2; //increase Ember gain

    public Ruby() {
        super(ID, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC_NUMBER;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new EmberPower(p, MAGIC_NUMBER));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC_NUMBER);
    }
}