package code.cards;

import code.powers.BurningScalesPower;
import code.powers.CauterizePower;
import code.powers.LoseBurningScalesPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlameBarrierEffect;

import static code.DragonCharacterFile.Enums.DRAGON_COLOR;
import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class BurningScales extends AbstractEasyCard {
    public final static String ID = makeID("BurningScales");

    private final static int BLOCK = 24;
    private final static int UPGRADE_BLOCK = 6;
    private final static int RETURN_DMG = 3;
    private final static int UPGRADE_RETURN_DMG = 2;
    private final static int CAUTERIZE_APPLICATION = 2;
    private final static int UPGRADE_CAUTERIZE_APPLICATION = 2;


    public BurningScales() {
        super(ID, 3, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, DRAGON_COLOR);
        baseBlock = BLOCK;
        magicNumber = baseMagicNumber = RETURN_DMG;
        baseSecondMagic = secondMagic = CAUTERIZE_APPLICATION;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (Settings.FAST_MODE){
            vfx(new FlameBarrierEffect(p.hb.cX, p.hb.cY), 0.1f);
        } else {
            vfx(new FlameBarrierEffect(p.hb.cX, p.hb.cY), 0.5f);
        }
        blck();
        for (AbstractMonster mon : getEnemies()){
            applyToEnemy(mon, new CauterizePower(mon, secondMagic));
        }
        applyToSelf(new BurningScalesPower(p, magicNumber));
        applyToSelf(new LoseBurningScalesPower(p, magicNumber));
    }

    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
        upgradeSecondMagic(UPGRADE_CAUTERIZE_APPLICATION);
    }
}