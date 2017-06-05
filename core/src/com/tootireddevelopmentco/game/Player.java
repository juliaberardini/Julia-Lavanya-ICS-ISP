package com.tootireddevelopmentco.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * @author Lavanya Sinha, Julia Berardini
 * @version 4.0_04.06.2017
 * The Player class sets up the Player.
 * <p><b> Instance variables </b>
 * <p><b> name </b> (private String) The name of the player
 * <p><b> score </b> (private Score) The score of the player
 * <p><b> canJump </b> (private boolean)  Whether or not the player can jump
 * <p><b> sprite </b> (Sprite) The variable that represents the icons in the game
 * <p><b> world </b> (World) The variable that represents the World (physical entities, simulation etc). 
 * <p><b> body </b> (Body) The variable that represents a rigid icon.
 * <p><b> game </b> (final RabbitRun) The variable that represents the game
 * <p><b> playX </b> (static int) The x-coordinate of the icon's location
 */

public class Player implements InputProcessor {
private String name = " ";
private Score score = new Score (0, null); 
private boolean canJump = false; 
Sprite sprite; 
World world; 
Body body; 
final RabbitRun game; 
static int playX; 

/** The constructor sets up the Sprite on the screen
 * @param world (World) The variable that represents the rigid icon.
 * @param game (final RabbitRun) The variable that represents the screen.
 * @param startX (float) The variable that represents the starting x-coordinate of the icon.
 * @param StartY (float) The variable that represents the starting y-coordinate of the icon.
 * @param name (String) The variable that represents the name of the user.
 * @param score (Score) The variable that represents the score of the user. 
 */
public Player (World world, final RabbitRun game,  float startX, float StartY, String name, Score score)
{
	this.game= game; 
	this.world = world;
	sprite = new Sprite(new Texture ("bunnySprite.png"));
	BodyDef bodDef = new BodyDef (); 
	bodDef.type = BodyDef.BodyType.DynamicBody; 
	bodDef.fixedRotation = true; 
	bodDef.position.set ((startX + sprite.getWidth () /2)/ Constants.PIXELS_TO_METERS, (StartY + sprite.getHeight () /2) / Constants.PIXELS_TO_METERS);
	body = world.createBody(bodDef);
	PolygonShape shape = new PolygonShape (); 
	shape.setAsBox ((sprite.getWidth ()/2) / Constants.PIXELS_TO_METERS, (sprite.getHeight ()/2)/Constants.PIXELS_TO_METERS);

	FixtureDef def = new FixtureDef (); 
	def.shape = shape; 
	def.density = 1f; 
	body.createFixture(def); 
	shape.dispose();
}

/** This constructor establishes the game, name and score.
 * @param game (final RabbitRun) The variable that represents the game. 
 * @param name (String) The variable that represents the name.
 * @param score (Score) The variable that represents the score.
 */
public Player (final RabbitRun game, String name, Score score)
{
	this.game = game;
	this.name = name; 
	this.score = score; 
}

/** This is the getter method for the name
 * @return name (String) The user's name
 */
public String getName () 
{
	return name;
}

/** This is the setter method for the score
 * @param score (Score) The user's score
 * @return score (Score) The user's score
 */
public Score setScore (Score score)
{
	return score; 
}

/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
public String toString ()
{
	return name + "     " + score ;
}

/** This method determines if the user can jump or not
 * @param type (boolean) If the icon can jump or not
 */
public void changeJump (boolean type)
{
	canJump = type; 
}

/** This method keeps the screen updated
 * @param delta (float) The amount of time between the last frame and the current
 */
public void update (float delta) { 
	sprite.setPosition((body.getPosition().x * Constants.PIXELS_TO_METERS) - sprite.getWidth() / 2,
            ((body.getPosition().y *Constants.PIXELS_TO_METERS) - sprite.getHeight() / 2));
}

/* (non-Javadoc)
 * @see com.badlogic.gdx.InputProcessor#keyDown(int)
 * 
 * LOCAL VARIABLES
 * force (float) The icon in between jumps and lands
 *  
 * CONDITIONAL STATEMENTS
 * 1- Moves the icon right
 * 2- Moves the icon left
 * 3- Moves the icon up
 */
@Override
public boolean keyDown(int keycode) {
	 float force = (float) (body.getMass() * 10 / (1/60.0));
	 //1
	if(keycode == Input.Keys.RIGHT) 
        body.setLinearVelocity(10f, 0f);
	//2
    if(keycode == Input.Keys.LEFT)
    	body.setLinearVelocity(-10f, 0f);
    //3
    if (keycode == Input.Keys.UP && canJump && sprite.getY () <= 188)
    {
    	body.applyForce(new Vector2(0,force-15), body.getWorldCenter(), true );
    }
    	
  
	return false;
}

/* (non-Javadoc)
 * @see com.badlogic.gdx.InputProcessor#keyUp(int)
 */
@Override
public boolean keyUp(int keycode) {

    return false; 
}

/* (non-Javadoc)
 * @see com.badlogic.gdx.InputProcessor#keyTyped(char)
 */
@Override
public boolean keyTyped(char character) {
	return false;
}

/* (non-Javadoc)
 * @see com.badlogic.gdx.InputProcessor#touchDown(int, int, int, int)
 */
@Override
public boolean touchDown(int screenX, int screenY, int pointer, int button) {
	return false;
}

/* (non-Javadoc)
 * @see com.badlogic.gdx.InputProcessor#touchUp(int, int, int, int)
 */
@Override
public boolean touchUp(int screenX, int screenY, int pointer, int button) {
	return false;
}

/* (non-Javadoc)
 * @see com.badlogic.gdx.InputProcessor#touchDragged(int, int, int)
 */
@Override
public boolean touchDragged(int screenX, int screenY, int pointer) {
	return false;
}

/* (non-Javadoc)
 * @see com.badlogic.gdx.InputProcessor#mouseMoved(int, int)
 */
@Override
public boolean mouseMoved(int screenX, int screenY) {
	return false;
}

/* (non-Javadoc)
 * @see com.badlogic.gdx.InputProcessor#scrolled(int)
 */
@Override
public boolean scrolled(int amount) {
	return false;
}

/** This method draws the Sprites onto the screen
 * @param batch (SpriteBatch) The collection of Sprites
 */
public void draw(SpriteBatch batch) {
	 update(Gdx.graphics.getDeltaTime());
	 sprite.draw(batch);	
}

/** This method gets the x-coordinate of the Sprite 
 * @return sprite.getX (int) The x-coordinate of the Sprite 
 */
public float getX() {
	playX = (int) sprite.getX (); 
	return sprite.getX();
}

/**This method gets the y-coordinate of the Sprite 
 * @return sprite.getY (int) The y-coordinate of the Sprite 
 */
public float getY() {
	return sprite.getY (); 
}

/** This method returns the texture of the Sprite
 * @return sprite.getTexture (Sprite) The texture of the Sprite
 */
public Texture getTexture() {
	return sprite.getTexture (); 
}

}
