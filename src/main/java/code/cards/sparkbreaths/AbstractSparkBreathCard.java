package code.cards.sparkbreaths;


import basemod.helpers.TooltipInfo;
import code.DragonCharacterFile;
import code.actions.CheckEmberBreakpointAction;
import code.cards.AbstractTwoSidedCard;
import code.powers.EmberPower;
import code.util.DragonUtils.CustomTags;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.ArrayList;
import java.util.List;

import static code.ModFile.makeID;
import static code.powers.EmberPower.getEmberBreakpoint;
import static code.util.Wiz.*;

public abstract class AbstractSparkBreathCard extends AbstractTwoSidedCard {
    protected static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("Spark"));
    protected static boolean affectSecondMagic = false;
    private static ArrayList<TooltipInfo> SparkBreathTooltip;

    public AbstractSparkBreathCard(String cardID, CardType typeA, CardType typeB, CardRarity rarity, CardTarget targetA, CardTarget targetB, boolean generatePreview){
        super(cardID, 0, 2, typeA, typeB, rarity, targetA, targetB, DragonCharacterFile.Enums.DRAGON_COLOR, generatePreview);
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

    public void checkEmberTriggerTop(){
        att(new CheckEmberBreakpointAction(this));
    }

    // call this at the end of the Card constructor
    public void initializeSide() {
        boolean changeToBreath = false;
        if (adp() != null){
            if (pwrAmt(adp(), EmberPower.POWER_ID) >= getEmberBreakpoint()) {
                changeToBreath = true;
            }
        }
        changeToBack(changeToBreath);
    }

    public static boolean isAffectSecondMagic() {
        return affectSecondMagic;
    }
}
