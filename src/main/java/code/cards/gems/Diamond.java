package code.cards.gems;

import code.powers.PridePower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;
import static code.util.Wiz.applyToSelf;

public class Diamond extends AbstractGemCard {
    public final static String ID = makeID("Diamond");


    private final static int PRIDE_AMOUNT = 2;
    private final static int UPGRADE_PRIDE_AMOUNT = 2;

    public Diamond() {
        super(ID, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        baseMagicNumber = magicNumber = PRIDE_AMOUNT;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new PridePower(p, magicNumber));
        hoardThisGem();
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_PRIDE_AMOUNT);
    }
}