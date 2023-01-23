package code.cards;

import basemod.AutoAdd;
import code.actions.TransformTwoSidedCardAction;
import code.powers.EmberPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static code.ModFile.makeID;
import static code.powers.EmberPower.getEmberBreakpoint;
import static code.util.Wiz.*;


@AutoAdd.Ignore
public class BarrageSpark extends AbstractTwoSidedCard {
    public final static String ID = makeID("BlazingSpark");

    private final static int COST_A = 0;
    private final static int COST_B = 2;

    private final static int DAMAGE_A = 1;
    private final static int DAMAGE_B = 1;

    private final static int MAGIC_NUMBER_A = 1; //spark gain
    private final static int MAGIC_NUMBER_B = 1; //spark multiplier
    private final static int SECOND_MAGIC_NUMBER_A = 5; //Number of hits
    private final static int SECOND_MAGIC_NUMBER_B = 5; //Number of hits
    private final static int UPGRADE_MAGIC_NUMBER_B = 1; //Number of hits increase

    public BarrageSpark(boolean needsPreview) {
        super(ID,  COST_A, COST_B, CardType.ATTACK, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY, CardTarget.ALL_ENEMY, needsPreview);
        setDamage(DAMAGE_A, DAMAGE_B);
        setMagic(MAGIC_NUMBER_A, MAGIC_NUMBER_B);
        setSecondMagic(SECOND_MAGIC_NUMBER_A, SECOND_MAGIC_NUMBER_B);

        this.changeSide(false);
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
            if(upgraded){
                atb(new DrawCardAction(1));
            }
            for (int i = 0; i < secondMagic; i++){
                atb(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.FIRE));
            }
            applyToSelf(new EmberPower(p, magicNumber));
            AbstractPower ember = adp().getPower(EmberPower.POWER_ID);
            if (ember != null){
                if (ember.amount + magicNumber >= getEmberBreakpoint()){
                    atb(new TransformTwoSidedCardAction(this, true));
                }
            }
        } else { // Breath
            //TODO: this may require a new action since the base dmg changes
            for (int i = 0; i < secondMagic; i++){
                atb(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.FIRE));
            }
            atb(new RemoveSpecificPowerAction(p, p, EmberPower.POWER_ID));
            atb(new TransformTwoSidedCardAction(this, false));
        }
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
        upgradeMagicNumber(0, UPGRADE_MAGIC_NUMBER_B);
        descriptionA = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}