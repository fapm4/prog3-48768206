package model.aircraft;

import model.Orientation;

public class Fighter extends Aircraft{
	
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
		
	public Fighter(Orientation o) {
		super(o, '⇄', "Fighter");
	}
	
	public int[][] getShape(){
		return this.shape;
	}
			
			
}
