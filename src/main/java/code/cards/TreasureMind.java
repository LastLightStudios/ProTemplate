package code.cards;

import code.util.DragonUtils;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;

import static code.DragonCharacterFile.Enums.DRAGON_COLOR;
import static code.ModFile.makeID;
import static code.util.DragonUtils.getRandomGem;
import static code.util.Wiz.atb;
import static code.util.Wiz.makeInHand;

public class TreasureMind extends AbstractEasyCard {
    public final static String ID = makeID("TreasureMind");

    private final static int CARD_DRAW = 2;
    // private final static int UPGRADE_CARD_DRAW = 1;
    private final static int GEMS = 1;
    private final static int UPGRADE_GEMS = 1;
    private final static int MAKE_IT_RAIN = 25;


    public TreasureMind() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, DRAGON_COLOR);
        baseMagicNumber = magicNumber = CARD_DRAW;
        baseSecondMagic = secondMagic = GEMS;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(magicNumber));
        for (int i = 0; i < secondMagic; i++){
            makeInHand(DragonUtils.returnTrulyRandomCardWithTagInCombat(DragonUtils.CustomTags.GEM).makeCopy());
            AbstractDungeon.effectList.add(new RainingGoldEffect(MAKE_IT_RAIN, false));
        }
    }

    public void upp() {
        upgradeSecondMagic(UPGRADE_GEMS);
    }
}