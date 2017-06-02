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

public class LevelMenu implements Screen {
	private Skin skin;  //disposed 
	private Table table;  //not disposable
	private TweenManager tweenManager; 
	public final RabbitRun game; 
	private Stage stage; //disposed
	private Texture background; 
	
	public LevelMenu (final RabbitRun game)
	{
		this.game= game; 
		stage= new Stage (); 
		Gdx.input.setInputProcessor(stage);
		background= new Texture ("background.png");
		
		skin = new Skin(Gdx.files.internal("clean-crispy-ui.json"), new TextureAtlas("clean-crispy-ui.atlas"));
		table = new Table(skin);
		table.setFillParent(true);
		//make heading
		Label heading = new Label("Levels", skin);
		heading.setFontScale(2);
		
		//level buttons!! 
		//level 1 button 
		TextButton level1 = new TextButton("Garden Jump", skin);
		level1.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				stage.addAction(sequence(moveTo(0, -stage.getHeight(), .5f), run(new Runnable() {

					@Override
					public void run() {
						((Game) Gdx.app.getApplicationListener()).setScreen(new PhysicsLevelGraph(game, 100, 100));
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
						((Game) Gdx.app.getApplicationListener()).setScreen(new BiologyLevelGraph(game, 100, 100));
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
						((Game) Gdx.app.getApplicationListener()).setScreen(new ChemistryLevelGraph(game, 100, 100));
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
		
		//fade in 
		Timeline.createSequence().beginSequence()
		.push(Tween.set(level1, ActorAccessor.ALPHA).target(0))
		.push(Tween.set(level2, ActorAccessor.ALPHA).target(0))
		.push(Tween.set(level3, ActorAccessor.ALPHA).target(0))
		.push(Tween.set(mainMenu, ActorAccessor.ALPHA).target(0))
		.push(Tween.from(heading, ActorAccessor.ALPHA, .25f).target(0))
		.push(Tween.to(level1, ActorAccessor.ALPHA, .25f).target(1))
		.push(Tween.to(level2, ActorAccessor.ALPHA, .25f).target(1))
		.push(Tween.to(level3, ActorAccessor.ALPHA, .25f).target(1))
		.push(Tween.to(mainMenu, ActorAccessor.ALPHA,.25f).target (1))
		.end().start(tweenManager);
		
		//table fade
		Tween.from(table, ActorAccessor.ALPHA, .75f).target(0).start(tweenManager);
		Tween.from(table, ActorAccessor.Y, .75f).target(Gdx.graphics.getHeight() / 8).start(tweenManager);

		tweenManager.update(Gdx.graphics.getDeltaTime());
		
		
	}
	@Override
	public void show() {
	
	}

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

	@Override
	public void resize(int width, int height) {	
		table.invalidateHierarchy (); 
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
		stage.dispose();
		skin.dispose();
		background.dispose();
	}

}
