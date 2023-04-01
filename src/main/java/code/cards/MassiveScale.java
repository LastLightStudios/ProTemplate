package code.cards;

import code.powers.PridePower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static code.ModFile.makeID;
import static code.util.Wiz.adp;

public class MassiveScale extends AbstractEasyCard {
    public final static String ID = makeID("MassiveScale");

    private final static int BLOCK = 0; //used to be 5
    private final static int UPGRADE_BLOCK = 3;

    public MassiveScale() {
        super(ID, 2, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = BLOCK;
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

    @Override
    public void applyPowers(){
        baseBlock = adp().masterDeck.size();
        AbstractPower pride = adp().getPower(PridePower.POWER_ID);
        if (pride != null){
            baseBlock += pride.amount;
        }
        if (this.upgraded)
            this.baseBlock += UPGRADE_BLOCK;
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
        upgradeBlock(UPGRADE_BLOCK); //this line is required in case the card gets upgraded during combat...I think
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}