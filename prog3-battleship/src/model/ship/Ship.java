package model.ship;

import model.Craft;
import model.Orientation;


// TODO: Auto-generated Javadoc
/**
 * The Class Ship.
 * @author Francisco Alejandro Pérez Meneses - 48768206H
 */
public abstract class Ship extends Craft {
	
	/**
	 * Instantiates a new ship.
	 *
	 * @param o the o
	 * @param s the s
	 * @param n the n
	 */
	public Ship(Orientation o, char s, String n) {
		super(o, s, n);
	}
}
