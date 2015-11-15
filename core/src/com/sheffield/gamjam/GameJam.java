package com.sheffield.gamjam;

import com.badlogic.gdx.Game;

public class GameJam extends Game {
    public StartScreen startScreen;
    public GameScreen gameScreen;

    @Override
    public void create() {
        startScreen = new StartScreen(this);
        gameScreen = new GameScreen(this);

        setScreen(startScreen);

//    }
//	@Override
//	public void create () {
//		batch = new SpriteBatch();
//
//        // Creating ground object
//        ground = new Ground(Gdx.files.local("ground.png"));
//        bg = new Texture(Gdx.files.local("bg.png"));
//
//        player = new Player(this);
//        enemies = new ArrayList<Enemy>();
//
//        enemyBullets = new ArrayList<EnemyBullet>();
//        bullets = new ArrayList<Bullet>();
//		explosions = new ArrayList<Explosion>();
//		ground = new Ground(Gdx.files.local("ground.png"));
//
//
//        // Font stuff
//		FreeTypeFontGenerator gen =
//				new FreeTypeFontGenerator(Gdx.files.internal("OpenSans-Regular.ttf"));
//
//		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
//		parameter.size = 50;
//		parameter.color = Color.WHITE;
//		parameter.shadowOffsetY = 1;
//		parameter.shadowOffsetX = 1;
//		font12 = gen.generateFont(parameter);
//		gen.dispose();
//
//
//		// Creating clouds with texture region
//		TextureRegion t = new TextureRegion(new Texture(Gdx.files.local("sprite_sheet1.png")), 930, 1805, 203, 133);
//		clouds = new Cloud[]{new Cloud(t, 3, 50),
//							  new Cloud(t, 3, 500),
//							  new Cloud(t, 3, 1000)};
//
//        // Creating the trees array
//        trees = new Tree[]{new Tree(t,ground, 6, 50, 1),
//                new Tree(t,ground, 6, 1000, 0),
//                new Tree(t,ground, 6, 2000, 1)};
//
//        // Creating enemies init
//		for(int i = 0; i<1; i++)
//			enemies.add(new Enemy(this));
//		explosions = new ArrayList<Explosion>();
//
//		shapeRenderer = new ShapeRenderer();
//	}
//
//	@Override
//	public void render () {
//		Gdx.gl.glClearColor(0.4f, 0.4f, 1, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//
//        player.update();
//
//        for (Iterator<Bullet> it = bullets.iterator(); it.hasNext();) {
//            Bullet b = it.next();
//            b.update();
//            if (b.pos.y <= ground.g.getHeight()) {
//                Vector2 pos = b.pos;
//                it.remove();
//                money += Math.random()*1000;
//                explosions.add(new Explosion(pos.x, pos.y));
//            }
//        }
//
//        batch.begin();
//
//        batch.draw(bg, 0,0);
//        ground.draw(batch);
//
//        // Updating clouds fam
//        for(Cloud cloud:clouds) cloud.update(batch);
//        for(Tree tree:trees) tree.update(batch);
//
//
//		// Render player's bullets
//        for(Bullet b : bullets)
//        	b.draw(batch);
//        System.out.println("Bullets: "+bullets.size());
//
//        // Render enemy's bullets
//        for(EnemyBullet eb : enemyBullets) {
//        	eb.update();
//        	eb.draw(batch);
//        }
//        System.out.println("Enemy Bullets: "+enemyBullets.size());
//
//        // Render enemies
//        for(Enemy e : enemies)
//        	e.render(batch);
//
//
//        // Render player
//		player.render(batch);
//
//        for (Iterator<Bullet> it = bullets.iterator(); it.hasNext();) {
//            Bullet b = it.next();
//            b.update();
//            if (b.pos.y <= ground.g.getHeight()) {
//                Vector2 pos = b.pos;
//                it.remove();
//                explosions.add(new Explosion(pos.x, pos.y));
//            }
//        }
//
//
//
//        font12.draw(batch, "Money: £"+numFormat(money,","), 10, 705);
//
//        batch.end();
//
//        for (Iterator<Explosion> it = explosions.iterator(); it.hasNext();) {
//            Explosion e = it.next();
//			boolean doDelete = e.update(1.0f / 60.0f);
//            if (doDelete) {
//                it.remove();
//                continue;
//            }
//			e.render(shapeRenderer);
//		}
//	}
//
//	public static String numFormat(double number, String divisor)
//	{
//		String numString = String.valueOf((long)number);
//
//		for(int i = numString.length()-3; i > 0; i -= 3)
//		{
//			numString = numString.substring( 0, i ) + divisor +
//					    numString.substring( i, numString.length());
//		}
//		return numString;
//	}
    }
}