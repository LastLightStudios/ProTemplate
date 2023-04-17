package code.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.rewards.RewardItem;

import static code.ModFile.makeID;

public class PostCombatExtraCardRewardPower extends AbstractEasyPower{

    public static final String POWER_ID = makeID("PostCombatExtraCardRewardPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public PostCombatExtraCardRewardPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        loadRegion("draw");
        updateDescription();
    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            description = powerStrings.DESCRIPTIONS[0] + amount + powerStrings.DESCRIPTIONS[1];
        } else {
            description = powerStrings.DESCRIPTIONS[0] + amount + powerStrings.DESCRIPTIONS[2];
        }
    }

    @Override
    public void onVictory() {
        for (int i = 0; i < amount; i++)
            AbstractDungeon.getCurrRoom().addCardReward(new RewardItem());
    }
}
