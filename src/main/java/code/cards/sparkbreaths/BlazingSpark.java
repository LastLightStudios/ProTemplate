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

import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class BlazingSpark extends AbstractSparkBreathCard {
    public final static String ID = makeID("BlazingSpark");

    //Spark Stuff
    private final static int SPARK_DAMAGE = 1;
    private final static int SPARK_EMBER_GAIN = 1;

    //Breath Stuff
    private final static int BREATH_DAMAGE = 10;
    private final static int BREATH_EMBER_MULTIPLIER = 1;
    private final static int UPGRADE_BREATH_EMBER_MULTIPLIER = 1;

    public BlazingSpark(boolean needsPreview) {
        super(ID, CardType.ATTACK, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY, CardTarget.ALL_ENEMY, needsPreview);
        setDamage(SPARK_DAMAGE, BREATH_DAMAGE);
        setMagic(SPARK_EMBER_GAIN, BREATH_EMBER_MULTIPLIER);
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
            if(upgraded){
                atb(new DrawCardAction(1));
            }
        } else { // Breath
            allDmg(AbstractGameAction.AttackEffect.FIRE);
            atb(new RemoveSpecificPowerAction(p, p, EmberPower.POWER_ID));
        }
        checkEmberTrigger();
    }

    @Override
    public void upp() {
        upgradeMagicNumber(0, UPGRADE_BREATH_EMBER_MULTIPLIER);
        descriptionA = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}