package com.tootireddevelopmentco.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RabbitRun extends Game {
	public SpriteBatch batch; 
	public OrthographicCamera camera; 
	public static final String TITLE = "Rabbit Run";
	final public RabbitRun game= this; 
	
	@Override
	public void create () 
	{
		batch = new SpriteBatch (); 
		camera = new OrthographicCamera (); 
		setScreen (new enterUsername (game));
	}

	@Override
	public void render ()
	{
		super.render (); 
	}
	
	@Override
	public void dispose ()
	{

	}

}
