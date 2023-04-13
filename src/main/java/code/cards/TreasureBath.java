package code.cards;

import code.actions.TreasureBathAction;
import code.util.DragonUtils;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.BaseMod;

import static code.DragonCharacterFile.Enums.DRAGON_COLOR;
import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class TreasureBath extends AbstractEasyCard {
    public final static String ID = makeID("TreasureBath");
    private final static int UPGRADED_COST = 0;
    private final static int FILL_UNTIL_HAND_SIZE = 6;
    private final static int UPGRADE_HAND_SIZE = 1;

    public TreasureBath() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, DRAGON_COLOR);
        baseMagicNumber = magicNumber = FILL_UNTIL_HAND_SIZE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new TreasureBathAction(p, magicNumber));
    }

    public void upp() {
        //upgradeBaseCost(UPGRADED_COST);
        upgradeMagicNumber(UPGRADE_HAND_SIZE);
    }
}