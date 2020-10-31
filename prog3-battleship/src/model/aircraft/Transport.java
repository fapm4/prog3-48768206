package model.aircraft;

import model.Orientation;

public class Transport extends Aircraft{
	
	private int[][] shape = new int[][] {
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
		
	public Transport(Orientation o) {
		super(o, '⇋', "Transport");
	}
	
	public int[][] getShape(){
		return this.shape;
	}
}
