package code.cards.gems;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static code.ModFile.makeID;
import static code.util.Wiz.atb;
import static code.util.Wiz.getEnemies;

public class Amethyst extends AbstractGemCard {
    public final static String ID = makeID("Amethyst");

    private final static int MAGIC_NUMBER = 2; //Enemy Strength Reduction
    private final static int UPGRADE_MAGIC_NUMBER = 2; //increase Enemy Strength Reduction

    public Amethyst() {
        super(ID, CardType.SKILL, CardRarity.SPECIAL, CardTarget.ALL_ENEMY);
        baseMagicNumber = magicNumber = MAGIC_NUMBER;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mon : getEnemies()){
            atb(new ApplyPowerAction(mon, p, new StrengthPower(mon, -magicNumber), -magicNumber, true, AbstractGameAction.AttackEffect.NONE));
        }
        for (AbstractMonster mon : getEnemies()){
            if (!mon.hasPower(ArtifactPower.POWER_ID)) //TODO test if this still works, used to be hard coded "Artifact"
            atb(new ApplyPowerAction(mon, p, new GainStrengthPower(mon, magicNumber), magicNumber, true, AbstractGameAction.AttackEffect.NONE));
        }
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC_NUMBER);
    }
}