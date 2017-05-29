package com.tootireddevelopmentco.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Obstacle {
	
	Sprite obs1;
	Sprite obs2;
	Sprite obs3;
	Sprite obs4;
	Sprite obs5; 
	private Sprite [] fiveSprites= new Sprite [5];
	
	public Obstacle (String pic1, String pic2, String pic3)
	{
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
		shuffleObstacles (); 
	}
	
	private void shuffleObstacles ()
	{
		Sprite temp;
		for (int i =0 ; i < fiveSprites.length ; i++)
		{
			int rand = (int) (Math.random ()* 5); 
			temp= fiveSprites [i];
			fiveSprites [i] = fiveSprites [rand];
			fiveSprites [rand]= temp; 
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
	
	public void draw (SpriteBatch batch) {
		 showObstacles ();
		 for (int i= 0; i < fiveSprites.length; i++)
		 fiveSprites [i].draw(batch);	
	}
	
}
