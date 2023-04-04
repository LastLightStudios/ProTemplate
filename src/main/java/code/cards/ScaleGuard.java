package code.cards;

import code.powers.PridePower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;
import static code.DragonCharacterFile.Enums.DRAGON_COLOR;
import static code.util.Wiz.applyToSelf;

public class ScaleGuard extends AbstractEasyCard {
    public final static String ID = makeID("ScaleGuard");
    private final static int PRIDE_GAIN = 5;
    private final static int UPGRADE_PRIDE_GAIN = 2;

    public ScaleGuard() {
        super(ID, 2, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF, DRAGON_COLOR);
        baseMagicNumber = magicNumber = PRIDE_GAIN;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new PridePower(p, magicNumber));
        applyToSelf(new PridePower(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_PRIDE_GAIN);
    }
}