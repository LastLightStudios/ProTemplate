package code.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;

import static code.ModFile.makeID;
import static code.util.Wiz.atb;
import static code.util.Wiz.vfx;

public class Wingrake extends AbstractEasyCard {
    public final static String ID = makeID("Wingrake");

    private final static int DAMAGE = 15;
    private final static int UPGRADE_DAMAGE = 5;
    private final static int CARD_DRAW = 1;
    private final static int UPGRADE_CARD_DRAW = 1;


    public Wingrake() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = CARD_DRAW;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SFXAction("ATTACK_WHIRLWIND"));
        vfx(new WhirlwindEffect(), 0.0f);
        allDmg(AbstractGameAction.AttackEffect.NONE);
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeMagicNumber(UPGRADE_CARD_DRAW);
    }
}