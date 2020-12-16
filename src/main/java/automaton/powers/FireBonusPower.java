package automaton.powers;

import automaton.actions.RepeatCardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static automaton.AutomatonMod.makeID;

public class FireBonusPower extends AbstractAutomatonPower implements OnFireSubscriber {
    public static final String NAME = "FireBonus";
    public static final String POWER_ID = makeID(NAME);
    public static final PowerType TYPE = PowerType.BUFF;
    public static final boolean TURN_BASED = false;

    public FireBonusPower(int amount) {
        super(NAME, TYPE, TURN_BASED, AbstractDungeon.player, null, amount);
    }

    @Override
    public void onFire(AbstractCard toFire) {
        flash();
        addToTop(new ReducePowerAction(owner, owner, this, 1));
        addToTop(new RepeatCardAction(toFire));
    }
}
