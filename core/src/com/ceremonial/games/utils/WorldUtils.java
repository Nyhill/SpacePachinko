package com.ceremonial.games.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by ile on 22.12.2015.
 */
public class WorldUtils {
    public static World createWorld() {
        return new World(Constants.WORLD_GRAVITY, false);
    }

    //**********************************************************SHIPPZ *********************************************************************************
    public static Body createShip(World world) {
    //SHIP BODY
    BodyDef spaceShip = new BodyDef();
    spaceShip.type = BodyDef.BodyType.KinematicBody;

    //SHIP FIXTURE
    FixtureDef tempFD = new FixtureDef();
    PolygonShape tempShape = new PolygonShape();
    tempFD.shape = tempShape;

    tempShape.setAsBox(.1f, .1f, Constants.SHIP_VECTOR, 90);
    spaceShip.position.set(Constants.SHIP_STARTPOS);

    Body ship = world.createBody(spaceShip);
    ship.createFixture(tempFD);

    Constants.shipSprite = new Sprite(new Texture("ship.png"));
        Constants.shipSprite.setSize(.2f, .2f);
        Constants.shipSprite.setOrigin(Constants.shipSprite.getWidth() / 2, Constants.shipSprite.getHeight() / 2);
    ship.setUserData(Constants.shipSprite);

    tempShape.dispose();
    return ship;
}

    //**********************************************************BOMBBZ *********************************************************************************
    public static Body createBomb(World world, float x){
        BodyDef bombDef = new BodyDef();
        bombDef.type = BodyDef.BodyType.DynamicBody;
        bombDef.position.set(x, .85f);

        //Ball shape
        CircleShape shape = new CircleShape();
        shape.setRadius(Constants.BOMB_RADIUS);

        //ficture definition (physical properties)
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 2.5f;
        fixtureDef.friction = 0.25f;
        fixtureDef.restitution = 0.5f;

        Body bomb = world.createBody(bombDef);
        bomb.createFixture(fixtureDef);
        //bomb.setLinearVelocity(-9.81f,0);

        Constants.bombSprite = new Sprite(new Texture("bomb.png"));
        Constants.bombSprite.setSize(.05f, .05f);
        Constants.bombSprite.setOrigin(Constants.bombSprite.getWidth() / 2, Constants.bombSprite.getHeight() / 2);
        bomb.setUserData(Constants.bombSprite);
        Fixture dataFix = bomb.createFixture(fixtureDef);
        dataFix.setUserData("Bomb");

        shape.dispose();
        return bomb;
    }
    //*********************************************************ASTEROIDZZ *********************************************************************************
    public static Body createAsteroid(World world, float x, float y){
        BodyDef asteroidDef = new BodyDef();
        asteroidDef.type = BodyDef.BodyType.KinematicBody;
        asteroidDef.position.set(x, y);

        //Ball shape
        CircleShape shape = new CircleShape();
        shape.setRadius(Constants.ASTEROID_RADIUS);

        //ficture definition (physical properties)
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 2.5f;
        fixtureDef.friction = 0.25f;
        fixtureDef.restitution = 0.5f;

        Body asteroid = world.createBody(asteroidDef);
        asteroid.createFixture(fixtureDef);
        //bomb.setLinearVelocity(-9.81f,0);

        Constants.asteroidSprite = new Sprite(new Texture("asteroid1.png"));
        Constants.asteroidSprite.setSize(.05f, .05f);
        Constants.asteroidSprite.setOrigin(Constants.asteroidSprite.getWidth() / 2, Constants.asteroidSprite.getHeight() / 2);
        asteroid.setUserData(Constants.asteroidSprite);
        Fixture dataFix = asteroid.createFixture(fixtureDef);
        dataFix.setUserData("Asteroid");

        shape.dispose();
        return asteroid;
    }
    //**********************************************************BLACKHOLE *********************************************************************************
    public static Body createBlackHole(World world, float x, float y){
        BodyDef bombDef = new BodyDef();
        bombDef.type = BodyDef.BodyType.StaticBody;
        bombDef.position.set(x, y);

        //Ball shape
        CircleShape shape = new CircleShape();
        shape.setRadius(Constants.BH_RADIUS);

        //ficture definition (physical properties)
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 2.5f;
        fixtureDef.friction = 0.25f;
        fixtureDef.restitution = 0.5f;

        Body bh = world.createBody(bombDef);
        bh.createFixture(fixtureDef);
        //bomb.setLinearVelocity(-9.81f,0);

        Constants.bhSprite = new Sprite(new Texture("bh.png"));
        Constants.bhSprite.setSize(.12f, .12f);
        Constants.bhSprite.setOrigin(Constants.bhSprite.getWidth() / 2, Constants.bhSprite.getHeight() / 2);
        bh.setUserData(Constants.bhSprite);
        Fixture dataFix = bh.createFixture(fixtureDef);
        dataFix.setUserData("BlackHole");

        shape.dispose();
        return bh;
    }



}
