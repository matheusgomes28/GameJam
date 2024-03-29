package com.sheffield.gamjam;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Ground{
	
	// Variables we need for the ground stuff
	public Texture g;
	
	// The constructor we'll need 
	public Ground(FileHandle f){
		this.g = new Texture(f);
	}
	
	public void draw(SpriteBatch b){
		b.draw(g, 0, 0);
	}
}
