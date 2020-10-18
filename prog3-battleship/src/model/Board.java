package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
* @author Francisco Alejandro Pérez Meneses - 48768206H
*/

// TODO: Auto-generated Javadoc
/**
 * The Class Board.
 */

public class Board {
	
	//Variables
	/** The size. */
	private int size;
	
	/** The num crafts. */
	private int numCrafts;
	
	/** The destroyed crafts. */
	private int destroyedCrafts;
	
	/** The seen. */
	Set<Coordinate> seen = new HashSet<Coordinate>();
	
	/** The board. */
	Map<Coordinate, Ship> board = new HashMap<Coordinate, Ship>();
	
	//Constantes
	/** The Constant MIN_BOARD_SIZE. */
	private final static int MIN_BOARD_SIZE = 5;
	
	/** The Constant MAX_BOARD_SIZE. */
	public final static int MAX_BOARD_SIZE = 20;
	
	/** The Constant NOTSEEN_SYMBOL. */
	public final static char NOTSEEN_SYMBOL = '?';
	
	/** The Constant WATER_SYMBOL. */
	public final static char WATER_SYMBOL = ' ';
	
	/** The Constant HIT_SYMBOL. */
	public final static char HIT_SYMBOL = '•';
	
	/**
	 * Instantiates a new board.
	 *
	 * @param size the size
	 */
	public Board(int size) {
		if(size < MIN_BOARD_SIZE | size > MAX_BOARD_SIZE) {
			
			System.err.println("Tamaño incorrecto");
			this.size = MIN_BOARD_SIZE;
		}
		
		else {
			this.size = size;
			numCrafts = 0;
			destroyedCrafts = 0;
			seen = new HashSet<Coordinate>();
			board = new HashMap<Coordinate, Ship>();
		}
	}
	
	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Check coordinate.
	 *
	 * @param c the c
	 * @return true, if successful
	 */
	public boolean checkCoordinate(Coordinate c) {
		
		if(c.get(0) < 0 | c.get(1) < 0 | c.get(0) > (size - 1) | c.get(1) > (size - 1)) {
			return false;
		}
		else {
			return true;
		}
	}
	
	/**
	 * Adds the ship.
	 *
	 * @param ship the ship
	 * @param position the position
	 * @return true, if successful
	 */
	public boolean addShip(Ship ship, Coordinate position) {
		
		// Variable a devolver
		boolean dev = false;
		
		// Variables de las posiciones
		boolean posCorrecta = false;
		boolean posOcupada = false;	
		boolean posVecindario = false;
		
		// Conjuntos de coordenadas
		ship.setPosition(position);
		Set<Coordinate> posAbs = new HashSet<Coordinate>();
		Set<Coordinate> vecindario = new HashSet<Coordinate>();
		posAbs = ship.getAbsolutePositions();

		// Compruebo si las coordenadas del barco están fuera del tablero
		for(Coordinate c: posAbs) {
			if(!checkCoordinate(c)) {
				posCorrecta = false;
			}
			else {
				posCorrecta = true;
			}
		}
		
		// ----------------------------
		
		// Compruebo si algunas de las posiciones donde se pondrá el barco ya están ocupadas
		for(Coordinate c: posAbs) {
			if(board.containsKey(c)) {
				posOcupada = true;
			}
		}
					
		if(posOcupada == true) {
			System.err.println("Una de las coordenads del barco ya se encuentra ocupada");
		}
		
		// ------------------------------
		
		// Compruebo que la vecindad no esté ocupada
		
		vecindario = getNeighborhood(ship, position);
		
		for(Coordinate c: vecindario) {
			if(board.containsKey(c)) {
				posVecindario = true;
			}
		}
		
		if(posVecindario) {
			System.err.println("Vecindario ocupado");
		}
		
		// ------------------------------

		// Si se cumplen las tres condiciones añado el ship
		if(posCorrecta == true && posOcupada == false && posVecindario == false) {
			for(Coordinate c: posAbs) {
				board.put(c, ship);
			}
			
			dev = true;
			numCrafts ++;
		}
		
		return dev;
	}
	
	
	/**
	 * Gets the ship.
	 *
	 * @param c the c
	 * @return the ship
	 */
	public Ship getShip(Coordinate c) {
		
		if(board.containsKey(c)) {
			return board.get(c);
		}
		else {
			return null;
		}
	}
	
