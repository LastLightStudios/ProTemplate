package code.cards;

import code.actions.ConflagrationAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.DragonCharacterFile.Enums.DRAGON_COLOR;
import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class Conflagration extends AbstractEasyCard {
    public final static String ID = makeID("Conflagration");


    public Conflagration() {
        super(ID, -1, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY, DRAGON_COLOR);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ConflagrationAction(p, m, this.upgraded, this.freeToPlayOnce, this.energyOnUse));
    }

    public void upp() {
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}