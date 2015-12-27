package com.ceremonial.games.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by ile on 22.12.2015.
 */
public class Constants {
    public static final int APP_WIDTH = 720;
    public static final int APP_HEIGHT = 1280;

    public static final float FRAME_RATE = 1 / 60f;

    //BACKGROUND

    public static Sprite backgroundSprite = new Sprite(new Texture("bgp.png"));

    public static final Vector2 WORLD_GRAVITY = new Vector2(0, -15f);



    //SHIP STUFF
    public static Sprite shipSprite = new Sprite(new Texture("ship.png"));
    public static final Vector2 SHIP_VECTOR = new Vector2(0, 0);
    public static final Vector2 SHIP_STARTPOS = new Vector2(0, 1080);
    public static final float SHIP_SPEED = 5000f;


    //BOMB STUFF
    public static  int bomb_count = 50;
    public static final float BOMB_RADIUS = 7f;
    public static int score = 0;
    public static Sprite bombSprite = new Sprite(new Texture("bomb.png"));

    //ASTEROID STUFF
    public static final float ASTEROID_RADIUS = 15f;
    public static Sprite asteroidSprite = new Sprite(new Texture("asteroid1.png"));

    //BLACKHOLE STUFF
    public static final float BH_RADIUS = 20f;
    public static Sprite bhSprite = new Sprite(new Texture("bh.png"));


}
