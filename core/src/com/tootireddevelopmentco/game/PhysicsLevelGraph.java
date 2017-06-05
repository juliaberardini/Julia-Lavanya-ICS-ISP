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

/**
 * @author Lavanya Sinha, Julia Berardini
 * @version 4.0_04.06.2017
 * The ChemistryLevelGraph class implements the background images into the background of the screen for the chemistry level of RabbitRun.
 * <p><b> Instance variables </b>
 * <p><b> map </b> (private TiledMap) The variable used for png that is the background map.
 * <p><b> timeElapsed </b> (private float) The timer variable.
 * <p><b> renderer </b> (private TileMapRenderer) Renders variable 'map' as the background of the game. 
 * <p><b> detector </b> (public static CollisionDetector) Collision detector between the world, game and map.
 * <p><b> game </b> (public final) The variable that represents the music, spriteBatch and camera within the RabbitRun game class.
 * <p><b> player </b> (private Player) The variable used to represent the player. 
 * <p><b> world </b> (private World) The variable that represents the World (physical entities, simulation etc). 
 * <p><b> debugRenderer </b> (private Box2DDebugRenderer) The variable that creates the debugger box around the sprites.
 * <p><b> debugMatrix </b> (private Matrix4) Variable that encapsulates a column major 4 by 4 matrix.
 * <p><b> name </b> (private String) String variable that holds the user's name
 * <p><b> score </b> (private Score) Score variable that holds the user's score and level.
 * <p><b> o </b> (private Obstacle) The variable that creates an Obstacle in the game. 
 * <p><b> skin </b> (private Skin) The variable that contains the textures, fonts, colours etc. 
 * <p><b> stage </b> (private Stage) The variable that draws the background.
 * <p><b> label </b> (private Label) Variable that creates a text label. 
 */


public class PhysicsLevelGraph extends ApplicationAdapter implements Screen {
	
	private TiledMap map;  
	private float timeElapsed = 0; 
	private TiledMapRenderer renderer; 
	public static CollisionDetector detector;
	public final RabbitRun game;  
	private Player player;   
	private World world;
	private Box2DDebugRenderer debugRenderer; 
	private Matrix4 debugMatrix; 
	private String name;
	private Score score;
	private Obstacle o; 
	private Skin skin; 
	private Stage stage; 
	private Label label; 

	
	
	/** The constructor sets up all the Sprites and Maps to the background of the screen.
	 * @param game (final RabbitRun) The RabbitRun game class with the spriteBatch and music.
	 * @param strtX (float) The starting x-position of the user.
	 * @param strtY (float) The starting y-position of the user. 
	 */
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
	
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#show()
	 */
	@Override
	public void show() 
	{
		
	}
	
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#render(float)
	 * trunc (int) This variable truncates the timeElapsed variable to an int. 
	 * trunk2 (int) This variable divides trunc by 100
	 * 
	 * Conditional Statement 1: this makes sure that no black space is shown around the screen when the player jumps. 
	 * Conditional Statement 2: this makes sure that when the player reaches the coordinates of the house, it switches screens,
	 * informing the player that they have completed the level and allowing them to return to the main menu. 
	 * Conditional Statement 2: this makes sure that when the player reaches the coordinates of the house, it switches screens,
	 * informing the player that they have completed the level and allowing them to return to the main menu. 
	 */
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
	    }
	    
	}
	
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#hide()
	 */
	@Override
	public void hide() {
		dispose (); 
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.ApplicationAdapter#dispose()
	 */
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
