package com.mygdx.game.glstart.supjump;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.framework.Game;
import com.mygdx.game.framework.Input;
import com.mygdx.game.framework.gl.Camera2D;
import com.mygdx.game.framework.gl.SpriteBatcher;
import com.mygdx.game.framework.impl.GLScreen;
import com.mygdx.game.framework.math.OverlapTester;
import com.mygdx.game.framework.math.Rectangle;
import com.mygdx.game.framework.math.Vector2;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

public class GameScreen extends GLScreen {
    static final int GAME_READY = 0;
    static final int GAME_RUNNING = 1;
    static final int GAME_PAUSED = 2;
    static final int GAME_LEVEL_END = 3;
    static final int GAME_OVER = 4;

    int state;
    Camera2D guiCam;
    Vector2 touchPoint;
    SpriteBatcher batcher;
    World world;
    World.WorldListener worldListener;
    WorldRenderer renderer;
    Rectangle pauseBounds;
    Rectangle resumeBounds;
    Rectangle quitBounds;
    Rectangle rightBounds;
    Rectangle leftBounds;
    int lastScore;
    String scoreString;

    public GameScreen(Game game) {
        super(game);
        state = GAME_READY;
        guiCam = new Camera2D(glGraphics, 480, 768);
        touchPoint = new Vector2();
        batcher = new SpriteBatcher(glGraphics, 1000);
        worldListener = new World.WorldListener() {
            public void jump() {
                Assets.playSound(Assets.jumpSound);
            }

            public void highJump() {
                Assets.playSound(Assets.highJumpSound);
            }

            public void hit() {
                Assets.playSound(Assets.hitSound);
            }

            public void coin() {
                Assets.playSound(Assets.coinSound);
            }
        };
        world = new World(worldListener);
        renderer = new WorldRenderer(glGraphics, batcher, world);
        pauseBounds = new Rectangle(480 - 64, 768- 64, 64, 64);
        resumeBounds = new Rectangle(240 - 96, 384, 192, 36);
        quitBounds = new Rectangle(240 - 96, 384 - 36, 192, 36);
        rightBounds = new Rectangle(480 - 392, 0, 64, 64);
        leftBounds = new Rectangle(32, 32, 64, 64);
        lastScore = 0;
        scoreString = "score: 0";
    }

    @Override
    public void update(float deltaTime) {
        if(deltaTime > 0.1f)
            deltaTime = 0.1f;

        switch(state) {
            case GAME_READY:
                updateReady();
                break;
            case GAME_RUNNING:
                updateRunning(deltaTime);
                break;
            case GAME_PAUSED:
                updatePaused();
                break;
            case GAME_LEVEL_END:
                updateLevelEnd();
                break;
            case GAME_OVER:
                updateGameOver();
                break;
        }
    }

    private void updateReady() {
        if(game.getInput().getTouchEvents().size() > 0) {
            state = GAME_RUNNING;
        }
    }

    private void updateRunning(float deltaTime) {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if(event.type != Input.TouchEvent.TOUCH_UP)
                continue;

            touchPoint.set(event.x, event.y);
            guiCam.touchToWorld(touchPoint);

            if(OverlapTester.pointInRectangle(pauseBounds, touchPoint)) {
                Assets.playSound(Assets.clickSound);
                state = GAME_PAUSED;
                return;
            }

            if(OverlapTester.pointInRectangle(rightBounds, touchPoint)) {
                Assets.playSound(Assets.clickSound);
                state = GAME_PAUSED;
                return;
            }
        }

        world.update(deltaTime, game.getInput().getAccelX());
        if(world.score != lastScore) {
            lastScore = world.score;
            scoreString = "" + lastScore;
        }
        if(world.state == World.WORLD_STATE_NEXT_LEVEL) {
            state = GAME_LEVEL_END;
        }
        if(world.state == World.WORLD_STATE_GAME_OVER) {
            state = GAME_OVER;
            if(lastScore >= Settings.highscores[4])
                scoreString = "new highscore: " + lastScore;
            else
                scoreString = "score: " + lastScore;
            Settings.addScore(lastScore);
            Settings.save(game.getFileIO());
        }
    }

    private void updatePaused() {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if(event.type != Input.TouchEvent.TOUCH_UP)
                continue;

            touchPoint.set(event.x, event.y);
            guiCam.touchToWorld(touchPoint);

            if(OverlapTester.pointInRectangle(resumeBounds, touchPoint)) {
                Assets.playSound(Assets.clickSound);
                state = GAME_RUNNING;
                return;
            }

            if(OverlapTester.pointInRectangle(quitBounds, touchPoint)) {
                Assets.playSound(Assets.clickSound);
                game.setScreen(new MainMenuScreen(game));
                return;

            }
        }
    }

    private void updateLevelEnd() {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if(event.type != Input.TouchEvent.TOUCH_UP)
                continue;
            world = new World(worldListener);
            renderer = new WorldRenderer(glGraphics, batcher, world);
            world.score = lastScore;
            state = GAME_READY;
        }
    }

    private void updateGameOver() {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if(event.type != Input.TouchEvent.TOUCH_UP)
                continue;
            game.setScreen(new MainMenuScreen(game));
        }
    }

    @Override
    public void present(float deltaTime) {
        GL10 gl = glGraphics.getGL();
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        gl.glEnable(GL10.GL_TEXTURE_2D);

        renderer.render();

        guiCam.setViewportAndMatrices();
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        batcher.beginBatch(Assets.items);
        switch(state) {
            case GAME_READY:
                presentReady();
                break;
            case GAME_RUNNING:
                presentRunning();
                break;
            case GAME_PAUSED:
                presentPaused();
                break;
            case GAME_LEVEL_END:
                presentLevelEnd();
                break;
            case GAME_OVER:
                presentGameOver();
                break;
        }
        batcher.endBatch();
        gl.glDisable(GL10.GL_BLEND);
    }

    private void presentReady() {
        batcher.drawSprite(240, 384, 192, 32, Assets.ready);
    }

    private void presentRunning() {
        batcher.drawSprite(480 - 360, 32, -64, 64, Assets.arrow);
        batcher.drawSprite(32, 32, 64, 64, Assets.arrow);
        batcher.drawSprite(480 - 32, 768 - 32, 64, 64, Assets.pause);
        Assets.font.drawText(batcher, scoreString, 16, 768-20);
    }

    private void presentPaused() {
        batcher.drawSprite(240, 384, 192, 96, Assets.pauseMenu);
        Assets.font.drawText(batcher, scoreString, 16, 768-20);
    }

    private void presentLevelEnd() {
        String topText = "the princess is ...";
        String bottomText = "in another castle!";
        float topWidth = Assets.font.glyphWidth * topText.length();
        float bottomWidth = Assets.font.glyphWidth * bottomText.length();
        Assets.font.drawText(batcher, topText, 240 - topWidth / 2, 384 - 40);
        Assets.font.drawText(batcher, bottomText, Gdx.graphics.getWidth() / 2 - bottomWidth / 2, 40);
    }

    private void presentGameOver() {
        batcher.drawSprite(240, 420, 240, 96, Assets.gameOver);
        float scoreWidth = Assets.font.glyphWidth * scoreString.length();
        Assets.font.drawText(batcher, scoreString, 240 - scoreWidth / 2, 384-20);
    }

    @Override
    public void pause() {
        if(state == GAME_RUNNING)
            state = GAME_PAUSED;
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}
