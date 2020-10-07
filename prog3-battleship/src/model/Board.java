package model;

public class Board {
	private int size;
	private int numCrafts;
	private int destroyedCrafts;
	private static int MIN_BOARD_SIZE = 5;
	public static int MAX_BOARD_SIZE = 20;
	public static int NOTSEEN_SYMBOL = '?';
	public static char WATER_SYMBOL = ' ';
	public static char HIT_SYMBOL = '•';
	
	public Board(int size) {
		if(size < MIN_BOARD_SIZE | size > MAX_BOARD_SIZE) {
			
			System.err.println("Tamaño incorrecto");
		}
		
		else {
			this.size = MIN_BOARD_SIZE;
			// board Map<>?
			// resto de params
			numCrafts = 0;
			destroyedCrafts = 0;
		}
	}
	
	public int getSize() {
		return size;
	}
	
	public boolean checkCoordinate(Coordinate c) {
		
		if(c.get(0) < 0 | c.get(0) < 0 | c.get(0) > (size - 1) | c.get(1) > (size - 1)) {
			return false;
		}
		else {
			return true;
		}
	}
	
	/*public boolean add(Ship ship, Coordinate position) {
		
	}
	
	public Ship getShip() {
		
	}
	
	public boolean isSeen(Coordinate c) {
		
	}
	
	public CellStatus hit(Coordinate c) {
		
	}
	
	public boolean areAllCraftsDestroyed() {
		
	}
	
	public Set<Coordinate> getNeighborhood(Ship ship, Coordinate position){
		
	}
	
	public Set<Coordinate> getNeighborhood(Ship s){
		
	}
	
	public String show(boolean unveil) {
		
	}
	
	public String toString() {
		
	}*/
}
