package com.tootireddevelopmentco.game;

public class GameLogic {
	
	/**
	 * @author Lavanya Sinha, Julia Berardini
	 * @version 4.0_04.06.2017
	 * The GameLogic class identifies the correct answers in the drag and drop elements of the chemistry and bio level.
	 * <p><b> Instance variables </b>
	 * <p><b> level </b> (private String) The name of the level that the user is on.
	 * <p><b> correctChem [][] </b> (2D int array) The correct matching of the drag and drop element to the obstacle in the chemistry level.
	 * <p><b> correctBio [][] </b> (2D int array) The correct matching of the drag and drop element to the obstacle in the biology level.
	 */
	
	String level; 
	private int [] [] correctChem, correctBio; 
	/**
	 * @param level (String) The level that the user is on.
	 */
	public GameLogic (String level)
	{
		this.level = level; 
		correctChem = new int [] [] {{1, 0}, {2, 1}, {3,1}, {4,0}, {5,1}};
		correctBio = new int [] [] {{1,1}, {2, 0}, {3, 2}, {4,1}, {5,0}}; 
	}
	
	
	/**
	 * @param iconCode (int) The variable representing the icon Sprite
	 * @param obsCode (int) The variable representing the obstacle Sprite
	 * @return (boolean) The variable that represents whether there are more obstacles or not
	 */
	/* LOCAL VARIABLES
	 * arr [][] (int array) Array that assesses if there are more obstacles or not
	 * 
	 * CONDITIONAL STATEMENTS
	 * 1- Chooses answers depending on level
	 * 
	 * LOOPS
	 * A- Loops for all the icons
	 */
	public boolean doLogic (int iconCode, int obsCode)
	{
		int [] [] arr;
		//1
		if (level =="chemistry")
		{
			arr = correctChem; 
		}
		else 
		{
			arr = correctBio;
		}
		
		//A
		for (int i =0 ; i < 5; i++)
		{
			if (iconCode == arr [i] [1] &&  obsCode == arr [i] [0])
				{
					return true; 
				}

		}
		return false;
	}

}
