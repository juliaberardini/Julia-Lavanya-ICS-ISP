package com.tootireddevelopmentco.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * @author Lavanya Sinha, Julia Berardini
 * @version 4.0_04.06.2017
 * The RabbitRun class sets up the screen and GUI for the goodbye screen.
 * <p><b> Instance variables </b>
 * <p><b> batch </b> (public SpriteBatch) The group of Sprites used in the game 
 * <p><b> camera </b> (public OrthographicCamera) The viewed angle of the screen
 * <p><b> TITLE </b> (public static final String) The title of the game
 * <p><b> game </b> (final public RabbitRun) The reference to the game 
 * <p><b> sound </b> (private Music) The background music
 */

public class RabbitRun extends Game {
	public SpriteBatch batch; 
	public OrthographicCamera camera; 
	public static final String TITLE = "Rabbit Run";
	final public RabbitRun game= this; 
	private Music sound; 
	
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.ApplicationListener#create()
	 */
	@Override
	public void create () 
	{	
		sound = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		sound.setLooping(true);
		batch = new SpriteBatch (); 
		camera = new OrthographicCamera (); 
		setScreen (new Splash (game));
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Game#render()
	 */
	@Override
	public void render ()
	{
		super.render (); 
		sound.play();
	}
	
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Game#dispose()
	 */
	@Override
	public void dispose ()
	{
		sound.dispose();
	}

}
