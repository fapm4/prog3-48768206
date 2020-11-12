package model.exceptions;

import model.Coordinate;

// TODO: Auto-generated Javadoc
/**
 * The Class NextToAnotherCraftException.
 * @author Francisco Alejandro Pérez Meneses - 48768206H
 */
public class NextToAnotherCraftException extends CoordinateException{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new next to another craft exception.
	 *
	 * @param c the c
	 */
	public NextToAnotherCraftException(Coordinate c) {
		super(c);
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		
		return "La coordenada está cerca de otra nave (NextToAnotherCraftException)";
	}
}
