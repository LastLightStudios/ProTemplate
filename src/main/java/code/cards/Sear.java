package code.cards;

import code.powers.CauterizePower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class Sear extends AbstractEasyCard {
    public final static String ID = makeID("Sear");

    private final static int CAUTERIZE_APPLICATION = 2;
    private final static int UPGRADE_CAUTERIZE_APPLICATION = 0;

    public Sear() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = CAUTERIZE_APPLICATION;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mon : getEnemies()){
            applyToEnemy(mon, new CauterizePower(mon, magicNumber));
        }
    }

    public void upp() {
        exhaust = false;
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}