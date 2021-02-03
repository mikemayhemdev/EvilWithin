package gremlin;

import basemod.BaseMod;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import gremlin.cards.*;
import gremlin.cards.SharpenBlades;
import gremlin.characters.GremlinCharacter;
import gremlin.orbs.*;
import gremlin.patches.AbstractCardEnum;
import gremlin.patches.GremlinEnum;
import gremlin.patches.GremlinModSaveState;
import gremlin.powers.AbstractGremlinPower;
import gremlin.relics.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import static basemod.BaseMod.addRelicToCustomPool;


@SpireInitializer
public class GremlinMod implements EditCharactersSubscriber, EditStringsSubscriber, EditKeywordsSubscriber,
        EditRelicsSubscriber, EditCardsSubscriber, OnStartBattleSubscriber, PostBattleSubscriber{
    private static String modID = "gremlin";

    private static final Color GREMLIN_COLOR = CardHelper.getColor(205.0f, 92.0f, 92.0f);
    private static final String ASSETS_FOLDER = "gremlinResources/images";

    private static final String ATTACK_CARD = "512/bg_attack_gremlin.png";
    private static final String SKILL_CARD = "512/bg_skill_gremlin.png";
    private static final String POWER_CARD = "512/bg_power_gremlin.png";
    private static final String ENERGY_ORB = "512/card_gremlin_orb.png";

    private static final String ATTACK_CARD_PORTRAIT = "1024/bg_attack_gremlin.png";
    private static final String SKILL_CARD_PORTRAIT = "1024/bg_skill_gremlin.png";
    private static final String POWER_CARD_PORTRAIT = "1024/bg_power_gremlin.png";
    private static final String ENERGY_ORB_PORTRAIT = "1024/card_gremlin_orb.png";

    private static final String CHAR_BUTTON = "charSelect/button.png";
    private static final String CHAR_PORTRAIT = "charSelect/portrait.png";

    public static String getResourcePath(String resource) {
        return ASSETS_FOLDER + "/" + resource;
    }

    public static final Logger logger = LogManager.getLogger(GremlinMod.class);

    public GremlinMod() {
        logger.info("Loading GremlinMod.");
        BaseMod.subscribe(this);

        BaseMod.addColor(AbstractCardEnum.GREMLIN,
                GREMLIN_COLOR, GREMLIN_COLOR, GREMLIN_COLOR, GREMLIN_COLOR, GREMLIN_COLOR, GREMLIN_COLOR, GREMLIN_COLOR,
                getResourcePath(ATTACK_CARD), getResourcePath(SKILL_CARD), getResourcePath(POWER_CARD),
                getResourcePath(ENERGY_ORB), getResourcePath(ATTACK_CARD_PORTRAIT),
                getResourcePath(SKILL_CARD_PORTRAIT), getResourcePath(POWER_CARD_PORTRAIT),
                getResourcePath(ENERGY_ORB_PORTRAIT));

        BaseMod.addSaveField("Gremlins", new GremlinModSaveState());
    }

    public static String getModID() {
        return modID;
    }

    public static void initialize() {
        new GremlinMod();
    }

    @Override
    public void receiveEditCharacters() {
        logger.info("Adding Gremlins.");
        BaseMod.addCharacter(new GremlinCharacter("The Gremlins"),
                getResourcePath(CHAR_BUTTON),
                getResourcePath(CHAR_PORTRAIT),
                GremlinEnum.GREMLIN);
    }

    @Override
    public void receiveEditRelics() {
        logger.info("Adding Gremlin relics.");
        // Starter
        addRelicToCustomPool(new GremlinKnob(), AbstractCardEnum.GREMLIN);

        // Common
        addRelicToCustomPool(new SupplyScroll(), AbstractCardEnum.GREMLIN);

        // Uncommon
        addRelicToCustomPool(new WizardStaff(), AbstractCardEnum.GREMLIN);

        // Rare
        addRelicToCustomPool(new PricklyShields(), AbstractCardEnum.GREMLIN);

        // Boss
        addRelicToCustomPool(new GremlinBomb(), AbstractCardEnum.GREMLIN);

        // Shop
        addRelicToCustomPool(new StolenMerchandise(), AbstractCardEnum.GREMLIN);
    }

    @Override
    public void receiveEditCards() {
        logger.info("Adding Gremlin Cards.");

        //Basic
        BaseMod.addCard(new Strike());
        BaseMod.addCard(new Defend());
        BaseMod.addCard(new GremlinDance());
        BaseMod.addCard(new TagTeam());

        //Common Attacks
        BaseMod.addCard(new CatScratch());
        BaseMod.addCard(new DaggerDance());
        BaseMod.addCard(new Glimmer());
        BaseMod.addCard(new Jeer());
        BaseMod.addCard(new Pinprick());
        BaseMod.addCard(new PourSalt());
        BaseMod.addCard(new Presto());
        BaseMod.addCard(new Pretaliation());
        BaseMod.addCard(new ToeStub());
        BaseMod.addCard(new TwistTheKnife());

        //Common Skills
        BaseMod.addCard(new BubbleBarrier());
        BaseMod.addCard(new BulkUp());
        BaseMod.addCard(new Changeo());
        BaseMod.addCard(new GlitterGuard());
        BaseMod.addCard(new GremlinArms());
        BaseMod.addCard(new GremlinMeal());
        BaseMod.addCard(new Irritability());
        BaseMod.addCard(new Patsy());
        BaseMod.addCard(new Tadah());
        BaseMod.addCard(new Tricksy());

        //Uncommon Attacks
        BaseMod.addCard(new AggressiveDefense());
        BaseMod.addCard(new BurlyBlow());
        BaseMod.addCard(new CounterStrike());
        BaseMod.addCard(new Dazzle());
        BaseMod.addCard(new FanOfKnives());
        BaseMod.addCard(new FeelTheAudience());
        BaseMod.addCard(new Flurry());
        BaseMod.addCard(new FollowThrough());
        BaseMod.addCard(new GremlinOffensive());
        BaseMod.addCard(new GremlinToss());
        BaseMod.addCard(new IrksomeBlow());
        BaseMod.addCard(new Kablamo());
        BaseMod.addCard(new Pickpocket());
        BaseMod.addCard(new PinNeedle());
        BaseMod.addCard(new ProperTools());
        BaseMod.addCard(new Stupend());

        //Uncommon Skills
        BaseMod.addCard(new ArmsTheft());
        BaseMod.addCard(new Astound());
        BaseMod.addCard(new EdibleArmor());
        BaseMod.addCard(new Mockery());
        BaseMod.addCard(new PartyStick());
        BaseMod.addCard(new RageBreak());
        BaseMod.addCard(new Raid());
        BaseMod.addCard(new Revel());
        BaseMod.addCard(new Rhythm());
        BaseMod.addCard(new Scatter());
        BaseMod.addCard(new ShankStone());
        BaseMod.addCard(new SharpenBlades());
        BaseMod.addCard(new ShowOfHands());
        BaseMod.addCard(new Whiz());

        //Uncommon Powers
        BaseMod.addCard(new Enthusiasm());
        BaseMod.addCard(new Heckle());
        BaseMod.addCard(new InfiniteBlocks());
        BaseMod.addCard(new MakeshiftArmor());
        BaseMod.addCard(new Polish());
        BaseMod.addCard(new Wizardry());

        //Rare Attacks
        BaseMod.addCard(new Exacerbate());
        BaseMod.addCard(new FlipOut());
        BaseMod.addCard(new Fury());
        BaseMod.addCard(new SecondVolley());
        BaseMod.addCard(new ShowStopper());

        //Rare Skills
        BaseMod.addCard(new BrokenShin());
        BaseMod.addCard(new Duplicate());
        BaseMod.addCard(new Erupt());
        BaseMod.addCard(new FairyDust());
        BaseMod.addCard(new Necromancy());

        //Rare Powers
        BaseMod.addCard(new CongaLine());
        BaseMod.addCard(new Encore());
        BaseMod.addCard(new Nob());
        BaseMod.addCard(new ShadowShiv());
        BaseMod.addCard(new TargetWeakness());
        BaseMod.addCard(new Unforgiving());

        //Special
        BaseMod.addCard(new Bellow());
        BaseMod.addCard(new Rush());
        BaseMod.addCard(new SkullBash());
    }

    @Override
    public void receiveEditStrings() {
        String language = "eng";
        logger.info("Loading GremlinMod Strings.");
        // CharacterStrings
        BaseMod.loadCustomStringsFile(CharacterStrings.class,
                "gremlinResources/localization/" +language+"/CharacterStrings.json");
        // CardStrings
        BaseMod.loadCustomStringsFile(CardStrings.class,
                "gremlinResources/localization/" +language+"/CardStrings.json");

        // PowerStrings
        BaseMod.loadCustomStringsFile(PowerStrings.class,
                "gremlinResources/localization/" +language+"/PowerStrings.json");

        // OrbStrings
        BaseMod.loadCustomStringsFile(OrbStrings.class,
                "gremlinResources/localization/" +language+"/OrbStrings.json");

        // RelicStrings
        BaseMod.loadCustomStringsFile(RelicStrings.class,
                "gremlinResources/localization/" +language+"/RelicStrings.json");

        // UIStrings
        BaseMod.loadCustomStringsFile(UIStrings.class,
                "gremlinResources/localization/" +language+"/UIStrings.json");

        // EventStrings
        BaseMod.loadCustomStringsFile(EventStrings.class,
                "gremlinResources/localization/" +language+"/EventStrings.json");
    }

    @Override
    public void receiveEditKeywords() {
        String language = "eng";
        final Gson gson = new Gson();
        String jsonPath = "gremlinResources/localization/" +language+"/";
        final String json = Gdx.files.internal(jsonPath + "KeywordStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        final Keyword[] keywords = gson.fromJson(json, Keyword[].class);
        if (keywords != null) {
            for (final Keyword keyword : keywords) {
                logger.info("Adding Keyword - " + keyword.PROPER_NAME + " | " + keyword.NAMES[0]);
                BaseMod.addKeyword(keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }

    @Override
    public void receiveOnBattleStart(AbstractRoom abstractRoom) {
        if (AbstractDungeon.player instanceof GremlinCharacter){
            ((GremlinCharacter)(AbstractDungeon.player)).startOfBattle();
        }
    }

    @Override
    public void receivePostBattle(AbstractRoom abstractRoom) {
        if (AbstractDungeon.player instanceof GremlinCharacter){
            ((GremlinCharacter)(AbstractDungeon.player)).updateMobState();
        }
    }

    public static GremlinStandby getGremlinOrb(String gremlinName) {
        int hp = AbstractDungeon.player.currentHealth;
        return getGremlinOrb(gremlinName, hp);
    }

    public static ArrayList<String> getGremlinStrings(){
        ArrayList<String> grems = new ArrayList<>();
        grems.add("angry");
        grems.add("fat");
        grems.add("shield");
        grems.add("sneak");
        grems.add("wizard");
        return grems;
    }

    public static GremlinStandby getGremlinOrb(String gremlinName, int hp){
        switch (gremlinName){
            case "angry":
                return new MadGremlin(hp);
            case "fat":
                return new FatGremlin(hp);
            case "shield":
                return new ShieldGremlin(hp);
            case "sneak":
                return new SneakyGremlin(hp);
            case "wizard":
                return new GremlinWizard(hp);
        }
        return new MadGremlin(hp);
    }

    public static void onSwap(){
        for(AbstractPower p: AbstractDungeon.player.powers){
            if(p instanceof AbstractGremlinPower){
                ((AbstractGremlinPower) p).onGremlinSwap();
            }
        }
        for(AbstractCard c: AbstractDungeon.player.hand.group){
            if(c instanceof AbstractGremlinCard){
                ((AbstractGremlinCard) c).onGremlinSwap();
            }
        }
        for(AbstractCard c: AbstractDungeon.player.drawPile.group){
            if(c instanceof AbstractGremlinCard){
                ((AbstractGremlinCard) c).onGremlinSwapInDeck();
            }
        }
        for(AbstractCard c: AbstractDungeon.player.discardPile.group) {
            if (c instanceof AbstractGremlinCard) {
                ((AbstractGremlinCard) c).onGremlinSwapInDeck();
            }
        }
        for(AbstractRelic relic: AbstractDungeon.player.relics){
            if(relic instanceof AbstractGremlinRelic){
                ((AbstractGremlinRelic) relic).onGremlinSwap();
            }
        }
    }

    public static void onGremlinDeath(){
        for(AbstractRelic relic: AbstractDungeon.player.relics){
            if(relic instanceof AbstractGremlinRelic){
                ((AbstractGremlinRelic) relic).onGremlinDeath();
            }
        }
    }
}