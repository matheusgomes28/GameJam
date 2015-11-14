package com.sheffield.gamjam;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Gdx;

/**
 * Created by Angus on 14/11/2015.
 */
public class Player {
	Texture image;
    Vector2 pos;
    final int LEFT_BOUND, RIGHT_BOUND, UPPER_BOUND, LOWER_BOUND;
    final int BASE_SPEED = 7;

    Player() {
        pos = new Vector2(200, 100);
        image = new Texture("shipGreen.png");
        final int SCREEN_WIDTH = Gdx.graphics.getWidth();
        final int SCREEN_HEIGHT = Gdx.graphics.getHeight();
        
        LEFT_BOUND = 0;
        RIGHT_BOUND = SCREEN_WIDTH;
        UPPER_BOUND = SCREEN_HEIGHT;
        LOWER_BOUND = SCREEN_HEIGHT/2;
    }

    public void render(SpriteBatch batch) {
        batch.draw(image, pos.x, pos.y);
        batch.end();
        ShapeRenderer renderer = new ShapeRenderer();
    }

    public void update() {
    	int speed = BASE_SPEED;
    	if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT ))
    	{
    		speed *= 2;
    	}
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            pos.x -= speed;//MathUtils.clamp(pos.x - 6, w / 2, screenW - w / 2);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            pos.x += speed; //= MathUtils.clamp(x + 6, w / 2, screenW - w / 2);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            pos.y += speed;//MathUtils.clamp(pos.x - 6, w / 2, screenW - w / 2);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            pos.y -= speed; //= MathUtils.clamp(x + 6, w / 2, screenW - w / 2);
        }

        collideBounds();
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
