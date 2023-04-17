package code.rewards;

import basemod.abstracts.CustomReward;
import code.patches.TrophyRewardPatch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import static code.ModFile.makeID;

public class TrophyReward extends CustomReward {
    private static final Texture ICON = new Texture(Gdx.files.internal("images/ui/reward/normalCardReward.png"));
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("TrophyItem"));

    public TrophyReward() {
        super(ICON, uiStrings.TEXT[0], TrophyRewardPatch.DRAGONMOD_TROPHYREWARD);
    }

    @Override
    public boolean claimReward() {
        if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.COMBAT_REWARD) {
            AbstractDungeon.cardRewardScreen.open(this.cards, this, uiStrings.TEXT[1]);
            AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.COMBAT_REWARD;
        }
        return false;
    }

    public void populateCards(){
        //https://github.com/modargo/PackmasterCharacter/blob/87e67d7065dba6d6090f78bda410d93196e6a69a/src/main/java/thePackmaster/rewards/PMBoosterBoxCardReward.java
        //We have this as a method that gets a delayed call (from a patch on CombatRewardScreen.open) because
        //immediately generating the cards in the constructor would make saving and loading after combat change
        //the cards you get (because we'd advance RNG the first time, save the advanced RNG, load, and generate
        //card rewards from the advanced RNG)
        if (this.cards.isEmpty()){
            this.cards = AbstractDungeon.getRewardCards();
        }
    }
}