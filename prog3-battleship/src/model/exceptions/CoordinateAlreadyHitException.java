package model.exceptions;

import model.Coordinate;

public class CoordinateAlreadyHitException extends BattleshipException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CoordinateAlreadyHitException(Coordinate c) {
		super(c);
	}
	
	public String getMessage() {
		Coordinate copia = super.getCoord();
		
		return "La coordenada " + copia.toString() + " ya ha golpeada anteriormente";
	}
}
