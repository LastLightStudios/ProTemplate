package code.cards.gems;

import code.actions.HoardThisCardAction;
import code.powers.PridePower;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;
import static code.util.Wiz.applyToSelf;
import static code.util.Wiz.atb;

public class Citrine extends AbstractGemCard {
    public final static String ID = makeID("Citrine");

    private final static int ENERGY_GAIN = 1; //Energy Gain
    private final static int UPGRADE_ENERGY_GAIN = 1; //increase energy gain

    public Citrine() {
        super(ID, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        baseMagicNumber = magicNumber = ENERGY_GAIN;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new GainEnergyAction(magicNumber));
        applyToSelf(new PridePower(p, 1));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_ENERGY_GAIN);
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}