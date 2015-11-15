package com.sheffield.gamjam;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class GameScreen implements Screen {
	
	SpriteBatch batch;
	ShapeRenderer shapeRenderer;

    public Player player;
    public ArrayList<Bullet> bullets;
	ArrayList<Explosion> explosions;
	public Cloud[] clouds;
	public Ground ground;
	private BitmapFont font12;
	int money = 0;
	GameJam game;
	
	List<Building> buildings;
	
	ArrayList<MoneyFly> moneyFlies = new ArrayList<MoneyFly>();
	private BitmapFont fontGreen;
	private BitmapFont fontRed;
	
	
	public GameScreen(GameJam g)
	{
		game = g;
	}
	
	@Override
	public void show() {
		batch = new SpriteBatch();
		// Creating ground object
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
		parameter.size = 30;
		parameter.color = Color.GREEN;
		fontGreen = gen.generateFont(parameter);
		parameter.color = Color.RED;
		fontRed = gen.generateFont(parameter);
		gen.dispose();
		
		
		// Creating clouds with texture region
		TextureRegion t = new TextureRegion(new Texture(Gdx.files.local("sprite_sheet1.png")), 930, 1805, 203, 133);
		clouds = new Cloud[]{new Cloud(t, 4, 50),
							  new Cloud(t, 4, 500),
							  new Cloud(t, 4, 1000)};

		explosions = new ArrayList<Explosion>();

		shapeRenderer = new ShapeRenderer();
		
		buildings = new ArrayList<Building>();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.4f, 0.4f, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        player.update();
        
        loop:
        for (Iterator<Bullet> it = bullets.iterator(); it.hasNext();) {
            Bullet b = it.next();
            b.update();
            
            for(Building bldng : buildings)
            	if(bldng.checkBoundaries(b.pos.x, b.pos.y))
            	{
            		bldng.destroy();
            		it.remove();
            		int win = (int)(Math.random()*1000);
            		money += win;
            		moneyFlies.add(new MoneyFly(new Vector2(bldng.x, bldng.y), win, fontGreen));
            		explosions.add(new Explosion(b.pos.x, b.pos.y));
            		continue loop;
            	}
            
            if (b.pos.y <= ground.g.getHeight()) {
                Vector2 pos = b.pos;
                it.remove();
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
		
		for(MoneyFly mf : moneyFlies)
		{
			mf.update();
			mf.draw(batch);
		}
		
        batch.end();
        
        for (Iterator<MoneyFly> it = moneyFlies.iterator(); it.hasNext();) {
            if(it.next().finished)
            	it.remove();
		}
        
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

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
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
	}

}
/*package com.sheffield.gamjam;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class GameScreen implements Screen {
	
	SpriteBatch batch;
	ShapeRenderer shapeRenderer;

    public Player player;
    public ArrayList<Bullet> bullets;
	ArrayList<Explosion> explosions;
	public Cloud[] clouds;
	public Ground ground;
	private BitmapFont font12;
	int money = 0;
	GameJam game;
	
	List<Building> buildings;
	
	
	public GameScreen(GameJam g)
	{
		game = g;
	}
	
	@Override
	public void show() {
		batch = new SpriteBatch();
		// Creating ground object
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

		explosions = new ArrayList<Explosion>();

		shapeRenderer = new ShapeRenderer();
		
		buildings = new ArrayList<Building>();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.4f, 0.4f, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        player.update();
        
        loop:
        for (Iterator<Bullet> it = bullets.iterator(); it.hasNext();) {
            Bullet b = it.next();
            b.update();
            
            for(Building bldng : buildings)
            	if(bldng.checkBoundaries(b.pos.x, b.pos.y))
            	{
            		bldng.destroy();
            		it.remove();
            		money += Math.random()*1000;
            		explosions.add(new Explosion(b.pos.x, b.pos.y));
            		continue loop;
            	}
            
            if (b.pos.y <= ground.g.getHeight()) {
                Vector2 pos = b.pos;
                it.remove();
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
        
        font12.draw(batch, "Money: �"+numFormat(money,","), 10, 705);
        
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

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
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
	}

}
>>>>>>> branch 'menuez' of https://github.com/mattgomes28/GameJam.gi*/
