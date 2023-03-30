package code.cards.sparkbreaths;

import code.cards.AbstractTwoSidedCard;
import code.powers.EmberPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class ToxicSpark extends AbstractSparkBreathCard {
    public final static String ID = makeID("ToxicSpark");

    private final static int DAMAGE_A = 1;
    private final static int DAMAGE_B = 10;

    private final static int MAGIC_NUMBER_A = 1; //ember gain
    private final static int MAGIC_NUMBER_B = 1; //ember multiplier
    private final static int SECOND_MAGIC_NUMBER_A = 1; //spark poison application
    private final static int SECOND_MAGIC_NUMBER_B = 1; //breath poison application
    private final static int UPGRADE_MAGIC_NUMBER_B = 1; //ember multiplier increase
    private final static int UPGRADE_SECOND_MAGIC_NUMBER_B = 1; //breath poison application increase

    public ToxicSpark(boolean needsPreview) {
        super(ID, CardType.ATTACK, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY, CardTarget.ALL_ENEMY, needsPreview);
        setDamage(DAMAGE_A, DAMAGE_B);
        setMagic(MAGIC_NUMBER_A, MAGIC_NUMBER_B);
        setSecondMagic(SECOND_MAGIC_NUMBER_A, SECOND_MAGIC_NUMBER_B);

        initializeSide();
    }

    public ToxicSpark() {
        this(true);
    }

    @Override
    protected AbstractTwoSidedCard noPreviewCopy(){
        return new ToxicSpark(false);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (isFront) {
            dmg(m, AbstractGameAction.AttackEffect.FIRE);
            applyToEnemy(m, new PoisonPower(m, p, SECOND_MAGIC_NUMBER_A));
            applyToSelf(new EmberPower(p, magicNumber));
            if(upgraded){
                atb(new DrawCardAction(1));
            }
        } else { // Breath
            allDmg(AbstractGameAction.AttackEffect.FIRE);
            AbstractPower ember = adp().getPower(EmberPower.POWER_ID);
            if (ember != null) {
                for (AbstractMonster monster : getEnemies()){
                    applyToEnemy(monster, new PoisonPower(monster, p, secondMagic));
                }
            }
            atb(new RemoveSpecificPowerAction(p, p, EmberPower.POWER_ID));
        }
        checkEmberTrigger();
    }

    @Override
    public void calculateCardDamage(AbstractMonster m){
        if (isFront) {
            super.calculateCardDamage(m);
        } else {
            AbstractPower ember = adp().getPower(EmberPower.POWER_ID);
            if (ember != null) {
                int realBaseDamage = baseDamage; //temp store realBaseDamage b/c baseDamage is used in card damage calculations
                baseDamage = baseDamage + (magicNumber * ember.amount);

                // adjusting Poison values based on Ember
                secondMagic = baseSecondMagic + (magicNumber * ember.amount);
                super.calculateCardDamage(m);

                baseDamage = realBaseDamage; //restore the realBaseDamage
                isDamageModified = (damage != baseDamage);
                isSecondMagicModified = (secondMagic != baseSecondMagic);
            }
        }
    }

    @Override
    public void applyPowers() {
        if (isFront) {
            super.applyPowers();
        } else {
            AbstractPower ember = adp().getPower(EmberPower.POWER_ID);
            if (ember != null) {
                int realBaseDamage = baseDamage; //temp store realBaseDamage b/c baseDamage is used in card damage calculations
                baseDamage = baseDamage + (magicNumber * ember.amount);

                // adjusting Poison values based on Ember
                secondMagic = baseSecondMagic + (magicNumber * ember.amount);
                super.applyPowers();

                baseDamage = realBaseDamage; //restore the realBaseDamage
                isDamageModified = (damage != baseDamage);
                isSecondMagicModified = (secondMagic != baseSecondMagic);
            }
        }
    }

    @Override
    public void upp() {
        upgradeMagicNumber(0, UPGRADE_MAGIC_NUMBER_B);
        upgradeSecondMagicNumber(0, UPGRADE_SECOND_MAGIC_NUMBER_B);
        descriptionA = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}