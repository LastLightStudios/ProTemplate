package code.cards.nests;

import code.powers.nestpowers.ForestNestPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;

import static code.ModFile.makeID;
import static code.util.Wiz.applyToSelf;

public class ForestNest extends AbstractNestCard {
    public final static String ID = makeID("ForestNest");
    private final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    private final static int IMMEDIATE_DEX_UPGRADE = 2;
    private final static int DEX_PER_ENERGY = 4;

    public ForestNest() {
        super(ID, 1, CardRarity.UNCOMMON);
        baseMagicNumber = magicNumber = DEX_PER_ENERGY;
        baseSecondMagic = secondMagic = IMMEDIATE_DEX_UPGRADE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (upgraded){
            applyToSelf(new DexterityPower(p, secondMagic));
            applyToSelf(new LoseDexterityPower(p, secondMagic));
        }
        applyToSelf(new ForestNestPower(p, magicNumber));
    }

    public void upp() {
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}