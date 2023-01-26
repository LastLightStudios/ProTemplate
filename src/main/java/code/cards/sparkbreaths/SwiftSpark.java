package code.cards.sparkbreaths;

import code.cards.AbstractSparkBreathCard;
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

public class SwiftSpark extends AbstractSparkBreathCard {
    public final static String ID = makeID("SwiftSpark");

    private final static int DAMAGE_A = 1;
    private final static int DAMAGE_B = 15;

    private final static int MAGIC_NUMBER_A = 1; //spark gain
    private final static int MAGIC_NUMBER_B = 1; //spark multiplier
    private final static int SECOND_MAGIC_NUMBER_A = 1; //cards drawn
    private final static int SECOND_MAGIC_NUMBER_B = 2; //cards drawn
    private final static int UPGRADE_SECOND_MAGIC_NUMBER_A = 1; //cards drawn increase
    private final static int UPGRADE_SECOND_MAGIC_NUMBER_B = 1; //cards drawn increase

    public SwiftSpark(boolean needsPreview) {
        super(ID, CardType.ATTACK, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY, CardTarget.ALL_ENEMY, needsPreview);
        setDamage(DAMAGE_A, DAMAGE_B);
        setMagic(MAGIC_NUMBER_A, MAGIC_NUMBER_B);
        setSecondMagic(SECOND_MAGIC_NUMBER_A, SECOND_MAGIC_NUMBER_B);

        initializeSide();
    }

    public SwiftSpark() {
        this(true);
    }

    @Override
    protected AbstractTwoSidedCard noPreviewCopy(){
        return new SwiftSpark(false);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (isFront) {
            atb(new DrawCardAction(secondMagic));
            dmg(m, AbstractGameAction.AttackEffect.FIRE);
            applyToSelf(new EmberPower(p, magicNumber));
        } else { // Breath
            atb(new DrawCardAction(secondMagic));
            allDmg(AbstractGameAction.AttackEffect.FIRE);
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
        upgradeSecondMagicNumber(UPGRADE_SECOND_MAGIC_NUMBER_A, UPGRADE_SECOND_MAGIC_NUMBER_B);
        descriptionA = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}