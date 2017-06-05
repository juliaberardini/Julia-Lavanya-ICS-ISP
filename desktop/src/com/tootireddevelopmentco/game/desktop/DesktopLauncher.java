package com.tootireddevelopmentco.game.desktop;

 import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
 import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
 import com.tootireddevelopmentco.game.RabbitRun;
 
 /**
  * @author Lavanya Sinha, Julia Berardini
  * @version 4.0_04.06.2017
  * The Level2Instruct class sets up the instructions for Level 2, Plant Passage.
  * <p><b> Instance variables </b>
  * <p><b> NAME </b> (public static final String) The name of the game.
  */
 
 public class DesktopLauncher 
 {
 	
 	public static final String NAME = "Rabbit Run";  
 	
 	/** The main method runs the game
 	 */
 	public static void main (String[] arg) {
 		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
 		cfg.title = RabbitRun.TITLE;
 		cfg.useGL30 = false; 
 		cfg.width = 945;
 		cfg.height = 630; 
		cfg.vSyncEnabled = true; 
		new LwjglApplication(new RabbitRun(), cfg);
	}
}