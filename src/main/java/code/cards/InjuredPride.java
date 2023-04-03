package code.cards;

import code.powers.PridePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static code.ModFile.makeID;
import static code.util.Wiz.adp;
import static code.util.Wiz.applyToSelf;

public class InjuredPride extends AbstractEasyCard {
    public final static String ID = makeID("InjuredPride");

    private final static int DAMAGE = 0;
    private final static int UPGRADE_COST = 0;
    private final static int BIG_NUMBER_SO_IT_SHOWS_RED = 9999;

    public InjuredPride() {
        super(ID, 0, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = BIG_NUMBER_SO_IT_SHOWS_RED;
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
        if (!upgraded){
            rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        } else {
            rawDescription = cardStrings.UPGRADE_DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        }
        initializeDescription();
    }

    @Override
    public void applyPowers(){
        AbstractPower pride = adp().getPower(PridePower.POWER_ID);
        if (pride != null){
            baseDamage = pride.amount / 2;
            magicNumber = pride.amount / 2;
            isMagicNumberModified = true;
        }
        super.applyPowers();
        if (!upgraded){
            rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        } else {
            rawDescription = cardStrings.UPGRADE_DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        }
        initializeDescription();
    }

    public void onMoveToDiscard() {
        rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }

    public void upp() {
        retain = true;
    }
}