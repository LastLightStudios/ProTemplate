package code.cards;

import basemod.helpers.TooltipInfo;
import code.powers.MonstrousGreedPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.List;

import static code.ModFile.makeID;
import static code.util.Wiz.applyToSelf;

public class MonstrousGreed extends AbstractEasyCard {
    public final static String ID = makeID("MonstrousGreed");
    protected static final UIStrings prideUIStrings = CardCrawlGame.languagePack.getUIString(makeID("Pride"));

    private final static int DAMAGE = 5;
    private final static int UPGRADE_DAMAGE = 3;

    public MonstrousGreed() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = DAMAGE;
    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        ArrayList<TooltipInfo> retVal = new ArrayList<>();
        retVal.add(new TooltipInfo(prideUIStrings.TEXT[0], prideUIStrings.TEXT[1]));
        return retVal;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new MonstrousGreedPower(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_DAMAGE);
    }
}