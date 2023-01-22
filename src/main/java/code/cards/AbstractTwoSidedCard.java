package code.cards;


import basemod.AutoAdd;
import code.util.CustomTags;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.UIStrings;

import static code.ModFile.makeID;
import code.DragonCharacterFile;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.List;

public abstract class AbstractTwoSidedCard extends AbstractEasyCard{
    protected static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("Spark"));
    protected boolean isFront; //true = front(Spark), false = back(Breath)

    private String nameA;
    private String nameB;

    protected String descriptionA;
    protected String descriptionB;

    public int cardCostA;
    public int cardCostB;
    public int baseDamageA;
    public int baseDamageB;
    public int baseBlockA;
    public int baseBlockB;
    public int baseMagicNumberA;
    public int baseMagicNumberB;
    public int baseSecondMagicA;
    public int baseSecondMagicB;
    public int baseSecondDamageA;
    public int baseSecondDamageB;

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
        baseBlockA = 0;
        baseBlockB = 0;
        baseMagicNumberA = 0;
        baseMagicNumberB = 0;
        baseSecondMagicA = 0;
        baseSecondMagicB = 0;
        baseSecondDamageA = 0;
        baseSecondDamageB = 0;

        if (generatePreview){
            cardsToPreview = noPreviewCopy();
        }

        tags.add(CustomTags.DOUBLE_SIDED);
    }

    protected abstract AbstractTwoSidedCard noPreviewCopy();

    @Override
    public abstract List<String> getCardDescriptors();

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
            initializeTitle();
        }
    }

    public abstract void upp();

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
