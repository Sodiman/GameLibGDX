package com.mygdx.game.glstart.supjump;

import com.mygdx.game.framework.Music;
import com.mygdx.game.framework.Sound;
import com.mygdx.game.framework.gl.Animation;
import com.mygdx.game.framework.gl.Font;
import com.mygdx.game.framework.gl.Texture;
import com.mygdx.game.framework.gl.TextureRegion;
import com.mygdx.game.framework.impl.GLGame;

public class Assets {
    public static Texture background;
    public static TextureRegion backgroundRegion;

    public static Texture items;

    public static Texture air, daun, kebun, kunci, tanah, tanah_basah;
    public static Texture cuaca_cerah, cuaca_mendung, hujan_deras, hujan_gerimis, langit_malam;
    public static Texture hero_front, hero_back, hero_left, hero_right;
    public static Texture door_open, door_close, lantai, tuas_off, tuas_on, sekat;
    public static Texture tembok_a, tembok_b, tembok_kn, tembok_kr, tembok_tengah;
    public static Texture tembok_knpa, tembok_knpb, tembok_krpa, tembok_krpb;
    public static Texture LVL1_OFF, LVL1_ON, LVL1_OPEN, LVL2_OFF, LVL2_ON, LVL2_OPEN, LVL3_OFF, LVL3_ON, LVL3_OPEN;
    public static Texture B_LEVEL, HAND;
    public static Texture B_RESULT, TRY, TRY_ON, NEXT, NEXT_ON;
    public static Texture upTombol, downTombol, leftTombol, righTombol, upTombolPress, downTombolPress, leftTombolPress, righTombolPress;


    public static TextureRegion bupTombol, bdownTombol, bleftTombol, brighTombol, bupTombolPress, bdownTombolPress, bleftTombolPress, brighTombolPress;

    public static Texture ICON, about, PLAY_OFF, PLAY_ON, ABOUT_OFF, ABOUT_ON, EXIT_OFF, EXIT_ON;
    public static TextureRegion bair, bdaun, bkebun, bkunci, btanah, btanah_basah;
    public static TextureRegion bcuaca_cerah, bcuaca_mendung, bhujan_deras, bhujan_gerimis, blangit_malam;
    public static TextureRegion bhero_front, bhero_back, bhero_left, bhero_right;
    public static TextureRegion bdoor_open, bdoor_close, blantai, btuas_off, btuas_on, bsekat;
    public static TextureRegion btembok_a, btembok_b, btembok_kn, btembok_kr, btembok_tengah;
    public static TextureRegion btembok_knpa, btembok_knpb, btembok_krpa, btembok_krpb;
    public static TextureRegion bLVL1_OFF, bLVL1_ON, bLVL1_OPEN, bLVL2_OFF, bLVL2_ON, bLVL2_OPEN, bLVL3_OFF, bLVL3_ON, bLVL3_OPEN;
    public static TextureRegion bB_LEVEL, bHAND;
    public static TextureRegion bB_RESULT, bTRY, bTRY_ON, bNEXT, bNEXT_ON;
    public static TextureRegion bICON, babout, bPLAY_OFF, bPLAY_ON, bABOUT_OFF, bABOUT_ON, bEXIT_OFF, bEXIT_ON;

    public static TextureRegion mainMenu, pauseMenu, ready, gameOver, highScoresRegion, logo, soundOn, soundOff, arrow, pause, spring, castle;
    public static Animation coinAnim;
    public static Animation bobJump;
    public static Animation bobFall;
    public static TextureRegion bobHit;
    public static Animation squirrelFly;
    public static TextureRegion platform;
    public static Animation brakingPlatform;
    public static Font font;

    public static Music music;
    public static Sound jumpSound;
    public static Sound highJumpSound;
    public static Sound hitSound;
    public static Sound coinSound;
    public static Sound clickSound;

