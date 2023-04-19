package code.cardmodifiers;

import basemod.abstracts.AbstractCardModifier;
import code.cards.sparkbreaths.AbstractSparkBreathCard;
import code.powers.EmberPower;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static code.util.Wiz.*;

public class BreathModifier extends AbstractCardModifier {

    @Override
    public boolean isInherent(AbstractCard card) {
        return true;
    }


    @Override
    public float modifyDamage(float damage, DamageInfo.DamageType type, AbstractCard card, AbstractMonster target) {
        if (!((AbstractSparkBreathCard)card).isFront()){
            AbstractPower ember = adp().getPower(EmberPower.POWER_ID);
            if (ember == null) {
                return damage;
            } else {
                return damage + (card.magicNumber * ember.amount);
            }
        } else {
            return damage;
        }
    }

    public void onApplyPowers(AbstractCard card) {
        AbstractSparkBreathCard c = (AbstractSparkBreathCard) card;
        if (!c.isFront() && c.isAffectSecondMagic()){
            AbstractPower ember = adp().getPower(EmberPower.POWER_ID);
            if (ember != null) {
                c.secondMagic = c.baseSecondMagic + (c.magicNumber * ember.amount);
                c.isSecondMagicModified = (c.secondMagic != c.baseSecondMagic);
            }
        }
    }

    public void onCalculateCardDamage(AbstractCard card, AbstractMonster mo) {
        AbstractSparkBreathCard c = (AbstractSparkBreathCard) card;
        if (!c.isFront() && c.isAffectSecondMagic()){
            AbstractPower ember = adp().getPower(EmberPower.POWER_ID);
            if (ember != null) {
                c.secondMagic = c.baseSecondMagic + (c.magicNumber * ember.amount);
                c.isSecondMagicModified = (c.secondMagic != c.baseSecondMagic);
            }
        }
    }

    @Override
    public boolean shouldApply(AbstractCard card) {
        return card instanceof AbstractSparkBreathCard;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new BreathModifier();
    }
}