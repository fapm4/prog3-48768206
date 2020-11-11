package model.aircraft;

import model.Orientation;

// TODO: Auto-generated Javadoc
/**
 * The Class Bomber.
 * @author Francisco Alejandro Pérez Meneses - 48768206H
 */
public class Bomber extends Aircraft{

		
		
	/**
	 * Instantiates a new bomber.
	 *
	 * @param o the o
	 */
	public Bomber(Orientation o) {
		super(o, '⇶', "Bomber");
		
		/** The shape. */
		super.shape = new int[][] {
	       {0, 0, 0, 0, 0,
	    	0, 0, 1, 0, 0,	
	    	1, 1, 1, 1, 1,	
	    	1, 0, 1, 0, 1,
	    	0, 0, 1, 0, 0},
		      
		   {0, 1, 1, 0, 0,
			0, 0, 1, 0, 0,	
			1, 1, 1, 1, 0,	
			0, 0, 1, 0, 0,
			0, 1, 1, 0, 0},
		      
		   {0, 0, 1, 0, 0,
			1, 0, 1, 0, 1,
			1, 1, 1, 1, 1,	
			0, 0, 1, 0, 0,
			0, 0, 0, 0, 0},
		      
		   {0, 0, 1, 1, 0,
			0, 0, 1, 0, 0,	
			0, 1, 1, 1, 1,	
			0, 0, 1, 0, 0,
			0, 0, 1, 1, 0}};
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
