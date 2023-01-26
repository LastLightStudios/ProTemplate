package code.cards;

import code.powers.TakingFlightPower;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.DragonCharacterFile.Enums.DRAGON_COLOR;
import static code.ModFile.makeID;
import static code.util.Wiz.applyToSelf;
import static code.util.Wiz.atb;

public class TakeFlight extends AbstractEasyCard {
    public final static String ID = makeID("Take Flight");

    private final static int BUFFER_GAIN = 1;
    private final static int UPGRADE_BUFFER_GAIN = 1;


    public TakeFlight() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, DRAGON_COLOR);
        baseMagicNumber = magicNumber = BUFFER_GAIN;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new TakingFlightPower(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_BUFFER_GAIN);
    }
}