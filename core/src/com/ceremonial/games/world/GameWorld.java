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

    public Array<Body> tmpBodies = new Array<Body>();

    public float shipX;
    boolean right;
    boolean left;
    boolean bombs;

   public void GameWorld() {
       //Create the world
       world = WorldUtils.createWorld();
       //Create the ship
       ship = WorldUtils.createShip(world);
       world.setContactListener(new WorldContactListener());
       //camera = new OrthographicCamera(800f, 900f);
       font = new BitmapFont();
       B2DR = new Box2DDebugRenderer();
//Constants.APP_WIDTH/2 - Constants.backgroundSprite.getWidth()/2, Constants.APP_HEIGHT/2 - Constants.backgroundSprite.getHeight() / 2
       Constants.backgroundSprite.setPosition(0,0);
       Constants.backgroundSprite.setSize(Constants.APP_WIDTH, Constants.APP_HEIGHT);

       camera = new OrthographicCamera(720, 1280);
       camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
       camera.update();

       asteroidBelt();
       blackHoleEnd();
       B2DR.render(world, camera.combined);

   }
    private void shipControls(Body ship) {
        Texture leftShip;
        Texture rightShip;
        leftShip = new Texture("ship2.png");
        rightShip = new Texture("ship.png");
        shipX = ship.getPosition().x;
     //   Gdx.app.log("shipControls", "x is:" + shipX);
        if(shipX <= 0){
           // Gdx.app.log("shipControls", "x is:" + shipX);
            right = false;
            left = true;
        }
        if(shipX >= 720){
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
            //Create a bomb when mouse is clicked / finger tap
            bomb = WorldUtils.createBomb(world, shipX);

            if (Constants.bomb_count > 0) {
                bombs = true;
            }
            if (Constants.bomb_count  == 0) {
                bombs = false;
            }
            if (bombs) {
                Constants.bomb_count  -= 1;
            }
            if (!bombs) {
                Constants.bomb_count  = 0;

                world.destroyBody(bomb);

            }
            if(bomb.getPosition().y < 0){
                world.destroyBody(bomb);
            }
            //Gdx.app.log("dropping bomb", "bobms left:" + Constants.bomb_count );
           // checkCollision();
        }else{

        }
    }
    public void asteroidBelt(){
        Texture asteroid2;
        Texture asteroid3 = new Texture("asteroid3.png");

        for(float i=10f; i< Constants.APP_WIDTH; i+=87f){
            asteroid = WorldUtils.createAsteroid(world,i,850f);
        }

        for(float i=10f; i< Constants.APP_WIDTH; i+=200f){
            asteroid = WorldUtils.createAsteroid(world,i,750f);
            Constants.asteroidSprite.setTexture(asteroid3);
        }

        //row 1 9
        for(float i=10f; i< Constants.APP_WIDTH; i+=87f){
            asteroid = WorldUtils.createAsteroid(world,i,650f);
            }

        for(float i=10f; i < Constants.APP_WIDTH ; i+=200){
            asteroid2 = new Texture("asteroid2.png");
            Constants.asteroidSprite.setTexture(asteroid2);
            asteroid = WorldUtils.createAsteroid(world,i,550f);

        }
        for(float i=10f; i < Constants.APP_WIDTH; i+=87){
            asteroid = WorldUtils.createAsteroid(world,i,450f);
        }
        for(float i=10f; i < Constants.APP_WIDTH; i+=160){
            asteroid = WorldUtils.createAsteroid(world,i,350f);
        }

        for(float i=10f; i < Constants.APP_WIDTH; i+=87){
            asteroid = WorldUtils.createAsteroid(world,i,250f);
        }
        for(float i=10f; i < Constants.APP_WIDTH; i+=100){
            asteroid = WorldUtils.createAsteroid(world,i,150f);
        }
 /*       for(float i=0.1f; i < Constants.APP_WIDTH; i+=87){
            asteroid = WorldUtils.createAsteroid(world, i, 50f);
        }
*/
        }

    public void blackHoleEnd(){
        for(float i=200; i< 600; i+=133f){
            blackhole = WorldUtils.createBlackHole(world, i, 50f);
        }

    }
    public void destroy(){
        Body d;
        //Gdx.app.log("gameworld", "destroyBodies:" + GameWorld.destroyBodies);
        for (int i=0; i<destroyBodies.size;i++){
           d = (Body) destroyBodies.get(i);
            world.destroyBody(d);
            destroyBodies.removeIndex(i);
            Constants.score +=1;

        }
    }


    public void update(){
        destroy();
        shipControls(ship);
        dropBomb();
    }

}
