package com.tootireddevelopmentco.game;

/*
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.badlogic.RabbitRun.utils.GdxTest;

public class DragAndDrop {
	Stage stage;

	public void create () {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		final Skin skin = new Skin();
		skin.add(" ", new LabelStyle(new BitmapFont(), Color.WHITE));
		skin.add(" ", new Texture(" "));

		Image sourceImage = new Image(skin, " ");
		sourceImage.setBounds(50, 125, 100, 100);
		stage.addActor(sourceImage);

		Image validTargetImage = new Image(skin, " ");
		validTargetImage.setBounds(200, 50, 100, 100);
		stage.addActor(validTargetImage);

		Image invalidTargetImage = new Image(skin, " ");
		invalidTargetImage.setBounds(200, 200, 100, 100);
		stage.addActor(invalidTargetImage);

		DragAndDrop dragAndDrop = new DragAndDrop();
		dragAndDrop.addSource(new Source(/*image) {
			public Payload dragStart (InputEvent event, float x, float y, int pointer) {
				Payload payload = new Payload();
				payload.setObject("Water");

				payload.setDragActor(new Label("Sunlight", skin));

				Label validLabel = new Label("Soil", skin);
				validLabel.setColor(0, 1, 0, 1);
				payload.setValidDragActor(validLabel);


				return payload;
			}
		});
		dragAndDrop.addTarget(new Target(/*image) {
			public boolean drag (Source source, Payload payload, float x, float y, int pointer) {
				getActor().setColor(Color.GREEN);
				return true;
			}

			public void reset (Source source, Payload payload) {
				getActor().setColor(Color.WHITE);
			}

			public void drop (Source source, Payload payload, float x, float y, int pointer) {
				System.out.println("Accepted: " + payload.getObject() + " " + x + ", " + y);
			}
		});
		dragAndDrop.addTarget(new Target(/*image) {
			public boolean drag (Source source, Payload payload, float x, float y, int pointer) {
				getActor().setColor(Color.RED);
				return false;
			}

			public void reset (Source source, Payload payload) {
				getActor().setColor(Color.WHITE);
			}

			public void drop (Source source, Payload payload, float x, float y, int pointer) {
			}
		});
	}

	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	public void resize (int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	public void dispose () {
		stage.dispose();
	}
}*/ 