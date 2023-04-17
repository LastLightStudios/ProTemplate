package code.cards;

import basemod.helpers.TooltipInfo;
import code.actions.HoardCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.List;

import static code.ModFile.makeID;
import static code.util.Wiz.atb;
import static code.util.Wiz.shuffleIn;

public class KidnapPrincess extends AbstractEasyCard {
    public final static String ID = makeID("KidnapPrincess");
    protected static final UIStrings prideUIStrings = CardCrawlGame.languagePack.getUIString(makeID("Pride"));

    private final static int UPGRADED_COST = 0;

    public KidnapPrincess() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        cardsToPreview = new RoyalRansom();
    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        ArrayList<TooltipInfo> retVal = new ArrayList<>();
        retVal.add(new TooltipInfo(prideUIStrings.TEXT[0], prideUIStrings.TEXT[1]));
        return retVal;
    }
    
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        shuffleIn(new RoyalRansom());
        atb(new HoardCardAction(this));
    }

    @Override
    public void upp() {
        upgradeBaseCost(UPGRADED_COST);
    }
}