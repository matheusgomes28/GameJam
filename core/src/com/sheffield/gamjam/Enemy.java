package com.sheffield.gamjam;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Enemy {
	
	Texture image,bullet;
	Vector2 pos,direction;
	final int SCREEN_WIDTH = Gdx.graphics.getWidth();
	final int SCREEN_HEIGHT = Gdx.graphics.getHeight();
	final int LEFT_BOUND;
	float lastShoot = 0;
	float elapsedTime = 0;
	Animation animation;
	GameScreen game;
	
	public Enemy(GameScreen g) {
        image = new Texture("gent64trans.png");
        bullet = new Texture("bacon-trans.png");
	    game = g;
	    pos = new Vector2(Gdx.graphics.getWidth() + bullet.getWidth()/2, game.ground.g.getHeight()-30);
	    direction = new Vector2(-2,0);
	    LEFT_BOUND = 0;

		// Creating the animation
		Array<Sprite> keys = new Array<Sprite>();
		keys.add(new Sprite(new Texture(Gdx.files.local("animation-enemy/001.png"))));
		keys.add(new Sprite(new Texture(Gdx.files.local("animation-enemy/002.png"))));
		keys.add(new Sprite(new Texture(Gdx.files.local("animation-enemy/003.png"))));
		animation = new Animation(0.1f, keys, Animation.PlayMode.LOOP_REVERSED);


	}
	
	public void render(SpriteBatch batch) {
		elapsedTime += Gdx.graphics.getDeltaTime();
		batch.draw(animation.getKeyFrame(elapsedTime, true), this.pos.x, game.ground.g.getHeight()-30, 50, 100);
	    update();
	}
	
	public void update() {
		pos.add(direction);
		if(pos.x<=SCREEN_WIDTH-image.getWidth()) {
			if(Gdx.input.justTouched() && Math.random()>0.5) {
				Vector2 origin = game.player.pos.cpy();
				Vector2 v = origin.sub(pos.cpy()).nor();
				double degrees = Math.toDegrees(Math.atan2(v.y, v.x));
				Vector2 dir = v.cpy();
				dir.nor();				
				Vector2 gunPos = pos.cpy();				
				gunPos.mulAdd(v, 80);							
				game.enemyBullets.add(new EnemyBullet(bullet, gunPos, v.cpy()));
			}
		}
		if (pos.x < -bullet.getWidth()) {
			pos.x = (float)(Gdx.graphics.getWidth() + bullet.getWidth()/1.5);
			pos.y = game.ground.g.getHeight()-5;
		}
	}
	
}
