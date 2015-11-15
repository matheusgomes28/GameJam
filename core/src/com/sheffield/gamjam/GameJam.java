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
    public ArrayList<Enemy> enemies;
    public ArrayList<EnemyBullet> enemyBullets;
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
        enemies = new ArrayList<Enemy>();
        
        enemyBullets = new ArrayList<EnemyBullet>();
        bullets = new ArrayList<Bullet>();
		explosions = new ArrayList<Explosion>();
		ground = new Ground(Gdx.files.local("ground.png"));

		// Creating clouds with texture region
		TextureRegion t = new TextureRegion(new Texture(Gdx.files.local("sprite_sheet1.png")), 930, 1805, 203, 133);
		clouds = new Cloud[]{new Cloud(t, 3, 50),
							  new Cloud(t, 3, 500),
							  new Cloud(t, 3, 1000)};
		for(int i = 0; i<1; i++)
			enemies.add(new Enemy(this));
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
		
		// Render player's bullets
        for(Bullet b : bullets)
        	b.draw(batch);
        System.out.println("Bullets: "+bullets.size());
        
        // Render enemy's bullets
        for(EnemyBullet eb : enemyBullets) {
        	eb.update();
        	eb.draw(batch);
        }
        System.out.println("Enemy Bullets: "+enemyBullets.size());
        
        // Render enemies
        for(Enemy e : enemies)
        	e.render(batch);
        // Render player
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