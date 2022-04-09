package com.mygdx.game.glstart.supjump;

import com.mygdx.game.framework.Game;
import com.mygdx.game.framework.Input;
import com.mygdx.game.framework.gl.Camera2D;
import com.mygdx.game.framework.gl.SpriteBatcher;
import com.mygdx.game.framework.gl.Texture;
import com.mygdx.game.framework.gl.TextureRegion;
import com.mygdx.game.framework.impl.GLScreen;
import com.mygdx.game.framework.math.OverlapTester;
import com.mygdx.game.framework.math.Rectangle;
import com.mygdx.game.framework.math.Vector2;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

public class HelpScreen4 extends GLScreen {
    Camera2D guiCam;
    SpriteBatcher batcher;
    Rectangle nextBounds;
    Vector2 touchPoint;
    Texture helpImage;
    TextureRegion helpRegion;

    public HelpScreen4(Game game) {
        super(game);

        guiCam = new Camera2D(glGraphics, 480, 768);
        nextBounds = new Rectangle(480 - 64, 0, 64, 64);
        touchPoint = new Vector2();
        batcher = new SpriteBatcher(glGraphics, 1);
    }

    @Override
    public void resume() {
        helpImage = new Texture(glGame, "help4.png" );
        helpRegion = new TextureRegion(helpImage, 0, 0, 480, 768);
    }

    @Override
    public void pause() {
        helpImage.dispose();
    }

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            touchPoint.set(event.x, event.y);
            guiCam.touchToWorld(touchPoint);

            if(event.type == Input.TouchEvent.TOUCH_UP) {
                if(OverlapTester.pointInRectangle(nextBounds, touchPoint)) {
                    Assets.playSound(Assets.clickSound);
                    game.setScreen(new HelpScreen5(game));
                    return;
                }
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        GL10 gl = glGraphics.getGL();
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        guiCam.setViewportAndMatrices();

        gl.glEnable(GL10.GL_TEXTURE_2D);

        batcher.beginBatch(helpImage);
        batcher.drawSprite(240, 384, 480, 768, helpRegion);
        batcher.endBatch();

        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        batcher.beginBatch(Assets.items);
        batcher.drawSprite(480 - 32, 32, -64, 64, Assets.arrow);
        batcher.endBatch();

        gl.glDisable(GL10.GL_BLEND);
    }

    @Override
    public void dispose() {
    }
}
