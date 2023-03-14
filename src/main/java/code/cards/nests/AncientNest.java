package code.cards.nests;

import code.cards.AbstractEasyCard;
import code.powers.nestpowers.AbyssNestPower;
import code.powers.nestpowers.AncientNestPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.MetallicizePower;

import static code.ModFile.makeID;
import static code.util.Wiz.applyToSelf;

public class AncientNest extends AbstractNestCard {
    public final static String ID = makeID("AncientNest");
    private final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    private final static int IMMEDIATE_ARTIFACT_UPGRADE = 1;
    private final static int ARTIFACT_PER_ENERGY = 1;

    public AncientNest() {
        super(ID, 1, CardRarity.RARE);
        baseMagicNumber = magicNumber = ARTIFACT_PER_ENERGY;
        baseSecondMagic = secondMagic = IMMEDIATE_ARTIFACT_UPGRADE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (upgraded){
            applyToSelf(new ArtifactPower(p, secondMagic));
        }
        applyToSelf(new AncientNestPower(p, magicNumber));
    }

    public void upp() {
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}