package code.cards;

import code.powers.CrashingPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class CrashLanding extends AbstractEasyCard {
    public final static String ID = makeID("CrashLanding");

    private final static int DAMAGE_DEALT = 15;
    private final static int UPGRADE_DAMAGE_DEALT = 5;
    private final static int BLOCK = 40;
    private final static int UPGRADE_BLOCK = 10;


    public CrashLanding() {
        super(ID, 3, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseMagicNumber = magicNumber = DAMAGE_DEALT;
        baseBlock = BLOCK;
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToSelf(new CrashingPower(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_DAMAGE_DEALT);
        upgradeDamage(UPGRADE_DAMAGE_DEALT);
        upgradeBlock(UPGRADE_BLOCK);
    }
}