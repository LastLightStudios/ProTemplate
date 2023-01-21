package code.cards;



import code.actions.SwapCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import code.powers.EmberPower;

import static code.DragonCharacterFile.Enums.DRAGON_COLOR;
import static code.ModFile.makeID;
import static code.util.Wiz.*;

import code.util.CustomTags;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class BlazingSpark extends AbstractSwappableCard {
    public final static String ID = makeID("BlazingSpark");

    public BlazingSpark() {
        this(null);
    }

    public BlazingSpark(AbstractSwappableCard linkedCard){
        super(ID,0, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY, DRAGON_COLOR);
        baseDamage = 1;
        baseMagicNumber = 1; // Amount of Spark gained
        magicNumber = baseMagicNumber;
        tags.add(CustomTags.SPARK);
        if (linkedCard == null) {
            setLinkedCard(new BlazingBreath(this));
        } else {
            setLinkedCard(linkedCard);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if(upgraded){
            atb(new DrawCardAction(1));
        }
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        // so this is in a wonky order b/c the whole use function happens before the actions are resolved
        AbstractPower ember = adp().getPower(EmberPower.POWER_ID);
        if (ember != null) {
            if (ember.amount + magicNumber >= EmberPower.getEmberBreakpoint()){
                atb(new SwapCardsAction(this, (AbstractSwappableCard)this.cardsToPreview, adp().limbo));
            }
        }
        applyToSelf(new EmberPower(p, magicNumber));
    }

    public void upp() {
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
    }
}