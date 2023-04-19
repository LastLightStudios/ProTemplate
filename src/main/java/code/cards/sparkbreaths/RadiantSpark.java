package code.cards.sparkbreaths;

import basemod.helpers.CardModifierManager;
import code.cardmodifiers.BreathModifier;
import code.cards.AbstractTwoSidedCard;
import code.powers.EmberPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class RadiantSpark extends AbstractSparkBreathCard {
    public final static String ID = makeID("RadiantSpark");

    //Spark Stuff
    private final static int SPARK_DAMAGE = 3;
    private final static int UPGRADE_SPARK_DAMAGE = 2;
    private final static int SPARK_EMBER_GAIN = 1;
    private final static int UPGRADE_SPARK_EMBER_GAIN = 0;
    private final static int SPARK_ENERGY_GAIN = 1;
    private final static int UPGRADE_SPARK_ENERGY_GAIN = 1;

    //Breath Stuff
    private final static int BREATH_DAMAGE = 10;
    private final static int UPGRADE_BREATH_DAMAGE = 5;
    private final static int BREATH_EMBER_MULTIPLIER = 1;
    private final static int UPGRADE_BREATH_EMBER_MULTIPLIER = 0;
    private final static int BREATH_ENERGY_GAIN = 0; //Energy Gain
    private final static int UPGRADE_BREATH_ENERGY_GAIN = 0; //Energy Gain increase

    public RadiantSpark(boolean needsPreview) {
        super(ID, CardType.ATTACK, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY, CardTarget.ALL_ENEMY, needsPreview);
        setDamage(SPARK_DAMAGE, BREATH_DAMAGE);
        setMagic(SPARK_EMBER_GAIN, BREATH_EMBER_MULTIPLIER);
        setSecondMagic(SPARK_ENERGY_GAIN, BREATH_ENERGY_GAIN);
        initializeSide();
        affectSecondMagic = true;
        CardModifierManager.addModifier(this, new BreathModifier());
        exhaust = true;
    }

    public RadiantSpark() {
        this(true);
    }

    @Override
    protected AbstractTwoSidedCard noPreviewCopy(){
        return new RadiantSpark(false);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (isFront) {
            dmg(m, AbstractGameAction.AttackEffect.FIRE);
            atb(new GainEnergyAction(secondMagic));
            applyToSelf(new EmberPower(p, magicNumber));
        } else { // Breath
            allDmg(AbstractGameAction.AttackEffect.FIRE);
            atb(new GainEnergyAction(secondMagic));
            atb(new RemoveSpecificPowerAction(p, p, EmberPower.POWER_ID));
            incrementFirepowerPower();
        }
        checkEmberTrigger();
    }

    @Override
    public void upp() {
        upgradeDamage(UPGRADE_SPARK_DAMAGE, UPGRADE_BREATH_DAMAGE);
        upgradeSecondMagic(UPGRADE_SPARK_ENERGY_GAIN, UPGRADE_BREATH_ENERGY_GAIN);
        descriptionA = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}