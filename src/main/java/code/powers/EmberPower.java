package code.powers;

import code.util.Wiz;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

import code.util.CustomTags;

import java.util.ArrayList;

import static code.ModFile.makeID;

public class EmberPower extends AbstractEasyPower {

    public static final String POWER_ID = makeID("EmberPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final int EMBER_BREAKPOINT = 5;

    public EmberPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        if (this.amount >= 9999)
            this.amount = 9999;

        updateDescription();
    }

    @Override
    public void stackPower(int stackAmount){
        super.stackPower(stackAmount);
        if (amount >= EMBER_BREAKPOINT){
            for (AbstractCard c : Wiz.getAllCardsInCardGroups(true, true)){
                if (isSpark(c)){
                    // transform into its breath
                }
            }
        }
    }

    @Override
    public void reducePower(int reduceAmount){
        super.reducePower(reduceAmount);
        if (amount <= EMBER_BREAKPOINT ) {
            // transform Breaths into Sparks
        }
    }

    public static boolean isSpark(AbstractCard c){
        return c.hasTag(CustomTags.SPARK);
    }

    public static boolean isBreath(AbstractCard c){
        return c.hasTag(CustomTags.BREATH);
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    };
}
