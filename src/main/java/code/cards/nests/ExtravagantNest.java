package code.cards.nests;

import code.powers.PridePower;
import code.powers.nestpowers.ExtravagantNestPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;
import static code.util.Wiz.applyToSelf;

public class ExtravagantNest extends AbstractNestCard {
    public final static String ID = makeID("ExtravagantNest");
    private final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    private final static int PRIDE_GAIN = 4;
    private final static int IMMEDIATE_PRIDE_UPGRADE = 4;

    public ExtravagantNest() {
        super(ID, 1, CardRarity.UNCOMMON);
        baseMagicNumber = magicNumber = PRIDE_GAIN;
        baseSecondMagic = secondMagic = IMMEDIATE_PRIDE_UPGRADE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (upgraded){
            applyToSelf(new PridePower(p, secondMagic));
        }
        applyToSelf(new ExtravagantNestPower(p, magicNumber));
    }

    public void upp() {
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}