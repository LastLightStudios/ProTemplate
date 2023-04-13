package code.cards;

import code.actions.ConflagrationAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.DragonCharacterFile.Enums.DRAGON_COLOR;
import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class Conflagration extends AbstractEasyCard {
    public final static String ID = makeID("Conflagration");

    private final static int EMBER_MULTIPLIER = 2;
    private final static int UPGRADE_EMBER_MULTIPLIER = 1;
    private final static int CAUTERIZE_MULTIPLIER = 2;
    private final static int UPGRADE_CAUTERIZE_MULTIPLIER = 1;


    public Conflagration() {
        super(ID, -1, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY, DRAGON_COLOR);
        baseMagicNumber = magicNumber = EMBER_MULTIPLIER;
        baseSecondMagic = secondMagic = CAUTERIZE_MULTIPLIER;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ConflagrationAction(p, m, this.upgraded, this.freeToPlayOnce, this.energyOnUse, magicNumber, secondMagic));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_EMBER_MULTIPLIER);
        upgradeSecondMagic(UPGRADE_CAUTERIZE_MULTIPLIER);
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}