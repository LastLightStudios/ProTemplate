package code.cards;

import code.powers.PridePower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static code.ModFile.makeID;
import static code.util.DragonUtils.countRaresInDeck;
import static code.util.Wiz.adp;

public class PridefulScale extends AbstractEasyCard {
    public final static String ID = makeID("PridefulScale");

    private final static int BLOCK = 10;
    private final static int UPGRADE_BLOCK = 2;
    private final static int BLOCK_SCALAR = 2;
    private final static int UPGRADE_BLOCK_SCALAR = 1;
    private final static int PRIDE_SCALAR = 1;
    private final static int UPGRADE_PRIDE_SCALAR = 0;

    public PridefulScale() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = BLOCK_SCALAR;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mon){
        int realBaseBlock = baseBlock;
        baseBlock += magicNumber * countRaresInDeck();
        AbstractPower pride = adp().getPower(PridePower.POWER_ID);
        if (pride != null){
            baseBlock += secondMagic * pride.amount;
        }
        super.calculateCardDamage(mon);
        baseBlock = realBaseBlock;
        isBlockModified = (block != baseBlock);
    }

    @Override
    public void applyPowers(){
        int realBaseBlock = baseBlock;
        baseBlock += magicNumber * countRaresInDeck();
        AbstractPower pride = adp().getPower(PridePower.POWER_ID);
        if (pride != null){
            baseBlock += secondMagic * pride.amount;
        }
        super.applyPowers();
        baseBlock = realBaseBlock;
        isBlockModified = (block != baseBlock);
    }

    public void upp() {
        upgradeDamage(UPGRADE_BLOCK);
        upgradeMagicNumber(UPGRADE_BLOCK_SCALAR);
    }
}