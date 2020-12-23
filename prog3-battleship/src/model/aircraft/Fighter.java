package model.aircraft;

import model.Orientation;


// TODO: Auto-generated Javadoc
/**
 * The Class Fighter.
 * @author Francisco Alejandro Pérez Meneses - 48768206H
 */

public class Fighter extends Aircraft{
	
	/**
	 * Instantiates a new fighter.
	 *
	 * @param o the o
	 */
	public Fighter(Orientation o) {
		super(o, '⇄', "Fighter");
		
		super.shape = new int[][] {
	       {0, 0, 0, 0, 0,
	    	0, 0, 1, 0, 0,	
	    	0, 1, 1, 1, 0,	
	    	0, 0, 1, 0, 0,
	    	0, 0, 1, 0, 0},
		      
		   {0, 0, 0, 0, 0,
			0, 0, 1, 0, 0,	
			1, 1, 1, 1, 0,	
			0, 0, 1, 0, 0,
			0, 0, 0, 0, 0},
		      
		   {0, 0, 1, 0, 0,
			0, 0, 1, 0, 0,	
			0, 1, 1, 1, 0,	
			0, 0, 1, 0, 0,
			0, 0, 0, 0, 0},
		      
		   {0, 0, 0, 0, 0,
			0, 0, 1, 0, 0,	
			0, 1, 1, 1, 1,	
			0, 0, 1, 0, 0,
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
		return 10;
	}		
}
