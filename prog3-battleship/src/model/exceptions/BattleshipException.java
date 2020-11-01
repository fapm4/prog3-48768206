package model.exceptions;
import model.Coordinate;

public abstract class BattleshipException extends Exception{
	
	private static final long serialVersionUID = 1L;
	private Coordinate c;
	
	public BattleshipException(Coordinate c) {
		super();
		this.c = c;
	}
	
	public String getMessage() {
		return super.getMessage();
	}
	
	public Coordinate getCoord() {
		return c;
	}
}
