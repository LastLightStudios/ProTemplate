package code.cards;

import code.powers.PridePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static code.ModFile.makeID;
import static code.util.Wiz.adp;
import static code.util.Wiz.applyToSelf;

public class EgoCheck extends AbstractEasyCard {
    public final static String ID = makeID("EgoCheck");

    private final static int DAMAGE = 0;
    private final static int UPGRADE_COST = 0;
    private final static int BIG_NUMBER_SO_IT_SHOWS_RED = 9999;

    public EgoCheck() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        // The next 3 lines are some fuckery that doesn't matter any more cuz there's no Pride loss
        /*
        baseMagicNumber = BIG_NUMBER_SO_IT_SHOWS_RED;
        magicNumber = 0;
        isMagicNumberModified = true;
         */
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        AbstractPower pride = adp().getPower(PridePower.POWER_ID);
        if (pride != null){
            applyToSelf(new PridePower(p, -pride.amount / 2));
        }
        rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mon){
        super.calculateCardDamage(mon);
        rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    @Override
    public void applyPowers(){
        AbstractPower pride = adp().getPower(PridePower.POWER_ID);
        if (pride != null){
            baseDamage = pride.amount;
            magicNumber = pride.amount / 2;
            isMagicNumberModified = true;
        }
        super.applyPowers();
        rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    public void onMoveToDiscard() {
        rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }

    public void upp() {
        upgradeBaseCost(UPGRADE_COST);
    }
}