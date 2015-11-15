package com.sheffield.gamjam;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class MoneyFly {
	
	BitmapFont font;
	Vector2 pos;
	int amount;
	boolean finished = false;
	
	public MoneyFly(Vector2 p, int a, BitmapFont bmf)
	{
		font = bmf;
		amount = a;
		pos = p;
	}
	
	public void update()
	{
		pos.y += 2;
		pos.x -= 2;
		
		if(pos.y > 300)
			finished = true;
	}
	
	public void draw(SpriteBatch batch)
	{
		font.draw(batch, "£"+GameScreen.numFormat(amount,","), pos.x, pos.y);
	}
	
	

}
