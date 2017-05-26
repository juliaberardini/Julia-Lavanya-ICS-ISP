package com.tootireddevelopmentco.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class HighScoreDisplay implements Screen {

	Game rabbitRun; 
// private Texture highScoreBack; 
	private List <String> playerString = new ArrayList <String> (); 
	
	public HighScoreDisplay ()
	{
		PlayerCollection p = new PlayerCollection ();
		for (int i = 0 ; i < p.displayArr().size(); i++)
		{
			playerString.add(p.displayArr().get (i).toString ()); 
		}
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
