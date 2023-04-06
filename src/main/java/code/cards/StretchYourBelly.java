package code.cards;

import basemod.helpers.TooltipInfo;
import code.actions.ReduceDebuffsAction;
import code.util.DragonUtils;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import java.util.List;

import static code.ModFile.makeID;
import static code.util.Wiz.applyToSelf;
import static code.util.Wiz.atb;

public class StretchYourBelly extends AbstractEasyCard {
    public final static String ID = makeID("StretchYourBelly");

    private final static int ENERGY_GAIN = 2;
    private final static int VULN_GAIN = 2;
    private final static int UPGRADE_VULN_GAIN = -1;
    private final static int SHED_VALUE = 1; // this do be hardcoded

    public StretchYourBelly() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        magicNumber = baseMagicNumber = ENERGY_GAIN;
        secondMagic = baseSecondMagic = VULN_GAIN;
    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        if (upgraded){
            return DragonUtils.getSheddableDebuffTooltips();
        } else {
            return null;
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new GainEnergyAction(magicNumber));
        applyToSelf(new VulnerablePower(p, secondMagic, false));
        if (upgraded){
            atb(new ReduceDebuffsAction(p, SHED_VALUE));
        }
    }

    public void upp() {
        //upgradeMagicNumber();
        //upgradeSecondMagic(UPGRADE_VULN_GAIN);
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}