package com.sheffield.gamjam;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Bullet {
	
	Vector2 pos;
	Vector2 vel;
	Sprite bulletSprite;
	final float SPEED = 15f;

	public Bullet(Texture b, Vector2 p, Vector2 d)
	{
		pos = p;
		vel = d.nor().scl(SPEED);
		bulletSprite = new Sprite(b);
		bulletSprite.setPosition(pos.x, pos.y);
		
		Vector2 dir = vel.cpy().nor();
		
		bulletSprite.setRotation((float)Math.toDegrees(Math.atan2(dir.y, dir.x)));
	}
	
	public void update()
	{	
		vel.y -= 0.05;
		
		Vector2 dir = vel.cpy().nor();
		
		bulletSprite.setRotation((float)Math.toDegrees(Math.atan2(dir.y, dir.x)));
		
		pos.add(vel);
		bulletSprite.setPosition(pos.x, pos.y);
	}
	
	public void draw(SpriteBatch batch)
	{
		bulletSprite.draw(batch);
	}
	
}
