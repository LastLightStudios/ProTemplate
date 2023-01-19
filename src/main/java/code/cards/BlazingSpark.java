package code.cards;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import code.powers.EmberPower;

import static code.ModFile.makeID;
import static code.util.Wiz.applyToSelf;
import static code.util.Wiz.atb;

public class BlazingSpark extends AbstractSparkCard {
    public final static String ID = makeID("BlazingSpark");
    public final static String BreathID = makeID("BlazingBreath");

    public BlazingSpark() {
        super(ID, BreathID, 0, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        baseDamage = 1;
        baseMagicNumber = 1; // Amount of Spark gained
        magicNumber = baseMagicNumber;
        cardsToPreview = new BlazingBreath();
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