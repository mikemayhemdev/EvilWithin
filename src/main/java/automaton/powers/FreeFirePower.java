package automaton.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static automaton.AutomatonMod.makeID;

public class FreeFirePower extends AbstractAutomatonPower implements OnFireSubscriber {
    public static final String NAME = "FreeFire";
    public static final String POWER_ID = makeID(NAME);
    public static final PowerType TYPE = PowerType.BUFF;
    public static final boolean TURN_BASED = false;

    public FreeFirePower(int amount) {
        super(NAME, TYPE, TURN_BASED, AbstractDungeon.player, null, amount);
    }

    @Override
    public void onFire(AbstractCard toFire) {
        flash();
        toFire.freeToPlayOnce = true;
        addToTop(new ReducePowerAction(owner, owner, this, 1));
    }
}
