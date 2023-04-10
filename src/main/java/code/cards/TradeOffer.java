package code.cards;

import basemod.BaseMod;
import code.util.DragonUtils;
import com.evacipated.cardcrawl.mod.stslib.actions.common.MultiGroupSelectAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.DragonCharacterFile.Enums.DRAGON_COLOR;
import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class TradeOffer extends AbstractEasyCard {
    public final static String ID = makeID("TradeOffer");
    public static final String[] UIStrings = CardCrawlGame.languagePack.getUIString(makeID("TradeOfferScreen")).TEXT;
    private static final int NUM_CARDS_TO_TRADE = 1; //if this changes, will have to change the gem generation code


    public TradeOffer() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.SELF, DRAGON_COLOR);
        selfRetain = false;
        exhaust = true;
        baseMagicNumber = magicNumber = NUM_CARDS_TO_TRADE; //if this changes, will have to change the gem generation code
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new MultiGroupSelectAction(UIStrings[0], (list, map) ->
        {
            AbstractCard gem;
            for (AbstractCard c : list) {
                gem = DragonUtils.returnTrulyRandomCardWithTagInCombat(DragonUtils.CustomTags.GEM).makeCopy();
                if (upgraded){
                    gem.upgrade();
                }
                CardGroup group = map.get(c);
                int index = group.group.indexOf(c);
                group.group.set(index, gem);
                if (adp().hand.group.size() < BaseMod.MAX_HAND_SIZE ) {
                    adp().hand.group.add(c);
                } else {
                    adp().discardPile.group.add(c);
                }
            }
        }, magicNumber, CardGroup.CardGroupType.DRAW_PILE, CardGroup.CardGroupType.DISCARD_PILE));
    }

    public void upp() {
        selfRetain = true;
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}