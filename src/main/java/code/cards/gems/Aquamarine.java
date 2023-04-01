package code.cards.gems;

import code.actions.HoardThisCardAction;
import code.powers.PridePower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;
import static code.util.Wiz.applyToSelf;
import static code.util.Wiz.atb;

public class Aquamarine extends AbstractGemCard {
    public final static String ID = makeID("Aquamarine");


    private final static int BLOCK_AMOUNT = 3;
    private final static int UPGRADE_BLOCK_AMOUNT = 2;

    public Aquamarine() {
        super(ID, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        baseBlock = BLOCK_AMOUNT;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToSelf(new PridePower(p, 1));
    }

    public void upp() {
        upgradeBlock(UPGRADE_BLOCK_AMOUNT);
    }
}