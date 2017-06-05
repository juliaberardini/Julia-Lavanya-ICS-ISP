package com.tootireddevelopmentco.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

import aurelienribon.tweenengine.TweenAccessor;

/**
 * @author Lavanya Sinha, Julia Berardini
 * @version 4.0_04.06.2017
 * The Splash class displays the splash screen.
 * <p><b> Instance variables </b>
 * <p><b> ALPHA </b> (public static final int) Represents the colours in the Sprites

 */

public class SpriteAccessor implements TweenAccessor<Sprite> {

	public static final int ALPHA = 0;
	
	/* (non-Javadoc)
	 * @see aurelienribon.tweenengine.TweenAccessor#getValues(java.lang.Object, int, float[])
	 */
	@Override
	public int getValues(Sprite target, int tweenType, float[] returnValues) {
	switch (tweenType){
	case ALPHA: 
		returnValues [0] = target.getColor ().a;
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
	public void setValues(Sprite target, int tweenType, float[] newValues) {
		switch (tweenType){
		case ALPHA: 
			target.setColor(target.getColor().r, target.getColor().g, target.getColor ().b, newValues [0]);
			break; 
		default: 
			assert false; 
		}
	}

}
