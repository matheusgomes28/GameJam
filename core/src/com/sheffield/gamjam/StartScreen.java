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
	BitmapFont bigFont;
	BitmapFont smallFont;
	
	//start button variables
	float startWidth = 300;
	float startHeight = 70;
	float startX = (Gdx.graphics.getWidth() - startWidth)/2;
	float startY = 550;
	
	//instruction string
	String instructions =	"  You are playing as David Cameron. You must fly around using WASD and\n" +
							"    shoot rockets at schools, hospitals and houses; cutting unnecessary\n" +
							"govenment funding and adding it to your pocket. Don't shoot at skyscrapers\n" +
							"  or banks; you will lose your \"hard-earnt\" money. To make things difficult\n" +
							"  for you, your enemies are taunting you by throwing bacon at you; try to\n" +
							" dodge this. Now get out there and amass your fortune, create anarchy and\n" +
							"end the world as we know it ... mwahahahahahahahahahahahahahahahaha.";
	
	public StartScreen(GameJam g)
	{
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		buttonFont();
		instructionFont();
		ground = new Ground(Gdx.files.local("ground.png"));
		game = g;
	}
	
	private void buttonFont() {
		FreeTypeFontGenerator gen = 
				new FreeTypeFontGenerator(Gdx.files.internal("OpenSans-Regular.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 50;
		parameter.color = Color.WHITE;
		parameter.shadowOffsetY = 1;
		parameter.shadowOffsetX = 1;
		bigFont = gen.generateFont(parameter);
		gen.dispose();
	}
	
	private void instructionFont() {
		FreeTypeFontGenerator gen = 
				new FreeTypeFontGenerator(Gdx.files.internal("OpenSans-Regular.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 25;
		parameter.color = Color.BLACK;
		parameter.shadowOffsetY = 1;
		parameter.shadowOffsetX = 1;
		smallFont = gen.generateFont(parameter);
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
			renderInstructions();
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
		bigFont.draw(batch, "Start Game", fontX, fontY);
		batch.end();
	}
	
	private void renderInstructions() {
		batch.begin();
		smallFont.draw(batch, instructions, 220, 450);
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
