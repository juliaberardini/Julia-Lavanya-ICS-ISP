package com.tootireddevelopmentco.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class RabbitRun extends Game {
	public SpriteBatch batch; 
	public OrhtographicCamera camera; 
	public static final String TITLE = "Rabbit Run"; 
	
	@Override
	public void create () {
		batch = new SpriteBatch (); 
		cam = new OrthographicCamera (); 
		setScreen (new Splash ());
	}

	@Override
	public void render () {
		super.render (); 
	}
	
	@Override
	public void dispose () {
		batch.dispose ();
	}

}
