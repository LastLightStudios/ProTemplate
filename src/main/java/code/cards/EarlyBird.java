package code.cards;

import code.actions.EarlyBirdAction;
import code.powers.PridePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;
import static code.util.Wiz.applyToSelf;
import static code.util.Wiz.atb;

public class EarlyBird extends AbstractEasyCard {
    public final static String ID = makeID("EarlyBird");

    private final static int DAMAGE = 5;
    private final static int UPGRADE_DAMAGE = 1;
    private final static int MAX_CARDS_PLAYED = 3;
    private final static int UPGRADE_MAX_CARDS_PLAYED = 1;

    public EarlyBird() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAX_CARDS_PLAYED;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EarlyBirdAction(m, new DamageInfo(p, damage, damageTypeForTurn), magicNumber));
        this.rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        int count = AbstractDungeon.actionManager.cardsPlayedThisTurn.size();
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
        if (count == 1) {
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        } else {
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[2];
        }
        initializeDescription();
    }

    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }

    public void triggerOnGlowCheck() {
        this.glowColor = (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() < this.magicNumber) ? AbstractCard.GOLD_BORDER_GLOW_COLOR : AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeMagicNumber(UPGRADE_MAX_CARDS_PLAYED);
    }
}