package code.cards.nests;

import basemod.helpers.TooltipInfo;
import code.actions.ReduceDebuffsAction;
import code.powers.nestpowers.MoltingNestPower;
import code.util.DragonUtils;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.List;

import static code.ModFile.makeID;
import static code.util.Wiz.applyToSelf;
import static code.util.Wiz.atb;

public class MoltingNest extends AbstractNestCard {
    public final static String ID = makeID("MoltingNest");
    private final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    private final static int SHED_VALUE = 1;
    private final static int IMMEDIATE_SHED_VALUE = 1;

    public MoltingNest() {
        super(ID, 1, CardRarity.RARE);
        baseMagicNumber = magicNumber = SHED_VALUE;
        baseSecondMagic = secondMagic = IMMEDIATE_SHED_VALUE;
    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        return DragonUtils.getSheddableDebuffTooltips();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (upgraded){
            atb(new ReduceDebuffsAction(p, secondMagic));
        }
        applyToSelf(new MoltingNestPower(p, magicNumber));
    }

    public void upp() {
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}