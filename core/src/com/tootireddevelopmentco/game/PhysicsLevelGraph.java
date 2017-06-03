package com.tootireddevelopmentco.game;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;



public class PhysicsLevelGraph extends ApplicationAdapter implements Screen {
	
	private TiledMap map;  //disposed
	private float timeElapsed = 0; //not disposable
	private TiledMapRenderer renderer; //not disposable 
	public static CollisionDetector detector; //not disposable 
	public final RabbitRun game;  // shouldn't dispose game? 
	private Player player; //disposed  
	private World world;//disposed 
	private Box2DDebugRenderer debugRenderer; //disposed 
	private Matrix4 debugMatrix; //not disposable 
	private String name; //not
	private Score score;//not
	private Obstacle o; //not?
	private Skin skin; //disposed
	private Stage stage; //disposed
	private Label label; //no dispose

	
	
	public PhysicsLevelGraph (final RabbitRun game, float strtX, float strtY)
	{
		this.game=game; 
		game.camera.setToOrtho(false);
		game.camera.position.set (0, 0, 0);
		game.camera.update();
		stage= new Stage (); 
		world = new World (new Vector2(0f,-9.8f), true);  
		map = new TmxMapLoader ().load ("LevelMap.tmx"); 
		renderer = new OrthogonalTiledMapRenderer(map);
		detector= new CollisionDetector (world, "LevelMap.tmx", game);
	    player = new Player(world, game, strtX, strtY, name, score);
	    player.changeJump(true);
	    debugRenderer = new Box2DDebugRenderer();
	    Gdx.input.setInputProcessor(player); 
	    o = new Obstacle ("carrotsprite.png", "potatosprite.png", "tomatosprite.png");
	    skin = new Skin(Gdx.files.internal("clean-crispy-ui.json"), new TextureAtlas("clean-crispy-ui.atlas"));
	    label = new Label (Float.toString(timeElapsed), skin);
	    label.setPosition(game.camera.position.x+30, 600);
	    label.setFontScale(2f);
	    stage.addActor (label);
	}
	@Override
	public void show() 
	{
		
	}
	@Override
	public void render(float delta) 
	{
		int trunc; 
		double trunk2; 
		Gdx.gl.glClearColor(0, 0, 0, 1);
	    Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    timeElapsed += delta;
	    trunc = (int) (timeElapsed);
	    trunk2 = trunc/100.0; 
	    if (player.getX () < 3250)
	    {
	    game.camera.position.set(new Vector3(player.getX()+472, 315, 0));
	    }
	    game.camera.update();
	    label.setText(Double.toString(trunk2));
	    world.step(1f / 60f, 6, 2);
	    
	    debugMatrix = game.batch.getProjectionMatrix().cpy().scale(Constants.PIXELS_TO_METERS, Constants.PIXELS_TO_METERS, 0);
	    game.batch.setProjectionMatrix(game.camera.combined);
	    renderer.setView(game.camera);
	    renderer.render();
	    game.batch.begin();
	    player.draw(game.batch);
	    o.draw (game.batch);
	    game.batch.end();
	    stage.draw();

	    debugRenderer.render(world, debugMatrix);	
	    
	    
	    if (player.getX () >= 4000)
	    {
	    	((Game) Gdx.app.getApplicationListener()).setScreen (new ReturnToMain (game));
	    	Score.calculateScore (timeElapsed); 

	    }
	    
	}
	
	@Override
	public void hide() {
		dispose (); 
	}

	@Override
	public void dispose () 
	{
		map.dispose();
	    player.getTexture().dispose();
	    world.dispose();
	    debugRenderer.dispose (); 
	    stage.dispose();
	    skin.dispose();
	}
}
