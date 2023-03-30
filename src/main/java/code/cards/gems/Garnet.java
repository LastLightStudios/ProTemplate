package code.cards.gems;

import code.actions.HoardThisCardAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import static code.ModFile.makeID;
import static code.util.Wiz.atb;
import static code.util.Wiz.getEnemies;

public class Garnet extends AbstractGemCard {
    public final static String ID = makeID("Garnet");

    private final static int VULN_APPLIED = 1; //Amount of Vuln Applied
    private final static int UPGRADE_VULN_APPLIED = 1; //increase Amount of Vuln Applied

    public Garnet() {
        super(ID, CardType.SKILL, CardRarity.SPECIAL, CardTarget.ALL_ENEMY);
        baseMagicNumber = magicNumber = VULN_APPLIED;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mon : getEnemies()){
            atb(new ApplyPowerAction(mon, p, new VulnerablePower(mon, magicNumber, false), magicNumber, true, AbstractGameAction.AttackEffect.NONE));
        }
        atb(new HoardThisCardAction(p, this));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_VULN_APPLIED);
    }
}