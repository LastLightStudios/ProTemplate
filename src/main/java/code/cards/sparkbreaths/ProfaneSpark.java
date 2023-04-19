package code.cards.sparkbreaths;

import basemod.helpers.CardModifierManager;
import code.cardmodifiers.BreathModifier;
import code.cards.AbstractTwoSidedCard;
import code.powers.EmberPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.WeakPower;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class ProfaneSpark extends AbstractSparkBreathCard {
    public final static String ID = makeID("ProfaneSpark");

    //Spark Stuff
    private final static int SPARK_DAMAGE = 4;
    private final static int UPGRADE_SPARK_DAMAGE = 3;
    private final static int SPARK_EMBER_GAIN = 2;
    private final static int UPGRADE_SPARK_EMBER_GAIN = 1;
    private final static int SPARK_WEAK_APPLICATION = 1;
    private final static int UPGRADE_SPARK_WEAK_APPLICATION = 1;

    //Breath Stuff
    private final static int BREATH_DAMAGE = 5;
    private final static int UPGRADE_BREATH_DAMAGE = 5;
    private final static int BREATH_EMBER_MULTIPLIER = 2;
    private final static int UPGRADE_BREATH_EMBER_MULTIPLIER = 1; //spark multiplier increase
    private final static int BREATH_WEAK_APPLICATION = 2; //weak application
    private final static int UPGRADE_BREATH_WEAK_APPLICATION = 1; //weak application increase

    /*
    Ember only increases damage, not weak application
     */

    public ProfaneSpark(boolean needsPreview) {
        super(ID, CardType.ATTACK, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY, CardTarget.ALL_ENEMY, needsPreview);
        setDamage(SPARK_DAMAGE, BREATH_DAMAGE);
        setMagic(SPARK_EMBER_GAIN, BREATH_EMBER_MULTIPLIER);
        setSecondMagic(SPARK_WEAK_APPLICATION, BREATH_WEAK_APPLICATION);
        initializeSide();
        CardModifierManager.addModifier(this, new BreathModifier());
    }

    public ProfaneSpark() {
        this(true);
    }

    @Override
    protected AbstractTwoSidedCard noPreviewCopy(){
        return new ProfaneSpark(false);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (isFront) {
            dmg(m, AbstractGameAction.AttackEffect.FIRE);
            applyToEnemy(m, new WeakPower(m, secondMagic, false));
            applyToSelf(new EmberPower(p, magicNumber));
            if(upgraded){
                atb(new DrawCardAction(1));
            }
        } else { // Breath
            allDmg(AbstractGameAction.AttackEffect.FIRE);
            for (AbstractMonster monster : getEnemies()){
                applyToEnemy(monster, new WeakPower(monster, secondMagic, false));
            }
            atb(new RemoveSpecificPowerAction(p, p, EmberPower.POWER_ID));
            incrementFirepowerPower();
        }
        checkEmberTrigger();
    }

    @Override
    public void upp() {
        upgradeDamage(UPGRADE_SPARK_DAMAGE, UPGRADE_BREATH_DAMAGE);
        upgradeMagicNumber(UPGRADE_SPARK_EMBER_GAIN, UPGRADE_BREATH_EMBER_MULTIPLIER);
        upgradeSecondMagic(UPGRADE_SPARK_WEAK_APPLICATION, UPGRADE_BREATH_WEAK_APPLICATION);
    }
}