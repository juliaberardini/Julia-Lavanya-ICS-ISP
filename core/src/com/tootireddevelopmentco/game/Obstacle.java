package com.tootireddevelopmentco.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Obstacle {
	
	private Sprite obs1;
	private Sprite obs2;
	private Sprite obs3;
	private Sprite obs4;
	private Sprite obs5; 
	private static  Sprite [] fiveSprites;
	private static int [] shuffleOrder;
	private static boolean [] shouldDraw; 
	
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
	
	private void shuffleObstacles ()
	{
		Sprite temp;
		int temp2; 
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
	
	private void showObstacles ()
	{ 
		fiveSprites [0].setPosition(715f, 187f);
		fiveSprites [1].setPosition (1460f, 187f);
		fiveSprites [2].setPosition (2195f, 187f);
		fiveSprites [3].setPosition(2920f, 187f);
		fiveSprites [4].setPosition(3655f, 187f);
	}
	
	public void draw (SpriteBatch batch) 
	{
		 showObstacles ();
		 for (int i= 0; i < fiveSprites.length; i++)
		 { 
			 if (shouldDraw [i])
		 fiveSprites [i].draw(batch);	
		 }
	}
	
	public Sprite [] getSprite ()
	{
		return new Sprite [] {obs1, obs2, obs3}; 
	}
	
	public static float [] getPosition ()
	{
		float [] arr = new float [] {715, 1460, 2195, 2920, 3655}; 
		return arr; 
	}
	
	public static int getWidth (int num) 
	{
		return (int) fiveSprites [num].getWidth ();
	}
	public static int getHeight (int num)
	{
		return (int) fiveSprites [num].getHeight(); 
	}
	
	public static void setDraw (int num)
	{
		shouldDraw [num]= false;
	}
	
	public static int [] getOrder ()
	{
		return shuffleOrder; 
	}
}
