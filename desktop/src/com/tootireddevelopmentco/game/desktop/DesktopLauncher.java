package com.tootireddevelopmentco.game.desktop;

 
 import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
 import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
 import com.tootireddevelopmentco.game.RabbitRun;
 public class DesktopLauncher {
 	
 	public static final String NAME = "Rabbit Run";  
 	
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