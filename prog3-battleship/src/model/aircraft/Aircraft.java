package model.aircraft;

import model.Craft;
import model.Orientation;


// TODO: Auto-generated Javadoc
/**
 * The Class Aircraft.
 * @author Francisco Alejandro Pérez Meneses - 48768206H
 */

public abstract class Aircraft extends Craft{
	
	
	/**
	 * Instantiates a new aircraft.
	 *
	 * @param o the o
	 * @param s the s
	 * @param n the n
	 */
	public Aircraft(Orientation o, char s, String n) {
		super(o, s, n);
	}
}
