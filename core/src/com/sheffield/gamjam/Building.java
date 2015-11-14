package com.sheffield.gamjam;

import com.badlogic.gdx.graphics.Texture;
import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Building {
	boolean destroyed;
	Texture rubble;
	Texture building;
	float x,y, width, height;
	
	public static List<Building> setupBuildings() {
		List<Building> buildings = new ArrayList<Building>();
		Building hospital = new Building(new Texture(Gdx.files.internal("hospital.png")), 10, 10, 50, 50);
		Building school = new Building(new Texture(Gdx.files.internal("school.png")), 100, 10, 25, 50);
		Building house = new Building(new Texture(Gdx.files.internal("house.png")), 150, 10, 25, 25);
		buildings.add(hospital);
		buildings.add(school);
		buildings.add(house);
		return buildings;
	}
	
	public static void updateAndDraw(List<Building> buildings, SpriteBatch batch) {
		if (buildings != null && !buildings.isEmpty()) {
			for (Building b: buildings) {
				b.update(b.x, b.y);
				b.draw(batch);
			}
		}
	}
	
	Building(Texture buildingTexture) {
		destroyed = false; // change to false for actual
		rubble = new Texture(Gdx.files.internal("rubble.png"));
		building = buildingTexture;
		x = 10;
		y = 10;
		width = 200;
		height = 200;
	}
	
	Building(Texture buildingTexture, float x, float y, float width, float height) {
		destroyed = false;
		rubble = new Texture(Gdx.files.internal("rubble.png"));
		building = buildingTexture;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void setDimensions(float width, float height) {
		this.width = width;
		this.height = height;
	}
	
	public boolean checkBoundaries(float checkX, float checkY) { // checks if a location is with building boundary
		boolean result = x < checkX && checkX < x+width && y < checkY && checkY < y+height;
		return result;
	}
	
	public void destroy() {
		destroyed = true;
		setDimensions(width, height/10);
	}
	
	public void update(float x, float y) {
		this.x = x;
		this.y = y;
		/* //here for debug
		int mouseX = Gdx.input.getX();
		int mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();
		boolean mouseOver = this.checkBoundaries(mouseX, mouseY);
		boolean mouseDown = Gdx.input.isButtonPressed(Input.Buttons.LEFT);
		if (mouseOver && mouseDown) {
			destroy();
		}
		*/
	}

	public void draw(SpriteBatch batch) {
		if (destroyed) {
			batch.draw(rubble, x, y, width, height);
		}
		else {
			batch.draw(building, x, y, width, height);
		}
	}
}
