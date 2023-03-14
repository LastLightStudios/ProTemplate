package code.cards;

import code.util.DragonUtils;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.BaseMod;

import static code.DragonCharacterFile.Enums.DRAGON_COLOR;
import static code.ModFile.makeID;
import static code.util.DragonUtils.getRandomGem;
import static code.util.Wiz.*;

public class TreasureBath extends AbstractEasyCard {
    public final static String ID = makeID("TreasureBath");


    public TreasureBath() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, DRAGON_COLOR);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < (BaseMod.MAX_HAND_SIZE - adp().hand.group.size()); i++){
            makeInHand(DragonUtils.returnTrulyRandomCardWithTagInCombat(DragonUtils.CustomTags.GEM).makeCopy());
        }
    }

    public void upp() {
    }
}