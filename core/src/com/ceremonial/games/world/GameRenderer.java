package com.ceremonial.games.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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

    private GameWorld spaceWorld;
    private OrthographicCamera cam;
    private SpriteBatch sb;

    public GameRenderer (GameWorld world){

        spaceWorld = world;
        sb = new SpriteBatch();
        spaceWorld.GameWorld();
    }

    public void render() {
      //  Gdx.app.log("GameRenderer", "render");
        Gdx.gl.glClearColor(255, 255, 255, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
       // myWorld.B2DR.render(myWorld.world, myWorld.camera.combined);
               // cam.update();
        sb.setProjectionMatrix(spaceWorld.camera.combined);
        sb.begin();
        Constants.backgroundSprite.draw(sb);
        spaceWorld.font.setColor(Color.CYAN);
        spaceWorld.font.getData().setScale(2f, 2f);
        spaceWorld.font.draw(sb, "Score: " + Constants.score, 600f, 1220f);
        spaceWorld.font.draw(sb, "Bombs left: " + Constants.bomb_count, 400f, 1220f);


        spaceWorld.world.getBodies(spaceWorld.tmpBodies);
        for (Body body : spaceWorld.tmpBodies)
            if(body.getUserData() instanceof Sprite){
                Sprite sprite = (Sprite) body.getUserData();
                sprite.setPosition(body.getPosition().x - sprite.getWidth() / 2, body.getPosition().y - sprite.getHeight() / 2);
               // sprite.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
                sprite.draw(sb);
            }
        if(Constants.bomb_count == 0){
            spaceWorld.font.draw(sb, "NO BOMBS LEFT ", 280f, Constants.APP_HEIGHT / 2);
        }
        sb.end();

        spaceWorld.world.step(Constants.FRAME_RATE, 6, 2);
    }
}
