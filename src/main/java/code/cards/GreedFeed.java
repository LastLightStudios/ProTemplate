package code.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.unique.FeedAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;

import static code.ModFile.makeID;
import static code.util.Wiz.atb;
import static code.util.Wiz.vfx;

public class GreedFeed extends AbstractEasyCard {
    public final static String ID = makeID("GreedFeed");

    private final static int DAMAGE = 5;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int MAX_HP_INCREASE = 3;
    private final static int UPGRADE_MAX_HP_INCREASE = 1;

    public GreedFeed() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAX_HP_INCREASE;
        isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        vfx(new BiteEffect(m.hb.cX, m.hb.cY - 40.0F * Settings.scale, Color.SCARLET.cpy()), 0.3F);
        atb(new FeedAction(m, new DamageInfo(p, damage, damageTypeForTurn), magicNumber));
        updateCost(1);
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeMagicNumber(UPGRADE_MAX_HP_INCREASE);
    }
}