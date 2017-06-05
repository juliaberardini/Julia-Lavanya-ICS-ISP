package com.tootireddevelopmentco.game;


import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

/**
 * @author Lavanya Sinha, Julia Berardini
 * @version 4.0_04.06.2017
 * The QuitScreen class sets up the screen and GUI for the goodbye screen.
 * <p><b> Instance variables </b>
 * <p><b> skin </b> (private Skin) The variable that stores resources for UI widgets to use (texture regions, ninepatches, fonts, colors, etc)
 * <p><b> table </b> (private Table) The variable that makes an organised table to display buttons
 * <p><b> tweenManager </b> (private TweenManager)  Allows interpolation of every attributes from any object
 * <p><b> game </b> (private RabbitRun) Reference to the game
 * <p><b> stage </b> (private Stage) A 2D scene graph containing hierarchies of actors.
 * <p><b> background </b> (private Texture) The background image 
 * <p><b> goodBye </b> (private Texture) The goodbye image 
 * <p><b> rabbitRun </b> (Game) The variable that refers to the game 
 */

public class QuitScreen implements Screen {
	private Skin skin;  //disposed 
	private Table table;  //not disposable
	private TweenManager tweenManager; 
	public final RabbitRun game; 
	private Stage stage; //disposed
	private Texture background, goodBye; 
	Game rabbitRun; 
	
	/** The constructor sets up the quit screen graphics and GUI
	 * @param game (final RabbitRun) This variable references the game
	 */
	public QuitScreen (final RabbitRun game)
	{
		this.game= game; 
		stage= new Stage (); 
		Gdx.input.setInputProcessor(stage);
		background= new Texture ("background.png");
		goodBye = new Texture ("goodbyeMessage.png");
		
		skin = new Skin(Gdx.files.internal("clean-crispy-ui.json"), new TextureAtlas("clean-crispy-ui.atlas"));
		table = new Table(skin);
		table.setFillParent(true);
		//make heading
		Label heading = new Label("THANK YOU FOR PLAYING", skin);
		heading.setFontScale(2);
		
		//exit button 
		TextButton exit = new TextButton("EXIT", skin);
		exit.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				Timeline.createParallel().beginParallel()
						.push(Tween.to(table, ActorAccessor.ALPHA, .75f).target(0))
						.push(Tween.to(table, ActorAccessor.Y, .75f).target(table.getY() - 50)
								.setCallback(new TweenCallback() {

									@Override
									public void onEvent(int type, BaseTween<?> source) {
										Gdx.app.exit();
									}
								}))
						.end().start(tweenManager);
			}
		});
		
		//back button
		TextButton back = new TextButton("BACK", skin);
		back.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				stage.addAction(sequence(moveTo(0, -stage.getHeight(), .5f), run(new Runnable() {
					@Override
					public void run() {
						((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenu(game));
					}
				})));
			}
		});
		back.pad(15);
		

		//display
		table.add(heading).spaceBottom (400).row();
		table.add (exit); 
		table.add (back); 

		stage.addActor(table);
		
		tweenManager = new TweenManager();
		Tween.registerAccessor(Actor.class, new ActorAccessor());
		
		//fade in 
		Timeline.createSequence().beginSequence()
		.push(Tween.set(exit, ActorAccessor.ALPHA).target(0))
		.push(Tween.from(heading, ActorAccessor.ALPHA, .25f).target(0))
		.push(Tween.to(exit, ActorAccessor.ALPHA,.25f).target (1))
		.end().start(tweenManager);
		
		//table fade
		Tween.from(table, ActorAccessor.ALPHA, .75f).target(0).start(tweenManager);
		Tween.from(table, ActorAccessor.Y, .75f).target(Gdx.graphics.getHeight() / 8).start(tweenManager);

		tweenManager.update(Gdx.graphics.getDeltaTime());
		
	}
	
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#show()
	 */
	@Override
	public void show() {
		

	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#render(float)
	 */
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.batch.begin(); 
		game.batch.draw (background, 0, 0);
		game.batch.draw (goodBye, 0,0);
		game.batch.end (); 
		stage.act(delta);
		stage.draw();

		tweenManager.update(delta); 

	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#resize(int, int)
	 */
	@Override
	public void resize(int width, int height) {
		table.invalidateHierarchy (); 

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
		stage.dispose();
		skin.dispose();
		background.dispose();

	}

}
