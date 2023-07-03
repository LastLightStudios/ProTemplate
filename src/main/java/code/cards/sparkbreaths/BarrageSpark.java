package code.cards.sparkbreaths;

import basemod.helpers.CardModifierManager;
import code.cardmodifiers.BreathModifier;
import code.cards.AbstractTwoSidedCard;
import code.powers.EmberPower;
import code.util.DragonUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;
import static code.util.Wiz.applyToSelf;
import static code.util.Wiz.atb;


public class BarrageSpark extends AbstractSparkBreathCard {
    public final static String ID = makeID("BarrageSpark");

    //Spark Stuff
    private final static int SPARK_DAMAGE = 1;
    private final static int SPARK_EMBER_GAIN = 1;
    private final static int UPGRADE_SPARK_EMBER_GAIN = 1;
    private final static int SPARK_NUMBER_OF_HITS = 3;
    private final static int UPGRADE_NUMBER_OF_HITS = 1;

    //Breath Stuff
    private final static int BREATH_DAMAGE = 1;
    private final static int BREATH_EMBER_MULTIPLIER = 1; //ember multiplier
    private final static int UPGRADE_BREATH_EMBER_MULTIPLIER = 0; //ember multiplier
    private final static int BREATH_NUMBER_OF_HITS = 4; //Number of hits
    private final static int UPGRADE_BREATH_NUMBER_OF_HITS = 1; //Number of hits increase

    public BarrageSpark(boolean needsPreview) {
        super(ID, CardType.ATTACK, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY, CardTarget.ALL_ENEMY, needsPreview);
        setDamage(SPARK_DAMAGE, BREATH_DAMAGE);
        setMagic(SPARK_EMBER_GAIN, BREATH_EMBER_MULTIPLIER);
        setSecondMagic(SPARK_NUMBER_OF_HITS, BREATH_NUMBER_OF_HITS);
        initializeSide();
        CardModifierManager.addModifier(this, new BreathModifier());
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
        } else { // Breath
            for (int i = 0; i < secondMagic; i++){
                atb(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.FIRE));
            }
            applyToSelf(new EmberPower(p, -DragonUtils.DEFAULT_EMBER_BREAKPOINT));
            incrementFirepowerPower();
        }
        checkEmberTrigger();
    }

    @Override
    public void upp() {
        upgradeMagicNumber(UPGRADE_SPARK_EMBER_GAIN, UPGRADE_BREATH_EMBER_MULTIPLIER);
        upgradeSecondMagic(UPGRADE_NUMBER_OF_HITS, UPGRADE_BREATH_NUMBER_OF_HITS);
    }
}