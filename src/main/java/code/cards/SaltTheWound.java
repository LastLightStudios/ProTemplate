package code.cards;

import code.powers.CauterizePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;
import static code.util.Wiz.applyToEnemy;

public class SaltTheWound extends AbstractEasyCard {
    public final static String ID = makeID("SaltTheWound");

    private final static int DAMAGE = 6;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int SECOND_DAMAGE = 6;
    private final static int UPGRADE_SECOND_DAMAGE = 3;
    private final static int CAUTERIZE_APPLICATION = 2;
    private final static int UPGRADE_CAUTERIZE_APPLICATION = 0;

    public SaltTheWound() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseSecondDamage = SECOND_DAMAGE;
        baseMagicNumber = magicNumber = CAUTERIZE_APPLICATION;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        if (m.hasPower(CauterizePower.POWER_ID)){
            altDmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        } else {
            applyToEnemy(m, new CauterizePower(m, magicNumber));
        }
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeSecondDamage(UPGRADE_SECOND_DAMAGE);
    }
}