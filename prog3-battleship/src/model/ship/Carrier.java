package model.ship;

import model.Orientation;

// TODO: Auto-generated Javadoc
/**
 * The Class Carrier.
 * @author Francisco Alejandro Pérez Meneses - 48768206H
 */
public class Carrier extends Ship{
	
	/** The shape. */
	private int[][] shape = new int[][] {
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
		
		
	/**
	 * Instantiates a new carrier.
	 *
	 * @param o the o
	 */
	public Carrier(Orientation o) {
		super(o, '®', "Carrier");
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
