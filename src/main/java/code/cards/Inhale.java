package code.cards;

import code.powers.EmberPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.FreeAttackPower;

import static code.ModFile.makeID;
import static code.util.Wiz.applyToSelf;

public class Inhale extends AbstractEasyCard {
    public final static String ID = makeID("Inhale");

    private final static int BLOCK = 8;
    private final static int UPGRADE_BLOCK = 3;
    private final static int EMBER_GAIN = 3;
    private final static int UPGRADE_EMBER_GAIN = 2;

    public Inhale() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = EMBER_GAIN;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToSelf(new EmberPower(p, magicNumber));
        applyToSelf(new FreeAttackPower(p, 1));
    }

    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
        upgradeMagicNumber(UPGRADE_EMBER_GAIN);
    }
}