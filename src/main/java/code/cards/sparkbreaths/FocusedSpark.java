package code.cards.sparkbreaths;

import code.cards.AbstractTwoSidedCard;
import code.powers.EmberPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static code.ModFile.makeID;
import static code.util.Wiz.*;


public class FocusedSpark extends AbstractSparkBreathCard {
    public final static String ID = makeID("FocusedSpark");

    private final static int DAMAGE_A = 1;
    private final static int DAMAGE_B = 10;

    private final static int MAGIC_NUMBER_A = 1; //spark gain
    private final static int MAGIC_NUMBER_B = 1; //spark multiplier

    public FocusedSpark(boolean needsPreview) {
        super(ID, CardType.ATTACK, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY, CardTarget.ENEMY, needsPreview);
        setDamage(DAMAGE_A, DAMAGE_B);
        setMagic(MAGIC_NUMBER_A, MAGIC_NUMBER_B);

        initializeSide();
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
            if(upgraded){
                atb(new DrawCardAction(1));
            }
            dmg(m, AbstractGameAction.AttackEffect.FIRE);
            applyToSelf(new EmberPower(p, magicNumber));
        } else { // Breath
            for (AbstractMonster monster : getEnemies()){
                dmg(m, AbstractGameAction.AttackEffect.FIRE);
            }
            if (getEnemies().size() > 2){
                atb(new SFXAction("ATTACK_BOWLING"));
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
                baseDamage = baseDamage + ember.amount;
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
                baseDamage = baseDamage + ember.amount;
                super.applyPowers();
                baseDamage = realBaseDamage; //restore the realBaseDamage
                isDamageModified = (damage != baseDamage);
            }
        }
    }

    @Override
    public void upp() {
        descriptionA = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}