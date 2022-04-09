package com.mygdx.game.glstart;

import com.mygdx.game.framework.Screen;
import com.mygdx.game.framework.impl.GLGame;
import com.mygdx.game.glstart.supjump.Assets;
import com.mygdx.game.glstart.supjump.MainMenuScreen;
import com.mygdx.game.glstart.supjump.Settings;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class SuperJumper extends GLGame {

    boolean firstTimeCreate = true;

    @Override
    public Screen getStartScreen() {
        return new MainMenuScreen(this);
    }

    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        super.onSurfaceCreated(gl, config);
        if (firstTimeCreate) {
            Settings.load(getFileIO());
            Assets.load(this);
            firstTimeCreate = false;
        } else {
            Assets.reload();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Settings.soundEnabled)
            Assets.music.pause();
    }
}
