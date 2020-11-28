package model.exceptions.io;

import model.exceptions.BattleshipException;

public class BattleshipIOException extends BattleshipException{
	private String message;
	
	public BattleshipIOException(String m) {
		super();
		this.message = m;
	}
	
	public String getMessage() {
		return message;
	}
}
