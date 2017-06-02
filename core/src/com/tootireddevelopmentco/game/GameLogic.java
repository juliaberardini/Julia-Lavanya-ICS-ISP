package com.tootireddevelopmentco.game;

public class GameLogic {
	
	String level; 
	private int [] [] correctChem, correctBio; 
	public GameLogic (String level)
	{
		this.level = level; 
		correctChem = new int [] [] {{1, 0}, {2, 1}, {3,1}, {4,0}, {5,1}};
		correctBio = new int [] [] {{1,1}, {2, 0}, {3, 2}, {4,1}, {5,0}}; 
	}
	
	
	public boolean doLogic (int iconCode, int obsCode)
	{
		int [] [] arr; 
		if (level =="chemistry")
		{
			arr = correctChem; 
		}
		else 
		{
			arr = correctBio;
		}
		
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
