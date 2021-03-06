package com.tootireddevelopmentco.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

/**
 * @author Lavanya Sinha, Julia Berardini
 * @version 4.0_04.06.2017
 * The DragAndDropImplement class allows us to implement the code that allows players to drag and drop items throughout multiple levels.
 * <p><b> Instance variables </b>
 * <p><b> game </b> (final RabbitRun) The variable that represents the music, spriteBatch and camera within the RabbitRun game class.
 * <p><b> pics </b> (private Sprite []) An array of Sprites that decides whether there are 3 Sprites to be dragged and dropped in the given level or 2.
 * <p><b> num </b> (private int)  The number of Sprites that need to be drag and dropped in a given level of the game.
 * <p><b> startY </b> (private int) The starting y-value of the player.
 * <p><b> selected </b> (private int) The variable that represents the x and y location of the Sprites.
 * <p><b> startX </b> (private int []) The array that determines the starting x-value of the player.
 * <p><b> validTouch </b> (private boolean) Whether or not the drag and drop is able to drag onto the the obstacle Sprite
 * <p><b> touching </b> (private boolean) Whether or not the drag and drop Sprite is touching the obstacle Sprite
 * <p><b> level </b> (private String) The name of the level that the drag and drop code is implemented in. 
 */

public class DragAndDropImplement implements InputProcessor 
{
	final RabbitRun game; 
	private Sprite [] pics; 
	private int num, startY, selected; 
	private int [] startX; 
	private boolean validTouch, touching;
	private String level; 
	 
	

	/** The constructor establishes the specifics of the game and sets up the Sprites accordingly
	 * @param game (final RabbitRun) The RabbitRun game class with the spriteBatch and music.
	 * @param three (boolean) The variable that declares whether there are 2 or 3 drag and drop Sprites in a level.
	 * @param fi1 (String) Determines the texture of the Sprite.
	 * @param fi2 (String) Determines the texture of the Sprite.
	 * @param fi3 (String) Determines the texture of the Sprite.
	 * @param startX (int) The starting x-position of the player.
	 * @param startY (int) The starting y-position of the player.
	 * @param world (World) The variable that manages all physics entities and dynamic simulation
	 * @param level (String) The name of the level that the user is on
	 */
	/* CONDITIONAL STATEMENTS
	 * 1- determines if there are 2 Sprites to drag and drop or 3
	 * 2- Adds the image of the Sprite to the Sprite depending on whether there are 2 Sprites to drag and drop or 3
	 * 
	 * LOOPS
	 * A- Implements the drag and drop into the pictures
	 */
	public DragAndDropImplement (final RabbitRun game, boolean three, String fi1, String fi2, String fi3, int startX, int startY, World world, String level)
	{
		this.game= game; 
		this.startY= startY;
		this.level= level; 
		//1
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
		//2
		if (three)
		{
		pics [2] = new Sprite(new Texture (fi3));
		}
		//A
		for (int i = 0; i < pics.length; i++)
		{
			this.startX [i] = startX + 200*(i-1);
			pics [i].setPosition (startX, startY);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.InputProcessor#keyDown(int)
	 */
	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.InputProcessor#keyUp(int)
	 */
	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.InputProcessor#keyTyped(char)
	 */
	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.InputProcessor#touchDown(int, int, int, int)
	 * 
	 * LOCAL VARIABLES
	 * worldCoordinates (Vector3) Encapsulates a 3D vector
	 * 
	 * CONDITIONAL STATEMENTS
	 * 1- Determines if and where the Sprite can be dragged 
	 */
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		touching=true;
		Vector3 worldCoordinates = new Vector3(screenX, screenY, 0);
	    game.camera.unproject(worldCoordinates);
		screenY= 630-screenY;
		//A
		for(int i = 0; i < pics.length; i++)
	      {
		//1
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

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.InputProcessor#touchUp(int, int, int, int)
	 */
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) { 
		touching= false;
		validTouch = false;
		pics [selected].setPosition (startX [selected], startY);
		return false;
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.InputProcessor#touchDragged(int, int, int)
	 * 
	 * LOCAL VARIABLES
	 * g (GameLogic) Implements game logic in the given level
	 * worldCoordinates (Vector3) Encapsulates a 3D vector for the world's coordinates
	 * i (int) Loop variable
	 * 
	 * CONDITIONAL STATEMENTS
	 * 1- Adds Sprite if Sprite can be dragged and dropped
	 * 2- Adds collision detector if sprite can be dragged and is inside the coordinates of the map
	 * 
	 * LOOPS
	 * A- Loops for the amount of collisions there are 
	 */
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		
		GameLogic g= new GameLogic (level); 
		Vector3 worldCoordinates = new Vector3(screenX, screenY, 0);
	    game.camera.unproject(worldCoordinates);
	    //1
		if (validTouch)
		{
		pics [selected].setPosition (worldCoordinates.x, worldCoordinates.y);
		}
		//A
		for (int i= 0; i <5; i++)
		{
		//2
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

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.InputProcessor#mouseMoved(int, int)
	 */
	@Override
	public boolean mouseMoved(int screenX, int screenY) {

		return false;
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.InputProcessor#scrolled(int)
	 */
	@Override
	public boolean scrolled(int amount) {
		return false;
	}
	
	/** This method keeps the Sprite on the screen moving
	 * @param delta (float) The time between the last frame and the current frame
	 */
	/* CONDITIONAL STATEMENTS
	 * 1- Condition for if the Sprites are touching
	 * 
	 * LOOPS
	 * A- Loops for all the Sprites in the screens
	 */
	public void update (float delta)
	{
		//1
		if (!touching)
    	{
			//A
			for (int i =0 ; i < pics.length; i++)
			{
			startX [i] =(int) game.camera.position.x + 200*(i-1); 
			pics [i].setPosition (startX[i], startY); 
			}
    	}

	}
	
	/** This method draws the collision Sprites on the screen
	 * @param batch (SpriteBatch) The group of collision Sprites
	 */
	/*LOCAL VARIABLES
	 * i (int) Loop variable
	 * 
	 * LOOPS
	 * A- loops for all the Sprites
	 */
	public void draw(SpriteBatch batch) 
	{		
		 update(Gdx.graphics.getDeltaTime());
		 //A
		 for (int i =0; i < pics.length; i++)
		 pics[i].draw(batch);	
	}


}


