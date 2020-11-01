package model.ship;

import model.Orientation;

public class Battleship extends Ship{
	
	private int[][] shape = new int[][] {
       {0, 0, 0, 0, 0,
    	0, 0, 1, 0, 0,	
    	0, 0, 1, 0, 0,	
    	0, 0, 1, 0, 0,
    	0, 0, 1, 0, 0},
	      
       {0, 0, 0, 0, 0,
		0, 0, 0, 0, 0,	
		0, 1, 1, 1, 1,	
		0, 0, 0, 0, 0,
		0, 0, 0, 0, 0},
	      
       {0, 0, 0, 0, 0,
    	0, 0, 1, 0, 0,	
    	0, 0, 1, 0, 0,	
    	0, 0, 1, 0, 0,
    	0, 0, 1, 0, 0},
	      
	   {0, 0, 0, 0, 0,
		0, 0, 0, 0, 0,	
		0, 1, 1, 1, 1,	
		0, 0, 0, 0, 0,
		0, 0, 0, 0, 0}};
		
		
	public Battleship(Orientation o) {
		super(o, 'O', "Battleship");
	}
	
	
	public int[][] getShape(){
		return this.shape;
	}
}
