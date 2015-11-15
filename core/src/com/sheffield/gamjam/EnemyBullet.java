package com.sheffield.gamjam;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class EnemyBullet {
	
	Vector2 pos;
	Vector2 dir;
	Sprite bulletSprite;;
	//final float SPEED = 4f;

	public EnemyBullet(Texture b, Vector2 p, Vector2 d)
	{
		pos = p;
		dir = d.nor();
		bulletSprite = new Sprite(b);
		bulletSprite.setPosition(pos.x, pos.y);
		
	}
	
	public void update() {
		pos.mulAdd(dir, (float)Math.random()*7+4);
		bulletSprite.setPosition(pos.x, pos.y);
		bulletSprite.rotate((float)Math.random()*10);
	}
	
	public void draw(SpriteBatch batch) {
		bulletSprite.draw(batch);
	}
}
