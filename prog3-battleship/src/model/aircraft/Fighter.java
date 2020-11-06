package model.aircraft;

import model.Orientation;

// TODO: Auto-generated Javadoc
/**
 * The Class Fighter.
 * @author Francisco Alejandro Pérez Meneses - 48768206H
 */
public class Fighter extends Aircraft{
	
	/** The shape. */
	private int[][] shape = new int[][] {
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
		
		
	/**
	 * Instantiates a new fighter.
	 *
	 * @param o the o
	 */
	public Fighter(Orientation o) {
		super(o, '⇄', "Fighter");
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
