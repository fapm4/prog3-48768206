package model.io;

import model.Game;

// TODO: Auto-generated Javadoc
/**
 * The Class VisualiserConsole.
 * @author Francisco Alejandro Pérez Meneses - 48768206H
 */
public class VisualiserConsole implements IVisualiser{
	
	/** The game. */
	private Game game;
	
	/**
	 * Instantiates a new visualiser console.
	 *
	 * @param game the game
	 */
	public VisualiserConsole(Game game) {
		
		if(game == null) {
			throw new NullPointerException();
		}
		
		this.game = game;
	}
	
	
	/**
	 * Show.
	 */
	public void show() {
		System.out.println(game.toString());
	}
	
	
	/**
	 * Close.
	 */
	public void close() {
		
	}
}
