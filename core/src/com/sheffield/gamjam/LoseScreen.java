package com.sheffield.gamjam;

public class LoseScreen extends GameOverScreen {

	float highScore;
	String message;
	
	public LoseScreen(GameJam g, GameScreen gameScreen) {
		super(g, gameScreen);
	}

	@Override
	public void show(){
		message =	"  At one point you had " + GameScreen.numFormat((double) gameScreen.highScore, ",") + "\n" +
				"How could you lose it all? You don't\n" +
				"deserve to call yourself a dictator\n" +
				" ... wait I mean prime minister.";
	}

	@Override
	public void render(float delta) {
		renderBackground();
		batch.begin();
		smallFont.draw(batch, message, 220, 450);
		bigFont.draw(batch, "You Lose!", 425, 600);
		batch.end();
	}
}
