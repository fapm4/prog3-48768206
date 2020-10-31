package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;
import model.exceptions.NextToAnotherCraftException;
import model.exceptions.OccupiedCoordinateException;
import model.ship.Battleship;
import model.ship.Carrier;
import model.ship.Cruiser;
import model.ship.Destroyer;
import model.ship.Ship;

public abstract class Board {

	/** The desde add. */
	private boolean desdeAdd = false;
	/** The not set. */
	private boolean notSet = false;
	/** The size. */
	private int size;
	/** The num crafts. */
	private int numCrafts;
	/** The destroyed crafts. */
	private int destroyedCrafts;
	/** The seen. */
	private Set<Coordinate> seen = new HashSet<Coordinate>();
	/** The board. */
	private Map<Coordinate, Craft> board = new HashMap<Coordinate, Craft>();
	/** The Constant MIN_BOARD_SIZE. */
	protected static final int MIN_BOARD_SIZE = 5;
	/** The Constant MAX_BOARD_SIZE. */
	public static final int MAX_BOARD_SIZE = 20;
	/** The Constant NOTSEEN_SYMBOL. */
	public static final char NOTSEEN_SYMBOL = '?';
	/** The Constant WATER_SYMBOL. */
	public static final char WATER_SYMBOL = ' ';
	/** The Constant HIT_SYMBOL. */
	public static final char HIT_SYMBOL = '•';
	
	public static final char Board_SEPARATOR = '|';

	public Board(int size) {
		if(size < MIN_BOARD_SIZE | size > MAX_BOARD_SIZE) {
			throw new IllegalArgumentException();
		}
		
		else {
			this.size = size;
			numCrafts = 0;
			destroyedCrafts = 0;
			seen = new HashSet<Coordinate>();
			board = new HashMap<Coordinate, Craft>();
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
	 * Adds the ship.
	 *
	 * @param ship the ship
	 * @param position the position
	 * @return true, if successful
	 * @throws Exception 
	 */
	public boolean addCraft(Craft craft, Coordinate position) throws InvalidCoordinateException,
	OccupiedCoordinateException, NextToAnotherCraftException{
		
		// Variable a devolver
		boolean dev = false;
		
		// Variables de las posiciones
		boolean posIncorrecta = false;
		boolean posOcupada = false;	
		boolean posVecindario = false;
		
		// Conjuntos de coordenadas
		craft.setPosition(position);
		Set<Coordinate> posAbs = new HashSet<Coordinate>();
		Set<Coordinate> vecindario = new HashSet<Coordinate>();
		posAbs = craft.getAbsolutePositions();
	
		// ----------------------------------------------------------------
		// Compruebo que las coordenadas del ship no estén fuera del tablero
		for(Coordinate c: posAbs) {
			if(!checkCoordinate(c)) {
				posIncorrecta = true;
			}
		}
		
		if(posIncorrecta) {
			throw new InvalidCoordinateException(position);
		}
		
		// ----------------------------------------------------------------
		// Compruebo que las posiciones del barco no estén ocupadas
		for(Coordinate c: posAbs) {
			if(board.containsKey(c)) {
				posOcupada = true;
			}
		}
		
		if(posOcupada) {
			throw new OccupiedCoordinateException(position);
		}
		
		// ----------------------------------------------------------------
		// Compruebo que las posiciones cercanas del barco
		
		desdeAdd = true;
		vecindario = getNeighborhood(craft, position);
		for(Coordinate c: vecindario) {
			if(board.containsKey(c)) {
				posVecindario = true;
			}
		}
		
		if(posVecindario) {
			throw new NextToAnotherCraftException(position);
		}
		
		if(posIncorrecta == false && posOcupada == false && posVecindario == false) {
			for(Coordinate c: posAbs) {
				board.put(c, craft);
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
	public Craft getCraft(Coordinate c) {
		
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
	 * @param coord the coord
	 * @return the cell status
	 * @throws CoordinateAlreadyHitException 
	 * @throws Exception 
	 */
public CellStatus hit(Coordinate coord) throws InvalidCoordinateException, CoordinateAlreadyHitException{
		
		CellStatus estado = null;
		Coordinate c = coord.copy();
		
		if(!checkCoordinate(c)) {
			estado = CellStatus.WATER;
			throw new InvalidCoordinateException(c);
		}
		
		if(getCraft(c) == null) {
			estado = CellStatus.WATER;
			seen.add(c);
		}
		
		else {
			Craft barcoGolpeado = getCraft(c);
			boolean golpe = barcoGolpeado.hit(c);
			
			if(golpe | barcoGolpeado.getOrientation().equals(Orientation.EAST)) {
				if(barcoGolpeado.isShotDown()) {
					Set<Coordinate> vecinos = getNeighborhood(barcoGolpeado);
					seen.addAll(vecinos);
					estado = CellStatus.DESTROYED;
					destroyedCrafts ++;
				}
				else {
					estado = CellStatus.HIT;
				}
			}
			seen.add(c);
		}
		
		
		return estado;
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
	 * @throws Exception 
	 */
	public Set<Coordinate> getNeighborhood(Craft ship, Coordinate position){
		
		if(ship == null | position == null) {
			throw new NullPointerException();
		}
		
		Set<Coordinate> vecindario = new HashSet<Coordinate>();
		Set<Coordinate> vecindarioToReturn = new HashSet<Coordinate>();
		Set<Coordinate> listaVacia = new HashSet<Coordinate>();
		Set<Coordinate> posAbs = new HashSet<Coordinate>();
		int contFuera = 0;
		Coordinate pos = ship.getPosition();
		ship.setPosition(position);
		posAbs = ship.getAbsolutePositions();
		
		if(desdeAdd == true) {
			for(Coordinate c: posAbs) {
				vecindario.addAll(c.adjacentCoordinates());
			}
			
			for(Coordinate c: vecindario) {
				if(checkCoordinate(c)) {
					vecindarioToReturn.add(c);
				}
			}
			
			vecindarioToReturn.removeAll(posAbs);
			desdeAdd = false;
		}
		
		else {
			if(notSet == false) {
				vecindarioToReturn = listaVacia;
			}
			
			for(Coordinate c: posAbs) {
				if(!checkCoordinate(c)) {
					contFuera ++;
				}
			}
			
			if(contFuera == 3) {
				for(Coordinate c: posAbs) {
					vecindario.addAll(c.adjacentCoordinates());
				}
				
				for(Coordinate c: vecindario) {
					if(checkCoordinate(c)) {
						vecindarioToReturn.add(c);
					}
				}
				
			}
			else {
				for(Coordinate c: posAbs) {
					vecindario.addAll(c.adjacentCoordinates());
				}
				
				for(Coordinate c: vecindario) {
					if(checkCoordinate(c)) {
						vecindarioToReturn.add(c);
					}
				}
				
			}
			
			vecindarioToReturn.removeAll(posAbs);
		}
		
		return vecindarioToReturn;
	}

	/**
	 * Gets the neighborhood.
	 *
	 * @param s the s
	 * @return the neighborhood
	 * @throws Exception 
	 */
	public Set<Coordinate> getNeighborhood(Craft s){			
		if(!board.containsValue(s)) {
			throw new NullPointerException();
		}
		else {
			return getNeighborhood(s, s.getPosition());
		}
	}
	
	public abstract boolean checkCoordinate(Coordinate c);
	public abstract String show(boolean unveil);

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