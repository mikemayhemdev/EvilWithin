package theHexaghost.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class GhostShield extends AbstractHexaCard {

    public final static String ID = makeID("GhostShield");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    private static final int BLOCK = 5;
    private static final int UPG_BLOCK = 2;

    public GhostShield() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = BLOCK;
        isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                if (hasEthereal())
                    blck();
            }
        });
    }

    private boolean hasEthereal() {
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.isEthereal && c != this)
                return true;
        }
        return false;
    }

    public void triggerOnGlowCheck() {
        this.glowColor = hasEthereal() ? AbstractCard.GOLD_BORDER_GLOW_COLOR : AbstractCard.BLUE_BORDER_GLOW_COLOR;// 65
    }// 68

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPG_BLOCK);
        }
    }
}