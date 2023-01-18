package code.relics;

import code.DragonCharacterFile;

import static code.ModFile.makeID;

public class TodoItem extends AbstractEasyRelic {
    public static final String ID = makeID("TodoItem");

    public TodoItem() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, DragonCharacterFile.Enums.DRAGON_COLOR);
    }
}
