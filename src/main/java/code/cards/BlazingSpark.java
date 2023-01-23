package code.cards;

import code.actions.TransformTwoSidedCardAction;
import code.powers.EmberPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static code.powers.EmberPower.getEmberBreakpoint;
import static code.ModFile.makeID;
import static code.util.Wiz.*;
import static code.util.Wiz.adp;

import java.util.ArrayList;
import java.util.List;

public class BlazingSpark extends AbstractTwoSidedCard {
    public final static String ID = makeID("BlazingSpark");

    private final static int COST_A = 0;
    private final static int COST_B = 2;

    private final static int DAMAGE_A = 1;
    private final static int DAMAGE_B = 15;

    private final static int MAGIC_NUMBER_A = 1; //spark gain
    private final static int MAGIC_NUMBER_B = 1; //spark multiplier
    private final static int UPGRADE_MAGIC_NUMBER_B = 1; //spark multiplier increase

    public BlazingSpark(boolean needsPreview) {
        super(ID,  COST_A, COST_B, CardType.ATTACK, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY, CardTarget.ALL_ENEMY, needsPreview);
        //altCardStrings = CardCrawlGame.languagePack.getCardStrings(altID);
        baseDamageA = DAMAGE_A;
        baseDamageB = DAMAGE_B;
        baseMagicNumberA = MAGIC_NUMBER_A;
        baseMagicNumberB = MAGIC_NUMBER_B;

        this.changeSide(false);

    }

    public BlazingSpark() {
        this(true);
    }

    @Override
    protected AbstractTwoSidedCard noPreviewCopy(){
        return new BlazingSpark(false);
    }

    @Override
    public List<String> getCardDescriptors(){
        ArrayList<String> retVal = new ArrayList<>();
        if (isFront){
            retVal.add(uiStrings.TEXT[0]);
        } else {
            retVal.add(uiStrings.TEXT[1]);
        }
        return retVal;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (isFront) {
            if(upgraded){
                atb(new DrawCardAction(1));
            }
            dmg(m, AbstractGameAction.AttackEffect.FIRE);
            applyToSelf(new EmberPower(p, magicNumber));
            AbstractPower ember = adp().getPower(EmberPower.POWER_ID);
            if (ember != null){
                if (ember.amount + magicNumber >= getEmberBreakpoint()){
                    atb(new TransformTwoSidedCardAction(this, true));
                }
            }
        } else { // Breath
            dmg(m, AbstractGameAction.AttackEffect.FIRE);
            allDmg(AbstractGameAction.AttackEffect.FIRE);
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

    public void upp() {
        baseMagicNumberB += UPGRADE_MAGIC_NUMBER_B;
        descriptionA = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}