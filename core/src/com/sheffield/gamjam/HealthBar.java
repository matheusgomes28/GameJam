package com.sheffield.gamjam;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by Matheus on 15/11/2015.
 */
public class HealthBar {

    private int health;
    private ShapeRenderer s;

    public HealthBar(int h, ShapeRenderer s){
        this.health = h;
        this.s = s;
    }

    public void draw(){

        s.begin();
        s.setColor(1,1,1,0.5f);
    }
}
