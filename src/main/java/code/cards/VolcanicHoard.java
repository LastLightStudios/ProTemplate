package code.cards;

import basemod.BaseMod;
import code.actions.HoardCardAction;
import code.powers.CauterizePower;
import code.powers.PridePower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class VolcanicHoard extends AbstractEasyCard {
    public final static String ID = makeID("VolcanicHoard");

    private final static int PRIDE_GAIN = 1;
    private final static int UPGRADE_PRIDE_GAIN = 1;
    private final static int CAUTERIZE_APPLICATION = 1;
    private final static int UPGRADE_CAUTERIZE_APPLICATION = 1;

    public VolcanicHoard() {
        super(ID, 1, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        baseMagicNumber = magicNumber = PRIDE_GAIN;
        baseSecondMagic = secondMagic = CAUTERIZE_APPLICATION;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        atb(new HoardCardAction(BaseMod.MAX_HAND_SIZE, HoardCardAction.CAN_PICK_ZERO | HoardCardAction.ANY_NUMBER, magicNumber, selectedCards ->{
            for (int i = 0; i < selectedCards.size(); i++) {
                applyToSelf(new PridePower(p, magicNumber));
                for (AbstractMonster mon : getEnemies()){
                    applyToEnemy(mon, new CauterizePower(mon, secondMagic));
                }
            }
        }));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_PRIDE_GAIN);
        upgradeSecondMagic(UPGRADE_CAUTERIZE_APPLICATION);
    }
}