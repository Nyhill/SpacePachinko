package com.ceremonial.games.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.Body;
import com.ceremonial.games.handlers.WorldContactListener;
import com.ceremonial.games.utils.Constants;
import com.ceremonial.games.utils.WorldUtils;

/**
 * Created by ile on 21.12.2015.
 */


public class GameWorld extends Stage {

   // SpaceShip ship;
   // Explosives expl;
    public World world;// = WorldUtils.createWorld();
    private Body ship;// = WorldUtils.createShip(world);
    private Body bomb;// = WorldUtils.createBomb(world);
    private Body asteroid;// = WorldUtils.createBomb(world);
    private Body blackhole;
    public Box2DDebugRenderer B2DR;
    public OrthographicCamera camera;
    public static Array destroyBodies = new Array();
    public BitmapFont font;

    //score stuff

    private int score;
    public String hits= "score" + score;

    public Array<Body> tmpBodies = new Array<Body>();

    public float shipX;
    boolean right;
    boolean left;
    public int bombcount = 20;
    boolean bombs;

   public void GameWorld() {

       world = WorldUtils.createWorld();
       ship = WorldUtils.createShip(world);
       world.setContactListener(new WorldContactListener());
       camera = new OrthographicCamera();
       B2DR = new Box2DDebugRenderer();
       font = new BitmapFont(Gdx.files.internal("bmp.fnt"));
       font.setColor(25,0,15,0);
       font.getData().setScale(1,1);
       Constants.backgroundSprite.setPosition(-1,-1);
       Constants.backgroundSprite.setSize(2f, 2f);
       asteroidBelt();
       blackHoleEnd();

   }
    private void shipControls(Body ship) {
        Texture leftShip;
        Texture rightShip;
        leftShip = new Texture("ship2.png");
        rightShip = new Texture("ship.png");
        shipX = ship.getPosition().x;
     //   Gdx.app.log("shipControls", "x is:" + shipX);
        if(shipX <= -0.8){
           // Gdx.app.log("shipControls", "x is:" + shipX);
            right = false;
            left = true;
        }
        if(shipX >= 1){
           // Gdx.app.log("turn", "ffs:" + shipX);
            left = false;
            right = true;
        }
        if(right){
           // Gdx.app.log("shipControls", "reight is true:" + shipX);
           // shipX = ship.getPosition().x;
            ship.setLinearVelocity(-Constants.SHIP_SPEED * Gdx.graphics.getDeltaTime(), 0);
            Constants.shipSprite.setTexture(leftShip);
        }
        if(left){
           // Gdx.app.log("shipControls", "left is true:" + shipX);
            //shipX = ship.getPosition().x;
            ship.setLinearVelocity(Constants.SHIP_SPEED * Gdx.graphics.getDeltaTime(), 0);
            Constants.shipSprite.setTexture(rightShip);

        }
    }
    private void dropBomb() {
        if(Gdx.input.isTouched()) {
            bomb = WorldUtils.createBomb(world, shipX);

            if (bombcount > 0) {
                bombs = true;
            }
            if (bombcount == 0) {
                bombs = false;
            }
            if (bombs) {
                bombcount -= 1;
            }
            if (!bombs) {
                bombcount = 0;
                bomb = null;
            }
            Gdx.app.log("dropping bomb", "bobms left:" + bombcount);
           // checkCollision();
        }else{

        }
    }
    public void asteroidBelt(){
        Texture asteroid2;
        //row 1 9
        for(float i=-0.9f; i< 2; i+=0.20){
            asteroid = WorldUtils.createAsteroid(world,i,0.5f);
            }

        for(float i=-0.85f; i<2 ; i+=0.50){
            asteroid2 = new Texture("asteroid2.png");
            Constants.asteroidSprite.setTexture(asteroid2);
            asteroid = WorldUtils.createAsteroid(world,i,0.25f);

        }
        for(float i=-0.9f; i<2; i+=0.23){
            asteroid = WorldUtils.createAsteroid(world,i,0.0f);
        }
        for(float i=-0.9f; i<2; i+=0.60){
            asteroid = WorldUtils.createAsteroid(world,i,-0.125f);
        }

        for(float i=-0.9f; i<2; i+=0.22){
            asteroid = WorldUtils.createAsteroid(world,i,-0.25f);
        }
        for(float i=-0.9f; i<2; i+=0.30){
            asteroid = WorldUtils.createAsteroid(world,i,-0.50f);
        }
        for(float i=-0.9f; i<2; i+=0.21){
            asteroid = WorldUtils.createAsteroid(world, i, -0.75f);
        }

        }

    public void blackHoleEnd(){
        for(float i=-0.50f; i< 1.50; i+=0.55){
            blackhole = WorldUtils.createBlackHole(world, i, -0.90f);
        }

    }
    public void destroy(){
        Body d;
        //Gdx.app.log("gameworld", "destroyBodies:" + GameWorld.destroyBodies);
        for (int i=0; i<destroyBodies.size;i++){
           d = (Body) destroyBodies.get(i);
            world.destroyBody(d);
            destroyBodies.removeIndex(i);
            score +=1;
            Gdx.app.log("GameWorld", "score is:" + score);

        }
    }


    public void update(){
        B2DR.render(world, camera.combined);
        destroy();
        shipControls(ship);
        dropBomb();

    }

}
