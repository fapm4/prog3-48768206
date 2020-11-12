package model.exceptions;

import model.Coordinate;

public abstract class CoordinateException extends BattleshipException{
	
	private Coordinate c;
	
	public CoordinateException(Coordinate c) {
		super();
		this.c = c;
	}
	
	public String getMessage() {
		return super.getMessage();
	}
}
