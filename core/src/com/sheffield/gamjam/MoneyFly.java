package com.sheffield.gamjam;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class MoneyFly {
	
	BitmapFont font;
	Vector2 pos;
	int amount;
	private int duration;
	public boolean finished;
	public boolean stuck;
	public static float speed = 1f;
	
	public MoneyFly(Vector2 p, int a, BitmapFont bmf, boolean stp)
	{
		stuck = stp;
		
		font = bmf;
		amount = a;
		pos = p;
		
		duration = 80;
	}
	
	public void update()
	{
		duration--;
		
		pos.y += 2;
		
		if(!stuck)
			pos.x -= speed;
		
		if(duration < 0)
			finished = true;
	}
	
	public void draw(SpriteBatch batch)
	{
		font.draw(batch, "£"+GameScreen.numFormat(amount,","), pos.x, pos.y);
	}
	
	

}
