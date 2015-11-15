package com.sheffield.gamjam;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class StartScreen implements Screen {
	
	GameJam game;
	ShapeRenderer shapeRenderer;
	Ground ground;
	SpriteBatch batch;
	BitmapFont font12;
	
	//start button variables
	float startWidth = 300;
	float startHeight = 70;
	float startX = (Gdx.graphics.getWidth() - startWidth)/2;
	float startY = 550;
	
	//instruction button variables
	float instWidth = 320;
	float instHeight = 70;
	float instX = (Gdx.graphics.getWidth() - instWidth)/2;
	float instY = 400;
	
	public StartScreen(GameJam g)
	{
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		initFont();
		ground = new Ground(Gdx.files.local("ground.png"));
		game = g;
	}
	
	private void initFont() {
		FreeTypeFontGenerator gen = 
				new FreeTypeFontGenerator(Gdx.files.internal("OpenSans-Regular.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 50;
		parameter.color = Color.WHITE;
		parameter.shadowOffsetY = 1;
		parameter.shadowOffsetX = 1;
		font12 = gen.generateFont(parameter);
		gen.dispose();
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.4f, 0.4f, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if (checkScreenChange()) {
			game.setScreen(game.gameScreen);
		}
		else {
			batch.begin();
			ground.draw(batch);
			batch.end();
			renderStartButton();
			renderInstButton();
			checkScreenChange();
		}
	}
	
	private boolean checkScreenChange() {
		float mouseX = Gdx.input.getX();
		float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();
		boolean overStartButton = startX < mouseX && mouseX < startX + startWidth && startY < mouseY && mouseY < startY + startHeight;
		if ( Gdx.input.isButtonPressed(Input.Buttons.LEFT) && overStartButton) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private void renderStartButton() {
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(0, 1, 0, 1);
		shapeRenderer.rect(startX, startY, startWidth, startHeight);
		shapeRenderer.end();
		float fontX = startX + 20;
		float fontY = startY + 55;
		batch.begin();
		font12.draw(batch, "Start Game", fontX, fontY);
		batch.end();
	}
	
	private void renderInstButton() {
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(0, 1, 0, 1);
		shapeRenderer.rect(instX, instY, instWidth, instHeight);
		shapeRenderer.end();
		float fontX = instX + 25;
		float fontY = instY + 55;
		batch.begin();
		font12.draw(batch, "Instructions", fontX, fontY);
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
