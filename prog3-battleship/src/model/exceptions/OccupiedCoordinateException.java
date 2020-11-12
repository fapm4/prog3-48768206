package model.exceptions;

import model.Coordinate;

// TODO: Auto-generated Javadoc
/**
 * The Class OccupiedCoordinateException.
 * @author Francisco Alejandro Pérez Meneses - 48768206H
 */
public class OccupiedCoordinateException extends CoordinateException{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new occupied coordinate exception.
	 *
	 * @param c the c
	 */
	public OccupiedCoordinateException(Coordinate c) {
		super(c);
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		
		return "La coordenada ya está ocupada (OccupiedCoordinateException)";
	}
}
