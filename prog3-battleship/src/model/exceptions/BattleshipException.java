package model.exceptions;
import model.Coordinate;

// TODO: Auto-generated Javadoc
/**
 * The Class BattleshipException.
 * @author Francisco Alejandro Pérez Meneses - 48768206H
 */
public abstract class BattleshipException extends Exception{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The c. */
	private Coordinate c;
	
	/**
	 * Instantiates a new battleship exception.
	 *
	 * @param c the c
	 */
	public BattleshipException(Coordinate c) {
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
	
	/**
	 * Gets the coord.
	 *
	 * @return the coord
	 */
	public Coordinate getCoord() {
		return c;
	}
}
