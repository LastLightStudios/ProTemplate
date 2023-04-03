package code.powers;


import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.List;

public interface HoardingPowerInterface {
    default void onHoard(List<AbstractCard> hoardedCardsList) {}
}