	/**
	 * Checks if is seen.
	 *
	 * @param c the c
	 * @return true, if is seen
	 */
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
	
	/**
	 * Hit.
	 *
	 * @param c the c
	 * @return the cell status
	 */
	public CellStatus hit(Coordinate c) {
		
		CellStatus dev = CellStatus.WATER;
		Set<Coordinate> vecinos = new HashSet<Coordinate>();
		
		if(!checkCoordinate(c)) {
			System.err.println("Coordenada fuera del tablero");
		}
		
		if(board.containsKey(c)) {
			Ship barcoGolpeado = getShip(c);
			
			if(!barcoGolpeado.isShotDown()) {
				barcoGolpeado.llamadaBoard = true;
				
				if(barcoGolpeado.hit(c)) {
					if(barcoGolpeado.isShotDown()) {
						
						vecinos = getNeighborhood(barcoGolpeado, c);
						seen.addAll(vecinos);
						dev = CellStatus.DESTROYED;
						destroyedCrafts ++;
					}
					else {
						dev = CellStatus.HIT;
					}	
				}
			}
		}
		
		
		seen.add(c);
		return dev;
	}
	
	/**
	 * Are all crafts destroyed.
	 *
	 * @return true, if successful
	 */
	public boolean areAllCraftsDestroyed() {
		boolean dev = false;
		
		if(numCrafts == destroyedCrafts) {
			dev = true;
		}
		
		return dev;
	}
	
	/**
	 * Gets the neighborhood.
	 *
	 * @param ship the ship
	 * @param position the position
	 * @return the neighborhood
	 */
	public Set<Coordinate> getNeighborhood(Ship ship, Coordinate position){
		
		Set<Coordinate> vecindario = new HashSet<Coordinate>();
		Set<Coordinate> vecindarioToReturn = new HashSet<Coordinate>();
		Set<Coordinate> posAbsolutas = new HashSet<Coordinate>();
		
		Coordinate posActual = ship.getPosition();
		
		if(board.containsValue(ship)) {
			ship.setPosition(position);
			posAbsolutas = ship.getAbsolutePositions();
			
			for(Coordinate c: posAbsolutas) {
				vecindario.addAll(c.adjacentCoordinates());
			}
							
			for(Coordinate c: vecindario) {
				if(checkCoordinate(c)) {
					vecindarioToReturn.add(c);
				}
			}
						
			vecindarioToReturn.removeAll(posAbsolutas);	
		
		}
		
		ship.setPosition(posActual);
		return vecindarioToReturn;
	}
	
	
	/**
	 * Gets the neighborhood.
	 *
	 * @param s the s
	 * @return the neighborhood
	 */
	public Set<Coordinate> getNeighborhood(Ship s){
		return getNeighborhood(s, s.getPosition());
	}
	
	/**
	 * Show.
	 *
	 * @param unveil the unveil
	 * @return the string
	 */
	public String show(boolean unveil) {
		StringBuilder sb = new StringBuilder();
			
		for(int i = 0;i < size;i++) {
			for(int j = 0;j < size;j++) {
				if(unveil) {
					Coordinate coord = new Coordinate(i, j);
					
					Ship barco = null;
					
					if(board.containsKey(coord)) {
						barco = getShip(coord);
					}
					
					if(hit(coord).equals(CellStatus.WATER)) {
						sb.append(WATER_SYMBOL);
					}
					else if(hit(coord).equals(CellStatus.HIT)) {
						sb.append(HIT_SYMBOL);
					}
					else {
						sb.append(barco.getSymbol());
					}
				}
				
				else {
					Coordinate coord = new Coordinate(i, j);
					
					Ship barco = null;
					
					if(board.containsKey(coord)) {
						barco = getShip(coord);
					}
					
					if(hit(coord).equals(CellStatus.WATER)) {
						sb.append(WATER_SYMBOL);
					}
					else if(hit(coord).equals(CellStatus.HIT)) {
						sb.append(HIT_SYMBOL);
					}
					else {
						sb.append(NOTSEEN_SYMBOL);
					}
				}
			}
			
			if(i < size - 1) {
				sb.append("\n");	
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		String dev = "Board " + this.size + "; " + "crafts: " + numCrafts + "; " + "destroyed: " + destroyedCrafts;
		return dev;
	}
}
