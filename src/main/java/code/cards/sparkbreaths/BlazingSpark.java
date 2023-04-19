package code.cards.sparkbreaths;

import basemod.helpers.CardModifierManager;
import code.cardmodifiers.BreathModifier;
import code.cards.AbstractTwoSidedCard;
import code.powers.EmberPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;
import static code.util.Wiz.applyToSelf;
import static code.util.Wiz.atb;

public class BlazingSpark extends AbstractSparkBreathCard {
    public final static String ID = makeID("BlazingSpark");

    //Spark Stuff
    private final static int SPARK_DAMAGE = 4;
    private final static int UPGRADE_SPARK_DAMAGE = 2;
    private final static int SPARK_EMBER_GAIN = 2;
    private final static int UPGRADE_SPARK_EMBER_GAIN = 1;

    //Breath Stuff
    private final static int BREATH_DAMAGE = 5;
    private final static int UPGRADE_BREATH_DAMAGE = 0;
    private final static int BREATH_EMBER_MULTIPLIER = 2;
    private final static int UPGRADE_BREATH_EMBER_MULTIPLIER = 1;
    private final static int BREATH_EMBER_GAIN = 3;
    private final static int UPGRADE_BREATH_EMBER_GAIN = 1;

    public BlazingSpark(boolean needsPreview) {
        super(ID, CardType.ATTACK, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY, CardTarget.ALL_ENEMY, needsPreview);
        setDamage(SPARK_DAMAGE, BREATH_DAMAGE);
        setMagic(SPARK_EMBER_GAIN, BREATH_EMBER_MULTIPLIER);
        setSecondMagic(0, BREATH_EMBER_GAIN);
        initializeSide();
        CardModifierManager.addModifier(this, new BreathModifier());
    }

    public BlazingSpark() {
        this(true);
    }

    @Override
    protected AbstractTwoSidedCard noPreviewCopy(){
        return new BlazingSpark(false);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (isFront) {
            dmg(m, AbstractGameAction.AttackEffect.FIRE);
            applyToSelf(new EmberPower(p, magicNumber));
        } else { // Breath
            allDmg(AbstractGameAction.AttackEffect.FIRE);
            atb(new RemoveSpecificPowerAction(p, p, EmberPower.POWER_ID));
            applyToSelf(new EmberPower(p, secondMagic));
            incrementFirepowerPower();
        }
        checkEmberTrigger();
    }

    @Override
    public void upp() {
        upgradeDamage(UPGRADE_SPARK_DAMAGE, UPGRADE_BREATH_DAMAGE);
        upgradeMagicNumber(UPGRADE_SPARK_EMBER_GAIN, UPGRADE_BREATH_EMBER_MULTIPLIER);
        upgradeSecondMagic(0, UPGRADE_BREATH_EMBER_GAIN);
    }
}