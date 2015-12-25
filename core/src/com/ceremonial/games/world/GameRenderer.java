package com.ceremonial.games.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.ceremonial.games.utils.Constants;

/**
 * Created by ile on 22.12.2015.
 */
public class GameRenderer {

    private GameWorld myWorld;
    private OrthographicCamera cam;
    private SpriteBatch sb;

    public GameRenderer (GameWorld world){

        myWorld = world;
        //cam = new OrthographicCamera();
        //cam.setToOrtho(true, Constants.APP_WIDTH / 2, Constants.APP_HEIGHT / 2);
        sb = new SpriteBatch();
        myWorld.GameWorld();
    }

    public void render() {
      //  Gdx.app.log("GameRenderer", "render");
        Gdx.gl.glClearColor(255, 255, 255, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
       // cam.update();
       // myWorld.touchCheck();
        sb.setProjectionMatrix(myWorld.camera.combined);
        sb.begin();

        //font.setColor(1, 0, 0, 1);
        //myWorld.font.getData().setScale(1f,1f);
        myWorld.font.draw(sb, myWorld.hits,0.1f,0.1f);
        Constants.backgroundSprite.draw(sb);
        myWorld.world.getBodies(myWorld.tmpBodies);
        for (Body body : myWorld.tmpBodies)
            if(body.getUserData() instanceof Sprite){
                Sprite sprite = (Sprite) body.getUserData();
                sprite.setPosition(body.getPosition().x - sprite.getWidth() / 2, body.getPosition().y - sprite.getHeight() / 2);
               // sprite.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
                sprite.draw(sb);
            }
        sb.end();

        myWorld.world.step(Constants.FRAME_RATE, 6, 2);
    }
}
