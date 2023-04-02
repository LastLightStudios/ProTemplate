package code.cards;

import basemod.BaseMod;
import code.powers.CauterizePower;
import code.powers.PridePower;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class VolcanicHoard extends AbstractEasyCard {
    public final static String ID = makeID("VolcanicHoard");
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("Hoard"));

    private final static int PRIDE_GAIN = 1;
    private final static int UPGRADE_PRIDE_GAIN = 1;
    private final static int CAUTERIZE_APPLICATION = 1;
    private final static int UPGRADE_CAUTERIZE_APPLICATION = 1;

    public VolcanicHoard() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = PRIDE_GAIN;
        baseSecondMagic = secondMagic = CAUTERIZE_APPLICATION;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SelectCardsInHandAction(BaseMod.MAX_HAND_SIZE, uiStrings.TEXT[2], true, true, c -> true, selectedCards -> {
            selectedCards.forEach(card -> {
                atb(new ExhaustSpecificCardAction(card, p.hand));
            });
            for (int i = 0; i < selectedCards.size(); ++i) {
                applyToSelf(new PridePower(p, magicNumber));
            }
            for (AbstractMonster mon : getEnemies()){
                applyToEnemy(mon, new CauterizePower(mon, secondMagic * selectedCards.size()));
            }
        }));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_PRIDE_GAIN);
        upgradeSecondMagic(UPGRADE_CAUTERIZE_APPLICATION);
    }
}