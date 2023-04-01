package code.cards.gems;

import code.actions.HoardThisCardAction;
import code.powers.PridePower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class Emerald extends AbstractGemCard {
    public final static String ID = makeID("Emerald");

    private final static int TEMP_DEX_GAIN = 2; //temp dex gain
    private final static int UPGRADE_TEMP_DEX_GAIN = 2; //increase temp dex gain

    public Emerald() {
        super(ID, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        baseMagicNumber = magicNumber = TEMP_DEX_GAIN;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new DexterityPower(p, magicNumber));
        applyToSelf(new LoseDexterityPower(p, magicNumber));
        applyToSelf(new PridePower(p, 1));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_TEMP_DEX_GAIN);
    }
}