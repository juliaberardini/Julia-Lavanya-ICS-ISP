package com.tootireddevelopmentco.game;

import java.util.ArrayList;
import java.util.List;

public class PlayerCollection {

	private List <Player> scoreArr = new ArrayList <Player> (); 
	
	public List<Player> displayArr ()
	{
		return scoreArr; 
	}
	public void sortArr () 
	{
		//TO BE DONE 
	}
	public void addPlayer (Player player)
	{
		scoreArr.add (player); 
	}
	public void clearPlayers ()
	{
		scoreArr.removeAll(scoreArr);
	}
	
}
