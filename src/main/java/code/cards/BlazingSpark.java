package code.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.DragonCharacterFile.Enums.DRAGON_COLOR;
import static code.ModFile.makeID;
import static code.util.Wiz.atb;

public class BlazingSpark extends AbstractEasyCard {
    public final static String ID = makeID("BlazingSpark");

    public BlazingSpark() {
        super(ID, 0, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY, DRAGON_COLOR);
        baseDamage = 1;
        baseMagicNumber = 1;
        magicNumber = baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if(upgraded){
            atb(new DrawCardAction(1));
        }
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
    }


    @Override
    public void updateDescription() {
        description = cardStrings.UPGRADE_DESCRIPTION;
    }

    public void upp() {

        upgradeBlock(3);
    }
}