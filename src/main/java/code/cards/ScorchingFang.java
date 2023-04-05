package code.cards;

import code.powers.CauterizePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;
import static code.util.Wiz.applyToEnemy;

public class ScorchingFang extends AbstractEasyCard {
    public final static String ID = makeID("ScorchingFang");

    private final static int DAMAGE = 6;
    private final static int BONUS_CAUTERIZE_SCALING = 2;
    private final static int UPGRADE_BONUS_CAUTERIZE_SCALING = 1;
    private final static int CAUTERIZE_APPLICATION = 1;
    private final static int UPGRADE_CAUTERIZE_APPLICATION = 1;

    public ScorchingFang() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = BONUS_CAUTERIZE_SCALING;
        baseSecondMagic = secondMagic = CAUTERIZE_APPLICATION;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL); //cauterizeMultiplier takes care of the bonus Cauterize scaling in CauterizePower
        applyToEnemy(m, new CauterizePower(m, secondMagic));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_BONUS_CAUTERIZE_SCALING);
        upgradeSecondMagic(UPGRADE_CAUTERIZE_APPLICATION);
    }

    public int getCauterizeMultiplier(){
        return magicNumber;
    }
}