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

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

/**
 * @author Lavanya Sinha, Julia Berardini
 * @version 4.0_04.06.2017
 * The HighScoreMenu class allows the user to choose which level they want to view high scores for.
 * <p><b> Instance variables </b>
 * <p><b> stage </b> (private stage) The variable that holds a 2D scene graph containing hierarchies of actors.
 * <p><b> skin </b> (private Skin) The variable that stores resources such as textures, fonts, colors, etc.
 * <p><b> table </b> (private Table) The variable that auto-positions buttons into an organized table.
 * <p><b> tweenManager </b> (private TweenManager) The variable that manages all the static animations.
 * <p><b> game </b> (public final RabbitRun) The variable that represents the game.
 * <p><b> background </b> (private Texture) The variable that represents the background images in the screen. 
 */
public class HighScoreMenu implements Screen {

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
	
	/** The constructor sets up all the labels, buttons and backgrounds associated with the screen.
	 * @param game (final RabbitRun) The variable that represents the game. 
	 */
	public HighScoreMenu (final RabbitRun game)
	{
		this.game= game; 
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		skin = new Skin(Gdx.files.internal("clean-crispy-ui.json"), new TextureAtlas("clean-crispy-ui.atlas"));

		table = new Table(skin);
		table.setFillParent(true);
		background = new Texture ("background.png"); 

		// creating heading
		Label heading = new Label("PICK A LEVEL TO VIEW HIGH SCORES:", skin);
		heading.setFontScale(2);

		// creating buttons
		//level 1 button 
				TextButton level1 = new TextButton("Garden Jump", skin);
				level1.addListener(new ClickListener() {

					@Override
					public void clicked(InputEvent event, float x, float y) {
						stage.addAction(sequence(moveTo(0, -stage.getHeight(), .5f), run(new Runnable() {

							@Override
							public void run() {
								((Game) Gdx.app.getApplicationListener()).setScreen(new HighScoresDisplay (game));
							}
						})));
					}
				});
				level1.pad(15);
				//level 2 button 
				TextButton level2 = new TextButton("Plant Passage", skin);
				level2.addListener(new ClickListener() {

					@Override
					public void clicked(InputEvent event, float x, float y) {
						stage.addAction(sequence(moveTo(0, -stage.getHeight(), .5f), run(new Runnable() {

							@Override
							public void run() {
								((Game) Gdx.app.getApplicationListener()).setScreen(new HighScoresDisplay (game));
							}
						})));
					}
				});
				level2.pad(15);
				//level 3 button 
				TextButton level3 = new TextButton("Matter March", skin);
				level3.addListener(new ClickListener() {

					@Override
					public void clicked(InputEvent event, float x, float y) {
						stage.addAction(sequence(moveTo(0, -stage.getHeight(), .5f), run(new Runnable() {

							@Override
							public void run() {
								((Game) Gdx.app.getApplicationListener()).setScreen(new HighScoresDisplay (game));
							}
						})));
					}
				});
				level3.pad(15);
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
				table.add(level1).spaceBottom(15).row();
				table.add(level2).spaceBottom(15).row();
				table.add(level3).spaceBottom(15).row ();
				table.add (mainMenu); 

				stage.addActor(table);
				
				tweenManager = new TweenManager();
				Tween.registerAccessor(Actor.class, new ActorAccessor());


	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#resize(int, int)
	 */
	@Override
	public void resize(int width, int height) {
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