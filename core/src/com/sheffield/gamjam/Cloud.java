package com.sheffield.gamjam;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class Cloud {
	
	private int x, y; // position
	private TextureRegion region;
	private final int MOVE_SPEED;
	
	
	public Cloud(TextureRegion region, int moveSpeed, int xOffSet){
		this.MOVE_SPEED = moveSpeed;
		this.region = new TextureRegion(region);
		this.region.setRegion(930, 1805, 203, 133);

		this.x = xOffSet + Gdx.graphics.getWidth() + region.getRegionWidth()/2;
		this.y = getY();
	}
	
	// Generate random Y position
	private int getY(){
		return (int) (Gdx.graphics.getHeight()/2 + Math.random()*(Gdx.graphics.getHeight()/2 - region.getRegionHeight()));
	}
	
	
	public void draw(SpriteBatch b){
		b.draw(region, x, y);
	}
	
	public void update(SpriteBatch b){
		x = x - MOVE_SPEED;
		draw(b);
		
		if (x < -region.getRegionWidth()) {
			x = Gdx.graphics.getWidth() + region.getRegionWidth()/2;
			y = getY();
		}
		
	}
}