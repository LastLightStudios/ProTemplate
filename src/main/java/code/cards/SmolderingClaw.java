package code.cards;

import code.powers.EmberPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;
import static code.util.Wiz.applyToSelf;

public class SmolderingClaw extends AbstractEasyCard {
    public final static String ID = makeID("SmolderingClaw");

    private final static int DAMAGE = 8;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int EMBER_GAIN = 2;
    private final static int UPGRADE_EMBER_GAIN = 1;

    public SmolderingClaw() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = EMBER_GAIN;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new EmberPower(p, magicNumber));
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeMagicNumber(UPGRADE_EMBER_GAIN);
    }
}