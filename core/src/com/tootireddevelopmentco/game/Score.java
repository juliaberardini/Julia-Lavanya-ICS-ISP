package com.tootireddevelopmentco.game;

public class Score {

	private Integer scoreValue; 
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
	
	public void calculateScore (int timeTaken)
	{
		scoreValue= 1000- timeTaken; 
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
