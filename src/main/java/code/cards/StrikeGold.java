package code.cards;

import code.util.DragonUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ReboundPower;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class StrikeGold extends AbstractEasyCard {
    public final static String ID = makeID("StrikeGold");

    private final static int DAMAGE = 9;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int MAKE_IT_RAIN = 50;

    public StrikeGold() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        tags.add(AbstractCard.CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        AbstractDungeon.effectList.add(new RainingGoldEffect(MAKE_IT_RAIN, false));
        AbstractCard gem = DragonUtils.returnTrulyRandomCardWithTagInCombat(DragonUtils.CustomTags.GEM).makeCopy();
        if (upgraded){
            gem.upgrade();
        }
        makeInHand(gem);
        applyToSelf(new ReboundPower(p));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}