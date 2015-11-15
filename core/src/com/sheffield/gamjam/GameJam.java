package com.sheffield.gamjam;

import com.badlogic.gdx.Game;

public class GameJam extends Game {
	
	public StartScreen startScreen;
	public GameScreen gameScreen;
	public GameOverScreen loseScreen, winScreen;

	@Override
	public void create() {
		startScreen = new StartScreen(this);
		gameScreen = new GameScreen(this);
		loseScreen = new LoseScreen(this, gameScreen);
		winScreen = new WinScreen(this, gameScreen);		
		
		setScreen(startScreen);
		
	}
}