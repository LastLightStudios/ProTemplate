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
    private final static int DAMAGE_A = 1;
    private final static int UPGRADE_DAMAGE_A = 0;
    private final static int MAGIC_NUMBER_A = 1; //ember gain
    private final static int UPGRADE_MAGIC_NUMBER_A = 0; //ember gain increase
    private final static int SECOND_MAGIC_NUMBER_A = 1; //cauterize application
    private final static int UPGRADE_SECOND_MAGIC_NUMBER_A = 0; //cauterize application increase

    // Breath Stuff
    private final static int DAMAGE_B = 10;
    private final static int UPGRADE_DAMAGE_B = 5; // increases base AoE dmg b/c the ember multiplier doesn't increase
    private final static int MAGIC_NUMBER_B = 1; //ember multiplier
    private final static int UPGRADE_MAGIC_NUMBER_B = 0; //ember multiplier increase
    private final static int SECOND_MAGIC_NUMBER_B = 0; //cauterize application
    private final static int UPGRADE_SECOND_MAGIC_NUMBER_B = 0; //breath cauterize application increase

    public CauterizingSpark(boolean needsPreview) {
        super(ID, CardType.ATTACK, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY, CardTarget.ALL_ENEMY, needsPreview);
        setDamage(DAMAGE_A, DAMAGE_B);
        setMagic(MAGIC_NUMBER_A, MAGIC_NUMBER_B);
        setSecondMagic(SECOND_MAGIC_NUMBER_A, SECOND_MAGIC_NUMBER_B);

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
        upgradeDamage(UPGRADE_DAMAGE_A,UPGRADE_DAMAGE_B);
        descriptionA = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}