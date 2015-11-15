package com.sheffield.gamjam;

import com.badlogic.gdx.Screen;

public class StartScreen implements Screen {
	
	GameJam game;
	
	public StartScreen(GameJam g)
	{
		game = g;
	}
	
	@Override
	public void show() {
		game.setScreen(game.gameScreen);
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
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
