package code.cards;

import code.actions.ShedAction;
import code.powers.CauterizePower;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class Soar extends AbstractEasyCard {
    public final static String ID = makeID("Soar");

    private final static int CARD_DRAW = 3;
    private final static int UPGRADE_CARD_DRAW = 1;
    private final static int SHED_VALUE = 1;
    private final static int UPGRADE_SHED_VALUE = 1;

    public Soar() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = CARD_DRAW;
        baseSecondMagic = secondMagic = SHED_VALUE;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(magicNumber));
        atb(new ShedAction(p, secondMagic));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_CARD_DRAW);
        upgradeSecondMagic(UPGRADE_SHED_VALUE);
    }
}