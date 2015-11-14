package com.sheffield.gamjam;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class GameJam extends Game {
	SpriteBatch batch;
	ShapeRenderer shapeRenderer;

    public Player player;
    public ArrayList<Bullet> bullets;
	ArrayList<Explosion> explosions;
	public Cloud[] clouds;
	public Ground ground;

	@Override
	public void create () {
		batch = new SpriteBatch();
		// Creating ground object
		ground = new Ground(Gdx.files.local("ground.png"));
        player = new Player(this);
        bullets = new ArrayList<Bullet>();
		explosions = new ArrayList<Explosion>();
		ground = new Ground(Gdx.files.local("ground.png"));

		// Creating clouds with texture region
		TextureRegion t = new TextureRegion(new Texture(Gdx.files.local("sprite_sheet1.png")), 930, 1805, 203, 133);
		clouds = new Cloud[]{new Cloud(t, 4, 50),
							  new Cloud(t, 4, 500),
							  new Cloud(t, 4, 1000)};

		explosions = new ArrayList<Explosion>();

		shapeRenderer = new ShapeRenderer();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.4f, 0.4f, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        player.update();

        for (Iterator<Bullet> it = bullets.iterator(); it.hasNext();) {
            Bullet b = it.next();
            b.update();
            if (b.pos.y <= ground.g.getHeight()) {
                Vector2 pos = b.pos;
                it.remove();
                explosions.add(new Explosion(pos.x, pos.y));
            }
        }

        batch.begin();

		ground.draw(batch);

		// Updating clouds fam
		for(Cloud cloud:clouds) cloud.update(batch);

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
			e.render(shapeRenderer);
		}
	}
}
