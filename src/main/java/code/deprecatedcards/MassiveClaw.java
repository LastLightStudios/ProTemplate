package code.deprecatedcards;

import code.cards.AbstractEasyCard;
import code.powers.PridePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class MassiveClaw extends AbstractEasyCard {
    public final static String ID = makeID("MassiveClaw");

    private final static int DAMAGE = 0;
    private final static int PRIDE_SCALAR = 1;
    private final static int UPGRADE_PRIDE_SCALAR = 1;

    public MassiveClaw() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = PRIDE_SCALAR;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
        rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mon){
        super.calculateCardDamage(mon);
        rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    @Override
    public void applyPowers(){
        //baseDamage = java.lang.Math.max(adp().drawPile.size(), adp().discardPile.size());
        baseDamage = adp().masterDeck.size();
        AbstractPower pride = adp().getPower(PridePower.POWER_ID);
        if (pride != null){
            baseDamage += magicNumber * pride.amount;
        }
        super.applyPowers();
        rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_PRIDE_SCALAR);
    }
}