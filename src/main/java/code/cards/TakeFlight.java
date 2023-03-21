package code.cards;

import code.powers.TakingFlightPower;
import code.powers.InFlightPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static code.DragonCharacterFile.Enums.DRAGON_COLOR;
import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class TakeFlight extends AbstractEasyCard {
    public final static String ID = makeID("TakeFlight");

    private final static int BUFFER_GAIN = 1;
    private final static int UPGRADE_BUFFER_GAIN = 0;
    private final static int UPGRADED_COST = 0;


    public TakeFlight() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, DRAGON_COLOR);
        baseMagicNumber = magicNumber = BUFFER_GAIN;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractPower takingFlight = adp().getPower(TakingFlightPower.POWER_ID);
        AbstractPower landing = adp().getPower(InFlightPower.POWER_ID);
        if (takingFlight == null && landing == null){
            applyToSelf(new TakingFlightPower(p, magicNumber));
        }
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m){
        boolean canUse = super.canUse(p, m);
        if (!canUse){
            return false;
        }
        AbstractPower takingFlight = adp().getPower(TakingFlightPower.POWER_ID);
        AbstractPower inFlight = adp().getPower(InFlightPower.POWER_ID);
        if (takingFlight != null){
            canUse = false;
            cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        }
        if (inFlight != null){
            canUse = false;
            cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[1];
        }
        return canUse;
    }

    public void upp() {
        upgradeBaseCost(UPGRADED_COST);
        upgradeMagicNumber(UPGRADE_BUFFER_GAIN);
    }
}