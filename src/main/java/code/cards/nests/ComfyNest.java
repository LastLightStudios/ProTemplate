package code.cards.nests;

import code.powers.EmberPower;
import code.powers.nestpowers.AncientNestPower;
import code.powers.nestpowers.ComfyNestPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;

import static code.ModFile.makeID;
import static code.util.Wiz.applyToSelf;

public class ComfyNest extends AbstractNestCard {
    public final static String ID = makeID("EmberNest");
    private final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    private final static int IMMEDIATE_EMBER_UPGRADE = 2;
    private final static int EMBER_PER_ENERGY = 2;

    public ComfyNest() {
        super(ID, 1, CardRarity.RARE);
        baseMagicNumber = magicNumber = EMBER_PER_ENERGY;
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