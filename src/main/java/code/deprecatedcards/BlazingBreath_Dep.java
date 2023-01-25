package code.deprecatedcards;


import code.cards.AbstractSwappableCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import code.powers.EmberPower;


import static code.DragonCharacterFile.Enums.DRAGON_COLOR;
import static code.ModFile.makeID;
import code.util.DragonUtils.CustomTags;
import static code.util.Wiz.*;

public class BlazingBreath_Dep extends AbstractSwappableCard {
    public final static String ID = makeID("BlazingBreath");

    public BlazingBreath_Dep() {
        this(null);
    }

    public BlazingBreath_Dep(AbstractSwappableCard linkedCard) {
        super(ID, 2, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ALL_ENEMY, DRAGON_COLOR);
        baseDamage = 15;
        baseMagicNumber = 1; // Increases dmg by this amount per Spark
        magicNumber = baseMagicNumber;
        isMultiDamage = true;
        tags.add(CustomTags.BREATH);
        if (linkedCard == null) {
            setLinkedCard(new BlazingSpark_Dep(this));
        } else {
            setLinkedCard(linkedCard);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.FIRE);
        atb(new RemoveSpecificPowerAction(p, p, EmberPower.POWER_ID));
    }

    @Override
    public void calculateCardDamage(AbstractMonster m){
        AbstractPower ember = adp().getPower(EmberPower.POWER_ID);
        if (ember != null){ //idk how tf it can ever not be null but
            int realBaseDamage = baseDamage; //temp store realBaseDamage b/c baseDamage is used in card damage calculations
            baseDamage = baseDamage + (magicNumber * ember.amount);
            super.calculateCardDamage(m);
            baseDamage = realBaseDamage; //restore the realBaseDamage
            isDamageModified = (damage != baseDamage);
        } else {
            super.calculateCardDamage(m);
        }
    }

    @Override
    public void applyPowers(){
        AbstractPower ember = adp().getPower(EmberPower.POWER_ID);
        if (ember != null){ //idk how tf it can ever not be null but
            int realBaseDamage = baseDamage; //temp store realBaseDamage b/c baseDamage is used in card damage calculations
            baseDamage = baseDamage + (magicNumber * ember.amount);
            super.applyPowers();
            baseDamage = realBaseDamage; //restore the realBaseDamage
            isDamageModified = (damage != baseDamage);
        } else {
            super.applyPowers();
        }
    }

    public void upp() {
        upgradeMagicNumber(1);
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
    }
}