package model.exceptions.io;

import model.exceptions.BattleshipException;

// TODO: Auto-generated Javadoc
/**
 * The Class BattleshipIOException.
 * @author Francisco Alejandro Pérez Meneses - 48768206H
 */
public class BattleshipIOException extends BattleshipException{
	
	/** The message. */
	private String message;
	
	/**
	 * Instantiates a new battleship IO exception.
	 *
	 * @param m the m
	 */
	public BattleshipIOException(String m) {
		super();
		this.message = m;
	}
	
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
}
