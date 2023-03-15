package code.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class Wingbeat extends AbstractEasyCard {
    public final static String ID = makeID("Wingbeat");

    private final static int DAMAGE = 4;
    private final static int NUMBER_OF_HITS = 3;
    private final static int UPGRADE_NUMBER_OF_HITS = 1;


    public Wingbeat() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = NUMBER_OF_HITS;
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SFXAction("ATTACK_WHIRLWIND"));
        vfx(new WhirlwindEffect(), 0.0f);
        for (int i = 0; i < magicNumber; i++){
            atb(new SFXAction("ORB_LIGHTNING_EVOKE"));
            vfx(new CleaveEffect(), 0.0f);
            allDmg(AbstractGameAction.AttackEffect.NONE);
        }
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_NUMBER_OF_HITS);
    }
}