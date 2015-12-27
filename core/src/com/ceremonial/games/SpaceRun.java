package com.ceremonial.games;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.ceremonial.games.screens.GameScreen;
import com.ceremonial.games.utils.Constants;
import com.ceremonial.games.world.GameWorld;

public class SpaceRun extends Game {


	@Override
	public void create () {
		setScreen(new GameScreen());

	}

}
