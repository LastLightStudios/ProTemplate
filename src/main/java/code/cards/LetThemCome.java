package code.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.DragonCharacterFile.Enums.DRAGON_COLOR;
import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class LetThemCome extends AbstractEasyCard {
    public final static String ID = makeID("LetThemCome");

    private final static int BLOCK = 7;
    private final static int UPGRADE_BLOCK = 3;


    public LetThemCome() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, DRAGON_COLOR);
        baseBlock = BLOCK;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for(AbstractMonster mon : getEnemies()){
            blck();
        }
    }

    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
    }
}