package code.cards;


import basemod.helpers.TooltipInfo;
import code.DragonCharacterFile;
import code.actions.CheckEmberBreakpointAction;
import code.powers.EmberPower;
import code.util.CustomTags;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;
import java.util.List;

import static code.ModFile.makeID;
import static code.util.Wiz.adp;
import static code.powers.EmberPower.getEmberBreakpoint;
import static code.util.Wiz.atb;

public abstract class AbstractSparkBreathCard extends AbstractTwoSidedCard{
    protected static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("Spark"));

    private static ArrayList<TooltipInfo> SparkBreathTooltip;

    public AbstractSparkBreathCard(String cardID, CardType typeA, CardType typeB, CardRarity rarity, CardTarget targetA, CardTarget targetB, boolean generatePreview){
        super(cardID, 0, 2, typeA, typeB, rarity, targetA, targetB, DragonCharacterFile.Enums.DRAGON_COLOR, generatePreview);
        tags.add(CustomTags.DOUBLE_SIDED);
    }

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

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        ArrayList<TooltipInfo> retVal = new ArrayList<>();
        if (isFront){
            retVal.add(new TooltipInfo(uiStrings.TEXT[0], uiStrings.TEXT[2]));
        } else {
            retVal.add(new TooltipInfo(uiStrings.TEXT[1], uiStrings.TEXT[3]));
        }
        return retVal;
    }

    public void checkEmberTrigger(){
        atb(new CheckEmberBreakpointAction(this));
    }

    // call this at the end of the Card constructor
    public void initializeSide() {
        boolean changeToBreath = false;
        if (adp() != null){
            AbstractPower ember = adp().getPower(EmberPower.POWER_ID);
            if (ember != null){
                if (ember.amount >= getEmberBreakpoint()) {
                    changeToBreath = true;
                }
            }
        }
        changeSide(changeToBreath);
    }
}
