package code.cards.nests;

import code.powers.CauterizePower;
import code.powers.nestpowers.VolcanicNestPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DemonFormPower;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class VolcanicNest extends AbstractNestCard {
    public final static String ID = makeID("VolcanicNest");
    private final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    private final static int CAUTERIZE_APPLICATION = 1;
    private final static int IMMEDIATE_CAUTERIZE_APPLICATION = 1;

    public VolcanicNest() {
        super(ID, 1, CardRarity.RARE);
        baseMagicNumber = magicNumber = CAUTERIZE_APPLICATION;
        baseSecondMagic = secondMagic = IMMEDIATE_CAUTERIZE_APPLICATION;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (upgraded){
            for (AbstractMonster monster : getEnemies()){
                applyToEnemy(monster, new CauterizePower(monster, secondMagic));
            }
        }
        applyToSelf(new VolcanicNestPower(p, magicNumber));
    }

    public void upp() {
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}