package code.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;
import static code.util.Wiz.adp;

public class MassiveScale extends AbstractEasyCard {
    public final static String ID = makeID("MassiveScale");

    private final static int BLOCK = 5;
    private final static int UPGRADE_BLOCK = 2;
    private final static int DAMAGE_SCALAR = 1;
    private final static int PER_CARDS = 4;
    private final static int UPGRADE_PER_CARDS = -1;

    public MassiveScale() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseDamage = BLOCK;
        baseMagicNumber = magicNumber = DAMAGE_SCALAR;
        baseSecondMagic = secondMagic = PER_CARDS;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mon){
        int realBaseBlock = baseBlock;
        baseBlock += magicNumber * (adp().masterDeck.size()/secondMagic);
        super.calculateCardDamage(mon);
        baseBlock = realBaseBlock;
        isBlockModified = (block != baseBlock);
    }

    @Override
    public void applyPowers(){
        int realBaseBlock = baseBlock;
        baseBlock += magicNumber * (adp().masterDeck.size()/secondMagic);
        super.applyPowers();
        baseDamage = realBaseBlock;
        isBlockModified = (block != baseBlock);
    }

    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
        upgradeSecondMagic(UPGRADE_PER_CARDS);
    }
}