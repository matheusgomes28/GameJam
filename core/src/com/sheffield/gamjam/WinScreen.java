package com.sheffield.gamjam;

import com.badlogic.gdx.Screen;

public class WinScreen extends GameOverScreen implements Screen {

	String message;
	float winningScore = 500000000; //0.5 billion
	
	public WinScreen(GameJam g, GameScreen gameScreen) {
		super(g, gameScreen);
		message =	"       You reached £" + GameScreen.numFormat(winningScore, ",") + "\n" +
					"          But did you really win?\n" +
					"          Is this enough money?\n" +
					"No! You need more. There is always\n" +
					"more money to take and why shouldn't\n" +
					"       you, David Cameron, have it.";
	}

	@Override
	public void render(float delta) {
		renderBackground();
		batch.begin();
		smallFont.draw(batch, message, 210, 500);
		bigFont.draw(batch, "You Win!", 450, 620);
		batch.end();
	}
}
