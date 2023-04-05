package code.deprecatedcards;

import code.cards.nests.AbstractNestCard;
import code.powers.dep.TundraNestPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;
import static code.util.Wiz.applyToSelf;

public class TundraNest extends AbstractNestCard {
    public final static String ID = makeID("TundraNest");
    private final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    private final static int BLOCK_UPGRADE = 5;
    private final static int BLOCK_PER_ENERGY = 5;

    public TundraNest() {
        super(ID, 1, CardRarity.UNCOMMON);
        baseMagicNumber = magicNumber = BLOCK_PER_ENERGY;
        baseBlock = BLOCK_UPGRADE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (upgraded){
            blck();
        }
        applyToSelf(new TundraNestPower(p, magicNumber));
    }

    public void upp() {
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}