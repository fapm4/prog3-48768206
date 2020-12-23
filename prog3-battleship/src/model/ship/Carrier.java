package model.ship;

import model.Orientation;


// TODO: Auto-generated Javadoc
/**
 * The Class Carrier.
 * @author Francisco Alejandro Pérez Meneses - 48768206H
 */

public class Carrier extends Ship{	
		
	/**
	 * Instantiates a new carrier.
	 *
	 * @param o the o
	 */
	public Carrier(Orientation o) {
		super(o, '®', "Carrier");
		
		 super.shape =  new int[][] {
	       {0, 0, 1, 0, 0,
	    	0, 0, 1, 0, 0,	
	    	0, 0, 1, 0, 0,	
	    	0, 0, 1, 0, 0,
	    	0, 0, 1, 0, 0},
		      
	       {0, 0, 0, 0, 0,
	        0, 0, 0, 0, 0,	
			1, 1, 1, 1, 1,	
			0, 0, 0, 0, 0,
			0, 0, 0, 0, 0},
		      
		   {0, 0, 1, 0, 0,
			0, 0, 1, 0, 0,	
			0, 0, 1, 0, 0,	
			0, 0, 1, 0, 0,
			0, 0, 1, 0, 0},
		      
		   {0, 0, 0, 0, 0,
			0, 0, 0, 0, 0,	
			1, 1, 1, 1, 1,	
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


	@Override
	public int getValue() {
		return 8;
	}
}
