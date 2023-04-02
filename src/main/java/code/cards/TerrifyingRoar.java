package code.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class TerrifyingRoar extends AbstractEasyCard {
    public final static String ID = makeID("TerrifyingRoar");

    private final static int STR_REDUX = 6;
    private final static int UPGRADE_STR_REDUX = 2;

    public TerrifyingRoar() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseMagicNumber = magicNumber = STR_REDUX;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (Settings.FAST_MODE){
            vfx(new ShockWaveEffect(p.hb.cX, p.hb.cY, Settings.RED_TEXT_COLOR, ShockWaveEffect.ShockWaveType.CHAOTIC), 0.3F);
        } else {
            vfx(new ShockWaveEffect(p.hb.cX, p.hb.cY, Settings.RED_TEXT_COLOR, ShockWaveEffect.ShockWaveType.CHAOTIC), 1.5F);
        }
        for (AbstractMonster mon : getEnemies()){
            applyToEnemy(mon, new StrengthPower(mon, -magicNumber));
        }
        for (AbstractMonster mon : getEnemies()){
            if (!mon.hasPower(ArtifactPower.POWER_ID)){
                applyToEnemy(mon, new GainStrengthPower(mon, magicNumber));
            }
        }
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_STR_REDUX);
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}