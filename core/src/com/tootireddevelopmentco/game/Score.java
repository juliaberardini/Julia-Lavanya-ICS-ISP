package com.tootireddevelopmentco.game;

public class Score {

	private static Integer scoreValue; 
	private String level; 
	
	
	public Score (int scoreValue, String level)
	{
		this.scoreValue= scoreValue;
		this.level = level; 
	}
	
	public Score (String level)
	{
		this.level= level; 
	}
	public static int getScoreValue ()
	{
		return scoreValue;
	}
	
	public void calculateScore (int timeElapsed)
	{
		scoreValue= (1000- timeElapsed); 
	}
	
	public String getLevel () 
	{
		return level; 
	}
	@Override 
	public String toString ()
	{
		return scoreValue.toString (); 
	}

	public String getName() {
		return EnterUsername.getStr(); 
	}


}
