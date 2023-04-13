package code.cards;

import basemod.helpers.TooltipInfo;
import code.actions.ShedAction;
import code.powers.EmberPower;
import code.util.DragonUtils;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.FreeAttackPower;

import java.util.List;

import static code.ModFile.makeID;
import static code.util.Wiz.applyToSelf;
import static code.util.Wiz.atb;

public class Inhale extends AbstractEasyCard {
    public final static String ID = makeID("Inhale");

    private final static int BLOCK = 8;
    private final static int UPGRADE_BLOCK = 3;
    private final static int EMBER_GAIN = 1;
    private final static int UPGRADE_EMBER_GAIN = 1;

    public Inhale() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = EMBER_GAIN;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToSelf(new EmberPower(p, magicNumber));
        applyToSelf(new FreeAttackPower(p, 1));
    }

    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
        upgradeMagicNumber(UPGRADE_EMBER_GAIN);
    }
}