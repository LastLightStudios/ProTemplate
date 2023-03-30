package code.cards;

import code.powers.CauterizePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;
import static code.util.Wiz.applyToEnemy;
import static code.util.Wiz.getEnemies;

public class SearingSwipe extends AbstractEasyCard {
    public final static String ID = makeID("SearingSwipe");

    private final static int DAMAGE = 7;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int CAUTERIZE_APPLICATION = 1;
    private final static int UPGRADE_CAUTERIZE_APPLICATION = 1;

    public SearingSwipe() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = CAUTERIZE_APPLICATION;
        exhaust = true;
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        for (AbstractMonster monster : getEnemies()){
            applyToEnemy(monster, new CauterizePower(monster, magicNumber));
        }
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeMagicNumber(UPGRADE_CAUTERIZE_APPLICATION);
    }
}