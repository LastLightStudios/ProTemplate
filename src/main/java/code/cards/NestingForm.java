package code.cards;

import code.DragonCharacterFile;
import code.cards.AbstractEasyCard;
import code.powers.NestingFormPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;
import static code.util.Wiz.applyToSelf;

public class NestingForm extends AbstractEasyCard {
    public final static String ID = makeID("NestingForm");
    private final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    private final static int UPGRADED_COST = 2;

    public NestingForm() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF, DragonCharacterFile.Enums.DRAGON_COLOR);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new NestingFormPower(p, 1));
    }

    public void upp() {
        upgradeBaseCost(UPGRADED_COST);
    }
}