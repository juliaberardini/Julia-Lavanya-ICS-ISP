package com.tootireddevelopmentco.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class BiologyLevelGraph extends ApplicationAdapter implements Screen {
	
private TiledMap map; 
private TiledMapRenderer renderer;
public static CollisionDetector detector; 
public final RabbitRun game; 
private Player player; 
World world;
Box2DDebugRenderer debugRenderer;
Matrix4 debugMatrix;
String name; 
Score score; 

public BiologyLevelGraph (final RabbitRun game, float strtX, float strtY)
{
	this.game=game; 
	game.camera.setToOrtho(false);
	game.camera.position.set (0, 0, 0);
	game.camera.update();
	world = new World (new Vector2(0f,-9.8f), false);  
	map = new TmxMapLoader ().load ("LevelMap.tmx"); 
	renderer = new OrthogonalTiledMapRenderer(map);
	detector= new CollisionDetector (world, "LevelMap.tmx", game);
    player = new Player(world, game, strtX, strtY, name, score);
    player.changeJump(true);
    debugRenderer = new Box2DDebugRenderer();
    Gdx.input.setInputProcessor(player);  
}


@Override
public void show() {
	// TODO Auto-generated method stub
	
}
@Override
public void render(float delta) {
	// TODO Auto-generated method stub
	Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    game.camera.position.set(new Vector3(player.getX()+472, player.getY()+136, 0));
    game.camera.update();
    world.step(1f / 60f, 6, 2);
    debugMatrix = game.batch.getProjectionMatrix().cpy().scale(Constants.PIXELS_TO_METERS,
            Constants.PIXELS_TO_METERS, 0);
    game.batch.setProjectionMatrix(game.camera.combined);
    renderer.setView(game.camera);
    renderer.render();
    game.batch.begin();
    player.draw(game.batch);
    game.batch.end();

    debugRenderer.render(world, debugMatrix);
	
}
@Override
public void hide() {
	// TODO Auto-generated method stub
	
}

@Override
public void dispose () 
{
	map.dispose();
    player.getTexture().dispose();
    world.dispose();
}
}
