package model.io;

import model.Game;

public class VisualiserConsole implements IVisualiser{
	
	private Game game;
	
	public VisualiserConsole(Game game) {
		
		if(game == null) {
			throw new NullPointerException();
		}
		
		this.game = game;
	}
	
	
	public void show() {
		System.out.println(game.toString());
	}
	
	
	public void close() {
		
	}
}
