package code.cards;

import code.util.DragonUtils;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;

import static code.DragonCharacterFile.Enums.DRAGON_COLOR;
import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class TreasureMind extends AbstractEasyCard {
    public final static String ID = makeID("TreasureMind");

    private final static int CARD_DRAW = 2;
    private final static int UPGRADE_CARD_DRAW = 0;
    private final static int GEMS = 1;
    private final static int UPGRADE_GEMS = 1;
    private final static int MAKE_IT_RAIN = 25;


    public TreasureMind() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, DRAGON_COLOR);
        baseMagicNumber = magicNumber = CARD_DRAW;
        baseSecondMagic = secondMagic = GEMS;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard gem;
        for (int i = 0; i < secondMagic; i++){
            gem = DragonUtils.returnTrulyRandomCardWithTagInCombat(DragonUtils.CustomTags.GEM).makeCopy();
            if (upgraded){
                gem.upgrade();
            }
            makeInHand(gem);
            AbstractDungeon.effectList.add(new RainingGoldEffect(MAKE_IT_RAIN, true));
        }
        atb(new DrawCardAction(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_CARD_DRAW);
        upgradeSecondMagic(UPGRADE_GEMS);
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}