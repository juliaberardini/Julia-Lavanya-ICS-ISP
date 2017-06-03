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
	public int getScoreValue ()
	{
		return scoreValue;
	}
	
	public static void calculateScore (float timeElapsed)
	{
		scoreValue= (int) (1000- timeElapsed); 
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
}
