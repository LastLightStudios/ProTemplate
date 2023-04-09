package code.cards.gems;

import basemod.helpers.TooltipInfo;
import code.actions.ShedAction;
import code.util.DragonUtils;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.List;

import static code.ModFile.makeID;
import static code.util.Wiz.atb;

public class Onyx extends AbstractGemCard {
    public final static String ID = makeID("Onyx");

    private final static int SHED_VALUE = 1; //Energy Gain
    private final static int UPGRADE_SHED_VALUE = 1; //increase energy gain

    public Onyx() {
        super(ID, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        baseMagicNumber = magicNumber = SHED_VALUE;
    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        return DragonUtils.getSheddableDebuffTooltips();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ShedAction(p, magicNumber));
        hoardThisGem();
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_SHED_VALUE);
        initializeDescription();
    }
}