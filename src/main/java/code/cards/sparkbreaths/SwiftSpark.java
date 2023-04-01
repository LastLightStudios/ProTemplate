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

public class SwiftSpark extends AbstractSparkBreathCard {
    public final static String ID = makeID("SwiftSpark");

    //Spark Stuff
    private final static int SPARK_DAMAGE = 1;
    private final static int SPARK_EMBER_GAIN = 1; //spark gain
    private final static int UPGRADE_SPARK_EMBER_GAIN = 0; //spark gain
    private final static int SPARK_CARDS_DRAWN = 1; //cards drawn
    private final static int UPGRADE_SPARK_CARDS_DRAWN = 1; //cards drawn increase

    //Breath Stuff
    private final static int BREATH_DAMAGE = 10;
    private final static int BREATH_EMBER_MULTIPLIER = 1; //spark multiplier
    private final static int UPGRADE_BREATH_EMBER_MULTIPLIER = 1; //spark multiplier increase
    private final static int BREATH_CARDS_DRAWN = 2; //cards drawn
    private final static int UPGRADE_BREATH_CARDS_DRAWN = 1; //cards drawn increase

    public SwiftSpark(boolean needsPreview) {
        super(ID, CardType.ATTACK, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY, CardTarget.ALL_ENEMY, needsPreview);
        setDamage(SPARK_DAMAGE, BREATH_DAMAGE);
        setMagic(SPARK_EMBER_GAIN, BREATH_EMBER_MULTIPLIER);
        setSecondMagic(SPARK_CARDS_DRAWN, BREATH_CARDS_DRAWN);

        initializeSide();
    }

    public SwiftSpark() {
        this(true);
    }

    @Override
    protected AbstractTwoSidedCard noPreviewCopy(){
        return new SwiftSpark(false);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (isFront) {
            dmg(m, AbstractGameAction.AttackEffect.FIRE);
            applyToSelf(new EmberPower(p, magicNumber));
            atb(new DrawCardAction(secondMagic));
        } else { // Breath
            allDmg(AbstractGameAction.AttackEffect.FIRE);
            atb(new RemoveSpecificPowerAction(p, p, EmberPower.POWER_ID));
            atb(new DrawCardAction(secondMagic));
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
                baseDamage = baseDamage + (magicNumber * ember.amount);
                super.applyPowers();
                baseDamage = realBaseDamage; //restore the realBaseDamage
                isDamageModified = (damage != baseDamage);
            }
        }
    }

    @Override
    public void upp() {
        upgradeMagicNumber(UPGRADE_SPARK_EMBER_GAIN, UPGRADE_BREATH_EMBER_MULTIPLIER);
        upgradeSecondMagic(UPGRADE_SPARK_CARDS_DRAWN, UPGRADE_BREATH_CARDS_DRAWN);
        descriptionA = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}