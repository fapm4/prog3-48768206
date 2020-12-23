package model.ship;

import model.Orientation;


// TODO: Auto-generated Javadoc
/**
 * The Class Destroyer.
 * @author Francisco Alejandro Pérez Meneses - 48768206H
 */

public class Destroyer extends Ship{
		
	/**
	 * Instantiates a new destroyer.
	 *
	 * @param o the o
	 */
	public Destroyer(Orientation o) {
		super(o, 'Ω', "Destroyer");
		
		super.shape = new int[][] {
	       {0, 0, 0, 0, 0,
	    	0, 0, 1, 0, 0,	
	    	0, 0, 1, 0, 0,	
	    	0, 0, 0, 0, 0,
	    	0, 0, 0, 0, 0},
		      
		   {0, 0, 0, 0, 0,
			0, 0, 0, 0, 0,	
			0, 1, 1, 0, 0,	
			0, 0, 0, 0, 0,
			0, 0, 0, 0, 0},
		      
		   {0, 0, 0, 0, 0,
			0, 0, 1, 0, 0,	
	    	0, 0, 1, 0, 0,	
	    	0, 0, 0, 0, 0,
	    	0, 0, 0, 0, 0},
		      
		   {0, 0, 0, 0, 0,
			0, 0, 0, 0, 0,	
			0, 1, 1, 0, 0,	
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
		return 3;
	}
}
