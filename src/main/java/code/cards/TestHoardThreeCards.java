package code.cards;

import code.actions.ChooseHoardCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.DragonCharacterFile.Enums.DRAGON_COLOR;
import static code.ModFile.makeID;
import static code.util.Wiz.atb;

public class TestHoardThreeCards extends AbstractEasyCard {
    public final static String ID = makeID("TestHoardThreeCards");

    private final static int HOARD_CARDS = 3;


    public TestHoardThreeCards() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, DRAGON_COLOR);
        baseMagicNumber = magicNumber = HOARD_CARDS;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ChooseHoardCardAction(p, magicNumber));
    }

    public void upp() {
    }
}