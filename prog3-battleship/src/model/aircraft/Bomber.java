package model.aircraft;

import model.Orientation;

public class Bomber extends Aircraft{
	protected int[][] shape = new int[][] {
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
		
		public Bomber(Orientation o) {
			super(o, '⇶', "Bomber");
		}
		
		public int[][] getShape(){
			return this.shape;
		}
}
