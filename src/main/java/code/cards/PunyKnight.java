package code.cards;

import basemod.helpers.TooltipInfo;
import code.actions.HoardCardAction;
import code.powers.EmberPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.List;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class PunyKnight extends AbstractEasyCard {
    public final static String ID = makeID("PunyKnight");
    protected static final UIStrings prideUIStrings = CardCrawlGame.languagePack.getUIString(makeID("Pride"));

    private final static int EMBER_GAIN = 1;

    public PunyKnight() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        baseMagicNumber = magicNumber = EMBER_GAIN;
        cardsToPreview = new Trophy();
    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        ArrayList<TooltipInfo> retVal = new ArrayList<>();
        retVal.add(new TooltipInfo(prideUIStrings.TEXT[0], prideUIStrings.TEXT[1]));
        return retVal;
    }
    
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        shuffleIn(new Trophy());
        applyToSelf(new EmberPower(p, magicNumber));
        atb(new HoardCardAction(this));
    }

    @Override
    public void upp() {

    }
}