package code.cards.sparkbreaths;

import basemod.helpers.CardModifierManager;
import code.cardmodifiers.BreathModifier;
import code.cards.AbstractTwoSidedCard;
import code.powers.EmberPower;
import code.util.DragonUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class CrystallineSpark extends AbstractSparkBreathCard {
    public final static String ID = makeID("CrystallineSpark");

    //Spark Stuff
    private final static int SPARK_DAMAGE = 1;
    private final static int UPGRADE_SPARK_DAMAGE = 0;
    private final static int SPARK_EMBER_GAIN = 1;
    private final static int SPARK_GEM_GAIN = 1;

    //Breath Stuff
    private final static int BREATH_DAMAGE = 10;
    private final static int UPGRADE_BREATH_DAMAGE = 5;
    private final static int BREATH_EMBER_MULTIPLIER = 1;
    private final static int BREATH_GEM_GAIN = 1;

    public CrystallineSpark(boolean needsPreview) {
        super(ID, CardType.ATTACK, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY, CardTarget.ALL_ENEMY, needsPreview);
        setDamage(SPARK_DAMAGE, BREATH_DAMAGE);
        setMagic(SPARK_EMBER_GAIN, BREATH_EMBER_MULTIPLIER);
        setSecondMagic(SPARK_GEM_GAIN, BREATH_GEM_GAIN);
        initializeSide();
        affectSecondMagic = true;
        CardModifierManager.addModifier(this, new BreathModifier());
    }

    public CrystallineSpark() {
        this(true);
    }

    @Override
    protected AbstractTwoSidedCard noPreviewCopy(){
        return new CrystallineSpark(false);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (isFront) {
            dmg(m, AbstractGameAction.AttackEffect.FIRE);
            applyToSelf(new EmberPower(p, magicNumber));
            makeInHand(DragonUtils.returnTrulyRandomCardWithTagInCombat(DragonUtils.CustomTags.GEM).makeCopy());
            if(upgraded){
                atb(new DrawCardAction(1));
            }
        } else { // Breath
            allDmg(AbstractGameAction.AttackEffect.FIRE);
            for (int i = 0; i < secondMagic; i++) {
                makeInHand(DragonUtils.returnTrulyRandomCardWithTagInCombat(DragonUtils.CustomTags.GEM).makeCopy());
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
        upgradeDamage(UPGRADE_SPARK_DAMAGE, UPGRADE_BREATH_DAMAGE);
        descriptionA = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}