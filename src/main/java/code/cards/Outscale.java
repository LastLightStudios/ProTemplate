package code.cards;

import code.powers.OutscalePower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.DragonCharacterFile.Enums.DRAGON_COLOR;
import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class Outscale extends AbstractEasyCard {
    public final static String ID = makeID("Outscale");

    private final static int BLOCK = 3;
    private final static int UPGRADE_BLOCK = 2;
    private final static int BLOCK_SCALING = 2;


    public Outscale() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, DRAGON_COLOR);
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = BLOCK_SCALING;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToSelf(new OutscalePower(p, magicNumber));
    }

    @Override
    public void calculateCardDamage(AbstractMonster m){
        int realBaseBlock = baseBlock;
        baseBlock += pwrAmt(adp(), OutscalePower.POWER_ID);
        super.calculateCardDamage(m);
        baseBlock = realBaseBlock;
        isBlockModified = (block != baseBlock);
    }

    @Override
    public void applyPowers(){
        int realBaseBlock = baseBlock;
        baseBlock += pwrAmt(adp(), OutscalePower.POWER_ID);
        super.applyPowers();
        baseBlock = realBaseBlock;
        isBlockModified = (block != baseBlock);
    }

    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
    }
}