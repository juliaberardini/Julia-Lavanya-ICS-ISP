package com.tootireddevelopmentco.game;

/**
 * @author Lavanya Sinha, Julia Berardini
 * @version 4.0_04.06.2017
 * The Score class sets up the elements  needed for the user's score.
 * <p><b> Instance variables </b>
 * <p><b> scoreValue </b> (private static Integer) The value of the score that the user has.
 * <p><b> level </b> (private String) The level that the user is on.
 */

public class Score {

	private static Integer scoreValue; 
	private String level; 
	
	
	/** The constructor identifies the score and level of the user
	 * @param scoreValue (int) The score of the user
	 * @param level (String) The level of the user
	 */
	public Score (int scoreValue, String level)
	{
		this.scoreValue= scoreValue;
		this.level = level; 
	}
	
	/** This constructor identifies the level that the user is on.
	 * @param level (String) The level that the user is on.
	 */
	public Score (String level)
	{
		this.level= level; 
	}
	/**This method returns the user's score
	 * @return scoreValue (int) The user's score
	 */
	public static int getScoreValue ()
	{
		return scoreValue;
	}
	
	/** This method calculates the amount of time that the user has taken to complete the level
	 * @param timeElapsed (int) The timer variable 
	 */
	public void calculateScore (int timeElapsed)
	{
		scoreValue= (1000- timeElapsed); 
	}
	
	/** This is the getter method for the level
	 * @return level (String) The level that the user is on.
	 */
	public String getLevel () 
	{
		return level; 
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override 
	public String toString ()
	{
		return scoreValue.toString (); 
	}

	/** This is the getter method for the user's name
	 * @return EnterUsername.getStr (String) The name that the user entered in the username box.
	 */
	public String getName() {
		return EnterUsername.getStr(); 
	}


}
