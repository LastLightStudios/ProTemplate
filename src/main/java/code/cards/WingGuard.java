package code.cards;

import code.powers.EmberPower;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.DragonCharacterFile.Enums.DRAGON_COLOR;
import static code.ModFile.makeID;
import static code.util.Wiz.applyToSelf;
import static code.util.Wiz.atb;

public class WingGuard extends AbstractEasyCard {
    public final static String ID = makeID("WingGuard");

    private final static int CARD_DRAW = 1;
    private final static int UPGRADE_CARD_DRAW = 1;
    private final static int BLOCK = 12;
    private final static int UPGRADE_BLOCK = 4;


    public WingGuard() {
        super(ID, 2, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, DRAGON_COLOR);
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = CARD_DRAW;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(magicNumber));
        blck();
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_CARD_DRAW);
        upgradeBlock(UPGRADE_BLOCK);
    }
}