package code.deprecatedcards;



import code.cards.deprecated.AbstractSwappableCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import code.powers.EmberPower;

import static code.DragonCharacterFile.Enums.DRAGON_COLOR;
import static code.ModFile.makeID;
import static code.util.Wiz.*;

import code.util.DragonUtils.CustomTags;

public class BlazingSpark_Dep extends AbstractSwappableCard {
    public final static String ID = makeID("BlazingSpark");

    public BlazingSpark_Dep() {
        this(null);
    }

    public BlazingSpark_Dep(AbstractSwappableCard linkedCard){
        super(ID,0, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY, DRAGON_COLOR);
        baseDamage = 1;
        baseMagicNumber = 1; // Amount of Spark gained
        magicNumber = baseMagicNumber;
        tags.add(CustomTags.SPARK);
        if (linkedCard == null) {
            setLinkedCard(new BlazingBreath_Dep(this));
        } else {
            setLinkedCard(linkedCard);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if(upgraded){
            atb(new DrawCardAction(1));
        }
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        applyToSelf(new EmberPower(p, magicNumber));
    }

    public void upp() {
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
    }
}