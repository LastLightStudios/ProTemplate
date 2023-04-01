package code.cards.sparkbreaths;

import code.cards.AbstractTwoSidedCard;
import code.powers.EmberPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static code.ModFile.makeID;
import static code.util.Wiz.*;


public class BarrageSpark extends AbstractSparkBreathCard {
    public final static String ID = makeID("BarrageSpark");

    //Spark Stuff
    private final static int SPARK_DAMAGE = 1;
    private final static int SPARK_EMBER_GAIN = 1;
    private final static int SPARK_NUMBER_OF_HITS = 3;
    private final static int UPGRADE_NUMBER_OF_HITS = 0;

    //Breath Stuff
    private final static int BREATH_DAMAGE = 1;
    private final static int BREATH_EMBER_MULTIPLIER = 1; //ember multiplier
    private final static int BREATH_NUMBER_OF_HITS = 4; //Number of hits
    private final static int UPGRADE_BREATH_NUMBER_OF_HITS = 1; //Number of hits increase

    public BarrageSpark(boolean needsPreview) {
        super(ID, CardType.ATTACK, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY, CardTarget.ALL_ENEMY, needsPreview);
        setDamage(SPARK_DAMAGE, BREATH_DAMAGE);
        setMagic(SPARK_EMBER_GAIN, BREATH_EMBER_MULTIPLIER);
        setSecondMagic(SPARK_NUMBER_OF_HITS, BREATH_NUMBER_OF_HITS);

        initializeSide();
    }

    public BarrageSpark() {
        this(true);
    }

    @Override
    protected AbstractTwoSidedCard noPreviewCopy(){
        return new BarrageSpark(false);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (isFront) {
            for (int i = 0; i < secondMagic; i++){
                atb(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.FIRE));
            }
            applyToSelf(new EmberPower(p, magicNumber));
            if(upgraded){
                atb(new DrawCardAction(1));
            }
        } else { // Breath
            for (int i = 0; i < secondMagic; i++){
                atb(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.FIRE));
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
                baseDamage = baseDamage + ember.amount;
                super.calculateCardDamage(m);
                baseDamage = realBaseDamage; //restore the realBaseDamage
                isDamageModified = (damage != baseDamage);
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
                baseDamage = baseDamage + ember.amount;
                super.applyPowers();
                baseDamage = realBaseDamage; //restore the realBaseDamage
                isDamageModified = (damage != baseDamage);
            }
        }
    }

    @Override
    public void upp() {
        upgradeSecondMagic(UPGRADE_NUMBER_OF_HITS, UPGRADE_BREATH_NUMBER_OF_HITS);
        descriptionA = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}