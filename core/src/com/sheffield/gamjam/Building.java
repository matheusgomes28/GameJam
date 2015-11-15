package com.sheffield.gamjam;

import com.badlogic.gdx.graphics.Texture;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Building {
	public static float speed = 7;
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

    static final Texture[] POSITIVE = {HOSPITAL, SCHOOL, HOUSE};
    static final Texture[] NEGATIVE = {SKYSCRAPER, BANK};

    static final int[] P_WIDTHS = {200,100,110};
    static final int[] N_WIDTHS = {68, 200};

    static final int[] P_HEIGHTS = {150,150,100};
    static final int[] N_HEIGHTS = {292,160};

    boolean positive;
    
    static float interval = 0.5f;
    static float lastTime = 0;
	

	
	public static Building randomBuilding(GameScreen gameScreen) {
		
		float prob = 0.1f*gameScreen.level;
		
		if(Math.random() > prob)
		{
			int num = (int)Math.round(Math.random()*(POSITIVE.length-1));
			return new Building(POSITIVE[num], Gdx.graphics.getWidth() + 50, 63, P_WIDTHS[num], P_HEIGHTS[num], true);
		}
		else
		{
			int num = (int)Math.round(Math.random()*(NEGATIVE.length-1));
			return new Building(NEGATIVE[num], Gdx.graphics.getWidth() + 50, 63, N_WIDTHS[num], N_HEIGHTS[num], false);
		}
	}
	
	public static void addNewBuilding(List<Building> buildings, float timeElapsed, GameScreen gameScreen) {
        if (buildings.isEmpty()) {
            buildings.add(randomBuilding(gameScreen));
            lastTime = timeElapsed;

        } else {

            if ((timeElapsed - lastTime) > interval) {
                buildings.add(randomBuilding(gameScreen));
                lastTime  = timeElapsed;
                //if(interval > 0.2)  interval = interval - 0.1f;
            }
        }
	}
	
	public static void removeBuildings(List<Building> buildings) {
		Building firstBuilding = buildings.get(0);
		if (firstBuilding.x + firstBuilding.width < 0
            && buildings.size() > 1) {
			buildings.remove(0);
		}
	}

	public static void updateAll(List<Building> buildings, SpriteBatch batch, float timeElapsed, GameScreen gameScreen) {
		addNewBuilding(buildings, timeElapsed, gameScreen);
		removeBuildings(buildings);
		if (buildings != null && !buildings.isEmpty()) {
			for (Building b: buildings) {
				b.update(b.x-speed, b.y);
				b.draw(batch);
			}
		}
		
		speed = gameScreen.level;
		//interval = 1 + 1/gameScreen.level;
	}
	
	Building(Texture buildingTexture, float x, float y, float width, float height, boolean pstv) {
		positive = pstv;
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
		setDimensions(width, (height + width/3)/5);
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
