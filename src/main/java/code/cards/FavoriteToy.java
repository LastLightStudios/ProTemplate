package code.cards;

import code.actions.ShedAction;
import code.powers.FavoriteToyPower;
import com.evacipated.cardcrawl.mod.stslib.actions.common.MultiGroupSelectAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class FavoriteToy extends AbstractEasyCard {
    public final static String ID = makeID("FavoriteToy");
    public static final String[] UIStrings = CardCrawlGame.languagePack.getUIString(makeID("FavoriteToyScreen")).TEXT;

    private final static int NO_OF_TURNS = 2;
    private final static int NO_OF_CARDS_SELECTED = 1;
    private final static int UPGRADE_COST = 2;

    public FavoriteToy() {
        super(ID, 3, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = NO_OF_TURNS;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new MultiGroupSelectAction(UIStrings[0], (list, map) ->
        {
            for (AbstractCard c : list) {
                makeInHand(c);
                applyToSelf(new FavoriteToyPower(adp(), magicNumber, c));
            }
        }, NO_OF_CARDS_SELECTED, CardGroup.CardGroupType.DRAW_PILE));
        atb(new ShedAction(p, secondMagic));
    }

    public void upp() {
        upgradeBaseCost(UPGRADE_COST);
    }
}