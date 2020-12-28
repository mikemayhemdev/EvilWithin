package automaton.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class Invalidate extends AbstractBronzeCard {

    public final static String ID = makeID("Invalidate");

    //stupid intellij stuff skill, self, common

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public Invalidate() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        thisEncodes();
        baseAuto = auto = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster q : monsterList()) {
            applyToEnemy(q, autoVuln(q, magicNumber));
        }
    }

    @Override
    public void onCompile(AbstractCard function, boolean forGameplay) {
        if (forGameplay){
            applyToSelf(new VulnerablePower(AbstractDungeon.player,auto, false));
        }
    }

    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
    }
}