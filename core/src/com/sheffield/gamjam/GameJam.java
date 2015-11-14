package com.sheffield.gamjam;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameJam extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Ground ground;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		// Creating ground object
		float[] rgb = {0.5f, 1, 0.5f};
		ground = new Ground(rgb, 100, 20);
	}

	@Override
	public void render () {		
		Gdx.gl.glClearColor(0.4f, 0.4f, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		ground.draw();
	}
}
