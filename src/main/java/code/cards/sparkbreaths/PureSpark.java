package code.cards.sparkbreaths;

import code.cards.AbstractTwoSidedCard;
import code.powers.EmberPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class PureSpark extends AbstractSparkBreathCard {
    public final static String ID = makeID("PureSpark");

    private final static int DAMAGE_A = 1;
    private final static int DAMAGE_B = 15;

    private final static int MAGIC_NUMBER_A = 1; //spark gain
    private final static int MAGIC_NUMBER_B = 1; //spark multiplier
    private final static int BLOCK_NUMBER_A = 3;
    private final static int BLOCK_NUMBER_B = 10;
    private final static int UPGRADE_BLOCK_NUMBER_A = 2;
    private final static int UPGRADE_BLOCK_NUMBER_B = 5;

    public PureSpark(boolean needsPreview) {
        super(ID, CardType.ATTACK, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY, CardTarget.ALL_ENEMY, needsPreview);
        setDamage(DAMAGE_A, DAMAGE_B);
        setBlock(BLOCK_NUMBER_A, BLOCK_NUMBER_B);
        setMagic(MAGIC_NUMBER_A, MAGIC_NUMBER_B);

        initializeSide();
    }

    public PureSpark() {
        this(true);
    }

    @Override
    protected AbstractTwoSidedCard noPreviewCopy(){
        return new PureSpark(false);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (isFront) {
            blck();
            dmg(m, AbstractGameAction.AttackEffect.FIRE);
            applyToSelf(new EmberPower(p, magicNumber));
            if(upgraded){
                atb(new DrawCardAction(1));
            }
        } else { // Breath
            blck();
            allDmg(AbstractGameAction.AttackEffect.FIRE);
            atb(new RemoveSpecificPowerAction(p, p, EmberPower.POWER_ID));
        }
        checkEmberTrigger();
    }

    @Override
    public void calculateCardDamage(AbstractMonster m){
        if (isFront) {
            super.calculateCardDamage(m);
        } else {
            AbstractPower ember = adp().getPower(EmberPower.POWER_ID);
            if (ember != null) {
                int realBaseDamage = baseDamage; //temp store realBaseDamage b/c baseDamage is used in card damage calculations
                baseDamage = baseDamage + (magicNumber * ember.amount);

                // adjusting Block values based on Ember
                int realBaseBlock = baseBlock;
                baseBlock = baseBlock + (magicNumber * ember.amount);
                super.calculateCardDamage(m);

                baseDamage = realBaseDamage; //restore the realBaseDamage
                baseBlock = realBaseBlock;
                isDamageModified = (damage != baseDamage);
                isBlockModified = (block != baseBlock);
            }
        }
    }

    @Override
    public void applyPowers() {
        if (isFront) {
            super.applyPowers();
        } else {
            AbstractPower ember = adp().getPower(EmberPower.POWER_ID);
            if (ember != null) {
                int realBaseDamage = baseDamage; //temp store realBaseDamage b/c baseDamage is used in card damage calculations
                baseDamage = baseDamage + (magicNumber * ember.amount);

                // adjusting Block values based on Ember
                int realBaseBlock = baseBlock;
                baseBlock = baseBlock + (magicNumber * ember.amount);
                super.applyPowers();

                baseDamage = realBaseDamage; //restore the realBaseDamage
                baseBlock = realBaseBlock;
                isDamageModified = (damage != baseDamage);
                isBlockModified = (block != baseBlock);
            }
        }
    }

    @Override
    public void upp() {
        upgradeBlock(UPGRADE_BLOCK_NUMBER_A, UPGRADE_BLOCK_NUMBER_B);
        descriptionA = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}