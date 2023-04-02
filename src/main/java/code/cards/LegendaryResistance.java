package code.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;

import static code.DragonCharacterFile.Enums.DRAGON_COLOR;
import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class LegendaryResistance extends AbstractEasyCard {
    public final static String ID = makeID("LegendaryResistance");

    private final static int BLOCK = 40;
    private final static int UPGRADE_BLOCK = 10;
    private final static int ARTIFACT = 1;
    private final static int UPGRADE_ARTIFACT = 1;


    public LegendaryResistance() {
        super(ID, 3, CardType.SKILL, CardRarity.RARE, CardTarget.SELF, DRAGON_COLOR);
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = ARTIFACT;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToSelf(new ArtifactPower(p, magicNumber));
    }

    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
        upgradeMagicNumber(UPGRADE_ARTIFACT);
    }
}