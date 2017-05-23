package com.tootireddevelopmentco.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Player implements InputProcessor {
private String name;
private Score score; 
private boolean canJump = false; 
Sprite sprite; 
World world; 
Body body; 
final RabbitRun game; 


public Player (World world, final RabbitRun game,  float startX, float StartY, String name, Score score)
{
	this.game= game; 
	this.name= name;
	this.score= score; 
	this.world = world;
	sprite = new Sprite(new Texture ("bunnySprite.png"));
	BodyDef bodDef = new BodyDef (); 
	bodDef.type = BodyDef.BodyType.DynamicBody; 
	bodDef.fixedRotation = true; 
	bodDef.position.set ((startX + sprite.getWidth () /2)/ Constants.PIXELS_TO_METERS, (StartY + sprite.getHeight () /2) / Constants.PIXELS_TO_METERS);

	PolygonShape shape = new PolygonShape (); 
	shape.setAsBox ((sprite.getWidth ()/2) / Constants.PIXELS_TO_METERS, (sprite.getHeight ()/2)/Constants.PIXELS_TO_METERS);

	FixtureDef def = new FixtureDef (); 
	def.shape = shape; 
	def.density = 0.3f; 
	body.createFixture(def); 
	shape.dispose();
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

public void changeJump ()
{
	canJump = !canJump; 
}

public void update (float delta) { 
	sprite.setPosition((body.getPosition().x * Constants.PIXELS_TO_METERS) - sprite.getWidth() / 2,
            ((body.getPosition().y * Constants.PIXELS_TO_METERS) - sprite.getHeight() / 2));
}

@Override
public boolean keyDown(int keycode) {
	return false;
}

@Override
public boolean keyUp(int keycode) {
	if(keycode == Input.Keys.RIGHT) 
        body.setLinearVelocity(1f, 0f);
    if(keycode == Input.Keys.LEFT)
        body.setLinearVelocity(-1f,0f);
    if (keycode == Input.Keys.UP && canJump)
    	body.applyForceToCenter (0f, 30f, true);
    
    return false; 
}

@Override
public boolean keyTyped(char character) {
	return false;
}

@Override
public boolean touchDown(int screenX, int screenY, int pointer, int button) {
	return false;
}

@Override
public boolean touchUp(int screenX, int screenY, int pointer, int button) {
	return false;
}

@Override
public boolean touchDragged(int screenX, int screenY, int pointer) {
	return false;
}

@Override
public boolean mouseMoved(int screenX, int screenY) {
	return false;
}

@Override
public boolean scrolled(int amount) {
	return false;
}
}
