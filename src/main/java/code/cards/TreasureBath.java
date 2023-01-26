package code.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.BaseMod;

import static code.DragonCharacterFile.Enums.DRAGON_COLOR;
import static code.ModFile.makeID;
import static code.util.DragonUtils.getRandomGem;
import static code.util.Wiz.*;

public class TreasureBath extends AbstractEasyCard {
    public final static String ID = makeID("TreasureBath");

    private final static int CARD_DRAW = 1;
    private final static int UPGRADE_CARD_DRAW = 1;
    private final static int BLOCK = 12;
    private final static int UPGRADE_BLOCK = 4;


    public TreasureBath() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, DRAGON_COLOR);
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = CARD_DRAW;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < (BaseMod.MAX_HAND_SIZE - adp().hand.group.size()); i++){
            makeInHand(getRandomGem().makeCopy());
        }
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_CARD_DRAW);
        upgradeBlock(UPGRADE_BLOCK);
    }
}