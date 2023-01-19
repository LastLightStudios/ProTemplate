package code.relics;

import code.DragonCharacterFile;
import code.powers.EmberPower;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;


import static code.util.Wiz.applyToSelfTop;

import static code.ModFile.makeID;

public class Dragonstone extends AbstractEasyRelic {
    public static final String ID = makeID("Dragonstone");


    // Stats
    private static final int STARTING_SPARK_AMOUNT = 4;

    public Dragonstone() {
        super(ID, RelicTier.STARTER, LandingSound.MAGICAL, DragonCharacterFile.Enums.DRAGON_COLOR);
    }

    @Override
    public void atBattleStart() {
        flash();
        applyToSelfTop(new EmberPower(AbstractDungeon.player, STARTING_SPARK_AMOUNT));
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + STARTING_SPARK_AMOUNT + DESCRIPTIONS[1];
    }
}
