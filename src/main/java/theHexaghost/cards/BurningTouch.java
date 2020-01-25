package theHexaghost.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHexaghost.actions.BurnAction;
import theHexaghost.powers.BurnPower;

public class BurningTouch extends AbstractHexaCard {

    public final static String ID = makeID("BurningTouch");

    //stupid intellij stuff SKILL, ENEMY, COMMON

    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 2;

    public BurningTouch() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                if (m.hasPower(BurnPower.POWER_ID)) {
                    addToTop(new BurnAction(m, magicNumber));
                }
            }
        });
        atb(new BurnAction(m, magicNumber));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPG_MAGIC);
        }
    }
}