package model.ship;

import model.Orientation;

// TODO: Auto-generated Javadoc
/**
 * The Class Battleship.
 * @author Francisco Alejandro Pérez Meneses - 48768206H
 */
public class Battleship extends Ship{
	
	/** The shape. */
	private int[][] shape = new int[][] {
       {0, 0, 0, 0, 0,
    	0, 0, 1, 0, 0,	
    	0, 0, 1, 0, 0,	
    	0, 0, 1, 0, 0,
    	0, 0, 1, 0, 0},
	      
       {0, 0, 0, 0, 0,
		0, 0, 0, 0, 0,	
		0, 1, 1, 1, 1,	
		0, 0, 0, 0, 0,
		0, 0, 0, 0, 0},
	      
       {0, 0, 0, 0, 0,
    	0, 0, 1, 0, 0,	
    	0, 0, 1, 0, 0,	
    	0, 0, 1, 0, 0,
    	0, 0, 1, 0, 0},
	      
	   {0, 0, 0, 0, 0,
		0, 0, 0, 0, 0,	
		0, 1, 1, 1, 1,	
		0, 0, 0, 0, 0,
		0, 0, 0, 0, 0}};
		
		
	/**
	 * Instantiates a new battleship.
	 *
	 * @param o the o
	 */
	public Battleship(Orientation o) {
		super(o, 'O', "Battleship");
	}
	
	
	/**
	 * Gets the shape.
	 *
	 * @return the shape
	 */
	public int[][] getShape(){
		return this.shape;
	}
}
