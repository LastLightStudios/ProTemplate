package code.cards;

import code.powers.PridePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static code.ModFile.makeID;
import static code.util.DragonUtils.countRaresInDeck;
import static code.util.Wiz.adp;

public class PridefulClaw extends AbstractEasyCard {
    public final static String ID = makeID("PridefulClaw");

    private final static int DAMAGE = 12;
    private final static int UPGRADE_DAMAGE = 4;
    private final static int DAMAGE_SCALAR = 3;
    private final static int UPGRADE_DAMAGE_SCALAR = 1;
    private final static int PRIDE_SCALAR = 1;
    private final static int UPGRADE_PRIDE_SCALAR = 1;

    public PridefulClaw() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = DAMAGE_SCALAR;
        baseSecondMagic = secondMagic = PRIDE_SCALAR;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
    }

    @Override
    public void calculateCardDamage(AbstractMonster mon){
        int realBaseDamage = baseDamage;
        baseDamage += magicNumber * countRaresInDeck();
        AbstractPower pride = adp().getPower(PridePower.POWER_ID);
        if (pride != null){
            baseDamage += secondMagic * pride.amount;
        }
        super.calculateCardDamage(mon);
        baseDamage = realBaseDamage;
        isDamageModified = (damage != baseDamage);
    }

    @Override
    public void applyPowers(){
        int realBaseDamage = baseDamage;
        baseDamage += magicNumber * countRaresInDeck();
        AbstractPower pride = adp().getPower(PridePower.POWER_ID);
        if (pride != null){
            baseDamage += secondMagic * pride.amount;
        }
        super.applyPowers();
        baseDamage = realBaseDamage;
        isDamageModified = (damage != baseDamage);
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeMagicNumber(UPGRADE_DAMAGE_SCALAR);
        upgradeSecondMagic(UPGRADE_PRIDE_SCALAR);
    }
}