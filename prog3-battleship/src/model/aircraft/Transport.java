package model.aircraft;

import model.Orientation;


// TODO: Auto-generated Javadoc
/**
 * The Class Transport.
 * @author Francisco Alejandro Pérez Meneses - 48768206H
 */
public class Transport extends Aircraft{
		
	/**
	 * Instantiates a new transport.
	 *
	 * @param o the o
	 */
	public Transport(Orientation o) {
		super(o, '⇋', "Transport");
		
		super.shape = new int[][] {
	       {0, 0, 1, 0, 0,	//2
	    	0, 0, 1, 0, 0,	//7
	    	0, 1, 1, 1, 0, 	//11 12 13
	    	1, 0, 1, 0, 1,	//15 17 19
	    	0, 0, 1, 0, 0},	//22
		      
		   {0, 1, 0, 0, 0,
			0, 0, 1, 0, 0,	
			1, 1, 1, 1, 1,	
			0, 0, 1, 0, 0,
			0, 1, 0, 0, 0},
		      
		   {0, 0, 1, 0, 0,
			1, 0, 1, 0, 1,	
			0, 1, 1, 1, 0,	
			0, 0, 1, 0, 0,
			0, 0, 1, 0, 0},
		      
		   {0, 0, 0, 1, 0,
			0, 0, 1, 0, 0,	
			1, 1, 1, 1, 1,	
			0, 0, 1, 0, 0,
			0, 0, 0, 1, 0}};
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
