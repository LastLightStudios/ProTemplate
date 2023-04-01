package code.cards;

import code.powers.CauterizePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;
import static code.util.Wiz.applyToEnemy;

public class GreedyStrike extends AbstractEasyCard {
    public final static String ID = makeID("SaltTheWound");

    private final static int DAMAGE = 9;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int CAUTERIZE_APPLICATION = 1;
    private final static int UPGRADE_CAUTERIZE_APPLICATION = 0;

    public GreedyStrike() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = CAUTERIZE_APPLICATION;
        tags.add(AbstractCard.CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        if (m.hasPower(CauterizePower.POWER_ID)){
            altDmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        } else {
            applyToEnemy(m, new CauterizePower(m, magicNumber));
        }
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}