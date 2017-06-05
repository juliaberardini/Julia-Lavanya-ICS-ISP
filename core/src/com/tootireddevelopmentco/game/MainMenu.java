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

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

/**
 * @author Lavanya Sinha, Julia Berardini
 * @version 4.0_04.06.2017
 * The MainMenu class sets up the screen and GUI for the main menu.
 * <p><b> Instance variables </b>
 * <p><b> stage </b> (private Stage) A 2D scene graph containing hierarchies of actors.
 * <p><b> skin </b> (private Skin) The variable that stores resources for UI widgets to use (texture regions, ninepatches, fonts, colors, etc)
 * <p><b> table </b> (private Table) The variable that makes an organised table to display buttons
 * <p><b> tweenManager </b> (private TweenManager)  Allows interpolation of every attributes from any object
 * <p><b> game </b> (private RabbitRun) Reference to the game
 * <p><b> background </b> (private Texture) The background image 
 */


public class MainMenu implements Screen {

	private Stage stage;
	private Skin skin;
	private Table table;  
	private TweenManager tweenManager;
	public final RabbitRun game;
	private Texture background; 
	

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#render(float)
	 */
	@Override
	public void render (float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		game.batch.begin ();
		game.batch.draw (background, 0, 0);
		game.batch.end();
		stage.act(delta);
		stage.draw();

		tweenManager.update(delta);
	}
	
	/**
	 * @param game (final RabbitRun) The variable that represents the game. 
	 */
	public MainMenu (final RabbitRun game)
	{
		this.game= game; 
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		skin = new Skin(Gdx.files.internal("clean-crispy-ui.json"), new TextureAtlas("clean-crispy-ui.atlas"));

		table = new Table(skin);
		table.setFillParent(true);
		background = new Texture ("background.png"); 

		// creating heading
		Label heading = new Label(RabbitRun.TITLE, skin);
		heading.setFontScale(2);

		// creating buttons
		TextButton buttonPlay = new TextButton("PLAY", skin);
		buttonPlay.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				stage.addAction(sequence(moveTo(0, -stage.getHeight(), .5f), run(new Runnable() {

					@Override
					public void run() {
						((Game) Gdx.app.getApplicationListener()).setScreen(new EnterUsername (game));
					}
				})));
			}
		});
		buttonPlay.pad(15);

		TextButton buttonScores = new TextButton("HIGH SCORES", skin);
		buttonScores.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				stage.addAction(sequence(moveTo(0, -stage.getHeight(), .5f), run(new Runnable() {

					@Override
					public void run() {
						((Game) Gdx.app.getApplicationListener()).setScreen(new HighScoreMenu(game));
					}
				})));
			}
		});
		TextButton buttonInstructions = new TextButton("INSTRUCTIONS", skin);
		buttonInstructions.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				stage.addAction(sequence(moveTo(0, -stage.getHeight(), .5f), run(new Runnable() {

					@Override
					public void run() {
						((Game) Gdx.app.getApplicationListener()).setScreen(new Instructions(game));
					}
				})));
			}
		});
		buttonInstructions.pad(15);

		buttonScores.pad(15);

		TextButton buttonExit = new TextButton("QUIT", skin);
		buttonExit.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {

				Timeline.createParallel().beginParallel();

				stage.addAction(sequence(moveTo(0, -stage.getHeight(), .5f), run(new Runnable() {



				@Override

				public void run() {
				((Game) Gdx.app.getApplicationListener()).setScreen(new QuitScreen (game));
				}
				})));
				}
		});
		buttonExit.pad(15);

		// putting stuff together
		table.add(heading).spaceBottom(100).row();
		table.add(buttonPlay).spaceBottom(15).row();
		table.add(buttonScores).spaceBottom(15).row();
		table.add (buttonInstructions).spaceBottom (15).row(); 
		table.add(buttonExit);

		stage.addActor(table);

		// creating animations
		tweenManager = new TweenManager();
		Tween.registerAccessor(Actor.class, new ActorAccessor());

		// heading colour animation
		Timeline.createSequence().beginSequence()
				.push(Tween.to(heading, ActorAccessor.RGB, .5f).target(0, 0, 1))
				.push(Tween.to(heading, ActorAccessor.RGB, .5f).target(0, 1, 0))
				.push(Tween.to(heading, ActorAccessor.RGB, .5f).target(1, 0, 0))
				.push(Tween.to(heading, ActorAccessor.RGB, .5f).target(1, 1, 0))
				.push(Tween.to(heading, ActorAccessor.RGB, .5f).target(0, 1, 1))
				.push(Tween.to(heading, ActorAccessor.RGB, .5f).target(1, 0, 1))
				.push(Tween.to(heading, ActorAccessor.RGB, .5f).target(1, 1, 1))
				.end().repeat(Tween.INFINITY, 0).start(tweenManager);

		// heading and buttons fade-in
		Timeline.createSequence().beginSequence()
				.push(Tween.set(buttonPlay, ActorAccessor.ALPHA).target(0))
				.push(Tween.set(buttonScores, ActorAccessor.ALPHA).target(0))
				.push(Tween.set(buttonExit, ActorAccessor.ALPHA).target(0))
				.push(Tween.from(heading, ActorAccessor.ALPHA, .25f).target(0))
				.push(Tween.to(buttonPlay, ActorAccessor.ALPHA, .25f).target(1))
				.push(Tween.to(buttonScores, ActorAccessor.ALPHA, .25f).target(1))
				.push(Tween.to(buttonExit, ActorAccessor.ALPHA, .25f).target(1))
				.end().start(tweenManager);

		// table fade-in
		Tween.from(table, ActorAccessor.ALPHA, .75f).target(0).start(tweenManager);
		Tween.from(table, ActorAccessor.Y, .75f).target(Gdx.graphics.getHeight() / 8).start(tweenManager);

		tweenManager.update(Gdx.graphics.getDeltaTime());
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#resize(int, int)
	 */
	@Override
	public void resize(int width, int height) {
		//stage.setViewport (width, height, false);
		table.invalidateHierarchy (); 
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#show()
	 */
	@Override
	public void show() {
		
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#hide()
	 */
	@Override
	public void hide() {
		dispose();
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
	 * @see com.badlogic.gdx.Screen#dispose()
	 */
	@Override
	public void dispose() {
		stage.dispose();
		skin.dispose();
		background.dispose();
	}

}