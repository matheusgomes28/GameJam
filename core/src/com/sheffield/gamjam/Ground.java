package com.sheffield.gamjam;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;


public class Ground{
	
	// Variables we need for the ground stuff
	private float[] gColor;
	public int height;
	private int grassHeight;
	private ShapeRenderer s;
	
	// The constructor we'll need 
	public Ground(float[] rgb, int height, int gHeight){
		this.gColor = rgb;
		this.height = height;
		this.grassHeight = gHeight;
		this.s = new ShapeRenderer();
	}
	
	public void draw(){
		s.begin(ShapeType.Filled);
		
		// Creating the actual ground 
		s.setColor(new Color(0.6f,0.4f,0,1));
		s.rect(0, 0, Gdx.graphics.getWidth(), height);
		
		// Create the grass
		s.setColor(new Color(gColor[0], gColor[1], gColor[2], 1));
		s.rect(0, height - grassHeight, Gdx.graphics.getWidth(), grassHeight);
		s.end();
	}
}
