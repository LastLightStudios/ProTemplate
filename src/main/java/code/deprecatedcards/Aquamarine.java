package code.deprecatedcards;

import code.cards.gems.AbstractGemCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;

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
        hoardThisGem();
    }

    public void upp() {
        upgradeBlock(UPGRADE_BLOCK_AMOUNT);
    }
}