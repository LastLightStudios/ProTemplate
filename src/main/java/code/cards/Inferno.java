package code.cards;

import code.actions.PlayAllSparksAction;
import code.cards.AbstractEasyCard;
import code.cards.InfernalBreath;
import code.powers.EmberPower;
import code.util.DragonUtils;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

import static code.powers.EmberPower.getEmberBreakpoint;
import static code.DragonCharacterFile.Enums.DRAGON_COLOR;
import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class Inferno extends AbstractEasyCard {
    public final static String ID = makeID("Inferno");


    public Inferno() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY, DRAGON_COLOR);
        exhaust = true;
        cardsToPreview = new InfernalBreath();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new PlayAllSparksAction(m));
        if (upgraded){
            AbstractCard c = (new InfernalBreath());
            c.upgrade();
            makeInHand(c);
        } else {
            makeInHand(new InfernalBreath());
        }
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m){
        boolean canUse = super.canUse(p, m);
        if (!canUse){
            return false;
        }
        AbstractPower ember = adp().getPower(EmberPower.POWER_ID);
        if (ember != null){
            if (ember.amount >= getEmberBreakpoint()){
                canUse = false;
                cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
            }
        }
        return canUse;
    }

    public void upp() {
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        cardsToPreview.upgrade();
        initializeDescription();
    }
}