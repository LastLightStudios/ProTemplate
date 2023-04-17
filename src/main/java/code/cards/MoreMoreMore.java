package code.cards;

import code.powers.HoardingInstinctPower;
import code.powers.MoreMoreMorePower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;
import static code.util.Wiz.applyToSelf;

public class MoreMoreMore extends AbstractEasyCard {
    public final static String ID = makeID("MoreMoreMore");

    private final static int CARD_DRAW = 1;
    //private final static int UPGRADE_CARD_DRAW = 1;
    private final static int UPGRADE_COST = 1;

    public MoreMoreMore() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = CARD_DRAW;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new MoreMoreMorePower(p, magicNumber));
    }

    public void upp() {
        upgradeBaseCost(UPGRADE_COST);
    }
}