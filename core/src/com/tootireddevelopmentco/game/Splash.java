package com.tootireddevelopmentco.game;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

public class Splash implements Screen {

	private Sprite splash, splash2, splash3, splash4, logo;
	private TweenManager tweenManager;
	final RabbitRun game; 

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

	@Override
	public void resize(int width, int height) {
	}

	public void show() {
		
}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		dispose (); 
	}

	@Override
	public void dispose() {
		splash.getTexture().dispose (); 
		splash2.getTexture().dispose (); 
		splash3.getTexture().dispose (); 
		splash4.getTexture().dispose();
		logo.getTexture().dispose(); 
	}
}
		

