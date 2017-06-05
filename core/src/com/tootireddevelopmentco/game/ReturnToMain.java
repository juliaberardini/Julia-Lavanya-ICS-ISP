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
 * The ReturnToMain class sets up the screen and GUI for the return to main screen.
 * <p><b> Instance variables </b>
 * <p><b> skin </b> (private Skin) The variable that stores resources for UI widgets to use (texture regions, ninepatches, fonts, colors, etc)
 * <p><b> table </b> (private Table) The variable that makes an organised table to display buttons
 * <p><b> tweenManager </b> (private TweenManager)  Allows interpolation of every attributes from any object
 * <p><b> game </b> (private RabbitRun) Reference to the game
 * <p><b> stage </b> (private Stage) A 2D scene graph containing hierarchies of actors.
 * <p><b> background </b> (private Texture) The background image 
 */

public class ReturnToMain implements Screen {
	private Skin skin;  
	private Table table;  
	private TweenManager tweenManager; 
	public final RabbitRun game; 
	private Stage stage; 
	private Texture background; 
	
	/** The constructor sets up the graphics and GUI of the return to menu screen
	 * @param game (final RabbitRun) The reference to the game
	 */
	public ReturnToMain (final RabbitRun game)
	{
		this.game= game; 
		stage= new Stage (); 
		Gdx.input.setInputProcessor(stage);
		background= new Texture ("background.png");
		
		skin = new Skin(Gdx.files.internal("clean-crispy-ui.json"), new TextureAtlas("clean-crispy-ui.atlas"));
		table = new Table(skin);
		table.setFillParent(true);
		//make heading
		Label heading = new Label("Congratulations! Ronnie is Home!", skin);
		heading.setFontScale(2);
		 

		//main menu button 
		TextButton mainMenu = new TextButton("Main Menu", skin);
		mainMenu.addListener(new ClickListener() {

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
		mainMenu.pad(15);

		//display
		table.add(heading).spaceBottom(100).row();
		table.add (mainMenu); 

		stage.addActor(table);
		
		tweenManager = new TweenManager();
		Tween.registerAccessor(Actor.class, new ActorAccessor());
		
		//fade in 
		Timeline.createSequence().beginSequence()
		.push(Tween.set(mainMenu, ActorAccessor.ALPHA).target(0))
		.push(Tween.from(heading, ActorAccessor.ALPHA, .25f).target(0))
		.push(Tween.to(mainMenu, ActorAccessor.ALPHA,.25f).target (1))
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
