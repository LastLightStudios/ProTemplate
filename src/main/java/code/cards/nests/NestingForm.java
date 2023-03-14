package code.cards.nests;

import code.powers.nestpowers.NestingFormPower;
import code.util.DragonUtils;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;
import static code.util.Wiz.applyToSelf;
import static code.util.Wiz.makeInHand;
import static code.util.DragonUtils.getRandomNest;

public class NestingForm extends AbstractNestCard {
    public final static String ID = makeID("NestingForm");
    private final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    //btw, wrote "a random" in cardstrings, so if these numbers ever change, need to change cardstrings
    private final static int IMMEDIATE_NEST_UPGRADE = 1;
    private final static int NEST_PER_ENERGY = 1;

    public NestingForm() {
        super(ID, 1, CardRarity.RARE);
        baseMagicNumber = magicNumber = NEST_PER_ENERGY;
        baseSecondMagic = secondMagic = IMMEDIATE_NEST_UPGRADE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (upgraded){
            makeInHand(DragonUtils.returnTrulyRandomCardWithTagInCombat(DragonUtils.CustomTags.NEST).makeCopy(), secondMagic);
        }
        applyToSelf(new NestingFormPower(p, magicNumber));
    }

    public void upp() {
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}