package code.cards;

import code.powers.CrashingPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class CrashLanding extends AbstractEasyCard {
    public final static String ID = makeID("CrashLanding");

    private final static int DAMAGE = 15;
    private final static int UPGRADE_DAMAGE = 5;
    private final static int BLOCK = 30;
    private final static int UPGRADE_BLOCK = 10;


    public CrashLanding() {
        super(ID, 3, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = DAMAGE;
        baseBlock = BLOCK;
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToSelf(new CrashingPower(p, baseDamage));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeBlock(UPGRADE_BLOCK);
    }
}