package code.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FlameBarrierPower;
import com.megacrit.cardcrawl.powers.RagePower;

import static code.DragonCharacterFile.Enums.DRAGON_COLOR;
import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class DraconicWrath extends AbstractEasyCard {
    public final static String ID = makeID("DraconicWrath");

    private final static int RAGE_PER_ENEMY = 3;
    private final static int UPGRADE_RAGE_PER_ENEMY = 2;
    private final static int FLAME_BARRIER_PER_ENEMY = 3;
    private final static int UPGRADE_FLAME_BARRIER_PER_ENEMY = 2;


    public DraconicWrath() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, DRAGON_COLOR);
        baseMagicNumber = magicNumber = RAGE_PER_ENEMY;
        baseSecondMagic = secondMagic = FLAME_BARRIER_PER_ENEMY;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < getEnemies().size(); i++){
            applyToSelf(new RagePower(p, magicNumber));
            applyToSelf(new FlameBarrierPower(p, secondMagic));
        }
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_RAGE_PER_ENEMY);
        upgradeSecondMagic(UPGRADE_FLAME_BARRIER_PER_ENEMY);
    }
}