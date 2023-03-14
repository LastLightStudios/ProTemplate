package code.cards.nests;

import code.cards.AbstractEasyCard;
import code.powers.nestpowers.AbyssNestPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MetallicizePower;

import static code.ModFile.makeID;
import static code.util.Wiz.applyToSelf;

public class AbyssNest extends AbstractNestCard {
    public final static String ID = makeID("AbyssNest");
    private final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    private final static int IMMEDIATE_METALLICIZE_UPGRADE = 1;
    private final static int METALLICIZE_PER_ENERGY = 1;

    public AbyssNest() {
        super(ID, 1, CardRarity.RARE);
        baseMagicNumber = magicNumber = METALLICIZE_PER_ENERGY;
        baseSecondMagic = secondMagic = IMMEDIATE_METALLICIZE_UPGRADE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (upgraded){
            applyToSelf(new MetallicizePower(p, secondMagic));
        }
        applyToSelf(new AbyssNestPower(p, magicNumber));
    }

    public void upp() {
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}