package com.tootireddevelopmentco.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

public class DragAndDropImplement implements InputProcessor 
{
	final RabbitRun game; 
	Sprite [] pics; 
	int num, startY, selected; 
	int [] startX; 
	World world; 
	private boolean validTouch, touching;
	private String level; 
	
	

	public DragAndDropImplement (final RabbitRun game, boolean three, String fi1, String fi2, String fi3, int startX, int startY, World world, String level)
	{
		this.game= game; 
		this.world= world;
		this.startY= startY;
		this.level= level; 
		if (three)
		{
			num=3;
		}
		else 
		{
			num=2; 
		}
		pics= new Sprite [num];
		this.startX = new int [num];
		pics [0] = new Sprite(new Texture (fi2));
		pics [1] = new Sprite(new Texture (fi1));
		if (three)
		{
		pics [2] = new Sprite(new Texture (fi3));
		}
		for (int i = 0; i < pics.length; i++)
		{
			this.startX [i] = startX + 200*(i-1);
			pics [i].setPosition (startX, startY);
		}
	}
	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		touching=true;
		Vector3 worldCoordinates = new Vector3(screenX, screenY, 0);
	    game.camera.unproject(worldCoordinates);
		screenY= 630-screenY;
		for(int i = 0; i < pics.length; i++)
	      {
		
		if (worldCoordinates.x >= startX [i] && worldCoordinates.x <= startX [i] + pics [i].getWidth ()
				&& screenY >= startY && screenY <= startY + pics [i].getHeight())
		{
			validTouch= true; 
			selected = i; 
			break; 
		}
	      }
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// implement this to check if it is touching the right body 
		touching= false;
		validTouch = false;
		pics [selected].setPosition (startX [selected], startY);
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		
		GameLogic g= new GameLogic (level); 
		Vector3 worldCoordinates = new Vector3(screenX, screenY, 0);
	    game.camera.unproject(worldCoordinates);
		if (validTouch)
		{
		pics [selected].setPosition (worldCoordinates.x, worldCoordinates.y);
		}
		
		for (int i= 0; i <5; i++)
		{
		if (validTouch &&(worldCoordinates.x >= Obstacle.getPosition ()[i] && worldCoordinates.x <= Obstacle.getPosition () [i] + Obstacle.getWidth(i)
		&& worldCoordinates.y >= 187 && worldCoordinates.y <= 187 + Obstacle.getHeight(i) && g.doLogic (selected, Obstacle.getOrder ()[i])))
		{
			Fixture fix= CollisionDetector.obstacleArr[i].getFixtureList().first ();
			fix.setSensor (true);
			Obstacle.setDraw (i); 
		}
		
		}			
			
		return false; 

	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {

		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void update (float delta)
	{
		if (!touching)
    	{
			for (int i =0 ; i < pics.length; i++)
			{
			startX [i] =(int) game.camera.position.x + 200*(i-1); 
			pics [i].setPosition (startX[i], startY); 
			}
    	}

	}
	
	public void draw(SpriteBatch batch) 
	{		
		 update(Gdx.graphics.getDeltaTime());
		 for (int i =0; i < pics.length; i++)
		 pics[i].draw(batch);	
	}


}


