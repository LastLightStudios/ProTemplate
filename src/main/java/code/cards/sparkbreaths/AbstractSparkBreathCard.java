package code.cards.sparkbreaths;


import basemod.helpers.TooltipInfo;
import code.DragonCharacterFile;
import code.actions.CheckEmberBreakpointAction;
import code.cards.AbstractTwoSidedCard;
import code.powers.EmberPower;
import code.powers.FirepowerPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.ArrayList;
import java.util.List;

import static code.ModFile.makeID;
import static code.util.DragonUtils.getEmberBreakpoint;
import static code.util.Wiz.*;

public abstract class AbstractSparkBreathCard extends AbstractTwoSidedCard {
    protected static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("Spark"));

    private ArrayList<TooltipInfo> SparkBreathTooltip;
    protected boolean affectSecondMagic = false;

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

    public void incrementFirepowerPower(){
        // not using Wiz here b/c I want FirepowerPower to default have amount = 5 and applyToSelf uses the power.amount. this should only be called when stacking by 1.
        atb(new ApplyPowerAction(adp(), adp(), new FirepowerPower(adp()), 1));
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

    public boolean isAffectSecondMagic() {
        return affectSecondMagic;
    }
}
