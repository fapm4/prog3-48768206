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
			//seen = null;
			//board = null;
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

		if(checkCoordinate(position)) {
			ship.setPosition(position);
			
			Set<Coordinate> posAbs = new HashSet<Coordinate>();
			posAbs = ship.getAbsolutePositions();
			
			for(Coordinate c: posAbs) {
				board.put(c, ship);
			}
			
			dev = true;
			numCrafts ++;
		}
	
		return dev;
	}
	
	
	public Ship getShip(Coordinate c) {
		
		if(board.containsKey(c)) {
			return board.get(c);
		}
		else {
			return null;
		}
	}
	
	public boolean isSeen(Coordinate c) {
		boolean dev = false;
		for(Coordinate b: seen) {
			if(b.equals(c)) {
				dev = true;
				break;
			}
		}
			
		return dev;
	}
	
	public CellStatus hit(Coordinate c) {
		
		CellStatus dev = CellStatus.WATER;
		
		if(!checkCoordinate(c)) {
			System.err.println("Coordenada fuera del tablero");
		}
		
		if(board.containsKey(c)) {
			Ship barcoGolpeado = getShip(c);
			
			if(!barcoGolpeado.isShotDown()) {
				barcoGolpeado.llamadaBoard = true;
				barcoGolpeado.hit(c);
				
				if(barcoGolpeado.isShotDown()) {
					dev = CellStatus.DESTROYED;
					destroyedCrafts ++;
				}
				else {
					dev = CellStatus.HIT;
				}
			}
		}
		
		seen.add(c);
		return dev;
	}
	
	public boolean areAllCraftsDestroyed() {
		boolean dev = false;
		
		if(numCrafts == destroyedCrafts) {
			dev = true;
		}
		
		return dev;
	}
	
	/*public Set<Coordinate> getNeighborhood(Ship ship, Coordinate position){
		
	}*/
	
	/*
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
