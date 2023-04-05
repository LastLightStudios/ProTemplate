package code.cards;

import basemod.helpers.TooltipInfo;
import code.powers.HoardingInstinctPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.List;

import static code.ModFile.makeID;
import static code.util.Wiz.applyToSelf;

public class HoardingInstinct extends AbstractEasyCard {
    public final static String ID = makeID("HoardingInstinct");
    protected static final UIStrings hoardUIStrings = CardCrawlGame.languagePack.getUIString(makeID("Hoard"));
    protected static final UIStrings prideUIStrings = CardCrawlGame.languagePack.getUIString(makeID("Pride"));
    private final static int UPGRADED_COST = 0;

    public HoardingInstinct() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        ArrayList<TooltipInfo> retVal = new ArrayList<>();
        retVal.add(new TooltipInfo(hoardUIStrings.TEXT[0], hoardUIStrings.TEXT[1]));
        retVal.add(new TooltipInfo(prideUIStrings.TEXT[0], prideUIStrings.TEXT[1]));
        return retVal;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new HoardingInstinctPower(p, 1));
    }

    public void upp() {
        upgradeBaseCost(UPGRADED_COST);
    }
}