package code.cards.sparkbreaths;

import basemod.helpers.CardModifierManager;
import code.cardmodifiers.BreathModifier;
import code.cards.AbstractTwoSidedCard;
import code.powers.EmberPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;
import static code.util.Wiz.*;


public class FocusedSpark extends AbstractSparkBreathCard {
    public final static String ID = makeID("FocusedSpark");

    //Spark Stuff
    private final static int SPARK_DAMAGE = 2;
    private final static int UPGRADE_SPARK_DAMAGE = 2;
    private final static int SPARK_EMBER_GAIN = 1;
    private final static int UPGRADE_SPARK_EMBER_GAIN = 1;

    //Breath Stuff
    private final static int BREATH_DAMAGE = 5;
    private final static int UPGRADE_BREATH_DAMAGE = 5;
    private final static int BREATH_EMBER_MULTIPLIER = 2;
    private final static int UPGRADE_BREATH_EMBER_MULTIPLIER = 1;

    public FocusedSpark(boolean needsPreview) {
        super(ID, CardType.ATTACK, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY, CardTarget.ENEMY, needsPreview);
        setDamage(SPARK_DAMAGE, BREATH_DAMAGE);
        setMagic(SPARK_EMBER_GAIN, BREATH_EMBER_MULTIPLIER);
        initializeSide();
        CardModifierManager.addModifier(this, new BreathModifier());
    }

    public FocusedSpark() {
        this(true);
    }

    @Override
    protected AbstractTwoSidedCard noPreviewCopy(){
        return new FocusedSpark(false);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (isFront) {
            for (AbstractMonster monster : getEnemies()){
                dmg(m, AbstractGameAction.AttackEffect.FIRE);
            }
            if (getEnemies().size() > 2){
                atb(new SFXAction("ATTACK_BOWLING"));
            }
            applyToSelf(new EmberPower(p, magicNumber));
        } else { // Breath
            for (AbstractMonster monster : getEnemies()){
                dmg(m, AbstractGameAction.AttackEffect.FIRE);
            }
            if (getEnemies().size() > 2){
                atb(new SFXAction("ATTACK_BOWLING"));
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
    }
}