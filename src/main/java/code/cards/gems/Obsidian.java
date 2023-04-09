package code.cards.gems;

import code.powers.BurningScalesPower;
import code.powers.LoseBurningScalesPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;
import static code.util.Wiz.applyToSelf;

public class Obsidian extends AbstractGemCard {
    public final static String ID = makeID("Obsidian");

    private final static int BURNING_SCALES_VALUE = 4; //Energy Gain
    private final static int UPGRADE_BURNING_SCALES_VALUE = 2; //increase energy gain

    public Obsidian() {
        super(ID, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        baseMagicNumber = magicNumber = BURNING_SCALES_VALUE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new BurningScalesPower(p, magicNumber));
        applyToSelf(new LoseBurningScalesPower(p, magicNumber));
        hoardThisGem();
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_BURNING_SCALES_VALUE);
        initializeDescription();
    }
}