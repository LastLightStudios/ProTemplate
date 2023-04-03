package code.cards;

import code.powers.KindlePower;
import code.powers.MonstrousGreedPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;
import static code.util.Wiz.applyToSelf;

public class MonstrousGreed extends AbstractEasyCard {
    public final static String ID = makeID("MonstrousGreed");

    private final static int DAMAGE = 5;
    private final static int UPGRADE_DAMAGE = 3;

    public MonstrousGreed() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new MonstrousGreedPower(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_DAMAGE);
    }
}