package code.cards.gems;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class Emerald extends AbstractGemCard {
    public final static String ID = makeID("Emerald");

    private final static int MAGIC_NUMBER = 2; //temp dex gain
    private final static int UPGRADE_MAGIC_NUMBER = 2; //increase temp dex gain

    public Emerald() {
        super(ID, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC_NUMBER;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new DexterityPower(p, MAGIC_NUMBER));
        applyToSelf(new LoseDexterityPower(p, MAGIC_NUMBER));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC_NUMBER);
    }
}