package code.cards.sparkbreaths;

import basemod.helpers.CardModifierManager;
import code.cardmodifiers.BreathModifier;
import code.cards.AbstractTwoSidedCard;
import code.powers.CauterizePower;
import code.powers.EmberPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class CauterizingSpark extends AbstractSparkBreathCard {
    public final static String ID = makeID("CauterizingSpark");

    //Spark Stuff
    private final static int SPARK_DAMAGE = 3;
    private final static int UPGRADE_SPARK_DAMAGE = 2;
    private final static int SPARK_EMBER_GAIN = 1;
    private final static int UPGRADE_SPARK_EMBER_GAIN = 0;
    private final static int SPARK_CAUTERIZE_APPLICATION = 1;
    private final static int UPGRADE_SPARK_CAUTERIZE_APPLICATION = 1;

    //Breath Stuff
    private final static int BREATH_DAMAGE = 10;
    private final static int UPGRADE_BREATH_DAMAGE = 0;
    private final static int BREATH_EMBER_MULTIPLIER = 1;
    private final static int UPGRADE_EMBER_MULTIPLIER = 1;
    private final static int BREATH_CAUTERIZE_APPLICATION = 0;
    private final static int UPGRADE_BREATH_CAUTERIZE_APPLICATION = 0;

    public CauterizingSpark(boolean needsPreview) {
        super(ID, CardType.ATTACK, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY, CardTarget.ALL_ENEMY, needsPreview);
        setDamage(SPARK_DAMAGE, BREATH_DAMAGE);
        setMagic(SPARK_EMBER_GAIN, BREATH_EMBER_MULTIPLIER);
        setSecondMagic(SPARK_CAUTERIZE_APPLICATION, BREATH_CAUTERIZE_APPLICATION);
        initializeSide();
        affectSecondMagic = true;
        CardModifierManager.addModifier(this, new BreathModifier());
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
        } else { // Breath
            allDmg(AbstractGameAction.AttackEffect.FIRE);
            for (AbstractMonster monster : getEnemies()){
                applyToEnemy(monster, new CauterizePower(monster, secondMagic));
            }
            atb(new RemoveSpecificPowerAction(p, p, EmberPower.POWER_ID));
            incrementFirepowerPower();
        }
        checkEmberTrigger();
    }

    @Override
    public void upp() {
        upgradeDamage(UPGRADE_SPARK_DAMAGE, UPGRADE_BREATH_DAMAGE);
        upgradeMagicNumber(UPGRADE_SPARK_EMBER_GAIN, UPGRADE_EMBER_MULTIPLIER);
        upgradeSecondMagic(UPGRADE_SPARK_CAUTERIZE_APPLICATION, UPGRADE_BREATH_CAUTERIZE_APPLICATION);
    }
}