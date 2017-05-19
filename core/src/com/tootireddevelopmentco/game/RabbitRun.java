package com.tootireddevelopmentco.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class RabbitRun extends Game {
	SpriteBatch batch; 
	Texture img;
	public static final String TITLE = "Rabbit Run"; 
	
	
	@Override
	public void create () {
		setScreen (new Splash ());
	}

	@Override
	public void render () {
		super.render (); 
	}
	
	@Override
	public void dispose () {
		super.dispose ();
	}

}
