package com.ceremonial.games.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.ceremonial.games.world.GameRenderer;
import com.ceremonial.games.world.GameWorld;

/**
 * Created by ile on 22.12.2015.
 */
public class GameScreen implements Screen {

    private GameWorld world;
    private GameRenderer renderer;

    public GameScreen(){

        world = new GameWorld(); // initialize world
        renderer = new GameRenderer(world); // initialize renderer
        Sound sound = Gdx.audio.newSound(Gdx.files.internal("de.mp3"));
         sound.play(1.0f);
    }

    @Override
    //update GameWorld object and GameRenderer Object
    public void render(float delta) {
        world.update();
        renderer.render();
    }

    @Override
    public void resize(int width, int height) {

    }
    @Override
    public void show() {

    }
    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
