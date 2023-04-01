package code.cards;

import code.powers.PridePower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.DragonCharacterFile.Enums.DRAGON_COLOR;
import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class LetThemCome extends AbstractEasyCard {
    public final static String ID = makeID("LetThemCome");

    private final static int PRIDE = 7;
    private final static int UPGRADE_PRIDE = 3;


    public LetThemCome() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, DRAGON_COLOR);
        baseMagicNumber = magicNumber = PRIDE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for(AbstractMonster mon : getEnemies()){
            applyToSelf(new PridePower(p, magicNumber));
        }
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_PRIDE);
    }
}