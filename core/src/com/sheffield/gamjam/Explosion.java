package com.sheffield.gamjam;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import java.util.ArrayList;
import java.util.Iterator;

public class Explosion {
    class Particle {
        public float x, y, radius, dx, dy;
        public Color color;
        public int framesAlive;
    }
    ArrayList<Particle> particles;

    Explosion(float x, float y) {
        particles = new ArrayList<Particle>();
        createParticles(x, y);
    }

    private static Color randomColor() {
        return choices[MathUtils.random(0, choices.length-1)];
    }

    private static final Color[] choices = {
            Color.ORANGE,
            Color.RED,
            Color.FIREBRICK,
            Color.GOLD,
            Color.YELLOW
    };

    void createParticles(float centerX, float centerY) {
        for (int i = 0; i < 30; i++) {
            Particle p = new Particle();
            p.radius = MathUtils.random(5, 20);
            final float angle = MathUtils.random(-(float)Math.PI, (float)Math.PI);
            final float speed = 200;
            p.x = centerX + MathUtils.cos(angle) * MathUtils.random(0.0f, 16.0f);
            p.y = centerY + MathUtils.sin(angle) * MathUtils.random(0.0f, 16.0f);
            p.dx = speed * (float)Math.cos(angle);
            p.dy = speed * (float)Math.sin(angle);
            p.color = randomColor();
            p.framesAlive = MathUtils.random(10, 40);
            particles.add(p);
        }
    }

    public boolean update(float dt) {
        for (Iterator<Particle> it = particles.iterator(); it.hasNext();) {
            Particle p = it.next();
            p.x += p.dx * dt;
            p.y += p.dy * dt;
            p.framesAlive -= 1;
            p.radius *= 0.98;
            if (p.framesAlive == 0) {
                it.remove();
            }
        }
        if (particles.size() == 0) return true;
        return false;
    }

    public void render(ShapeRenderer renderer) {
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        for (Particle p: particles) {
            renderer.setColor(p.color);
            renderer.circle(p.x, p.y, p.radius);
        }
        renderer.end();
    }
}