package code.cards.nests;

import code.powers.nestpowers.VolcanicNestPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DemonFormPower;

import static code.ModFile.makeID;
import static code.util.Wiz.applyToSelf;

public class VolcanicNest extends AbstractNestCard {
    public final static String ID = makeID("VolcanicNest");
    private final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    private final static int IMMEDIATE_DEMON_UPGRADE = 1;
    private final static int DEMON_PER_ENERGY = 1;

    public VolcanicNest() {
        super(ID, 1, CardRarity.RARE);
        baseMagicNumber = magicNumber = DEMON_PER_ENERGY;
        baseSecondMagic = secondMagic = IMMEDIATE_DEMON_UPGRADE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (upgraded){
            applyToSelf(new DemonFormPower(p, magicNumber));
        }
        applyToSelf(new VolcanicNestPower(p, magicNumber));
    }

    public void upp() {
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
    }
}