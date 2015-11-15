package com.sheffield.gamjam;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class GameScreen implements Screen {
	
	SpriteBatch batch;
    float timeElapsed = 0;

	ShapeRenderer shapeRenderer;

    public Player player;
    public ArrayList<Enemy> enemies;
    public ArrayList<Bullet> bullets;
    ArrayList<Explosion> explosions;
    ArrayList<EnemyBullet> enemyBullets;

	public Texture bg;
	public Cloud[] clouds;
	public Tree[] trees;
	public Ground ground;
	public float speed = 1f;

    ArrayList<Sound> sounds;
    Sound soundtrack;

    ArrayList<Sound> exSounds;


	private BitmapFont font12;
	long money = 100L;
	int rage = -1;
	GameJam game;
	
	List<Building> buildings;
    float time = 0; // For sound playing
    boolean sPlaying = false;

	ArrayList<MoneyFly> moneyFlies = new ArrayList<MoneyFly>();
	private BitmapFont fontGreen;
	private BitmapFont fontRed;
	public long highScore = 0;
	public int level = 1;

	final Sound SFX_CASH_GAIN = Gdx.audio.newSound(Gdx.files.local("sounds/coinFalling.ogg"));
	final Sound SFX_CASH_LOSS = Gdx.audio.newSound(Gdx.files.local("sounds/oinkPig.ogg"));
	final Sound SFX_LEVEL_UP = Gdx.audio.newSound(Gdx.files.local("sounds/cashRegister.ogg"));

	
	public GameScreen(GameJam g)
	{
		game = g;
	}
	
	@Override
	public void show() {
		batch = new SpriteBatch();


		// Creating ground object
		ground = new Ground(Gdx.files.local("ground.png"));
		bg = new Texture(Gdx.files.local("bg.png"));


        player = new Player(this);
        enemies = new ArrayList<Enemy>();
        bullets = new ArrayList<Bullet>();
        enemyBullets = new ArrayList<EnemyBullet>();
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

		// Creating the trees array
		trees = new Tree[]{new Tree(t,ground, 6, 50, 1),
				new Tree(t,ground, 6, 1000, 0),
				new Tree(t,ground, 6, 2000, 1)};

		// Creating enemies init
		enemies.add(new Enemy(this));

        exSounds = new ArrayList<Sound>();
        exSounds.add(Gdx.audio.newSound(Gdx.files.local("sounds/explosion1.wav")));
        exSounds.add(Gdx.audio.newSound(Gdx.files.local("sounds/explosion2.wav")));


		shapeRenderer = new ShapeRenderer();
		buildings = new ArrayList<Building>();


    // Creating sounds
    sounds = new ArrayList<Sound>();
    sounds.add(Gdx.audio.newSound(Gdx.files.local("sounds/disgustedByThePoor.ogg")));
    sounds.add(Gdx.audio.newSound(Gdx.files.local("sounds/gettingPiggyWithIt.ogg")));
    sounds.add(Gdx.audio.newSound(Gdx.files.local("sounds/iveGotLoadsOfMoney.ogg")));
    sounds.add(Gdx.audio.newSound(Gdx.files.local("sounds/readyForClassWar.ogg")));
    sounds.add(Gdx.audio.newSound(Gdx.files.local("sounds/risingFromTheBottomToTheTop.ogg")));
    sounds.add(Gdx.audio.newSound(Gdx.files.local("sounds/sellingTheNhs.ogg")));

    soundtrack = Gdx.audio.newSound(Gdx.files.local("sounds/soundtrack.mp3"));
    soundtrack.loop(0.6f);
}


	@Override
	public void render(float delta) {
        Gdx.gl.glClearColor(0.4f, 0.4f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        player.update();

        for (Iterator<EnemyBullet> it = enemyBullets.iterator(); it.hasNext(); ) {
        	EnemyBullet eb = it.next();

        	if(eb.pos.x +16 > player.pos.x && eb.pos.y +32> player.pos.y &&
        			eb.pos.x < player.pos.x+player.image.getWidth() &&
        			eb.pos.y < player.pos.y+player.image.getHeight())
        	{
        		it.remove();
        		int tax = -2*((int)(money*0.05f) + (int)(0.8*Math.pow(10, level)));
        		money += tax;
        		moneyFlies.add(new MoneyFly(new Vector2(player.pos.x, player.pos.y), tax, fontRed, true));

        	}
        }
        
        Building.speed = 7+level;
        MoneyFly.speed = 7+level;
        Tree.speed = (7+level)/2;
        Cloud.speed = (7+level)/2;
        
        loop:
        for (Iterator<Bullet> it = bullets.iterator(); it.hasNext(); ) {
            Bullet b = it.next();
            b.update();

            for (Building bldng : buildings)
                if (bldng.checkBoundaries(b.pos.x, b.pos.y)) {
                	bldng.destroy();
            		it.remove();
            		int win = (int)(Math.random()*Math.pow(10, level+1));

            		if(!bldng.positive)
            			win = -win;

            		money += win;

            		if(bldng.positive) {
						moneyFlies.add(new MoneyFly(new Vector2(bldng.x, bldng.y), win, fontGreen, false));
						SFX_CASH_GAIN.play();
					} else {
						moneyFlies.add(new MoneyFly(new Vector2(bldng.x, bldng.y), win, fontRed, false));
						SFX_CASH_LOSS.play();
					}

            		explosions.add(new Explosion(b.pos.x, b.pos.y));
                    exSounds.get(MathUtils.random(0, exSounds.size()-1)).play(0.2f);
            		continue loop;
                }

            if (b.pos.y <= ground.g.getHeight()) {
                Vector2 pos = b.pos;
                it.remove();
                explosions.add(new Explosion(pos.x, pos.y));
                exSounds.get(MathUtils.random(0, exSounds.size()-1)).play(0.2f);
            }
        }

        batch.begin();
        batch.draw(bg, 0, 0);
        ground.draw(batch);


        // Updating clouds fam
        for (Cloud cloud : clouds) cloud.update(batch);
        for (Tree tree : trees) tree.update(batch);

        Building.updateAll(buildings, batch, timeElapsed, this);

        for (Bullet b : bullets)
            b.draw(batch);

        // Render enemy's bullets
        for (EnemyBullet eb : enemyBullets) {
            eb.update();
            eb.draw(batch);
        }

        // Render enemies
        for (Enemy e : enemies)
            e.render(batch);

        font12.draw(batch, "Money: £" + numFormat(money, ","), 10, 705);
        font12.draw(batch, "Level "+ level , 1000, 705);
 
        player.render(batch);
        
        for(MoneyFly mf : moneyFlies)
		{
			mf.update();
			mf.draw(batch);
		}
        
        batch.end();

        for (Iterator<Explosion> it = explosions.iterator(); it.hasNext(); ) {
            Explosion e = it.next();
            boolean doDelete = e.update(1.0f / 60.0f);
            if (doDelete) {
                it.remove();
                continue;
            }
            e.render(shapeRenderer);
        }

        //updating time - play every 30 secs
        float t = Gdx.graphics.getDeltaTime();
        timeElapsed += t;
        time += t;


        if((int) time % 5 == 0 && sPlaying == false){
            time += 1;
            sounds.get(MathUtils.random(0, sounds.size()-1)).play();
            sPlaying = true;
        }
        else sPlaying = false;

        for (Iterator<MoneyFly> it = moneyFlies.iterator(); it.hasNext();) {
            if(it.next().finished)
            	it.remove();
		}

        if(money > highScore )
        	highScore = money;

        if(rage > 0)
        	rage--;

        if(money < 0)
            game.setScreen(game.loseScreen);

        //win condition
        if(false)
            game.setScreen(game.winScreen);

        int newLevel = Math.max(1, (int)Math.log10(money) - 1);
        if (newLevel > level) {
            level = newLevel;
            SFX_LEVEL_UP.play();
        }
        switch(newLevel) {
            case 4: // £100,000
                if (enemies.size() < 2) enemies.add(new Enemy(this)); // 2 enemies now
                break;
            case 8: // £1,000,000,000
                if (enemies.size() < 3) enemies.add(new Enemy(this)); // 3 enemies now
                break;
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
		
		if(number < 0)
			numString = numString.substring(1, numString.length());

		for(int i = numString.length()-3; i > 0; i -= 3)
		{
			numString = numString.substring( 0, i ) + divisor +
					    numString.substring( i, numString.length());
		}

		if(number < 0)
			numString = "-"+numString;

		return numString;
	}

}
