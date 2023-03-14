package code.cards;

import code.util.DragonUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;

import static code.ModFile.makeID;
import static code.util.Wiz.*;
import static code.util.DragonUtils.getRandomGem;

public class TreasureClaw extends AbstractEasyCard {
    public final static String ID = makeID("TreasureClaw");

    private final static int DAMAGE = 8;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int MAKE_IT_RAIN = 50;

    public TreasureClaw() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        makeInHand(DragonUtils.returnTrulyRandomCardWithTagInCombat(DragonUtils.CustomTags.GEM).makeCopy());
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        AbstractDungeon.effectList.add(new RainingGoldEffect(MAKE_IT_RAIN, false));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}