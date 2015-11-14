package com.sheffield.gamjam;

import java.util.List;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameJam extends Game {
	SpriteBatch batch;
	List<Building> buildings;
	public Player player;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		player = new Player();
		// buildings = Building.setupBuildings();   // <-- include when buildings are wanted or use specific setup
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        player.update();
		batch.begin();
		player.render(batch);
		Building.updateAndDraw(buildings, batch);
		batch.end();
	}
}
