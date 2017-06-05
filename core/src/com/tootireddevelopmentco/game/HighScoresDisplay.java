package com.tootireddevelopmentco.game;

import com.badlogic.gdx.Screen;
import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.print.*;
import javax.print.*;
import javax.print.attribute.*;
import javax.print.attribute.standard.Copies;
import javax.print.event.*;

//this is gonna be changed so comment it later bud
/**
 * @author Lavanya Sinha, Julia Berardini
 * @version 4.0_04.06.2017
 * The HighScoresDisplay class displays the high scores for each level. 
 * <p><b> Instance variables </b>
 * <p><b> scores </b> (private stage) The variable that holds a 2D scene graph containing hierarchies of actors.
 * <p><b> sc </b> (private Skin) The variable that stores resources such as textures, fonts, colors, etc.
 * <p><b> SCORES_SIZE </b> (private Table) The variable that auto-positions buttons into an organized table.
 * <p><b> reader </b> (private TweenManager) The variable that manages all the static animations.
 * <p><b> header </b> (public final RabbitRun) The variable that represents the game.
 */

public class HighScoresDisplay implements Screen {

	private ArrayList<Score> scores;
	private ScoreComparator sc;
	static final int SCORES_SIZE = 10;
	private BufferedReader reader;
	static final String header = "RabbitRun High Scores";
	  
	public HighScoresDisplay (final RabbitRun game)
	{
		createFile();
	    scores = new ArrayList<Score>();
	    sc = new ScoreComparator();
	}

	private void createFile(){
	    try{
	      File f = new File("./highscores.txt");
	      if(!f.exists()){
	        f.createNewFile();
	        try{
	          PrintWriter writer = new PrintWriter(new FileWriter("./highscores.txt"));
	          writer.println(header);
	          writer.close();
	        }catch(Exception e){
	        }
	      }
	    }catch(Exception e){}
	  }
	
	 public void add(Score score){
		    scores.add(score);
		    Collections.sort(scores, sc);
		  }
	 
	  public Score get(int x){
		    return scores.get(x);
		  }
	  
	  public int length(){
		    return scores.size();
		  }
	  
	  public void clear(){
		    createFile();
		    try{
		      PrintWriter writer = new PrintWriter(new FileWriter("./highscores.txt"));
		      writer.println(header);
		      writer.close();
		      
		      scores = new ArrayList<Score>();
		    }catch(Exception e){
		    }
		  }
	  
	  public void writeToFile(){
		    createFile();
		    try{
		      PrintWriter output = new PrintWriter(new FileWriter("./highscores.txt"));
		      output.println(header);
		      for(int i=0; i<scores.size(); i++){
		        if(i > SCORES_SIZE - 1){
		          break;
		        }
		        output.print(scores.get(i).getName() + ",");
		        output.println(scores.get(i).getScoreValue());
		      }
		      output.close();
		    }catch(Exception e){
		    }
		  }
	  
	  public void loadFromFile(){
		    createFile();
		    scores = new ArrayList<Score>();
		    try{
		      BufferedReader reader = new BufferedReader(new FileReader("./highscores.txt"));
		      if(reader.readLine().equals(header)){
		        String line;
		        while ((line = reader.readLine()) != null) {
		            scores.add(new Score(Integer.parseInt(line.split(",")[1]), line.split(",")[0]));
		        }
		      }
		    }catch(Exception e){
		    }
		  
		  }
	  
	  public void printHighscores(){
		    
		  createFile();
		    try{
		      PrintWriter writer = new PrintWriter(new FileWriter("./highscores.txt"));
		      writer.println(header);
		      writer.println(Score.getScoreValue());
		      
		      scores = new ArrayList<Score>();
		    }catch(Exception e){
		    }
		  }
		  
		  class PrintJobWatcher {
		    
		    boolean done = false;
		  
		    PrintJobWatcher(DocPrintJob job) {
		      job.addPrintJobListener(new PrintJobAdapter() {
		        public void printJobCanceled(PrintJobEvent pje) {
		          allDone();
		        }
		        public void printJobCompleted(PrintJobEvent pje) {
		          allDone();
		        }
		        public void printJobFailed(PrintJobEvent pje) {
		          allDone();
		        }
		        public void printJobNoMoreEvents(PrintJobEvent pje) {
		          allDone();
		        }
		        void allDone() {
		          synchronized (PrintJobWatcher.this) {
		            done = true;
		            PrintJobWatcher.this.notify();
		          }
		        }
		      });
		    }
		    public synchronized void waitForDone() {
		      try {
		        while (!done) {
		          wait();
		        }
		      } catch (InterruptedException e) {
		      }
		    }
		  }
		   
		  class ScoreComparator implements Comparator<Score>
		  {
		        public int compare(Score s1, Score s2)
		        {
		           //return -(Integer.compare(s1.getScoreValue()), s2.getScoreValue())); 
		        	if (s1.getScoreValue() > s2.getScoreValue())
		        	{
		        		return s1.getScoreValue();
		        	}
		        	else
		        	{
		        		return s2.getScoreValue(); 
		        	}
		           
		        }
		        
		  }

	  
	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
