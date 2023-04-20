package code.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;

import static code.ModFile.makeID;
import static code.util.Wiz.applyToSelf;

public class RecklessClaw extends AbstractEasyCard {
    public final static String ID = makeID("RecklessClaw");

    private final static int DAMAGE = 15;
    private final static int UPGRADE_DAMAGE = 5;
    private final static int DEXTERITY_LOSS = 1;

    public RecklessClaw() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = DEXTERITY_LOSS;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
        applyToSelf(new DexterityPower(p, -magicNumber));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}