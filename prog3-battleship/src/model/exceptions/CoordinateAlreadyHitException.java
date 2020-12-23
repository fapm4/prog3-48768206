package model.exceptions;

import model.Coordinate;


// TODO: Auto-generated Javadoc
/**
 * The Class CoordinateAlreadyHitException.
 * @author Francisco Alejandro Pérez Meneses - 48768206H
 */

public class CoordinateAlreadyHitException extends CoordinateException{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	
	/**
	 * Instantiates a new coordinate already hit exception.
	 *
	 * @param c the c
	 */
	public CoordinateAlreadyHitException(Coordinate c) {
		super(c);
	}
	
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		
		return "La coordenada ya ha golpeada anteriormente (CoordinateAlreadyHitException)";
	}
}
