package code.cards;

import code.powers.EmberPower;
import code.util.CustomTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static code.ModFile.makeID;
import static code.util.Wiz.*;
import static code.util.Wiz.adp;

public class BlazingTest extends AbstractTwoSidedCard {
    public final static String ID = makeID("BlazingTest");
    public final static String altID = makeID("BlazingTest2");

    protected final CardStrings altCardStrings;

    public boolean isFrontSide;

    public BlazingTest(boolean _isFrontSide, boolean needsPreview) {
        super(ID,  0, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        altCardStrings = CardCrawlGame.languagePack.getCardStrings(altID);
        tags.add(CustomTags.DOUBLE_SIDED);
        if (_isFrontSide){
            isFrontSide = true;
            changeToFront();
            if (needsPreview){
                cardsToPreview = new BlazingTest(false, false);
            }
        } else {
            isFrontSide = false;
            changeToBack();
            if (needsPreview){
                cardsToPreview = new BlazingTest(true, false);
            }
        }
    }

    public BlazingTest() {
        this(true, true);
    }

    @Override
    public void changeToFront(){
        if (!isFrontSide){
            target = CardTarget.ENEMY;
            cost = 0;
            baseDamage = 1;
            baseMagicNumber = 1; // Amount of Spark gained
            magicNumber = baseMagicNumber;
            isMultiDamage = false;

            if (upgraded){
                rawDescription = cardStrings.UPGRADE_DESCRIPTION;
                name = cardStrings.NAME + "+";
            } else {
                rawDescription = cardStrings.DESCRIPTION;
                name = originalName = cardStrings.NAME;
            }
            initializeTitle();
            initializeDescription();
            isFrontSide = true;
        }
    }

    @Override
    public void changeToBack(){
        if (isFrontSide){
            target = CardTarget.ALL_ENEMY;
            cost = 2;
            baseDamage = 15;
            if (upgraded) {
                baseSecondMagic = 2; // Increases dmg by this amount per Spark
            } else {
                baseSecondMagic = 1; // Increases dmg by this amount per Spark
            }
            secondMagic = baseSecondMagic;
            isMultiDamage = true;

            rawDescription = altCardStrings.DESCRIPTION;
            if (upgraded){
                name = altCardStrings.NAME + "+";
            } else {
                name = originalName = altCardStrings.NAME;
            }
            initializeTitle();
            initializeDescription();
            isFrontSide = false;
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (isFrontSide) {
            if(upgraded){
                atb(new DrawCardAction(1));
            }
            dmg(m, AbstractGameAction.AttackEffect.FIRE);
            applyToSelf(new EmberPower(p, magicNumber));
        } else { // Breath
            allDmg(AbstractGameAction.AttackEffect.FIRE);
            atb(new RemoveSpecificPowerAction(p, p, EmberPower.POWER_ID));
        }
    }

    @Override
    public void calculateCardDamage(AbstractMonster m){
        if (isFrontSide){
            AbstractPower ember = adp().getPower(EmberPower.POWER_ID);
            if (ember != null){ //idk how tf it can ever not be null but
                int realBaseDamage = baseDamage; //temp store realBaseDamage b/c baseDamage is used in card damage calculations
                baseDamage = baseDamage + (secondMagic * ember.amount);
                super.calculateCardDamage(m);
                baseDamage = realBaseDamage; //restore the realBaseDamage
                isDamageModified = (damage != baseDamage);
            }
        } else {
            super.calculateCardDamage(m);
        }
    }

    @Override
    public void applyPowers() {
        if (isFrontSide) {
            super.applyPowers();
        } else {
            AbstractPower ember = adp().getPower(EmberPower.POWER_ID);
            if (ember != null) { //idk how tf it can ever not be null but
                int realBaseDamage = baseDamage; //temp store realBaseDamage b/c baseDamage is used in card damage calculations
                baseDamage = baseDamage + (secondMagic * ember.amount);
                super.applyPowers();
                baseDamage = realBaseDamage; //restore the realBaseDamage
                isDamageModified = (damage != baseDamage);
            }
        }
    }

    public void upp() {
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
    }
}