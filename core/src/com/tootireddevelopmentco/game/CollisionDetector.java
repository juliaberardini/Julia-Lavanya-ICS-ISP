package com.tootireddevelopmentco.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
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

		BodyDef groundDef = new BodyDef ();
		groundDef.fixedRotation =true; 
		groundDef.type= BodyDef.BodyType.StaticBody; 
		groundDef.position.set(new Vector2(0, -9.8f));
		ground = world.createBody(groundDef); 
		Shape shape; 
		MapObject m= wall.get(0); 
		if (m instanceof RectangleMapObject )
		{
			shape = getRectProp ((RectangleMapObject) m); 
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
            collDef.position.set(new Vector2(0, -9.8f));
            obstacleArr[i] = world.createBody(collDef);
            MapObject m2 = obstacles.get(i);
            Shape shape2; 
            if (m2 instanceof RectangleMapObject )
    		{
    			shape2 = getRectProp ((RectangleMapObject) m); 
    		}
    		else 
    		{
    			throw (new ClassCastException("something went wrong")); 
    		}
            
            FixtureDef fix2 = new FixtureDef();
            fix2.density = 1;
            fix2.shape = shape2;
            fix2.restitution = 0;
            fix.isSensor = true;
            obstacleArr[i].createFixture(fix);
            shape2.dispose();
		}
	}
		
		private PolygonShape getRectProp (RectangleMapObject obj)
		{
			    Rectangle rect = (obj.getRectangle ());
			    PolygonShape poly = new PolygonShape();
		        Vector2 size = new Vector2((rect.x + rect.width * 0.5f) / 20f,
		                (rect.y + rect.height * 0.5f) / 20f);
		        poly.setAsBox(rect.width * 0.5f / 20f,
		                rect.height * 0.5f / 20f,
		                size,
		                0.0f);
		        ;
				return poly;
		}
		
		
	}