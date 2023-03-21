package code.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;
import static code.util.Wiz.adp;

public class MassiveScale extends AbstractEasyCard {
    public final static String ID = makeID("MassiveScale");

    private final static int BLOCK = 0; //used to be 5
    private final static int UPGRADE_BLOCK = 3;
    private final static int DAMAGE_SCALAR = 1;
    private final static int PER_CARDS = 4;
    private final static int UPGRADE_PER_CARDS = -1;

    public MassiveScale() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = BLOCK;
        /*
        baseMagicNumber = magicNumber = DAMAGE_SCALAR;
        baseSecondMagic = secondMagic = PER_CARDS;
        */
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        if (!upgraded){
            rawDescription = cardStrings.DESCRIPTION;
        } else {
            rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        }
        initializeDescription();
    }

    /*
    @Override
    public void calculateCardDamage(AbstractMonster mon){
        int realBaseBlock = baseBlock;
        baseBlock += magicNumber * (adp().masterDeck.size()/secondMagic);
        super.calculateCardDamage(mon);
        baseBlock = realBaseBlock;
        isBlockModified = (block != baseBlock);
    }
    */

    @Override
    public void applyPowers(){
        this.baseBlock = java.lang.Math.max(adp().drawPile.size(), adp().discardPile.size());
        if (this.upgraded)
            this.baseBlock += 3;
        super.applyPowers();
        if (!this.upgraded) {
            this.rawDescription = cardStrings.DESCRIPTION;
        } else {
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        }
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    public void upp() {
        upgradeBlock(UPGRADE_BLOCK); //this line is required in case the card gets upgraded during combat
        //upgradeSecondMagic(UPGRADE_PER_CARDS);
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}