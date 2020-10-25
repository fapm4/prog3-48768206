package model.ship;

import model.Orientation;

public class Carrier extends Ship{
	
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
		
	public Carrier(Orientation o) {
		super(o, '®', "Carrier");
	}
	
	public int[][] getShape(){
		return this.shape;
	}
}
