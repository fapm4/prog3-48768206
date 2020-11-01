package model.ship;

import model.Orientation;

public class Destroyer extends Ship{
	
	private int[][] shape = new int[][] {
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
		
		
	public Destroyer(Orientation o) {
		super(o, 'Ω', "Destroyer");
	}
	
	
	public int[][] getShape(){
		return this.shape;
	}
}
