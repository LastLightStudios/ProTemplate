package code.cards;

import code.powers.EmberPower;
import code.util.DragonUtils;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.DragonCharacterFile.Enums.DRAGON_COLOR;
import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class BurningTemper extends AbstractEasyCard {
    public final static String ID = makeID("BurningTemper");


    public BurningTemper() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF, DRAGON_COLOR);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int count = adp().hand.size() - 1;
        atb(new DiscardAction(p, p, count, false));
        if (upgraded){
            for (int i = 0; i < count; i++){
                AbstractCard c = DragonUtils.returnTrulyRandomCardWithTagInCombat(DragonUtils.CustomTags.SPARK).makeCopy();
                c.upgrade();
                makeInHand(c);
            }
        } else {
            for (int i = 0; i < count; i++){
                makeInHand(DragonUtils.returnTrulyRandomCardWithTagInCombat(DragonUtils.CustomTags.SPARK).makeCopy());
            }
        }
    }

    public void upp() {
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}