package com.sheffield.gamjam;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Bullet {
	
	Vector2 pos;
	Vector2 dir;
	Sprite bulletSprite;;
	final float SPEED = 6f;

	public Bullet(Texture b, Vector2 p, Vector2 d)
	{
		pos = p;
		dir = d.nor();
		bulletSprite = new Sprite(b);
		bulletSprite.setPosition(pos.x, pos.y);
		bulletSprite.setRotation((float)Math.toDegrees(Math.atan2(dir.y, dir.x)));
		
	}
	
	public void update()
	{
		pos.mulAdd(dir, SPEED);
		bulletSprite.setPosition(pos.x, pos.y);
	}
	
	public void draw(SpriteBatch batch)
	{
		bulletSprite.draw(batch);
	}
	
}
