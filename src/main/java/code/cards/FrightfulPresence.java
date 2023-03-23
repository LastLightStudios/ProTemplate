package code.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FlameBarrierPower;
import com.megacrit.cardcrawl.powers.RagePower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import static code.DragonCharacterFile.Enums.DRAGON_COLOR;
import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class FrightfulPresence extends AbstractEasyCard {
    public final static String ID = makeID("FrightfulPresence");
    private final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    private final static int WEAK_VULN_APPLIED = 99;


    public FrightfulPresence() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY, DRAGON_COLOR);
        baseMagicNumber = magicNumber = WEAK_VULN_APPLIED;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!upgraded){
            applyToEnemy(m, new VulnerablePower(m, magicNumber, false));
            applyToEnemy(m, new WeakPower(m, magicNumber, false));
        } else {
            for (AbstractMonster mon : getEnemies()){
                applyToEnemy(m, new VulnerablePower(mon, magicNumber, false));
                applyToEnemy(m, new WeakPower(mon, magicNumber, false));
            }
        }
    }

    public void upp() {
        target = CardTarget.ALL_ENEMY;
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}