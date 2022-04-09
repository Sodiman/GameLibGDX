package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class MyGdxGame extends ApplicationAdapter {

    private SpriteBatch batch;
    private Camera camera;
    private Stage gameStage;
    private BitmapFont font;
    private ImageButton upTombol, downTombol, leftTombol, rightTombol;
    private GambarTexture gambarTexture;
    private Music music;

    private int dx = 0;
    private int dy = 0;
    private int posPlayerX = 2;
    private int posPlayerY = 27;
    public int ARAHPEMAIN = 4;
    public static final int KIRI = 1;
    public static final int KANAN = 2;
    public static final int ATAS = 3;
    public static final int BAWAH = 4;
    private boolean inmenu, inlevel, ingame, inresult = false;

    public static final int MENU = 0;
    public static final int LVL_SEL = 1;
    public static final int ABOUT = 2;
    public static final int EXIT = 3;
    public static final int LVL1 = 11;
    public static final int LVL2 = 12;
    public static final int LVL3 = 13;
    public static final int GAMEOVER = 99;

    private String langkah;
    private String nilai;

    private int openLevel = 11;
    private int curLevel = 11;
    private int level;
    private int score = 0;
    private int step, step1, step2, step3, step4;
    private int pilih = 0;
    private int curMenu = 1;
    private int curResult = 1;
    private int stateTuas = 0;
    private int inventorisKunci = 0;

    private int map[][];
    private int obj[][];

    @Override
    public void create() {
        ExtendViewport viewport = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();
        gameStage = new Stage(viewport, batch);
        Group controlGroup = new Group();
        gameStage.addActor(controlGroup);
        Gdx.input.setInputProcessor(gameStage);
        camera = gameStage.getCamera();
        batch.setProjectionMatrix(camera.combined);

        font = new BitmapFont();
        gambarTexture = new GambarTexture();
        map = gambarTexture.map;
        obj = gambarTexture.obj;
        setSuara();
        buatTombol();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        switch (pilih) {
            case MENU:
                mainMenu();
                break;
            case LVL_SEL:
                menuLevel();
                break;
            case LVL1:
                setLevel1();
                break;
            case LVL2:
                setLevel2();
                break;
            case LVL3:
                setLevel3();
                break;
            case ABOUT:
                aboutGame();
                break;
            case EXIT:
                exitGame();
                break;
            case GAMEOVER:
                menuResult();
                break;
        }
        batch.end();
        gameStage.act();
        gameStage.draw();

        if (ingame == true) {
            music.play();
        } else {
            music.stop();
        }
    }

    private void buatPemain() {
        // ARAHPEMAIN, POSISIX, dan POSISIY
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (j == posPlayerX && i == posPlayerY) {
                    switch (ARAHPEMAIN) {
                        case ATAS:
                            batch.draw(gambarTexture.hero_back, j * 16, i * 16);
                            break;
                        case BAWAH:
                            batch.draw(gambarTexture.hero_front, j * 16, i * 16);
                            break;
                        case KANAN:
                            batch.draw(gambarTexture.hero_right, j * 16, i * 16);
                            break;
                        case KIRI:
                            batch.draw(gambarTexture.hero_left, j * 16, i * 16);
                            break;
                    }
                }
            }
        }
    }

    private void buatTombol() {
        dx = 0;
        dy = 0;
        upTombol = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("UPPRESS.png"))));
        upTombol.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture("UPPRESS.png")));
        upTombol.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture("UP.png")));
        upTombol.setPosition(100, 101);
        upTombol.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (pilih == 0 || pilih == MENU) {
                    inmenu = true;
                    inlevel = false;
                    ingame = false;
                    inresult = false;
                }
                if (inmenu == true) {
                    if (curMenu <= 1) {
                        curMenu = 3;
                    } else {
                        curMenu--;
                    }
                }

                if (pilih == LVL_SEL) {
                    inmenu = false;
                    inlevel = true;
                    ingame = false;
                    inresult = false;
                }
                if (inlevel == true) {
                    if (curLevel <= 11) {
                        curLevel = 13;
                    } else {
                        curLevel--;
                    }
                }

                if (ingame == true) {
                    if (map[posPlayerY + 1][posPlayerX] == 10 || map[posPlayerY + 1][posPlayerX] == 5 && ARAHPEMAIN == 3 && !cekComponent(posPlayerY + 1, posPlayerX) && pilih != GAMEOVER) {
                        movePemain(dx, dy + 1);
                        step++;
                    }
                    ARAHPEMAIN = 3;
                }

                if (pilih == GAMEOVER) {
                    inmenu = false;
                    inlevel = false;
                    ingame = false;
                    inresult = true;
                }
                if (inresult == true) {
                    if (curResult <= 1) {
                        curResult = 2;
                    } else {
                        curResult -= 1;
                    }

                }
                return true;
            }
        });
        gameStage.addActor(upTombol);

        downTombol = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("DOWNPRESS.png"))));
        downTombol.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture("DOWNPRESS.png")));
        downTombol.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture("DOWN.png")));
        downTombol.setPosition(100, 0);
        downTombol.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (pilih == 0 || pilih == MENU) {
                    inmenu = true;
                    inlevel = false;
                    ingame = false;
                    inresult = false;
                }
                if (inmenu == true) {

                    if (curMenu >= 3) {
                        curMenu = 1;
                    } else {
                        curMenu++;
                    }
                }

                if (pilih == LVL_SEL) {
                    inmenu = false;
                    inlevel = true;
                    ingame = false;
                    inresult = false;
                }
                if (inlevel == true) {
                    if (curLevel >= 13) {
                        curLevel = 11;
                    } else {
                        curLevel++;
                    }
                }

                if (ingame == true) {
                    if (map[posPlayerY - 1][posPlayerX] == 10 || map[posPlayerY - 1][posPlayerX] == 5 && ARAHPEMAIN == 4 && !cekComponent(posPlayerY - 1, posPlayerX) && pilih != GAMEOVER) {
                        movePemain(dx, dy - 1);
                        step++;
                    }
                    ARAHPEMAIN = 4;

                }

                if (pilih == GAMEOVER) {
                    inmenu = false;
                    inlevel = false;
                    ingame = false;
                    inresult = true;
                }

                if (inresult == true) {
                    if (curResult >= 2) {
                        curResult = 1;
                    } else {
                        curResult += 1;
                    }
                }
                return true;
            }
        });
        gameStage.addActor(downTombol);

        leftTombol = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("LEFTPRESS.png"))));
        leftTombol.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture("LEFTPRESS.png")));
        leftTombol.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture("LEFT.png")));
        leftTombol.setPosition(0, 0);
        leftTombol.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (pilih == ABOUT || pilih == LVL_SEL) {
                    pilih = 0;
                    inmenu = true;
                    inlevel = false;
                    ingame = false;
                    inresult = false;
                }
                if (ingame == true) {
                    if (map[posPlayerY][posPlayerX - 1] == 10 || map[posPlayerY][posPlayerX - 1] == 5 && ARAHPEMAIN == 2 && !cekComponent(posPlayerY, posPlayerX - 1) && pilih != GAMEOVER) {
                        movePemain(dx - 1, dy);
                        step++;
                    }
                    ARAHPEMAIN = 2;

                }
                return true;
            }
        });
        gameStage.addActor(leftTombol);

        rightTombol = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("RIGHTPRESS.png"))));
        rightTombol.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture("RIGHTPRESS.png")));
        rightTombol.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture("RIGHT.png")));
        rightTombol.setPosition(200, 0);
        rightTombol.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (pilih == 0 || pilih == MENU) {
                    pilih = curMenu;
                    inmenu = true;
                    inlevel = false;
                    ingame = false;
                    inresult = false;
                }

                if (pilih == LVL_SEL) {
                    inmenu = false;
                    inlevel = true;
                    ingame = false;
                    inresult = false;
                }

                if (inlevel == true) {
                    if (curLevel <= openLevel) {
                        pilih = curLevel;
                        level = curLevel - 10;
                        inmenu = false;
                        inlevel = false;
                        ingame = true;
                        inresult = false;
                    }
                }

                if (ingame == true) {
                    if (map[posPlayerY][posPlayerX + 1] == 10 || map[posPlayerY][posPlayerX + 1] == 5 && ARAHPEMAIN == 1 && !cekComponent(posPlayerY, posPlayerX + 1) && pilih != GAMEOVER) {
                        movePemain(dx + 1, dy);
                        step++;
                    }
                    ARAHPEMAIN = 1;

                }

                if (pilih == GAMEOVER) {
                    inmenu = false;
                    inlevel = false;
                    ingame = false;
                    inresult = true;
                }

                if (inresult == true) {
                    switch (curResult) {
                        case 1:
                            pilih = LVL_SEL;
                            posPlayerX = 2;
                            posPlayerY = 27;
                            stateTuas = 0;
                            inventorisKunci = 0;
                            step = 0;
                            score = 0;
                            ARAHPEMAIN = BAWAH;
                            break;
                        case 2:
                            pilih = MENU;
                            posPlayerX = 2;
                            posPlayerY = 27;
                            stateTuas = 0;
                            inventorisKunci = 0;
                            step = 0;
                            score = 0;
                            ARAHPEMAIN = BAWAH;
                            break;
                    }
                }
                return true;
            }
        });
        gameStage.addActor(rightTombol);
    }

    private void movePemain(int dx, int dy) {
        posPlayerX += dx;
        posPlayerY += dy;
    }

    private boolean cekComponent(int x, int y) {
        // nilai 1, 2, 3, adalah obsLvl[] yang tidak bisa dilewati
        if (obj[x][y] == 1 || obj[x][y] == 2 || obj[x][y] == 3 || obj[x][y] == 4) {
            return true;
        } else {
            return false;
        }
    }

    private void openComponent(int x, int y) {
        // tuas
        if (obj[posPlayerY][posPlayerX] == 5 || obj[posPlayerY][posPlayerX] == 6) {
            switch (stateTuas) {
                case 0:
                    obj[x][y] = 0;
                    stateTuas = 1;
                    obj[posPlayerY][posPlayerX] = 6;
                    break;
                case 1:
                    obj[x][y] = 2;
                    stateTuas = 0;
                    obj[posPlayerY][posPlayerX] = 5;
                    break;
            }
        } // kunci
        else if (obj[posPlayerY][posPlayerX] == 9) {
            inventorisKunci++;
            obj[posPlayerY][posPlayerX] = 0;
        }
    }

    private void cekPintu(int x, int y) {
        // posisi pemain y - 1 == pintu
        if (obj[posPlayerY + 1][posPlayerX] == 7 || obj[posPlayerY][posPlayerX - 1] == 7) {
            // kunci tidak nol
            if (inventorisKunci != 0) {
                obj[x][y] = 8;
                map[x][y] = 5;
                inventorisKunci--;
            }
        }
    }

    private void mainMenu() {
        batch.draw(gambarTexture.ICON, 210, 55);
        batch.draw(gambarTexture.PLAY_OFF, 340, 290);
        batch.draw(gambarTexture.ABOUT_OFF, 340, 215);
        batch.draw(gambarTexture.EXIT_OFF, 340, 140);

        switch (curMenu) {
            case 1:
                batch.draw(gambarTexture.PLAY_ON, 340, 290);
                batch.draw(gambarTexture.HAND, 310, 291);
                break;
            case 2:
                batch.draw(gambarTexture.ABOUT_ON, 340, 215);
                batch.draw(gambarTexture.HAND, 310, 216);
                break;
            case 3:
                batch.draw(gambarTexture.EXIT_ON, 340, 140);
                batch.draw(gambarTexture.HAND, 310, 141);
                break;

        }
    }

    private void aboutGame() {
        batch.draw(gambarTexture.about, 210, 55);
    }

    private void menuLevel() {
        batch.draw(gambarTexture.B_LEVEL, 210, 55);

        switch (openLevel) {
            case 11:
                batch.draw(gambarTexture.LVL1_OPEN, 340, 290);
                batch.draw(gambarTexture.LVL2_OFF, 340, 215);
                batch.draw(gambarTexture.LVL3_OFF, 340, 140);
                break;
            case 12:
                batch.draw(gambarTexture.LVL1_OPEN, 340, 290);
                batch.draw(gambarTexture.LVL2_OPEN, 340, 215);
                batch.draw(gambarTexture.LVL3_OFF, 340, 140);
                break;
            case 13:
                batch.draw(gambarTexture.LVL1_OPEN, 340, 290);
                batch.draw(gambarTexture.LVL2_OPEN, 340, 215);
                batch.draw(gambarTexture.LVL3_OPEN, 340, 140);
                break;
        }

        switch (curLevel) {
            case 11:
                batch.draw(gambarTexture.HAND, 310, 291);
                break;
            case 12:
                batch.draw(gambarTexture.HAND, 310, 216);
                break;
            case 13:
                batch.draw(gambarTexture.HAND, 310, 141);
                break;
        }
    }

    public void menuResult() {
        batch.draw(gambarTexture.B_RESULT, 210, 55);

        switch (curResult) {
            case 1:
                batch.draw(gambarTexture.NEXT_ON, 340, 205);
                batch.draw(gambarTexture.TRY, 340, 130);
                batch.draw(gambarTexture.HAND, 310, 206);
                break;
            case 2:
                batch.draw(gambarTexture.NEXT, 340, 205);
                batch.draw(gambarTexture.TRY_ON, 340, 130);
                batch.draw(gambarTexture.HAND, 310, 131);
                break;
        }
        font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        font.draw(batch, step + "", 430, 315);
        font.draw(batch, score + "%", 430, 290);
//		g.drawString(" " + step + "%", getWidth() / 2, 145);
//		g.drawString(" " + score + "%", getWidth() / 2, getHeight() / 155);
    }

    private void setLevel1() {
        initMapAndObj();
        openComponent(14, 1);
        cekPintu(20, 40);
        cekPintu(6, 19);
        updateInGame();
        if (obj[posPlayerY][posPlayerX] == 8) {
            pilih = 99;
            if (openLevel < 12) {
                openLevel = 12;
            }
            score = (56 * 100) / step;
        }
    }

    private void setLevel2() {
        initMapAndObj();
        openComponent(14, 1);
        cekPintu(20, 40);
        if (obj[posPlayerY][posPlayerX] == 8) {
            pilih = 99;
            if (openLevel < 13) {
                openLevel = 13;
            }
            score = (42 * 100) / step;
        }
    }

    private void setLevel3() {
        initMapAndObj();
        openComponent(14, 1);
        cekPintu(20, 40);
        if (obj[posPlayerY][posPlayerX] == 8) {
            pilih = 99;
            if (openLevel < 13) {
                openLevel = 13;
            }
            score = (42 * 100) / step;
        }
    }

    private void initMapAndObj() {
        setUpMapAndObj();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                switch (map[i][j]) {
                    case 1:
                        batch.draw(gambarTexture.tembok_krpa, j * 16, i * 16);
                        break;
                    case 2:
                        batch.draw(gambarTexture.tembok_a, j * 16, i * 16);
                        break;
                    case 3:
                        batch.draw(gambarTexture.tembok_knpa, j * 16, i * 16);
                        break;
                    case 4:
                        batch.draw(gambarTexture.tembok_kr, j * 16, i * 16);
                        break;
                    case 5:
                        batch.draw(gambarTexture.lantai, j * 16, i * 16);
                        break;
                    case 6:
                        batch.draw(gambarTexture.tembok_kn, j * 16, i * 16);
                        break;
                    case 7:
                        batch.draw(gambarTexture.tembok_krpa, j * 16, i * 16);
                        break;
                    case 8:
                        batch.draw(gambarTexture.tembok_b, j * 16, i * 16);
                        break;
                    case 9:
                        batch.draw(gambarTexture.tembok_knpb, j * 16, i * 16);
                        break;
                }
            }
        }

        for (int i = 0; i < obj.length; i++) {
            for (int j = 0; j < obj[0].length; j++) {
                switch (obj[i][j]) {
                    case 1:
                        batch.draw(gambarTexture.sekat, j * 16, i * 16);
                        break;
                    case 2:
                        batch.draw(gambarTexture.sekat, j * 16, i * 16);
                        break;
                    case 3:
                        batch.draw(gambarTexture.sekat, j * 16, i * 16);
                        break;
                    case 4:
                        batch.draw(gambarTexture.sekat, j * 16, i * 16);
                        break;
                    case 5:
                        batch.draw(gambarTexture.tuas_off, j * 16, i * 16);
                        break;
                    case 6:
                        batch.draw(gambarTexture.tuas_on, j * 16, i * 16);
                        break;
                    case 7:
                        batch.draw(gambarTexture.door_close, j * 16, i * 16);
                        break;
                    case 8:
                        batch.draw(gambarTexture.door_open, j * 16, i * 16);
                        break;
                    case 9:
                        batch.draw(gambarTexture.kunci, j * 16, i * 16);
                        break;
                }
            }
        }

        buatPemain();
        movePemain(dx, dy);
    }

    private void setUpMapAndObj() {
        switch (level) {
            case 1:
                map = gambarTexture.setMapLevel1();

                obj = gambarTexture.setObjLevel1();
                break;
            case 2:
                map = gambarTexture.setMapLevel2();

                obj = gambarTexture.setObjLevel2();
                level = 0;
                break;
            case 3:
                map = gambarTexture.setMapLevel3();

                obj = gambarTexture.setObjLevel3();
                level = 0;
                break;
            default:
                break;
        }
    }

    private void exitGame() {
        System.exit(0);
    }

    private void setSuara() {
        music = Gdx.audio.newMusic(Gdx.files.internal("0.mp3"));
    }

    @Override
    public void dispose() {
        batch.dispose();
        gambarTexture.lantai.dispose();
        gameStage.dispose();
    }

    private void updateInGame() {
        float xCamawal = camera.position.x;
        float yCamawal = camera.position.y;
        camera.position.x = (posPlayerX * 32 - 16) / 2;
        camera.position.y = (posPlayerY * 32 - 16) / 2;
        float rangeUpdateCamX = camera.position.x - xCamawal;
        float rangeUpdateCamY = camera.position.y - yCamawal;

        upTombol.setPosition(upTombol.getX() + rangeUpdateCamX, upTombol.getY() + rangeUpdateCamY);
        downTombol.setPosition(downTombol.getX() + rangeUpdateCamX, downTombol.getY() + rangeUpdateCamY);
        leftTombol.setPosition(leftTombol.getX() + rangeUpdateCamX, leftTombol.getY() + rangeUpdateCamY);
        rightTombol.setPosition(rightTombol.getX() + rangeUpdateCamX, rightTombol.getY() + rangeUpdateCamY);
    }
}
