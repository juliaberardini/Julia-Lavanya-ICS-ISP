package com.tootireddevelopmentco.game;

import com.badlogic.gdx.scenes.scene2d.Actor;

import aurelienribon.tweenengine.TweenAccessor;

/**
 * @author Lavanya Sinha, Julia Berardini
 * @version 4.0_04.06.2017
 * The ActorAccessor class uses the Universal Tween Engine to implement the TweenAccessor, so that Sprites (icons) can be implemented onto the level screen,
 * allowing us to move an image or a sprite across the screen.
 * <p><b> Instance variables </b>
 * <p><b> Y </b> (private static final) The variable used to draw all graphics to the screen.
 * <p><b> RGB </b> (private static final) The variable used to determine the RGB colour.
 * <p><b> ALPHA </b> (private static final) ****TO BE FINISHED****
 */


public class ActorAccessor implements TweenAccessor<Actor> {

	public static final int Y = 0; 
	public static final int RGB = 1;
	public static final int ALPHA = 2;

	/* (non-Javadoc)
	 * @see aurelienribon.tweenengine.TweenAccessor#getValues(java.lang.Object, int, float[])
	 */
	@Override
	public int getValues(Actor arg0, int arg1, float[] arg2) {
		switch (arg1) {
		case Y:
			arg2[0]  = arg0.getY();
			return 1;
		case RGB:
			arg2[0] = arg0.getColor().r;
			arg2[1] = arg0.getColor().g;
			arg2[2] = arg0.getColor().b;
			return 3;
		case ALPHA:
			arg2[0] = arg0.getColor().a;
			return 1;
		default:
			assert false;
			return -1;
		}
	}

	/* (non-Javadoc)
	 * @see aurelienribon.tweenengine.TweenAccessor#setValues(java.lang.Object, int, float[])
	 */
	@Override
	public void setValues(Actor arg0, int arg1, float[] arg2) {
		switch (arg1) {
		case Y:
			arg0.setY(arg2[0]);
			break;
		case RGB:
			arg0.setColor(arg2[0], arg2[1], arg2[2], arg0.getColor().a);
			break;
		case ALPHA:
			arg0.setColor(arg0.getColor().r, arg0.getColor().g, arg0.getColor().b, arg2[0]);
			break;
		default:
			assert false;
			break;
		}
	}

}
