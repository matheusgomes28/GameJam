package com.sheffield.gamjam;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameJam extends Game {
	SpriteBatch batch;
    public Player player;
    public ArrayList<Bullet> bullets;

	@Override
	public void create () {
		batch = new SpriteBatch();
        player = new Player(this);
        bullets = new ArrayList<Bullet>();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
        player.update();
		
        for(Bullet b : bullets)
        	b.update();
        
        batch.begin();
        
        player.render(batch);
        for(Bullet b : bullets)
        	b.draw(batch);
        batch.end();
	}
}
