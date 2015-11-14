package com.sheffield.gamjam;

import java.util.ArrayList;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GameJam extends Game {
	SpriteBatch batch;
	Ground ground;
    public Player player;
    ArrayList<Bullet> bullets;
    Cloud[] clouds;


	@Override
	public void create () {
		batch = new SpriteBatch();

		// Creating ground object
		float[] rgb = {0.5f, 1, 0.5f};
		ground = new Ground(Gdx.files.local("ground.png"));
        player = new Player(this);
        bullets = new ArrayList<Bullet>();
        
        TextureRegion t = new TextureRegion(new Texture(Gdx.files.local("sprite_sheet1.png")), 490, 1997, 230, 130);
        clouds = new Cloud[]{new Cloud(t, 3, 50),
        		             new Cloud(t, 3, 500),
        		             new Cloud(t, 3, 1000)};
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
        player.update();

        batch.begin();
        
        ground.draw(batch);
        for(Bullet b : bullets) b.draw(batch);
        for(Cloud cloud:clouds) cloud.draw(batch);
        player.render(batch);
        batch.end();
	}
}
