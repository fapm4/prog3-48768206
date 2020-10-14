package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Board {
	
	//Variables
	private int size;
	private int numCrafts;
	private int destroyedCrafts;
	Set<Coordinate> seen = new HashSet<Coordinate>();
	Map<Coordinate, Ship> board = new HashMap<Coordinate, Ship>();
	
	//Constantes
	private final static int MIN_BOARD_SIZE = 5;
	public final static int MAX_BOARD_SIZE = 20;
	public final static int NOTSEEN_SYMBOL = '?';
	public final static char WATER_SYMBOL = ' ';
	public final static char HIT_SYMBOL = '•';
	
	public Board(int size) {
		if(size < MIN_BOARD_SIZE | size > MAX_BOARD_SIZE) {
			
			System.err.println("Tamaño incorrecto");
			this.size = MIN_BOARD_SIZE;
		}
		
		else {
			this.size = size;
			numCrafts = 0;
			destroyedCrafts = 0;
			seen = null;
			board = null;
		}
	}
	
	public int getSize() {
		return size;
	}
	
	public boolean checkCoordinate(Coordinate c) {
		
		if(c.get(0) < 0 | c.get(1) < 0 | c.get(0) > (size - 1) | c.get(1) > (size - 1)) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public boolean addShip(Ship ship, Coordinate position) {
		boolean dev = false;
		
		ship.setPosition(position);
		Set<Coordinate> posAbs = new HashSet<Coordinate>();
		
		for(Coordinate c: posAbs) {
			board.put(c, ship);
		}
		
		dev = true;
		numCrafts ++;
		return dev;
	}
	
	
	public Ship getShip(Coordinate c) {
		return board.get(c);
	}
	
	/*public boolean isSeen(Coordinate c) {
		
	}
	
	public CellStatus hit(Coordinate c) {
		
	}*/
	
	public boolean areAllCraftsDestroyed() {
		return board.size() == 0;
	}
	
	/*public Set<Coordinate> getNeighborhood(Ship ship, Coordinate position){
		
	}
	
	public Set<Coordinate> getNeighborhood(Ship s){
		
	}
	
	public String show(boolean unveil) {
		
	}
	*/
	public String toString() {
		String dev = "Board " + this.size + "; " + "crafts: " + numCrafts + "; " + "destroyed: " + destroyedCrafts;
		return dev;
	}
}
