package code.cards.nests;

import code.powers.nestpowers.ImposingNestPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;

import static code.ModFile.makeID;
import static code.util.Wiz.*;
import static code.util.Wiz.applyToEnemy;

public class ImposingNest extends AbstractNestCard {
    public final static String ID = makeID("ImposingNest");
    private final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    private final static int ENEMY_STRENGTH_LOSS = 2;
    private final static int IMMEDIATE_STRENGTH_LOSS = 2;

    public ImposingNest() {
        super(ID, 1, CardRarity.UNCOMMON);
        baseMagicNumber = magicNumber = ENEMY_STRENGTH_LOSS;
        baseSecondMagic = secondMagic = IMMEDIATE_STRENGTH_LOSS;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (upgraded){
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
        applyToSelf(new ImposingNestPower(p, magicNumber));
    }

    public void upp() {
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}