package code.cards;

import code.powers.PreparingToFlyPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.DragonCharacterFile.Enums.DRAGON_COLOR;
import static code.ModFile.makeID;
import static code.util.Wiz.applyToSelf;

public class Fly extends AbstractEasyCard {
    public final static String ID = makeID("Fly");

    private final static int FLY_GAIN = 1;
    private final static int UPGRADED_COST = 0;


    public Fly() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, DRAGON_COLOR);
        baseMagicNumber = magicNumber = FLY_GAIN;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new PreparingToFlyPower(p, magicNumber));
    }
    public void upp() {
        upgradeBaseCost(UPGRADED_COST);
    }
}