package code.cards;


import basemod.AutoAdd;
import code.util.CustomTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.UIStrings;

import static code.ModFile.makeID;
import code.DragonCharacterFile;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTwoSidedCard extends AbstractEasyCard{
    protected static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("Spark"));
    protected boolean isFront; //true = front(Spark), false = back(Breath)

    private String nameA;
    private String nameB;

    protected String descriptionA;
    protected String descriptionB;

    protected int cardCostA;
    protected int cardCostB;
    protected int baseDamageA;
    protected int baseDamageB;
    protected int baseBlockA;
    protected int baseBlockB;
    protected int baseMagicNumberA;
    protected int baseMagicNumberB;
    protected int baseSecondMagicA;
    protected int baseSecondMagicB;
    protected int baseSecondDamageA;
    protected int baseSecondDamageB;

    private CardType cardTypeA;
    private CardType cardTypeB;

    protected CardTarget cardTargetA;
    protected CardTarget cardTargetB;

    protected final CardStrings cardStrings;

    public AbstractTwoSidedCard(String cardID, int costA, int costB, CardType typeA, CardType typeB, CardRarity rarity, CardTarget targetA, CardTarget targetB, boolean generatePreview){
        super(cardID, costA, typeA, rarity, targetA, DragonCharacterFile.Enums.DRAGON_COLOR);
        cardStrings = CardCrawlGame.languagePack.getCardStrings(cardID);
        nameA = cardStrings.NAME;
        nameB = cardStrings.EXTENDED_DESCRIPTION[0];

        descriptionA = cardStrings.DESCRIPTION;
        descriptionB = cardStrings.EXTENDED_DESCRIPTION[1];

        cardCostA = costA;
        cardCostB = costB;

        cardTypeA = typeA;
        cardTypeB = typeB;

        cardTargetA = targetA;
        cardTargetB = targetB;

        baseDamageA = 0;
        baseDamageB = 0;
        baseSecondDamageA = 0;
        baseSecondDamageB = 0;
        baseBlockA = 0;
        baseBlockB = 0;
        baseMagicNumberA = 0;
        baseMagicNumberB = 0;
        baseSecondMagicA = 0;
        baseSecondMagicB = 0;

        if (generatePreview){
            cardsToPreview = noPreviewCopy();
        }

        tags.add(CustomTags.DOUBLE_SIDED);
    }

    protected abstract AbstractTwoSidedCard noPreviewCopy();

    // This is for Sparks/Breaths, can take this out into another abstract class if i decide to use 2 sided cards in the future
    @Override
    public List<String> getCardDescriptors(){
        ArrayList<String> retVal = new ArrayList<>();
        if (isFront){
            retVal.add(uiStrings.TEXT[0]);
        } else {
            retVal.add(uiStrings.TEXT[1]);
        }
        return retVal;
    }

    protected void setDamage(int damageA, int damageB){
        baseDamageA = damageA;
        baseDamageB = damageB;
        if (isFront) {
            baseDamage = baseDamageA;
        } else {
            baseDamage = baseDamageB;
        }
    }

    protected void setSecondDamage(int secondDamageA, int secondDamageB){
        baseSecondDamageA = secondDamageA;
        baseSecondDamageB = secondDamageB;
        if (isFront) {
            baseSecondDamage = baseSecondDamageA;
        } else {
            baseSecondDamage = baseSecondDamageB;
        }
    }

    protected void setBlock(int blockA, int blockB){
        baseBlockA = blockA;
        baseBlockB = blockB;
        if (isFront) {
            baseBlock = baseBlockA;
        } else {
            baseBlock = baseBlockB;
        }
    }

    protected void setMagic(int magicA, int magicB){
        baseMagicNumberA = magicA;
        baseMagicNumberB = magicB;
        if (isFront) {
            baseMagicNumber = baseMagicNumberA;
        } else {
            baseMagicNumber = baseMagicNumberB;
        }
    }

    protected void setSecondMagic(int secondMagicA, int secondMagicB){
        baseSecondMagicA = secondMagicA;
        baseSecondMagicB = secondMagicB;
        if (isFront) {
            baseSecondMagic = baseSecondMagicA;
        } else {
            baseSecondMagic = baseSecondMagicB;
        }
    }

    public void changeSide(boolean changeToBack){
        if (!changeToBack){ // change to Spark
            name = nameA;
            rawDescription = descriptionA;

            //TODO: img switching

            type = cardTypeA;
            target = cardTargetA;

            baseDamage = baseDamageA;
            baseBlock = baseBlockA;
            baseMagicNumber = magicNumber = baseMagicNumberA;
            baseSecondMagic = secondMagic = baseSecondMagicA;
            baseSecondDamage = secondDamage = baseSecondDamageA;
        } else { // change to Breath
            name = nameB;
            rawDescription = descriptionB;

            //TODO: img switching

            type = cardTypeB;
            target = cardTargetB;

            baseDamage = baseDamageB;
            baseBlock = baseBlockB;
            baseMagicNumber = magicNumber = baseMagicNumberB;
            baseSecondMagic = secondMagic = baseSecondMagicB;
            baseSecondDamage = secondDamage = baseSecondDamageB;
        }
        if (target == CardTarget.ALL_ENEMY){
            isMultiDamage = true;
        } else {
            isMultiDamage = false;
        }
        isFront = !changeToBack;
        initializeTitle();
        initializeDescription();

        if (!changeToBack){ //has changed to Spark
            // the preview doesn't have a preview, so have to do a null check.
            if(cardsToPreview != null){
                ((AbstractTwoSidedCard)cardsToPreview).changeSide(true); // make the preview a Breath
            }
        } else { //has changed to Breath
            // the preview doesn't have a preview, so have to do a null check.
            if(cardsToPreview != null){
                ((AbstractTwoSidedCard)cardsToPreview).changeSide(false); // make the preview a Spark
            }
        }
    }

    @Override
    public void applyPowers(){
        super.applyPowers();
        if (cardsToPreview != null){
            cardsToPreview.applyPowers();
        }
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo){
        super.calculateCardDamage(mo);
        if (cardsToPreview != null){
            cardsToPreview.applyPowers();
        }
    }

    @Override
    public void resetAttributes() {
        super.resetAttributes();
        if (cardsToPreview != null){
            cardsToPreview.resetAttributes();
        }
    }

    @Override
    public void upgrade(){
        if(!upgraded){
            ++timesUpgraded;
            upgraded = true;
            name = this.name + "+";
            nameA = this.nameA + "+";
            nameB = this.nameB + "+";
            upp();
            if (cardsToPreview != null){
                cardsToPreview.upgrade();
            }
            initializeTitle();
        }
    }

    public abstract void upp();

    // doesn't actually Override the AbstractCard.upgradeDamage(int amount)
    protected void upgradeDamage(int amountA, int amountB) {
        baseDamageA += amountA;
        baseDamageB += amountB;
        if (isFront) {
            if (amountA != 0){
                upgradedDamage = true;
            }
            baseDamage = baseDamageA;
        } else {
            if (amountB != 0){
                upgradedDamage = true;
            }
            baseDamage = baseDamageB;
        }
    }

    protected void upgradeSecondDamage(int amountA, int amountB) {
        baseSecondDamageA += amountA;
        baseSecondDamageB += amountB;
        if (isFront) {
            if (amountA != 0){
                upgradedSecondDamage = true;
            }
            baseSecondDamage = baseSecondDamageA;
        } else {
            if (amountB != 0){
                upgradedSecondDamage = true;
            }
            baseSecondDamage = baseSecondDamageB;
        }
    }

    protected void upgradeBlock(int amountA, int amountB) {
        baseBlockA += amountA;
        baseBlockB += amountB;
        if (isFront) {
            if (amountA != 0){
                upgradedBlock = true;
            }
            baseBlock = baseBlockA;
        } else {
            if (amountB != 0){
                upgradedBlock = true;
            }
            baseBlock = baseBlockB;
        }
    }

    protected void upgradeMagicNumber(int amountA, int amountB) {
        baseMagicNumberA += amountA;
        baseMagicNumberB += amountB;
        if (isFront) {
            if (amountA != 0){
                upgradedMagicNumber = true;
            }
            baseMagicNumber = baseMagicNumberA;
        } else {
            if (amountA != 0){
                upgradedMagicNumber = true;
            }
            baseMagicNumber = baseMagicNumberB;
        }
        magicNumber = baseMagicNumber;
    }

    protected void upgradeSecondMagicNumber(int amountA, int amountB) {
        baseSecondMagicA += amountA;
        baseSecondMagicB += amountB;
        if (isFront) {
            if (amountA != 0){
                upgradedSecondMagic = true;
            }
            baseSecondMagic = baseSecondMagicA;
        } else {
            if (amountB != 0){
                upgradedSecondMagic = true;
            }
            baseSecondMagic = baseSecondMagicB;
        }
        secondMagic = baseSecondMagic;
    }

    @Override
    public void initializeDescription(){
        if (isFront && (descriptionA != null)) { //added null checks b/c idk this was getting called before the descriptionA/B was found
            rawDescription = descriptionA;
        } else if (!isFront && (descriptionB != null)) {
            rawDescription = descriptionB;
        }
        super.initializeDescription();
    }

    @Override
    public AbstractCard makeStatEquivalentCopy(){

        AbstractCard baseCopy = super.makeStatEquivalentCopy();

        if (baseCopy instanceof AbstractTwoSidedCard) {
            ((AbstractTwoSidedCard) baseCopy).baseBlockA = this.baseBlockA;
            ((AbstractTwoSidedCard) baseCopy).baseBlockB = this.baseBlockB;
            ((AbstractTwoSidedCard) baseCopy).baseDamageA = this.baseDamageA;
            ((AbstractTwoSidedCard) baseCopy).baseDamageB = this.baseDamageB;
            ((AbstractTwoSidedCard) baseCopy).baseMagicNumberA = this.baseMagicNumberA;
            ((AbstractTwoSidedCard) baseCopy).baseMagicNumberB = this.baseMagicNumberB;
        }
        return baseCopy;
    }
}
