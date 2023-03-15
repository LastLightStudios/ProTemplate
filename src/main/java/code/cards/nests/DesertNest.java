package code.cards.nests;

import code.powers.nestpowers.DesertNestPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static code.ModFile.makeID;
import static code.util.Wiz.applyToSelf;

public class DesertNest extends AbstractNestCard {
    public final static String ID = makeID("DesertNest");
    private final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    private final static int IMMEDIATE_STR_UPGRADE = 2;
    private final static int STR_PER_ENERGY = 4;

    public DesertNest() {
        super(ID, 1, CardRarity.UNCOMMON);
        baseMagicNumber = magicNumber = STR_PER_ENERGY;
        baseSecondMagic = secondMagic = IMMEDIATE_STR_UPGRADE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (upgraded){
            applyToSelf(new StrengthPower(p, secondMagic));
            applyToSelf(new LoseStrengthPower(p, secondMagic));
        }
        applyToSelf(new DesertNestPower(p, magicNumber));
    }

    public void upp() {
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}