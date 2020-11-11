package model.ship;

import model.Orientation;

// TODO: Auto-generated Javadoc
/**
 * The Class Battleship.
 * @author Francisco Alejandro Pérez Meneses - 48768206H
 */
public class Battleship extends Ship{		
		
	/**
	 * Instantiates a new battleship.
	 *
	 * @param o the o
	 */
	public Battleship(Orientation o) {
		super(o, 'O', "Battleship");
		
		/** The shape. */
		super.shape = new int[][] {
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
