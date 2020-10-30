package model.exceptions;

import model.Coordinate;

public class NextToAnotherCraftException extends BattleshipException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NextToAnotherCraftException(Coordinate c) {
		super(c);
	}
	
	public String getMessage() {
		Coordinate copia = super.getCoord();
		
		return "La coordenada " + copia.toString() + " está cerca de otra nave (NextToAnotherCraftException)";
	}
}
