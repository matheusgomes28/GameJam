package com.sheffield.gamjam;

import java.util.List;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameJam extends ApplicationAdapter {
	SpriteBatch batch;
	List<Building> buildings;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		// buildings = Building.setupBuildings();   // <-- include when buildings are wanted or use specific setup
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		Building.updateAndDraw(buildings, batch);
		batch.end();
	}
}
