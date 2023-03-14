package code.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class Armorfryer extends AbstractEasyCard {
    public final static String ID = makeID("Armorfryer");

    private final static int DAMAGE = 12;
    private final static int UPGRADE_DAMAGE = 3;

    public Armorfryer() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = DAMAGE;
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for(AbstractMonster mon : getEnemies()){
            atb(new RemoveAllBlockAction(mon, p));
        }
        allDmg(AbstractGameAction.AttackEffect.FIRE);
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}