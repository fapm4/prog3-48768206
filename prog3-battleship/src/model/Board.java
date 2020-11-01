package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;
import model.exceptions.NextToAnotherCraftException;
import model.exceptions.OccupiedCoordinateException;

public abstract class Board {

	private boolean desdeAdd = false;
	
	private boolean notSet = false;
	
	private int size;
	
	private int numCrafts;
	
	private int destroyedCrafts;
	
	private Set<Coordinate> seen = new HashSet<Coordinate>();
	
	private Map<Coordinate, Craft> board = new HashMap<Coordinate, Craft>();
	
	protected static final int MIN_BOARD_SIZE = 5;
	
	public static final int MAX_BOARD_SIZE = 20;
	
	public static final char NOTSEEN_SYMBOL = '?';
	
	public static final char WATER_SYMBOL = ' ';
	
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

	
	public int getSize() {
		return size;
	}


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


	public Craft getCraft(Coordinate c) {
		
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


	public boolean areAllCraftsDestroyed() {
		boolean dev = false;
		
		if(numCrafts == destroyedCrafts) {
			dev = true;
		}
		
		return dev;
	}


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


	public String toString() {
		String dev = "Board " + this.size + "; " + "crafts: " + numCrafts + "; " + "destroyed: " + destroyedCrafts;
		return dev;
	}

}