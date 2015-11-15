package com.sheffield.gamjam;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Tree {

    public TextureRegion g;
    public Ground ground;
    private int move;
    private int x;
    
    public static float speed = 0.5f;

    public Tree(TextureRegion region, Ground ground, int m, int xOffSet, int choice){
        this.move  = m;
        this.x  = Gdx.graphics.getWidth() + xOffSet;
        this.ground = ground;

        // Set the region of tree
        g = new TextureRegion(region);
        if(choice == 1) g.setRegion(1282, 1195, 145, 295);
        else g.setRegion(1422, 1192, 113, 240);

    }

    public void draw(SpriteBatch b){
        b.setColor(1, 1, 1, 0.8f);
        b.draw(g, x, ground.g.getHeight(), 100, 150);
        b.setColor(1,1,1,1);
    }

    public void update(SpriteBatch b){
        this.x = x-(int)speed;
        if(x+g.getRegionWidth() < 0)
            x = Gdx.graphics.getWidth();
        draw(b);
    }
}
