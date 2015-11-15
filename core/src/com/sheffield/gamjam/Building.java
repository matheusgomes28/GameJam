package com.sheffield.gamjam;

import com.badlogic.gdx.graphics.Texture;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Building {
	static float speed = 7;
	TextureRegion region;
	boolean destroyed;
	Texture building;
	GameScreen game;
	float x,y, width, height;
	static final Texture HOSPITAL = new Texture(Gdx.files.internal("hospital.png"));
	static final Texture SCHOOL = new Texture(Gdx.files.internal("school.png"));
	static final Texture HOUSE = new Texture(Gdx.files.internal("house.png"));
	static final Texture SKYSCRAPER = new Texture(Gdx.files.internal("skyscraper.png"));
	static final Texture BANK	 = new Texture(Gdx.files.internal("bank.png"));
	static final Texture RUBBLE = new Texture(Gdx.files.internal("rubble.png"));
	static final Texture[] TEXTURES = {HOSPITAL, SCHOOL, HOUSE, SKYSCRAPER, BANK, RUBBLE};
	static final int[] WIDTHS = {200, 100, 110, 68, 200};
	static final int[] HEIGHTS = {150, 150, 100, 292, 160};
	

	
	public static Building randomBuilding() {
		Random rand = new Random();
		int num = rand.nextInt(TEXTURES.length-1);
		return new Building(TEXTURES[num], Gdx.graphics.getWidth() + 50, 63, WIDTHS[num], HEIGHTS[num]);
	}
	
	public static void addNewBuilding(List<Building> buildings) {
		if (buildings.isEmpty()) {
			buildings.add(randomBuilding());
		}
		else {
			Building lastBuilding = buildings.get(buildings.size() - 1);
			boolean awayFromSide = lastBuilding.x + lastBuilding.width < Gdx.graphics.getWidth();
			if (awayFromSide) {
				buildings.add(randomBuilding());
			}
		}
	}
	
	public static void removeBuildings(List<Building> buildings) {
		Building firstBuilding = buildings.get(0);
		if (firstBuilding.x + firstBuilding.width < 0) {
			buildings.remove(0);
		}
	}

	public static void updateAll(List<Building> buildings, SpriteBatch batch) {
		addNewBuilding(buildings);
		removeBuildings(buildings);
		if (buildings != null && !buildings.isEmpty()) {
			for (Building b: buildings) {
				b.update(b.x-speed, b.y);
				b.draw(batch);
			}
		}
	}
	
	Building(Texture buildingTexture, float x, float y, float width, float height) {
		destroyed = false;
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
	}

	public void draw(SpriteBatch batch) {
		if (destroyed) {
			batch.draw(RUBBLE, x, y, width, height);
		}
		else {
			batch.draw(building, x, y, width, height);
		}
	}
}
