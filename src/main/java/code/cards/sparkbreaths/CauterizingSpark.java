package code.cards.sparkbreaths;

import code.cards.AbstractTwoSidedCard;
import code.powers.CauterizePower;
import code.powers.EmberPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class CauterizingSpark extends AbstractSparkBreathCard {
    public final static String ID = makeID("CauterizingSpark");

    //Spark Stuff
    private final static int SPARK_DAMAGE = 1;
    private final static int UPGRADE_SPARK_DAMAGE = 0;
    private final static int SPARK_EMBER_GAIN = 1;
    private final static int UPGRADE_SPARK_EMBER_GAIN = 0;
    private final static int SPARK_CAUTERIZE_APPLICATION = 1;
    private final static int UPGRADE_SPARK_CAUTERIZE_APPLICATION = 0;

    //Breath Stuff
    private final static int BREATH_DAMAGE = 10;
    private final static int UPGRADE_BREATH_DAMAGE = 5;
    private final static int BREATH_EMBER_MULTIPLIER = 1;
    private final static int UPGRADE_EMBER_MULTIPLIER = 0;
    private final static int BREATH_CAUTERIZE_APPLICATION = 0;
    private final static int UPGRADE_BREATH_CAUTERIZE_APPLICATION = 0;

    public CauterizingSpark(boolean needsPreview) {
        super(ID, CardType.ATTACK, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY, CardTarget.ALL_ENEMY, needsPreview);
        setDamage(SPARK_DAMAGE, BREATH_DAMAGE);
        setMagic(SPARK_EMBER_GAIN, BREATH_EMBER_MULTIPLIER);
        setSecondMagic(SPARK_CAUTERIZE_APPLICATION, BREATH_CAUTERIZE_APPLICATION);

        initializeSide();
    }

    public CauterizingSpark() {
        this(true);
    }

    @Override
    protected AbstractTwoSidedCard noPreviewCopy(){
        return new CauterizingSpark(false);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (isFront) {
            dmg(m, AbstractGameAction.AttackEffect.FIRE);
            applyToEnemy(m, new CauterizePower(m, secondMagic));
            applyToSelf(new EmberPower(p, magicNumber));
            if(upgraded){
                atb(new DrawCardAction(1));
            }
        } else { // Breath
            allDmg(AbstractGameAction.AttackEffect.FIRE);
            for (AbstractMonster monster : getEnemies()){
                applyToEnemy(monster, new CauterizePower(monster, secondMagic));
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

                //adjusting Cauterize value based on Ember
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

                //adjusting Cauterize value based on Ember
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
        upgradeDamage(UPGRADE_SPARK_DAMAGE, UPGRADE_BREATH_DAMAGE);
        descriptionA = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}