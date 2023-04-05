package code.cards;

import basemod.helpers.TooltipInfo;
import code.actions.HoardCardAction;
import code.powers.PridePower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.List;

import static code.ModFile.makeID;
import static code.DragonCharacterFile.Enums.DRAGON_COLOR;
import static code.util.Wiz.applyToSelf;
import static code.util.Wiz.atb;

public class ScaleGuard extends AbstractEasyCard {
    public final static String ID = makeID("ScaleGuard");
    protected static final UIStrings prideUIStrings = CardCrawlGame.languagePack.getUIString(makeID("Pride"));
    private final static int PRIDE_GAIN = 9;
    private final static int UPGRADE_PRIDE_GAIN = 3;
    private final static int CARDS_HOARDED = 1;

    public ScaleGuard() {
        super(ID, 2, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF, DRAGON_COLOR);
        baseMagicNumber = magicNumber = PRIDE_GAIN;
    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        ArrayList<TooltipInfo> retVal = new ArrayList<>();
        retVal.add(new TooltipInfo(prideUIStrings.TEXT[0], prideUIStrings.TEXT[1]));
        return retVal;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new PridePower(p, magicNumber));
        atb(new HoardCardAction(CARDS_HOARDED));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_PRIDE_GAIN);
    }
}