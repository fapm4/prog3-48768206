package model.ship;

import model.Orientation;

public class Cruiser extends Ship{
	
	protected int[][] shape = new int[][] {
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
		
	public Cruiser(Orientation o) {
		super(o, 'Ø', "Cruiser");
	}
	
	public int[][] getShape(){
		return this.shape;
	}
}
