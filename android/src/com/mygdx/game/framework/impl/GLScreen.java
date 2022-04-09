package com.mygdx.game.framework.impl;

import com.mygdx.game.framework.Game;
import com.mygdx.game.framework.Screen;

public abstract class GLScreen extends Screen {
    protected final GLGraphics glGraphics;
    protected final GLGame glGame;

    public GLScreen(Game game) {
        super(game);
        glGame = (GLGame)game;
        glGraphics = glGame.getGLGraphics();
    }
}
