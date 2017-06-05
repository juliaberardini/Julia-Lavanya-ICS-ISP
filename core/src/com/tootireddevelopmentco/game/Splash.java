package com.tootireddevelopmentco.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

/**
 * @author Lavanya Sinha, Julia Berardini
 * @version 4.0_04.06.2017
 * The Splash class displays the splash screen.
 * <p><b> Instance variables </b>
 * <p><b> splash </b> (private Sprite) Represents the first image in the splash screen
 * <p><b> splash2 </b> (private Sprite) Represents the second image in the splash screen
 * <p><b> splash3 </b> (private Sprite) Represents the third image in the splash screen
 * <p><b> splash4 </b> (private Sprite) Represents the fourth image in the splash screen
 * <p><b> logo </b> (private Sprite) Represents the logo in the splash screen
 * <p><b> tweenManager </b> (private TweenManager) Allows interpolation of every attributes from any object
 * <p><b> game </b> (final RabbitRun) Reference to the game
 */

public class Splash implements Screen {

	private Sprite splash, splash2, splash3, splash4, logo;
	private TweenManager tweenManager;
	final RabbitRun game; 

	/** The constructor assigns values to all the Sprites and sets up & displays the graphics
	 * @param game (final RabbitRun) The reference to the game
	 */
	public Splash (final RabbitRun game)
	{
		this.game= game; 
		Gdx.graphics.setVSync(true);

		tweenManager = new TweenManager();
		Tween.registerAccessor (Sprite.class, new SpriteAccessor ());

		logo= new Sprite (new Texture ("bigLogo.png"));
		splash = new Sprite(new Texture ("Splash1.png"));
		splash2= new Sprite (new Texture ("Splash2.png"));
		splash3= new Sprite (new Texture ("Splash3.png"));
		splash4= new Sprite (new Texture ("Splash4.png"));
		

	
		
Timeline.createSequence()
	.beginSequence()
		.push (Tween.set (logo, SpriteAccessor.ALPHA).target(0).start(tweenManager))
		.push (Tween.set (splash, SpriteAccessor.ALPHA).target(0).start(tweenManager))
		.push (Tween.set (splash2, SpriteAccessor.ALPHA).target(0).start(tweenManager))
		.push (Tween.set (splash3, SpriteAccessor.ALPHA).target(0).start(tweenManager))
		.push (Tween.set (splash4, SpriteAccessor.ALPHA).target(0).start(tweenManager))
		.push (Tween.to(logo, SpriteAccessor.ALPHA, 1.5f).target(1).repeatYoyo(1, 1f))
        .push (Tween.to(splash, SpriteAccessor.ALPHA, 1.5f).target(1).repeatYoyo(1, 1f))
		.push (Tween.to(splash2, SpriteAccessor.ALPHA, 1.5f).target(1).repeatYoyo(1, 1f))
		.push (Tween.to(splash3, SpriteAccessor.ALPHA, 1.5f).target(1).repeatYoyo(1, 1f))
		.push (Tween.to(splash4, SpriteAccessor.ALPHA, 1.5f).target(1).repeatYoyo(1, 1f))
		.end ().start (tweenManager).setCallback(new TweenCallback () {

			@Override
			public void onEvent(int arg0, BaseTween<?> arg1) {
				((Game) Gdx.app.getApplicationListener()).setScreen (new MainMenu (game));
			}
			}).start (tweenManager);
		tweenManager.update (Float.MIN_VALUE); 
	}
	
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#render(float)
	 */
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		game.batch.begin();
		logo.draw(game.batch);
		splash.draw(game.batch);
		splash2.draw (game.batch);
		splash3.draw(game.batch);
		splash4.draw(game.batch);
		game.batch.end();

		tweenManager.update(delta);
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#resize(int, int)
	 */
	@Override
	public void resize(int width, int height) {
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#show()
	 */
	public void show() {
		
}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#pause()
	 */
	@Override
	public void pause() {

	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#resume()
	 */
	@Override
	public void resume() {
		
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#hide()
	 */
	@Override
	public void hide() {
		dispose (); 
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#dispose()
	 */
	@Override
	public void dispose() {
		splash.getTexture().dispose (); 
		splash2.getTexture().dispose (); 
		splash3.getTexture().dispose (); 
		splash4.getTexture().dispose();
		logo.getTexture().dispose(); 
	}
}
		

