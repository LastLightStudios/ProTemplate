package code.cards;

import code.powers.EmberPower;
import code.powers.FirepowerPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static code.ModFile.makeID;
import static code.util.Wiz.*;
import static code.util.Wiz.adp;

public class InfernalBreath extends AbstractEasyCard {
    public final static String ID = makeID("InfernalBreath");

    private final static int DAMAGE = 20;
    private final static int UPGRADE_DAMAGE = 10;
    private final static int EMBER_MULTIPLIER = 3;
    private final static int UPGRADE_EMBER_MULTIPLIER = 2;

    public InfernalBreath() {
        super(ID, 0, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ALL_ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = EMBER_MULTIPLIER;
        isEthereal = true;
        exhaust = true;
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.FIRE);
        atb(new RemoveSpecificPowerAction(p, p, EmberPower.POWER_ID));
        atb(new ApplyPowerAction(adp(), adp(), new FirepowerPower(adp()), 1)); // can't use incrementFirepowerPower() b/c InfernalBreath is a fake Breath
    }

    @Override
    public void calculateCardDamage(AbstractMonster m){
        AbstractPower ember = adp().getPower(EmberPower.POWER_ID);
        if (ember != null) {
            int realBaseDamage = baseDamage; //temp store realBaseDamage b/c baseDamage is used in card damage calculations
            baseDamage = baseDamage + (magicNumber * ember.amount);
            super.calculateCardDamage(m);

            baseDamage = realBaseDamage; //restore the realBaseDamage
            isDamageModified = (damage != baseDamage);
        } else {
            super.calculateCardDamage(m);
        }
    }

    @Override
    public void applyPowers() {
        AbstractPower ember = adp().getPower(EmberPower.POWER_ID);
        if (ember != null) {
            int realBaseDamage = baseDamage; //temp store realBaseDamage b/c baseDamage is used in card damage calculations
            baseDamage = baseDamage + (magicNumber * ember.amount);
            super.applyPowers();

            baseDamage = realBaseDamage; //restore the realBaseDamage
            isDamageModified = (damage != baseDamage);
        } else {
            super.applyPowers();
        }
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeMagicNumber(UPGRADE_EMBER_MULTIPLIER);
    }
}