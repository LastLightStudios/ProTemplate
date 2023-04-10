package code.cards;

import code.powers.CauterizePower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.DragonCharacterFile.Enums.DRAGON_COLOR;
import static code.ModFile.makeID;
import static code.util.Wiz.applyToEnemy;

public class Aggravate extends AbstractEasyCard{
    public final static String ID = makeID("Aggravate");


    public Aggravate() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY, DRAGON_COLOR);
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m.hasPower(CauterizePower.POWER_ID)){
            if (!upgraded){
                applyToEnemy(m, new CauterizePower(m, m.getPower(CauterizePower.POWER_ID).amount));
            } else {
                applyToEnemy(m, new CauterizePower(m, m.getPower(CauterizePower.POWER_ID).amount * 2));
            }
        }
    }


    public void upp() {
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initialize();
    }
}