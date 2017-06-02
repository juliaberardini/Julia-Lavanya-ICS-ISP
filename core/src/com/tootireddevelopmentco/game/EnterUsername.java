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

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;


public class EnterUsername implements Screen {
	
	private Skin skin;  //disposed 
	private Table table;  //not disposable
	private TweenManager tweenManager; 
	public final RabbitRun game; 
	private Stage stage; //disposed
	private Texture background; 
	private TextField username; 
	Game rabbitRun;  


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
								//yoohoo this needs to go in an array dont forget!
								System.out.println (username.getText()); 
								((Game) Gdx.app.getApplicationListener()).setScreen(new LevelMenu (game));
							}
						})));
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
		
		System.out.println (username.getText()); 

		
	}


	@Override
	public void show() {
		
	}


	@Override
	public void resize(int width, int height) {
		
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


}
