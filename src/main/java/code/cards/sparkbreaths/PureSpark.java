package code.cards.sparkbreaths;

import code.cards.AbstractTwoSidedCard;
import code.powers.EmberPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class PureSpark extends AbstractSparkBreathCard {
    public final static String ID = makeID("PureSpark");

    //Spark Stuff
    private final static int SPARK_DAMAGE = 1;
    private final static int SPARK_EMBER_GAIN = 1;
    private final static int SPARK_PRIDE_GAIN = 3;
    private final static int UPGRADE_SPARK_PRIDE_GAIN = 0;

    //Breath Stuff
    private final static int BREATH_DAMAGE = 10;
    private final static int BREATH_EMBER_MULTIPLIER = 1;
    private final static int BREATH_PRIDE_GAIN = 10;
    private final static int UPGRADE_BREATH_PRIDE_GAIN = 5;

    public PureSpark(boolean needsPreview) {
        super(ID, CardType.ATTACK, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY, CardTarget.ALL_ENEMY, needsPreview);
        setDamage(SPARK_DAMAGE, BREATH_DAMAGE);
        setMagic(SPARK_EMBER_GAIN, BREATH_EMBER_MULTIPLIER);
        setSecondMagic(SPARK_PRIDE_GAIN, BREATH_PRIDE_GAIN);

        initializeSide();
    }

    public PureSpark() {
        this(true);
    }

    @Override
    protected AbstractTwoSidedCard noPreviewCopy(){
        return new PureSpark(false);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (isFront) {
            applyToSelf(new EmberPower(p, secondMagic));
            dmg(m, AbstractGameAction.AttackEffect.FIRE);
            applyToSelf(new EmberPower(p, magicNumber));
            if(upgraded){
                atb(new DrawCardAction(1));
            }
        } else { // Breath
            applyToSelf(new EmberPower(p, secondMagic));
            allDmg(AbstractGameAction.AttackEffect.FIRE);
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

                secondMagic = secondMagic + (magicNumber * ember.amount);
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

                secondMagic = secondMagic + (magicNumber * ember.amount);
                super.applyPowers();

                baseDamage = realBaseDamage; //restore the realBaseDamage
                isDamageModified = (damage != baseDamage);
                isSecondMagicModified = (secondMagic != baseSecondMagic);
            }
        }
    }

    @Override
    public void upp() {
        upgradeSecondMagic(UPGRADE_SPARK_PRIDE_GAIN, UPGRADE_BREATH_PRIDE_GAIN);
        descriptionA = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}