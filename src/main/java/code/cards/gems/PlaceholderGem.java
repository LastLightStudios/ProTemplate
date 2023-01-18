package code.cards.gems;

import code.cards.AbstractEasyCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;

public class PlaceholderGem extends AbstractEasyCard {
    public final static String ID = makeID("PlaceholderGem");

    public PlaceholderGem() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF, AbstractCard.CardColor.COLORLESS);
        baseBlock = 5;
        exhaust = true;
        //TODO add custom gem tag?
        //tags.add(CardTags.STARTER_DEFEND);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    public void upp() {
        upgradeBlock(3);
    }
}