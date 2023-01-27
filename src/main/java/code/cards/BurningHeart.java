package code.cards;

import code.powers.EmberPower;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.DragonCharacterFile.Enums.DRAGON_COLOR;
import static code.ModFile.makeID;
import static code.util.Wiz.applyToSelf;
import static code.util.Wiz.atb;

public class BurningHeart extends AbstractEasyCard {
    public final static String ID = makeID("BurningHeart");

    private final static int CARD_DRAW = 2;
    private final static int UPGRADE_CARD_DRAW = 1;
    private final static int EMBER_GAIN = 2;
    private final static int UPGRADE_EMBER_GAIN = 1;


    public BurningHeart() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, DRAGON_COLOR);
        baseMagicNumber = magicNumber = CARD_DRAW;
        baseSecondMagic = secondMagic = EMBER_GAIN;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new EmberPower(p, secondMagic));
        atb(new DrawCardAction(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_CARD_DRAW);
        upgradeSecondMagic(UPGRADE_EMBER_GAIN);
    }
}