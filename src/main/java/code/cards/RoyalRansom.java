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

public class RoyalRansom extends AbstractEasyCard {
    public final static String ID = makeID("RoyalRansom");
    protected static final UIStrings prideUIStrings = CardCrawlGame.languagePack.getUIString(makeID("Pride"));

    public RoyalRansom() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        cardsToPreview = new PunyKnight();
    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        ArrayList<TooltipInfo> retVal = new ArrayList<>();
        retVal.add(new TooltipInfo(prideUIStrings.TEXT[0], prideUIStrings.TEXT[1]));
        return retVal;
    }
    
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        shuffleIn(new PunyKnight());
        atb(new HoardCardAction(this));
    }

    @Override
    public void upp() {

    }
}