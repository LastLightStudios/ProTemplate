package code.cards.nests;

import code.powers.EmberPower;
import code.powers.nestpowers.ComfyNestPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;
import static code.util.Wiz.applyToSelf;

public class ComfyNest extends AbstractNestCard {
    public final static String ID = makeID("ComfyNest");
    private final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    private final static int EMBER_GAIN = 3;
    private final static int IMMEDIATE_EMBER_UPGRADE = 3;

    public ComfyNest() {
        super(ID, 1, CardRarity.UNCOMMON);
        baseMagicNumber = magicNumber = EMBER_GAIN;
        baseSecondMagic = secondMagic = IMMEDIATE_EMBER_UPGRADE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (upgraded){
            applyToSelf(new EmberPower(p, secondMagic));
        }
        applyToSelf(new ComfyNestPower(p, magicNumber));
    }

    public void upp() {
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}