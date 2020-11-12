package model.exceptions;

import model.Coordinate;

// TODO: Auto-generated Javadoc
/**
 * The Class InvalidCoordinateException.
 * @author Francisco Alejandro Pérez Meneses - 48768206H
 */
public class InvalidCoordinateException extends CoordinateException{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new invalid coordinate exception.
	 *
	 * @param c the c
	 */
	public InvalidCoordinateException(Coordinate c) {
		super(c);
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		
		return "La coordenada está fuera del tablero (InvalidCoordinateException)";
	}
}
