package code.cards;

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

    public MassiveClaw() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        isInnate = false;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
        if (!upgraded){
            rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        } else {
            rawDescription = cardStrings.UPGRADE_DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        }
        initializeDescription();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mon){
        /*
        int realBaseDamage = baseDamage;
        baseDamage += magicNumber * (adp().masterDeck.size()/secondMagic);
        super.calculateCardDamage(mon);
        baseDamage = realBaseDamage;
        isDamageModified = (damage != baseDamage);
         */
        super.calculateCardDamage(mon);
        if (!upgraded){
            rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        } else {
            rawDescription = cardStrings.UPGRADE_DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        }
        initializeDescription();
    }

    @Override
    public void applyPowers(){
        /*
        int realBaseDamage = baseDamage;
        baseDamage += magicNumber * (adp().masterDeck.size()/secondMagic);
        super.applyPowers();
        baseDamage = realBaseDamage;
        isDamageModified = (damage != baseDamage);
         */
        //baseDamage = java.lang.Math.max(adp().drawPile.size(), adp().discardPile.size());
        baseDamage = adp().masterDeck.size();
        AbstractPower pride = adp().getPower(PridePower.POWER_ID);
        if (pride != null){
            baseDamage += pride.amount;
        }
        super.applyPowers();
        if (!upgraded){
            rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        } else {
            rawDescription = cardStrings.UPGRADE_DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        }
        initializeDescription();
    }

    public void upp() {
        isInnate = true;
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}