package com.sheffield.gamjam;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Gun {
	
	Sprite gunSprite;
	Texture bulletTexture;
	Player player;
	
	public Gun(Player p)
	{
		player = p;
		
		gunSprite = new Sprite(new Texture("rocketLauncher.png"));
		gunSprite.setRotation(-90);
		bulletTexture = new Texture("bullet.png");
	}
	
	public void update()
	{
		Vector2 pos = player.pos;
		
		int mouseY = (Gdx.graphics.getHeight() - Gdx.input.getY())*720/Gdx.graphics.getHeight();
		int mouseX = Gdx.input.getX()*1280/Gdx.graphics.getWidth();
		
		Vector2 orgn = new Vector2(mouseX, mouseY);

		gunSprite.setPosition(pos.x, pos.y);

		Vector2 v = orgn.sub(pos).nor();
		
		double degrees = Math.toDegrees(Math.atan2(v.y, v.x));
		
		gunSprite.setRotation((int)degrees);
		
		if(Gdx.input.justTouched())
		{
			Vector2 dir = v.cpy();
			
			dir.nor();
			
			Vector2 gunPos = pos.cpy();
			
			gunPos.x += 50;
			gunPos.y += 20;
			
			gunPos.mulAdd(v, 80);
			
			
			player.game.bullets.add(new Bullet(bulletTexture, gunPos, v.cpy()));
			
		}
	}
}