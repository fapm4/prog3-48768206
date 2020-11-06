package model.ship;

import model.Orientation;

// TODO: Auto-generated Javadoc
/**
 * The Class Cruiser.
 * @author Francisco Alejandro Pérez Meneses - 48768206H
 */
public class Cruiser extends Ship{
	
	/** The shape. */
	private int[][] shape = new int[][] {
       {0, 0, 0, 0, 0,
    	0, 0, 1, 0, 0,	
    	0, 0, 1, 0, 0,	
    	0, 0, 1, 0, 0,
    	0, 0, 0, 0, 0},
	      
	   {0, 0, 0, 0, 0,
		0, 0, 0, 0, 0,	
		0, 1, 1, 1, 0,	
		0, 0, 0, 0, 0,
		0, 0, 0, 0, 0},
	      
	   {0, 0, 0, 0, 0,
		0, 0, 1, 0, 0,	
		0, 0, 1, 0, 0,	
		0, 0, 1, 0, 0,
		0, 0, 0, 0, 0},
	      
	   {0, 0, 0, 0, 0,
		0, 0, 0, 0, 0,	
		0, 1, 1, 1, 0,	
		0, 0, 0, 0, 0,
		0, 0, 0, 0, 0}};
		
		
	/**
	 * Instantiates a new cruiser.
	 *
	 * @param o the o
	 */
	public Cruiser(Orientation o) {
		super(o, 'Ø', "Cruiser");
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
