package com.tootireddevelopmentco.game;

public class Player {
private String name;
private Score score; 

public Player (String name, Score score)
{
	this.name= name;
	this.score= score; 
}

public String getName () 
{
	return name;
}

public Score getScore ()
{
	return score; 
}

public String toString (Player player)
{
	return name + "     " + score ;
}
}
