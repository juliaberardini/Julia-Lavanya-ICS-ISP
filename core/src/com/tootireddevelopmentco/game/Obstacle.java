package com.tootireddevelopmentco.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * @author Lavanya Sinha, Julia Berardini
 * @version 4.0_04.06.2017
 * The Level1Instruct class sets up the instructions for Level 1, Garden Jump.
 * <p><b> Instance variables </b>
 * <p><b> obs1 </b> (private Sprite) The Sprite for the first obstacle
 * <p><b> obs2 </b> (private Sprite) The Sprite for the second obstacle
 * <p><b> obs3 </b> (private Sprite) The Sprite for the third obstacle
 * <p><b> obs4 </b> (private Sprite) The Sprite for the fourth obstacle
 * <p><b> obs5 </b> (private Sprite) The Sprite for the fifth obstacle
 * <p><b> fiveSprites [] </b> (private static Sprite array) The array that holds the obstacles
 * <p><b> shuffleOrder [] </b> (private static int array) The array that randomizes the display of the obstacles. 
 * <p><b> shouldDraw [] </b> (private static boolean array) The array that determines if the obstacles need to be displayed or not.
 */

public class Obstacle {
	
	private Sprite obs1, obs2, obs3, obs4, obs5; 
	private static  Sprite [] fiveSprites;
	private static int [] shuffleOrder;
	private static boolean [] shouldDraw; 
	
	/** The constructor sets up the screen for the instructions for Level 1.
	 * @param pic1 (String) The first Sprite picture (drag and drop)
	 * @param pic2 (String) The second Sprite picture (drag and drop)
	 * @param pic3 (String) The third Sprite picture (drag and drop)
	 */
	public Obstacle (String pic1, String pic2, String pic3)
	{
		fiveSprites= new Sprite [5];
		shouldDraw = new boolean []{true, true, true, true, true};
		this.obs1= new Sprite (new Texture (pic1));
		this.obs2= new Sprite (new Texture (pic2));
		this.obs3= new Sprite (new Texture (pic3));
		this.obs4= new Sprite (new Texture (pic1));
		this.obs5= new Sprite (new Texture (pic2));
		fiveSprites [0]= obs1;
		fiveSprites [1]= obs2;
		fiveSprites [2]= obs3;
		fiveSprites [3]= obs4;
		fiveSprites [4]= obs5;
		shuffleOrder = new int [] {1, 2, 3, 4, 5}; 
		shuffleObstacles (); 
	}
	
	/** The shuffleObstacles method randomizes the order of the obstacles displayed.
	 */
	/* LOCAL VARIABLES
	 * temp (Sprite) temporary variable.
	 * temp2 (int) Integer temporary variable. 
	 * i (int) Loop variable
	 * 
	 * LOOPS
	 * A- shuffles the Sprite order
	 */
	private void shuffleObstacles ()
	{
		Sprite temp;
		int temp2; 
		//A
		for (int i =0 ; i < fiveSprites.length ; i++)
		{
			int rand = (int) (Math.random ()* 5); 
			temp= fiveSprites [i];
			temp2= shuffleOrder [i]; 
			fiveSprites [i] = fiveSprites [rand];
			shuffleOrder [i]= shuffleOrder [rand];
			fiveSprites [rand]= temp;
			shuffleOrder [rand]= temp2; 
		}
	}
	
	/** The showObstacles method sets the positions of the shuffled obstacles on the screen.
	 */
	private void showObstacles ()
	{ 
		fiveSprites [0].setPosition(715f, 187f);
		fiveSprites [1].setPosition (1460f, 187f);
		fiveSprites [2].setPosition (2195f, 187f);
		fiveSprites [3].setPosition(2920f, 187f);
		fiveSprites [4].setPosition(3655f, 187f);
	}
	
	/** The draw method draws the Sprites onto the screen
	 * @param batch (SpriteBatch) This is the group of Sprites needed to be displayed
	 */
	/* CONDITIONAL STATEMENTS
	 * 1- Draws the Sprites if Sprites can be drawn 
	 * 
	 * LOOPS
	 * A- Loops for all the Sprites in the level
	 */
	public void draw (SpriteBatch batch) 
	{
		 showObstacles ();
		 //A
		 for (int i= 0; i < fiveSprites.length; i++)
		 { 
			 //1
			 if (shouldDraw [i])
		 fiveSprites [i].draw(batch);	
		 }
	}
	
	/** This method returns the Sprites
	 * @return Sprite [] The array of the obstacles
	 */
	public Sprite [] getSprite ()
	{
		return new Sprite [] {obs1, obs2, obs3}; 
	}
	
	/** This method gets the position of the Sprite
	 * @return arr (float array) 
	 */
	public static float [] getPosition ()
	{
		float [] arr = new float [] {715, 1460, 2195, 2920, 3655}; 
		return arr; 
	}
	
	/** This method gets the width of the Sprites
	 * @param num (int) The width of the Sprite
	 * @return fiveSprites (num) The width in the array of the Sprite
	 */
	public static int getWidth (int num) 
	{
		return (int) fiveSprites [num].getWidth ();
	}
	
	/** This method gets the height of the Sprites
	 * @param num (int) The height of the Sprite
	 * @return fiveSprites (num) The height in the array of the Sprite 
	 */
	public static int getHeight (int num)
	{
		return (int) fiveSprites [num].getHeight(); 
	}
	
	/** This method declares that the Sprites cannot be drawn
	 * @param num (int) The dimensions of the array
	 */
	public static void setDraw (int num)
	{
		shouldDraw [num]= false;
	}
	
	/** This method gets the order of display of the Obstacles
	 * @return shuffleOrder (int array) The array with the order of the obstacle Sprite displays. 
	 */
	public static int [] getOrder ()
	{
		return shuffleOrder; 
	}
}
