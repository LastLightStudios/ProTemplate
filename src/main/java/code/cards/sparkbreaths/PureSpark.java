package code.cards.sparkbreaths;

import basemod.helpers.CardModifierManager;
import code.cardmodifiers.BreathModifier;
import code.cards.AbstractTwoSidedCard;
import code.powers.EmberPower;
import code.powers.PridePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;
import static code.util.Wiz.applyToSelf;
import static code.util.Wiz.atb;

public class PureSpark extends AbstractSparkBreathCard {
    public final static String ID = makeID("PureSpark");

    //Spark Stuff
    private final static int SPARK_DAMAGE = 4;
    private final static int UPGRADE_SPARK_DAMAGE = 2;
    private final static int SPARK_EMBER_GAIN = 1;
    private final static int UPGRADE_SPARK_EMBER_GAIN = 0;
    private final static int SPARK_PRIDE_GAIN = 3;
    private final static int UPGRADE_SPARK_PRIDE_GAIN = 1;

    //Breath Stuff
    private final static int BREATH_DAMAGE = 10;
    private final static int UPGRADE_BREATH_DAMAGE = 0;
    private final static int BREATH_EMBER_MULTIPLIER = 1;
    private final static int UPGRADE_BREATH_EMBER_MULTIPLIER = 1;
    private final static int BREATH_PRIDE_GAIN = 10;
    private final static int UPGRADE_BREATH_PRIDE_GAIN = 0;

    public PureSpark(boolean needsPreview) {
        super(ID, CardType.ATTACK, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY, CardTarget.ALL_ENEMY, needsPreview);
        setDamage(SPARK_DAMAGE, BREATH_DAMAGE);
        setMagic(SPARK_EMBER_GAIN, BREATH_EMBER_MULTIPLIER);
        setSecondMagic(SPARK_PRIDE_GAIN, BREATH_PRIDE_GAIN);
        initializeSide();
        affectSecondMagic = true;
        CardModifierManager.addModifier(this, new BreathModifier());
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
            applyToSelf(new PridePower(p, secondMagic));
            dmg(m, AbstractGameAction.AttackEffect.FIRE);
            applyToSelf(new EmberPower(p, magicNumber));
            if(upgraded){
                atb(new DrawCardAction(1));
            }
        } else { // Breath
            applyToSelf(new PridePower(p, secondMagic));
            allDmg(AbstractGameAction.AttackEffect.FIRE);
            atb(new RemoveSpecificPowerAction(p, p, EmberPower.POWER_ID));
            incrementFirepowerPower();
        }
        checkEmberTrigger();
    }

    @Override
    public void upp() {
        upgradeDamage(UPGRADE_SPARK_DAMAGE, UPGRADE_BREATH_DAMAGE);
        upgradeMagicNumber(UPGRADE_SPARK_EMBER_GAIN, UPGRADE_BREATH_EMBER_MULTIPLIER);
        upgradeSecondMagic(UPGRADE_SPARK_PRIDE_GAIN, UPGRADE_BREATH_PRIDE_GAIN);
    }
}