package com.sheffield.gamjam;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Gdx;

public class Player {
	Texture image;
    Vector2 pos;
    final int LEFT_BOUND, RIGHT_BOUND, UPPER_BOUND, LOWER_BOUND;
    final int BASE_SPEED = 7;
	final int HIGH_SPEED = 14;
	
	Gun gun;
	GameScreen game;

    Player(GameScreen g) {
        pos = new Vector2(200, 100);
        image = new Texture("alien-david.png");
        final int SCREEN_WIDTH = Gdx.graphics.getWidth();
        final int SCREEN_HEIGHT = Gdx.graphics.getHeight();
        
        LEFT_BOUND = 0;
        RIGHT_BOUND = SCREEN_WIDTH;
        UPPER_BOUND = SCREEN_HEIGHT;
        LOWER_BOUND = SCREEN_HEIGHT/2;

        game = g;

        gun = new Gun(this);
    }

    public void render(SpriteBatch batch) {
        batch.draw(image, pos.x, pos.y);
        gun.gunSprite.draw(batch);
    }

    public void update() {
    	int speed = BASE_SPEED;
    	if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT )) {
            speed = HIGH_SPEED;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) ||
        		Gdx.input.isKeyPressed(Input.Keys.A)) {
            pos.x -= speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) ||
        		Gdx.input.isKeyPressed(Input.Keys.D)) {
            pos.x += speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP) ||
        		Gdx.input.isKeyPressed(Input.Keys.W)) {
            pos.y += speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) ||
        		Gdx.input.isKeyPressed(Input.Keys.S)) {
            pos.y -= speed;
        }

        collideBounds();
        
        gun.update();

    }

    public void collideBounds() {
        if (pos.x < LEFT_BOUND) {
            pos.x = LEFT_BOUND;
        }
        if (pos.x + image.getWidth() > RIGHT_BOUND) {
            pos.x = RIGHT_BOUND - image.getWidth();
        }
        if (pos.y + image.getHeight() > UPPER_BOUND){
            pos.y = UPPER_BOUND - image.getHeight();
        }
        if (pos.y < LOWER_BOUND) {
            pos.y = LOWER_BOUND;
        }
    }
}
