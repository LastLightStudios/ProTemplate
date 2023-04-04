package code.cards;

import code.actions.ReduceDebuffsAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawReductionPower;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class ScourTheTrove extends AbstractEasyCard {
    public final static String ID = makeID("ScourTheTrove");

    private final static int CARD_DRAW = 4;
    private final static int UPGRADE_CARD_DRAW = 0;
    private final static int CARD_DRAW_PENALTY = 2; //this one is hard-coded as we've only got 2 magic numbers. I figured this is least likely to change
    private final static int SHED_VALUE = 1;

    public ScourTheTrove() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = CARD_DRAW;
        baseSecondMagic = secondMagic = SHED_VALUE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(magicNumber));
        applyToSelf(new DrawReductionPower(p, CARD_DRAW_PENALTY));
        if (upgraded){
            atb(new ReduceDebuffsAction(p, secondMagic));
        }
    }

    public void upp() {
        //upgradeMagicNumber(UPGRADE_CARD_DRAW);
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}