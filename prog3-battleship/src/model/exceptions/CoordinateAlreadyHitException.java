package model.exceptions;

import model.Coordinate;

// TODO: Auto-generated Javadoc
/**
 * The Class CoordinateAlreadyHitException.
 * @author Francisco Alejandro Pérez Meneses - 48768206H
 */
public class CoordinateAlreadyHitException extends BattleshipException{

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
		Coordinate copia = super.getCoord();
		
		return "La coordenada " + copia.toString() + " ya ha golpeada anteriormente (CoordinateAlreadyHitException)";
	}
}
