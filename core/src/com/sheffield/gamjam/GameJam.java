package com.sheffield.gamjam;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameJam extends Game {
	SpriteBatch batch;
	Texture img;
	Ground ground;

    public Player player;
    public ArrayList<Bullet> bullets;
	ArrayList<Explosion> explosions;

	@Override
	public void create () {
		batch = new SpriteBatch();
		// Creating ground object
		float[] rgb = {0.5f, 1, 0.5f};
		ground = new Ground(rgb, 100, 20);
        player = new Player(this);
        bullets = new ArrayList<Bullet>();
		explosions = new ArrayList<Explosion>();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.4f, 0.4f, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		ground.draw();

        player.update();

        for(Bullet b : bullets)
        	b.update();

        batch.begin();

        for(Bullet b : bullets)
        	b.draw(batch);
        player.render(batch);
        batch.end();

        for (Iterator<Explosion> it = explosions.iterator(); it.hasNext();) {
            Explosion e = it.next();
			boolean doDelete = e.update(1.0f / 60.0f);
            if (doDelete) {
                it.remove();
                continue;
            }
			e.render();
		}
	}
}
