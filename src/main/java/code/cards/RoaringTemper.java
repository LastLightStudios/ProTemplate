package code.cards;

import basemod.helpers.TooltipInfo;
import code.cards.sparkbreaths.AbstractSparkBreathCard;
import code.powers.EmberPower;
import code.util.DragonUtils;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;
import java.util.List;

import static code.DragonCharacterFile.Enums.DRAGON_COLOR;
import static code.ModFile.makeID;
import static code.powers.EmberPower.getEmberBreakpoint;
import static code.util.Wiz.*;

public class RoaringTemper extends AbstractEasyCard {
    public final static String ID = makeID("RoaringTemper");


    public RoaringTemper() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF, DRAGON_COLOR);
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        int count = adp().hand.size() - 1;
        atb(new DiscardAction(p, p, count, false));
        if (upgraded){
            for (int i = 0; i < count; i++){
                AbstractCard c = returnTrulyRandomPrediCardInCombat(card -> (card instanceof AbstractSparkBreathCard && !card.hasTag(CardTags.HEALING))).makeCopy();
                c.upgrade();
                makeInHand(c);
            }
        } else {
            for (int i = 0; i < count; i++){
                makeInHand(returnTrulyRandomPrediCardInCombat(card -> (card instanceof AbstractSparkBreathCard && !card.hasTag(CardTags.HEALING))).makeCopy());
            }
        }
    }

    @Override
    public void applyPowers(){
        AbstractPower ember = adp().getPower(EmberPower.POWER_ID);
        if (ember != null) {
            if (ember.amount >= getEmberBreakpoint()){
                useBreathDesc();
            } else {
                useSparkDesc();
            }
        } else {
            useSparkDesc();
        }
    }

    public void upp() {
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }

    private void useSparkDesc(){
        if (upgraded){
            rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        } else {
            rawDescription = cardStrings.DESCRIPTION;
            initializeDescription();
        }
    }

    private void useBreathDesc(){
        if (upgraded){
            rawDescription = cardStrings.EXTENDED_DESCRIPTION[1];
        } else {
            rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];
        }

    }
}