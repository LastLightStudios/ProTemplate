package code.cards;

import code.actions.HoardCardAction;
import code.actions.ShedAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.DragonCharacterFile.Enums.DRAGON_COLOR;
import static code.ModFile.makeID;
import static code.util.Wiz.atb;

public class RenovateNest extends AbstractEasyCard {
    public final static String ID = makeID("RenovateNest");

    private final static int CARDS_HOARDED = 1;
    private final static int ENERGY_GAIN = 1;
    private final static int SHED_VALUE = 1; // this is used as 2nd magic instead of energy gain b/c the cardstrings don't use numerals to represent energy [E]


    public RenovateNest() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, DRAGON_COLOR);
        baseMagicNumber = magicNumber = CARDS_HOARDED;
        baseSecondMagic = secondMagic = SHED_VALUE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new HoardCardAction(magicNumber));
        atb(new GainEnergyAction(ENERGY_GAIN));
        if (upgraded){
            atb(new ShedAction(p, secondMagic));
        }
    }

    public void upp() {
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initialize();
    }
}