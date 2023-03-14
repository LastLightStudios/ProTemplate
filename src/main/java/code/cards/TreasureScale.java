package code.cards;

import code.util.DragonUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;

import static code.ModFile.makeID;
import static code.util.DragonUtils.getRandomGem;
import static code.util.Wiz.makeInHand;

public class TreasureScale extends AbstractEasyCard {
    public final static String ID = makeID("TreasureScale");

    private final static int BLOCK = 7;
    private final static int UPGRADE_BLOCK = 3;
    private final static int MAKE_IT_RAIN = 50;

    public TreasureScale() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = BLOCK;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        makeInHand(DragonUtils.returnTrulyRandomCardWithTagInCombat(DragonUtils.CustomTags.GEM).makeCopy());
        AbstractDungeon.effectList.add(new RainingGoldEffect(MAKE_IT_RAIN, true));
    }

    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
    }
}