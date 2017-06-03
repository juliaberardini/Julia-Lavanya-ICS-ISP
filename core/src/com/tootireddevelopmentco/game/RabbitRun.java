package com.tootireddevelopmentco.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RabbitRun extends Game {
	public SpriteBatch batch; 
	public OrthographicCamera camera; 
	public static final String TITLE = "Rabbit Run";
	final public RabbitRun game= this; 
	private Music sound; 
	
	@Override
	public void create () 
	{	
		sound = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		sound.setLooping(true);
		batch = new SpriteBatch (); 
		camera = new OrthographicCamera (); 
		setScreen (new Splash (game));
	}

	@Override
	public void render ()
	{
		super.render (); 
		sound.play();
	}
	
	@Override
	public void dispose ()
	{
		sound.dispose();
	}

}
