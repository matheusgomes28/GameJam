package com.sheffield.gamjam;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class GameOverScreen implements Screen {

	GameJam game;
	Ground ground;
	SpriteBatch batch;
	GameScreen gameScreen;
	BitmapFont smallFont;
	BitmapFont bigFont;
	
	GameOverScreen(GameJam g, GameScreen gameScreen) {
		batch = new SpriteBatch();
		this.gameScreen = gameScreen;
		ground = new Ground(Gdx.files.local("ground.png"));
		game = g;
		textFont();
		titleFont();
	}
	
	private void textFont() {
		FreeTypeFontGenerator gen = 
				new FreeTypeFontGenerator(Gdx.files.internal("OpenSans-Regular.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 50;
		parameter.color = Color.WHITE;
		parameter.shadowOffsetY = 1;
		parameter.shadowOffsetX = 1;
		smallFont = gen.generateFont(parameter);
		gen.dispose();
	}
	
	private void titleFont() {
		FreeTypeFontGenerator gen = 
				new FreeTypeFontGenerator(Gdx.files.internal("OpenSans-Regular.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 75;
		parameter.color = Color.BLACK;
		parameter.shadowOffsetY = 1;
		parameter.shadowOffsetX = 1;
		bigFont = gen.generateFont(parameter);
		gen.dispose();
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		renderBackground();
	}
	
	public void renderBackground() {
		Gdx.gl.glClearColor(0.4f, 0.4f, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		ground.draw(batch);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
