package com.ceremonial.games.handlers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.utils.Array;
import com.ceremonial.games.anim.Animator;
import com.ceremonial.games.utils.Constants;
import com.ceremonial.games.world.GameWorld;


/**
 * Created by ile on 25.12.2015.
 */
public class WorldContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        //Gdx.app.log("wcl", "brginContact:" );
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        Body ba = fa.getBody();
        Body bb = fb.getBody();

        if(fa == null || fb == null) return;
        if(fa.getUserData() == null || fb.getUserData() ==null)return;

        //Gdx.app.log("wcl", "contact:"+ fa.getUserData() + " "+ fb.getUserData() );
        if(fa.getUserData() == "Bomb" && fa.getUserData() == "BlackHole"){
            GameWorld.destroyBodies.add(ba);
            Sound sound = Gdx.audio.newSound(Gdx.files.internal("woosh.mp3"));
            sound.play(1.0f);
            //Gdx.app.log("wcl", "destroyBodies:" + GameWorld.destroyBodies);
        }
        if(fa.getUserData() == "BlackHole" && fb.getUserData() == "Bomb"){
           GameWorld.destroyBodies.add(bb);
            Sound sound = Gdx.audio.newSound(Gdx.files.internal("woosh.mp3"));
            sound.play(1.0f);
        }

    /*    if(fa.getUserData() == "Bomb" && fb.getUserData() == "Bomb"){
            //GameWorld.destroyBodies.add(bb);
            Gdx.app.log("wcl", "statetime:" + stateTime);
            if(stateTime >= 2f) {
                Constants.bombSprite.setTexture(explosion);
            }
           // Animator a = new Animator();
           // a.render();
        }

/////////////////////////////////////////////////////////////////////SOUNDDZZ//////////////////////////////////////////////////////
/*
        if(fa.getUserData() == "Bomb" && fa.getUserData() == "Asteroid"){
            Sound sound = Gdx.audio.newSound(Gdx.files.internal("debris.mp3"));
           // sound.play(1.0f);

            //Gdx.app.log("wcl", "destroyBodies:" + GameWorld.destroyBodies);
        }
        if(fa.getUserData() == "Asteroid" && fb.getUserData() == "Bomb"){
            Sound sound = Gdx.audio.newSound(Gdx.files.internal("debris.mp3"));
         //   sound.play(1.0f);
        }
        */
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
    }

}
