package com.tootireddevelopmentco.game;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Source;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;

public class DragAndDrop extends ScreenAdapter {

	private Stage stage = new Stage();

	public DragAndDrop () 
	{
		Skin skin =  new Skin(Gdx.files.internal("clean-crispy-ui.json"), new TextureAtlas("clean-crispy-ui.atlas"));

		stage.setDebugAll(true);

		List<String> elements = new List<> (skin);
		List <String >sell = new List <> (skin);
		
		elements.add ("Water");
		elements.add("Oxygen");
		elements.add("Sun");
	
		Table table = new Table(skin);
		table.setFillParent(true);
		stage.addActor(table);

		table.defaults();
		table.add("Water");
		table.add ("Sun"); 
		table.add("Oxygen").row();
		table.add (elements).expand().fill();
		table.add (sell).expand().fill();

		DragAndDrop dnd = new DragAndDrop();
		dnd.addSource(new Source (elements)) {
			final Payload payload = new Payload();
			public Payload dragStart(InputEvent event, float x, float y, int pointer) {
				String item = elements.getSelected();
				payload.setObject(item);
				elements.getItems().removeIndex(elements.getSelectedIndex());
				payload.setDragActor(new Label(item, skin));
				payload.setInvalidDragActor(new Label(item + "Correct!", skin));
				payload.setValidDragActor(new Label(item + "Incorrect.", skin));
				return payload;
			}

			@Override
			public void dragStop(InputEvent event, float x, float y, int pointer, Payload payload, Target target) {
				if(target == null)
					elements.getItems().add((String) payload.getObject());
			}

			@Override
			public String getSystemId() {
				return null;
			}

			@Override
			public void setSystemId(String systemId) {
			}
		});
		dnd.addTarget(new Target(sell) {
			@Override
			public boolean drag(Source source, Payload payload, float x, float y, int pointer) {
				return !"Element".equals(payload.getObject());
			}

			@Override
			public void drop(Source source, Payload payload, float x, float y, int pointer) {
				sell.getItems().add((String) payload.getObject());
			}
		});
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

}
