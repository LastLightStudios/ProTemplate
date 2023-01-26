package code.cards.nests;

import code.cards.AbstractEasyCard;
import code.powers.nestpowers.OceanNestPower;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.localization.CardStrings;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class OceanNest extends AbstractEasyCard {
    public final static String ID = makeID("OceanNest");
    private final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    private final static int CARDS_DRAWN_UPGRADE = 1;
    private final static int CARDS_PER_ENERGY = 1;

    public OceanNest() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = CARDS_PER_ENERGY;
        baseSecondMagic = secondMagic = CARDS_DRAWN_UPGRADE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (upgraded){
            atb(new DrawCardAction(secondMagic));
        }
        applyToSelf(new OceanNestPower(p, magicNumber));
    }

    public void upp() {
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
    }
}