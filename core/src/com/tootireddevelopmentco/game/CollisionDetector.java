package com.tootireddevelopmentco.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * @author Lavanya Sinha, Julia Berardini
 * @version 4.0_04.06.2017
 * The CollisionDetector class allows us to assess if two icons have collided into each other. 
 * <p><b> Instance variables </b>
 * <p><b> mapLoader </b> (private TmxMapLoader) The variable that represents the synchronous loader for TMX maps created with the Tiled tool. 
 * <p><b> world </b> (World) The variable that represents the World (physical entities, simulation etc). 
 * <p><b> game </b> (final RabbitRun) The variable that represents the music, spriteBatch and camera within the RabbitRun game class.
 * <p><b> obstacleArr [] </b> (static Body) The array filled with obstacles.
 * <p><b> ground </b> (Body) The ground that the Sprites are on. 
 */

public class CollisionDetector extends ApplicationAdapter {

	private TmxMapLoader mapLoader; 
	World world;
	final RabbitRun game; 
	static Body[] obstacleArr; 
	Body ground; 
	
	
	/**This constructor sets up the background and icons of the level.
	 * @param world (World) The variable that represents the World (physical entities, simulation etc). 
	 * @param filename (String) The variable for the file that the map png is stored in.
	 * @param game (final RabbitRun) The variable that represents the music, spriteBatch and camera within the RabbitRun game class.
	 */
	/*
	 * LOCAL VARIABLES
	 * wall (MapObjects) The ground layer of the background
	 * obstacles (MapObject) The obstacle layer in the screen
	 * groundDef (BodyDef) Holds all the data needed to create a non-moving Body.
	 * shape (Shape) Used for collision detection.
	 * m (MapObject) Map object with basic attributes like name, opacity, color.
	 * fix (FixtureDef) Creates fixture in the ground.
	 * i (int) Loop variable.
	 * collDef (BodyDef) Holds all the data needed to create a non-moving Body.
	 * m2 (MapObject) Map object with basic attributes like name, opacity, color.
	 * shape2 (Shape) Used for collision detection.
	 * 
	 * CONDITIONAL STATEMENTS
	 * 1- Detects collisions using the TileMap
	 * 2- Sets up Coordinates of TileMap
	 * 
	 * LOOPS
	 * A- Loops for the amount of obstacles 
	 */
	public CollisionDetector (World world, String filename, final RabbitRun game)
	{
		this.game= game; 
		mapLoader= new TmxMapLoader (); 
		this.world = world; 
		MapObjects wall = mapLoader.load (filename).getLayers().get ("ground").getObjects (); 
		MapObjects obstacles = mapLoader.load(filename).getLayers ().get ("collisions").getObjects(); 
		obstacleArr= new Body[obstacles.getCount ()];
		
		BodyDef groundDef = new BodyDef ();
		groundDef.fixedRotation =true; 
		groundDef.type= BodyDef.BodyType.StaticBody; 
		groundDef.position.set(new Vector2(0, 0f));
		ground = world.createBody(groundDef); 
		Shape shape; 
		MapObject m= wall.get(0); 
		//1
		if (m instanceof PolylineMapObject)
		{
			shape = getChain ((PolylineMapObject) m); 
		}
		else 
		{
			throw (new ClassCastException("something went wrong")); 
		}
		 FixtureDef fix = new FixtureDef();
         fix.density = 1;
         fix.shape = shape;
         fix.restitution = 0;
         ground.createFixture(fix);
         shape.dispose();
	    
        //A
		for (int i = 0; i< obstacles.getCount (); i++ )
		{
            BodyDef collDef = new BodyDef();
            collDef.fixedRotation = true; 
            collDef.type = BodyDef.BodyType.StaticBody; 
            collDef.position.set(new Vector2(0, 0f));
            obstacleArr[i] = world.createBody(collDef); 
            Shape shape2;  
            MapObject m2 = obstacles.get(i);
            //2
            if (m2 instanceof RectangleMapObject )
    		{
    			shape2 = getRectProp ((RectangleMapObject) m2); 
    		}
    		else 
    		{
    			throw (new ClassCastException("something went wrong")); 
    		}
            FixtureDef fix2 = new FixtureDef();
            fix2.density = 1f;
            fix2.shape = shape2;
            fix2.restitution = 0;
            obstacleArr[i].createFixture(fix2);
            shape2.dispose();
		}
	}
	/** This method makes the border body for the boundaries of the map
	 * @param obj (RectangleMapObject) A rectangle shaped map object
	 * @return poly (PolygonShape) A polygon shaped map object
	 */
	/*LOCAL VARIABLES
	 * rect (Rectangle) A rectangle shaped map object.
	 * size (Vector2) Variable used to encapsulate a 2D vector.
	 */
		private PolygonShape getRectProp (RectangleMapObject obj)
		{
			    Rectangle rect = (obj.getRectangle ());
			    PolygonShape poly = new PolygonShape();
		        Vector2 size = new Vector2((rect.x + rect.width) / Constants.PIXELS_TO_METERS, 
		        		(rect.y + rect.height) / Constants.PIXELS_TO_METERS);
		        poly.setAsBox(rect.width / Constants.PIXELS_TO_METERS,
		                rect.height / Constants.PIXELS_TO_METERS,
		                size,0.0f);
				return poly;
		}
		
		 /** This method also makes the border body for the boundaries of the map
		 * @param line (PolyLineMapObject) A polyline shaped map object
		 * @return chain (ChainShape) A chain of complex polygon shaped map objects
		 */
		/*LOCAL VARIABLES
		 * vertices [] (float) An array of the vertices in a shape
		 * vertices2 [] (Vector2) An array of 2D vectors
		 * 
		 * LOOPS
		 * A- converts the vertices to pixels
		 */
		private ChainShape getChain (PolylineMapObject line)
		  {
			  ChainShape chain = new ChainShape();
			  float[] vertices = line.getPolyline().getTransformedVertices();
		      Vector2[] vertices2 = new Vector2[vertices.length / 2];
		      //A
		        for (int i = 0; i < vertices.length / 2; i++) 
		        {
		            vertices2[i] = new Vector2();
		            vertices2[i].x = vertices[i * 2] / Constants.PIXELS_TO_METERS;
		            vertices2[i].y = vertices[i * 2 + 1] / Constants.PIXELS_TO_METERS;
		        }
		       
		        chain.createChain(vertices2);
		        return chain; 
		    }
		  
		/** This method returns the ground of the Sprites.
		 * @return ground (Body) returns the ground that the Sprites are on. 
		 */
		public Body getGround ()
		{
			return ground; 
		}
		
		/* (non-Javadoc)
		 * @see com.badlogic.gdx.ApplicationAdapter#dispose()
		 */
		@Override 
		public void dispose ()
		{
			world.dispose();
		}
		
		
	}