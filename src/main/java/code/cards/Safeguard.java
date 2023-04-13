package code.cards;

import code.interfaces.HoardingCardInterface;
import code.patches.CardCounterPatches;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.DragonCharacterFile.Enums.DRAGON_COLOR;
import static code.ModFile.makeID;

public class Safeguard extends AbstractEasyCard implements HoardingCardInterface {
    public final static String ID = makeID("Safeguard");

    private final static int BLOCK = 12;
    private final static int UPGRADE_BLOCK = 4;

    public Safeguard() {
        super(ID, 4, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, DRAGON_COLOR);
        baseBlock = BLOCK;
        if (CardCrawlGame.dungeon != null && AbstractDungeon.currMapNode != null){
            configureCostsOnNewCard();
        }
    }

    @Override
    public void didHoard(){
        updateCost(-1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    @Override
    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
    }

    private void configureCostsOnNewCard() {
        updateCost(-CardCounterPatches.cardsHoardedThisTurn);
    }
}