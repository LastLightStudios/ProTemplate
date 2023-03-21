package code.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static code.ModFile.makeID;
import static code.util.Wiz.adp;

public class TradeOfferAction extends AbstractGameAction {
    public final static String ID = makeID("TradeOfferAction");
    public static final String[] UIStrings = CardCrawlGame.languagePack.getUIString(ID).TEXT;
    private AbstractCard gemToInsert;
    private boolean isUpgraded;

    public TradeOfferAction(AbstractCard gem, boolean upgraded) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.gemToInsert = gem;
        this.isUpgraded = upgraded;
    }

    @Override
    public void update() {
        if (duration == Settings.ACTION_DUR_FAST) {
            CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

            for (AbstractCard c : adp().drawPile.group) {
                tmp.addToRandomSpot(c);
            }
            for (AbstractCard c : adp().discardPile.group) {
                tmp.addToRandomSpot(c);
            }
            if (tmp.size() == 0) {
                isDone = true;
                return;
            }
            // if there's only one card in discard+draw combined
            if (tmp.size() == 1) {
                AbstractCard card = tmp.getTopCard();
                if (adp().drawPile.contains(card)){
                    adp().drawPile.addToTop(gemToInsert);
                    adp().drawPile.removeCard(card);
                    adp().hand.group.add(card);
                    adp().hand.refreshHandLayout();
                } else if (adp().discardPile.contains(card)){
                    adp().discardPile.addToTop(gemToInsert);
                    adp().discardPile.removeCard(card);
                    adp().hand.group.add(card);
                    adp().hand.refreshHandLayout();
                } else {
                    // how tf did this happen
                }
                isDone = true;
                return;
            } else {
                tmp.sortByRarityPlusStatusCardType(true);
                if (!isUpgraded){
                    AbstractDungeon.gridSelectScreen.open(tmp,1, false, UIStrings[1]);
                } else {
                    AbstractDungeon.gridSelectScreen.open(tmp,1, false, UIStrings[2]);
                }
                tickDuration();
            }
        }
        if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0) {
            AbstractCard card = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
            if (adp().drawPile.contains(card)){
                int index = adp().drawPile.group.indexOf(card);
                adp().drawPile.group.set(index, gemToInsert);
                adp().hand.group.add(card);
            } else if (adp().discardPile.contains(card)){
                int index = adp().discardPile.group.indexOf(card);
                adp().discardPile.group.set(index, gemToInsert);
                adp().hand.group.add(card);
            } else {
                // but like srsly this should never happen
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            isDone = true;
        }
    }
}
