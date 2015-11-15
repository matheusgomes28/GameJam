package com.sheffield.gamjam;

import com.badlogic.gdx.Game;

public class GameJam extends Game {
	
	public StartScreen startScreen;
	public GameScreen gameScreen;

	@Override
	public void create() {
		startScreen = new StartScreen(this);
		gameScreen = new GameScreen(this);
		
		setScreen(startScreen);
		
	}
}
