package model.exceptions;

import model.Coordinate;

// TODO: Auto-generated Javadoc
/**
 * The Class CoordinateException.
 * @author Francisco Alejandro Pérez Meneses - 48768206H
 */

public abstract class CoordinateException extends BattleshipException{
	
	/** The c. */
	private Coordinate c;
	
	/**
	 * Instantiates a new coordinate exception.
	 *
	 * @param c the c
	 */
	public CoordinateException(Coordinate c) {
		super();
		this.c = c;
	}
	
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return super.getMessage();
	}
}
