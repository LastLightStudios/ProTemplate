package code.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;
import static code.DragonCharacterFile.Enums.DRAGON_COLOR;

public class ScaleGuard extends AbstractEasyCard {
    public final static String ID = makeID("ScaleGuard");

    public ScaleGuard() {
        super(ID, 2, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF, DRAGON_COLOR);
        baseBlock = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        blck();
    }

    public void upp() {
        upgradeBlock(3);
    }
}