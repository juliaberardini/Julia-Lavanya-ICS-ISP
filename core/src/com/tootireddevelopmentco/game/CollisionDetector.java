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

public class CollisionDetector extends ApplicationAdapter {

	private TmxMapLoader mapLoader; 
	World world;
	final RabbitRun game; 
	Body[] obstacleArr; 
	Body ground; 
	
	
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
	    
		for (int i = 0; i< obstacles.getCount (); i++ )
		{
            BodyDef collDef = new BodyDef();
            collDef.fixedRotation = true; 
            collDef.type = BodyDef.BodyType.StaticBody;
            collDef.position.set(new Vector2(0, 0f));
            obstacleArr[i] = world.createBody(collDef); 
            Shape shape2;  
            MapObject m2 = obstacles.get(i);
            if (m2 instanceof RectangleMapObject )
    		{
    			shape2 = getRectProp ((RectangleMapObject) m2); 
    			System.out.println ("done");
    		}
    		else 
    		{
    			throw (new ClassCastException("something went wrong")); 
    		}
            FixtureDef fix2 = new FixtureDef();
            fix2.density = 1;
            fix2.shape = shape2;
            fix2.restitution = 0;
            obstacleArr[i].createFixture(fix2);
            shape2.dispose();
		}
	}
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
		  private ChainShape getChain (PolylineMapObject line)
		  {
			  ChainShape chain = new ChainShape();
			  float[] vertices = line.getPolyline().getTransformedVertices();
		      Vector2[] vertices2 = new Vector2[vertices.length / 2];
		        for (int i = 0; i < vertices.length / 2; i++) 
		        {
		            vertices2[i] = new Vector2();
		            vertices2[i].x = vertices[i * 2] / Constants.PIXELS_TO_METERS;
		            vertices2[i].y = vertices[i * 2 + 1] / Constants.PIXELS_TO_METERS;
		        }
		       
		        chain.createChain(vertices2);
		        return chain; 
		    }
		  
		public Body getGround ()
		{
			return ground; 
		}
		@Override 
		public void dispose ()
		{
			world.dispose();
		}
		
		
	}