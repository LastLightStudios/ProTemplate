package code.cards;



import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import code.powers.EmberPower;

import static code.DragonCharacterFile.Enums.DRAGON_COLOR;
import static code.ModFile.makeID;
import code.util.CustomTags;
import static code.util.Wiz.applyToSelf;
import static code.util.Wiz.atb;

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
        applyToSelf(new EmberPower(p, baseMagicNumber));
    }

    public void upp() {
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
    }
}