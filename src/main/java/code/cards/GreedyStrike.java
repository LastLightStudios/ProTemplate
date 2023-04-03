package code.cards;

import code.actions.HoardCardAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;
import static code.util.Wiz.atb;

public class GreedyStrike extends AbstractEasyCard {
    public final static String ID = makeID("GreedyStrike");

    private final static int DAMAGE = 9;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int HOARD_CARD = 1;

    public GreedyStrike() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = HOARD_CARD;
        tags.add(AbstractCard.CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        if (!upgraded){
            atb(new HoardCardAction(magicNumber, HoardCardAction.IS_RANDOM));
        } else {
            atb(new HoardCardAction(magicNumber));
        }
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}