package com.sheffield.gamjam;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class GameJam extends Game {
	
	public StartScreen startScreen;
	public GameScreen gameScreen;

	@Override
	public void create() {
		startScreen = new StartScreen(this);
		gameScreen = new GameScreen(this);
		
		setScreen(startScreen);
		
	}
	/*SpriteBatch batch;
	ShapeRenderer shapeRenderer;

    public Player player;
    public ArrayList<Bullet> bullets;
	ArrayList<Explosion> explosions;
	public Cloud[] clouds;
	public Ground ground;
	private BitmapFont font12;
	int money = 0;
	List<Building> buildings;

	@Override
	public void create () {
		batch = new SpriteBatch();
		// Creating ground object
		ground = new Ground(Gdx.files.local("ground.png"));
		float[] rgb = {0.5f, 1, 0.5f};
		ground = new Ground(Gdx.files.local("ground.png"));
        player = new Player(this);
        bullets = new ArrayList<Bullet>();
		explosions = new ArrayList<Explosion>();
		ground = new Ground(Gdx.files.local("ground.png"));
		
		FreeTypeFontGenerator gen = 
				new FreeTypeFontGenerator(Gdx.files.internal("OpenSans-Regular.ttf"));
		
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 50;
		parameter.color = Color.WHITE;
		parameter.shadowOffsetY = 1;
		parameter.shadowOffsetX = 1;
		font12 = gen.generateFont(parameter);
		gen.dispose();
		
		
		// Creating clouds with texture region
		TextureRegion t = new TextureRegion(new Texture(Gdx.files.local("sprite_sheet1.png")), 930, 1805, 203, 133);
		clouds = new Cloud[]{new Cloud(t, 4, 50),
							  new Cloud(t, 4, 500),
							  new Cloud(t, 4, 1000)};
		
		shapeRenderer = new ShapeRenderer();
		buildings = new ArrayList<Building>();   // <-- include when buildings are wanted or use specific setup
		explosions = new ArrayList<Explosion>();

		shapeRenderer = new ShapeRenderer();
		
		
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.4f, 0.4f, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		

        player.update();

        for (Iterator<Bullet> it = bullets.iterator(); it.hasNext();) {
            Bullet b = it.next();
            b.update();
            if (b.pos.y <= ground.g.getHeight()) {
                Vector2 pos = b.pos;
                it.remove();
                money += Math.random()*1000;
                explosions.add(new Explosion(pos.x, pos.y));
            }
        }

        batch.begin();
        
		ground.draw(batch);
		Building.updateAll(buildings, batch);
		// Updating clouds fam
		for(Cloud cloud:clouds) cloud.update(batch);

        for(Bullet b : bullets)
        	b.draw(batch);
        
        font12.draw(batch, "Money: £"+numFormat(money,","), 10, 705);
        
		player.render(batch);
        
        player.render(batch);
        batch.end();

        for (Iterator<Explosion> it = explosions.iterator(); it.hasNext();) {
            Explosion e = it.next();
			boolean doDelete = e.update(1.0f / 60.0f);
            if (doDelete) {
                it.remove();
                continue;
            }
			e.render(shapeRenderer);
		}
	}
	
	public static String numFormat(double number, String divisor)
	{
		String numString = String.valueOf((long)number);
		
		for(int i = numString.length()-3; i > 0; i -= 3)
		{
			numString = numString.substring( 0, i ) + divisor +
					    numString.substring( i, numString.length());
		}
		return numString;
	}*/
}
