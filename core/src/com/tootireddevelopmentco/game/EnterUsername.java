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
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import java.util.ArrayList; 

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;


/**
 * @author Lavanya Sinha, Julia Berardini
 * @version 4.0_04.06.2017
 * The EnterUsername class sets up the screen and allows the user to enter a username.
 * <p><b> Instance variables </b>
 * <p><b> skin </b> (private Skin) The variable that stores resources for UI widgets to use (texture regions, ninepatches, fonts, colors, etc)
 * <p><b> table </b> (private Table) The variable that makes an organised table to display buttons
 * <p><b> tweenManager </b> (private TweenManager)  Allows interpolation of every attributes from any object
 * <p><b> game </b> (private RabbitRun) Reference to the game
 * <p><b> stage </b> (private Stage) A 2D scene graph containing hierarchies of actors.
 * <p><b> background </b> (private Texture) The background image 
 * <p><b> username </b> (private static TextField) The player's username
 * <p><b> rabbitRun </b> (Game) The game
 * <p><b> rabrunPlayers </b> (ArrayList) The array list of players.
 */

public class EnterUsername implements Screen {

	private Skin skin;  
	private Table table;  
	private TweenManager tweenManager; 
	public final RabbitRun game; 
	private Stage stage;
	private Texture background; 
	private static TextField username; 
	Game rabbitRun; 
	ArrayList<Player> rabrunPlayers = new ArrayList<Player>();


	/**
	 * @param game (final RabbitRun) reference to the game
	 */
	public EnterUsername (final RabbitRun game)
	{

		
		this.game= game;   
		stage= new Stage (); 
		Gdx.input.setInputProcessor(stage);
		background= new Texture ("background.png");
		
		skin = new Skin(Gdx.files.internal("clean-crispy-ui.json"), new TextureAtlas("clean-crispy-ui.atlas"));
		table = new Table(skin);
		table.setFillParent(true);
		//make heading
		Label heading = new Label("ENTER YOUR USERNAME:", skin);
		heading.setFontScale(2);
		
		// creating buttons
				TextButton continueButton = new TextButton("Continue", skin);
				continueButton.addListener(new ClickListener() {
 
					@Override
					public void clicked(InputEvent event, float x, float y) {
						stage.addAction(sequence(moveTo(0, -stage.getHeight(), .5f), run(new Runnable() {

							@Override
							public void run() { 
								((Game) Gdx.app.getApplicationListener()).setScreen(new LevelMenu (game));

							}

							
						})));  

						rabrunPlayers.add (new Player(game, username.getText (), new Score (0, null)));
						System.out.println (rabrunPlayers);  
					}
					

				});

				continueButton.pad(15);
		
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
		table.add (continueButton); 
		table.add (back);  

		stage.addActor(table);
		
		tweenManager = new TweenManager();
		Tween.registerAccessor(Actor.class, new ActorAccessor());
		
		//fade in 
		Timeline.createSequence().beginSequence()
		.push(Tween.set(continueButton, ActorAccessor.ALPHA).target(0))
		.push(Tween.from(heading, ActorAccessor.ALPHA, .25f).target(0))
		.push(Tween.to(continueButton, ActorAccessor.ALPHA,.25f).target (1))
		.end().start(tweenManager);
		
		//table fade
		Tween.from(table, ActorAccessor.ALPHA, .75f).target(0).start(tweenManager);
		Tween.from(table, ActorAccessor.Y, .75f).target(Gdx.graphics.getHeight() / 8).start(tweenManager);

		tweenManager.update(Gdx.graphics.getDeltaTime());
		
		//username textbox
		username = new TextField ("", skin);
		username.setPosition(300, 300);
		username.setSize (300,40);
		
		stage.addActor(username);

		
	}


	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#show()
	 */
	@Override
	public void show() {
		
	}


	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#resize(int, int)
	 */
	@Override
	public void resize(int width, int height) {
		
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


	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#render(float)
	 */
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.batch.begin(); 
		game.batch.draw (background, 0, 0);
		game.batch.end (); 
		stage.act(delta);
		stage.draw();

		tweenManager.update(delta); 

		
	}

	/** This method returns the text in the username textbox. 
	 * @return the text in the username textbox
	 */
	public static String getStr() {
		return username.getText(); 
	}

}
