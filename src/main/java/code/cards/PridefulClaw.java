package code.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;
import static code.util.DragonUtils.countRaresInDeck;

public class PridefulClaw extends AbstractEasyCard {
    public final static String ID = makeID("PridefulClaw");

    private final static int DAMAGE = 12;
    private final static int UPGRADE_DAMAGE = 4;
    private final static int DAMAGE_SCALAR = 3;
    private final static int UPGRADE_DAMAGE_SCALAR = 1;

    public PridefulClaw() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = DAMAGE_SCALAR;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
    }

    @Override
    public void calculateCardDamage(AbstractMonster mon){
        int realBaseDamage = baseDamage;
        baseDamage += magicNumber * countRaresInDeck();
        super.calculateCardDamage(mon);
        baseDamage = realBaseDamage;
        isDamageModified = (damage != baseDamage);
    }

    @Override
    public void applyPowers(){
        int realBaseDamage = baseDamage;
        baseDamage += magicNumber * countRaresInDeck();
        super.applyPowers();
        baseDamage = realBaseDamage;
        isDamageModified = (damage != baseDamage);
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeMagicNumber(UPGRADE_DAMAGE_SCALAR);
    }
}