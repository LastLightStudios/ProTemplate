package code.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class MassiveClaw extends AbstractEasyCard {
    public final static String ID = makeID("MassiveClaw");

    private final static int DAMAGE = 5;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int DAMAGE_SCALAR = 1;
    private final static int PER_CARDS = 4;
    private final static int UPGRADE_PER_CARDS = -1;

    public MassiveClaw() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = DAMAGE_SCALAR;
        baseSecondMagic = secondMagic = PER_CARDS;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
    }

    @Override
    public void calculateCardDamage(AbstractMonster mon){
        int realBaseDamage = baseDamage;
        baseDamage += magicNumber * (adp().masterDeck.size()/secondMagic);
        super.calculateCardDamage(mon);
        baseDamage = realBaseDamage;
        isDamageModified = (damage != baseDamage);
    }

    @Override
    public void applyPowers(){
        int realBaseDamage = baseDamage;
        baseDamage += magicNumber * (adp().masterDeck.size()/secondMagic);
        super.applyPowers();
        baseDamage = realBaseDamage;
        isDamageModified = (damage != baseDamage);
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeSecondMagic(UPGRADE_PER_CARDS);
    }
}