    public static void load(GLGame game) {
        air = new Texture(game,"Air.png");
        cuaca_cerah = new Texture(game,"Cuaca_Cerah.png");
        cuaca_mendung = new Texture(game,"Cuaca_Mendung.png");
        daun = new Texture(game,"Daun.png");
        door_open = new Texture(game,"Door_Open.png");
        door_close = new Texture(game,"Door_Close.png");
        hero_back = new Texture(game,"Hero_Back.png");
        hero_front = new Texture(game,"Hero_Front.png");
        hero_left = new Texture(game,"Hero_Left.png");
        hero_right = new Texture(game,"Hero_Right.png");
        hujan_deras = new Texture(game,"Hujan_Deras.png");
        hujan_gerimis = new Texture(game,"Hujan_Gerimis.png");
        kebun = new Texture(game,"Kebun.png");
        kunci = new Texture(game,"Kunci.png");
        langit_malam = new Texture(game,"Langit_Malam.png");
        lantai = new Texture(game,"Lantai1.png");
        sekat = new Texture(game,"Sekat.png");
        tanah = new Texture(game,"Tanah.png");
        tanah_basah = new Texture(game,"Tanah_Basah.png");
        tembok_a = new Texture(game,"Tembok_A.png");
        tembok_b = new Texture(game,"Tembok_B.png");
        tembok_kn = new Texture(game,"Tembok_KN.png");
        tembok_knpa = new Texture(game,"Tembok_KNPA.png");
        tembok_knpb = new Texture(game,"Tembok_KNPB.png");
        tembok_kr = new Texture(game,"Tembok_KRPB.png");
        tembok_krpa = new Texture(game,"Tembok_KRPA.png");
        tembok_krpb = new Texture(game,"Tembok_KRPB.png");
        tembok_tengah = new Texture(game,"Tembok_Tengah.png");
        tuas_off = new Texture(game,"Tuas_OFF.png");
        tuas_on = new Texture(game,"Tuas_ON.png");
        LVL1_OFF = new Texture(game,"LVL1_OFF.png");
        LVL1_ON = new Texture(game,"LVL1_ON.png");
        LVL1_OPEN = new Texture(game,"LVL1_OPEN.png");
        LVL2_OFF = new Texture(game,"LVL2_OFF.png");
        LVL2_ON = new Texture(game,"LVL2_ON.png");
        LVL2_OPEN = new Texture(game,"LVL2_OPEN.png");
        LVL3_OFF = new Texture(game,"LVL3_OFF.png");
        LVL3_ON = new Texture(game,"LVL3_ON.png");
        LVL3_OPEN = new Texture(game,"LVL3_OPEN.png");
        B_LEVEL = new Texture(game,"B_LEVEL.png");
        HAND = new Texture(game,"HAND.png");
        B_RESULT = new Texture(game,"B_RESULT.png");
        TRY = new Texture(game,"TRY.png");
        TRY_ON = new Texture(game,"TRY_ON.png");
        NEXT = new Texture(game,"NEXT.png");
        NEXT_ON = new Texture(game,"NEXT_ON.png");
        PLAY_OFF = new Texture(game,"PLAY_OFF.png");
        PLAY_ON = new Texture(game,"PLAY_ON.png");
        ABOUT_OFF = new Texture(game,"ABOUT_OFF.png");
        ABOUT_ON = new Texture(game,"ABOUT_ON.png");
        EXIT_OFF = new Texture(game,"EXIT_OFF.png");
        EXIT_ON = new Texture(game,"EXIT_ON.png");
        ICON = new Texture(game,"icon.png");
        about = new Texture(game,"ABOUT.png");
        upTombol = new Texture(game,"UP.png");
        downTombol = new Texture(game,"DOWN.png");
        leftTombol = new Texture(game,"RIGHT.png");
        righTombol = new Texture(game,"LEFT.png");
        upTombolPress = new Texture(game,"UPPRESS.png");
        downTombolPress = new Texture(game,"DOWNPRESS.png");
        leftTombolPress = new Texture(game,"LEFTPRESS.png");
        righTombolPress = new Texture(game,"RIGHTPRESS.png");


        bair = new TextureRegion(air, 0,0,16,16);
        bcuaca_cerah = new TextureRegion(cuaca_cerah,0,0,16,16);
        bcuaca_mendung = new TextureRegion(cuaca_mendung,0,0,16,16);
        bdaun = new TextureRegion(daun,0,0,16,16);
        bdoor_open = new TextureRegion(door_open,0,0,16,16);
        bdoor_close = new TextureRegion(door_close,0,0,16,16);
        bhero_back = new TextureRegion(hero_back,0,0,16,16 );
        bhero_front = new TextureRegion(hero_front,0,0,16,16);
        bhero_left = new TextureRegion(hero_left,0,0,16,16);
        bhero_right = new TextureRegion(hero_right,0,0,16,16);
        bhujan_deras = new TextureRegion(hujan_deras,0,0,16,16);
        bhujan_gerimis = new TextureRegion(hujan_gerimis,0,0,16,16);
        bkebun = new TextureRegion(kebun,0,0,16,16);
        bkunci = new TextureRegion(kunci,0,0,16,16);
        blangit_malam = new TextureRegion(langit_malam,0,0,16,16);
        blantai = new TextureRegion(lantai,0,0,16,16);
        bsekat = new TextureRegion(sekat,0,0,16,16);
        btanah = new TextureRegion(tanah,0,0,16,16);
        btanah_basah = new TextureRegion(tanah_basah,0,0,16,16);
        btembok_a = new TextureRegion(tembok_a,0,0,16,16);
        btembok_b = new TextureRegion(tembok_b,0,0,16,16);
        btembok_kn = new TextureRegion(tembok_kn,0,0,16,16);
        btembok_knpa = new TextureRegion(tembok_knpa,0,0,16,16);
        btembok_knpb = new TextureRegion(tembok_knpb,0,0,16,16);
        btembok_kr = new TextureRegion(tembok_kr,0,0,16,16);
        btembok_krpa = new TextureRegion(tembok_krpa,0,0,16,16);
        btembok_krpb = new TextureRegion(tembok_krpb,0,0,16,16);
        btembok_tengah = new TextureRegion(tembok_tengah,0,0,16,16);
        btuas_off = new TextureRegion(tuas_off,0,0,16,16);
        btuas_on = new TextureRegion(tuas_on,0,0,16,16);
        bLVL1_OFF = new TextureRegion(LVL1_OFF,0,0,129,48);
        bLVL1_ON = new TextureRegion(LVL1_ON,0,0,129,48);
        bLVL1_OPEN = new TextureRegion(LVL1_OPEN,0,0,129,48);
        bLVL2_OFF = new TextureRegion(LVL2_OFF,0,0,129,48);
        bLVL2_ON = new TextureRegion(LVL2_ON,0,0,129,48);
        bLVL2_OPEN = new TextureRegion(LVL1_OPEN,0,0,129,48);
        bLVL3_OFF = new TextureRegion(LVL3_OFF,0,0,129,48);
        bLVL3_ON = new TextureRegion(LVL3_ON,0,0,129,48);
        bLVL3_OPEN = new TextureRegion(LVL3_OPEN,0,0,129,48);
        bB_LEVEL = new TextureRegion(B_LEVEL,0,0,384,368);
        bHAND = new TextureRegion(HAND,0,0,64,48);
        bB_RESULT = new TextureRegion(B_RESULT,0,0,384,368);
        bTRY = new TextureRegion(TRY,0,0,129,48);
        bTRY_ON = new TextureRegion(TRY_ON,0,0,129,48);
        bNEXT = new TextureRegion(NEXT,0,0,129,48);
        bNEXT_ON = new TextureRegion(NEXT_ON,0,0,129,48);
        bPLAY_OFF = new TextureRegion(PLAY_OFF,0,0,129,48);
        bPLAY_ON = new TextureRegion(PLAY_ON,0,0,129,48);
        bABOUT_OFF = new TextureRegion(ABOUT_OFF,0,0,129,48);
        bABOUT_ON = new TextureRegion(ABOUT_ON,0,0,129,48);
        bEXIT_OFF = new TextureRegion(EXIT_OFF,0,0,129,48);
        bEXIT_ON = new TextureRegion(EXIT_ON,0,0,129,48);
        bICON = new TextureRegion(ICON,0,0,384,368);
        babout = new TextureRegion(about,0,0,384,368);
        bupTombol = new TextureRegion(upTombol,0,0,80, 80);
        bdownTombol = new TextureRegion(downTombol,0,0,80, 80);
        bleftTombol = new TextureRegion(leftTombol,0,0,80, 80);
        brighTombol = new TextureRegion(righTombol,0,0,80, 80);
        bupTombolPress = new TextureRegion(upTombolPress,0,0,80, 80);
        bdownTombolPress = new TextureRegion(downTombolPress,0,0,80, 80);
        bleftTombolPress = new TextureRegion(leftTombolPress,0,0,80, 80);
        brighTombolPress = new TextureRegion(righTombolPress,0,0,80, 80);


        background = new Texture(game,"background.png");
        backgroundRegion = new TextureRegion(background, 0, 0, 480, 768);

        items = new Texture(game, "items.png");
        mainMenu = new TextureRegion(items, 0, 224, 300, 110);
        pauseMenu = new TextureRegion(items, 224, 128, 192, 96);
        ready = new TextureRegion(items, 320, 224, 192, 32);
        gameOver = new TextureRegion(items, 352, 256, 160, 96);
        highScoresRegion = new TextureRegion(Assets.items, 0, 257, 300, 110 / 3);
        logo = new TextureRegion(items, 0, 352, 274, 142);
        soundOff = new TextureRegion(items, 0, 0, 64, 64);
        soundOn = new TextureRegion(items, 64, 0, 64, 64);
        arrow = new TextureRegion(items, 0, 64, 64, 64);
        pause = new TextureRegion(items, 64, 64, 64, 64);
        spring = new TextureRegion(items, 128, 0, 32, 32);
        castle = new TextureRegion(items, 128, 64, 64, 64);

        coinAnim = new Animation(0.2f,
                new TextureRegion(items, 128, 32, 32, 32),
                new TextureRegion(items, 160, 32, 32, 32),
                new TextureRegion(items, 192, 32, 32, 32),
                new TextureRegion(items, 160, 32, 32, 32));

        bobJump = new Animation(0.2f,
                new TextureRegion(items, 0, 128, 32, 32),
                new TextureRegion(items, 32, 128, 32, 32));
        bobFall = new Animation(0.2f,
                new TextureRegion(items, 64, 128, 32, 32),
                new TextureRegion(items, 96, 128, 32, 32));

        bobHit = new TextureRegion(items, 128, 128, 32, 32);
        squirrelFly = new Animation(0.2f,
                new TextureRegion(items, 0, 160, 32, 32),
                new TextureRegion(items, 32, 160, 32, 32));
        platform = new TextureRegion(items, 64, 160, 64, 16);
        brakingPlatform = new Animation(0.2f,
                new TextureRegion(items, 64, 160, 64, 16),
                new TextureRegion(items, 64, 176, 64, 16),
                new TextureRegion(items, 64, 192, 64, 16),
                new TextureRegion(items, 64, 208, 64, 16));

        font = new Font(items, 224, 0, 16, 16, 20);

        music = game.getAudio().newMusic("music.mp3");
        music.setLooping(true);
        music.setVolume(0.5f);
        if (Settings.soundEnabled)
            music.play();
        jumpSound = game.getAudio().newSound("jump.ogg");
        highJumpSound = game.getAudio().newSound("highjump.ogg");
        hitSound = game.getAudio().newSound("hit.ogg");
        coinSound = game.getAudio().newSound("coin.ogg");
        clickSound = game.getAudio().newSound("click.ogg");
    }

    public static void reload() {
        background.reload();
        items.reload();
        if (Settings.soundEnabled)
            music.play();
    }

    public static void playSound(Sound sound) {
        if (Settings.soundEnabled)
            sound.play(1);
    }
}
