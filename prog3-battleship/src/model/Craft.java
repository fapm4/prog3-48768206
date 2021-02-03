package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.aircraft.Bomber;
import model.aircraft.Coordinate3D;
import model.aircraft.Fighter;
import model.aircraft.Transport;
import model.exceptions.CoordinateAlreadyHitException;
import model.ship.Battleship;
import model.ship.Carrier;
import model.ship.Coordinate2D;
import model.ship.Cruiser;
import model.ship.Destroyer;


// TODO: Auto-generated Javadoc
/**
 * The Class Craft.
 * @author Francisco Alejandro Pérez Meneses - 48768206H
 */

public abstract class Craft {
	
	/** The Constant BOUNDING_SQUARE_SIZE. */
	public static final int BOUNDING_SQUARE_SIZE = 5;
	
	/** The Constant HIT_VALUE. */
	private static final int HIT_VALUE = -1;
	
	/** The Constant CRAFT_VALUE. */
	private static final int CRAFT_VALUE = 1;
	
	/** The symbol. */
	private char symbol;
	
	/** The name. */
	private String name;
	
	/** The orientation. */
	private Orientation orientation;
	
	/** The position. */
	private Coordinate position;
	
	/** The shape. */
	protected int shape[][];
		

	/**
	 * Instantiates a new craft.
	 *
	 * @param o the o
	 * @param s the s
	 * @param n the n
	 */
	public Craft(Orientation o, char s, String n) {
		
		orientation = o;
		symbol = s;
		name = n;
		position = null;		
	}

	
	/**
	 * Gets the position.
	 *
	 * @return the position
	 */
	public Coordinate getPosition() {
		
		if(position == null) {
			return null;
		}
		else {
			return this.position;
		}
	}

	
	/**
	 * Sets the position.
	 *
	 * @param position the new position
	 */
	public void setPosition(Coordinate position){
		
		if(position == null) {
			throw new NullPointerException();
		}
		
		this.position = position.copy();
	}

	
	/**
	 * Sets the orientation.
	 *
	 * @param or the new orientation
	 */
	private void setOrientation(Orientation or) {
		
		if(or == null) {
			if(position == null) {
				throw new NullPointerException();
			}
		}
		
		this.orientation = or;
	}
	
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * Gets the orientation.
	 *
	 * @return the orientation
	 */
	public Orientation getOrientation() {
		return orientation;
	}


	/**
	 * Gets the symbol.
	 *
	 * @return the symbol
	 */
	public char getSymbol() {
		return symbol;
	}


	/**
	 * Gets the shape.
	 *
	 * @return the shape
	 */
	public int[][] getShape() {
		return shape;
	}

	
	/**
	 * Gets the shape index.
	 *
	 * @param c the c
	 * @return the shape index
	 */
	public int getShapeIndex(Coordinate c){
		
		if(c == null) {
			throw new NullPointerException();
		}
		
		return (c.get(1) * BOUNDING_SQUARE_SIZE) + c.get(0);
	}

	
	private int OrientationToInteger() {
		int numOrientation = 0;
		Orientation or = this.getOrientation();
		
		switch(or) {
			case NORTH:
				numOrientation = 0;
				break;
				
			case SOUTH:
				numOrientation = 2;
				break;
				
			case WEST:
				numOrientation = 3;
				break;
				
			case EAST:
				numOrientation = 1;
				break;
		}
		
		return numOrientation;
	}
	
	/**
	 * Gets the absolute positions.
	 *
	 * @param position the position
	 * @return the absolute positions
	 */
	public Set<Coordinate> getAbsolutePositions(Coordinate position){
		Set<Coordinate> positionsToReturn = new HashSet<Coordinate>();
		int or = OrientationToInteger();
		int aux = 0;
		
		if(position == null) {
			throw new NullPointerException();
		}
		
		
		for(int i = 0;i < BOUNDING_SQUARE_SIZE;i++) {
			for(int j = 0;j < BOUNDING_SQUARE_SIZE;j++) {
				Coordinate c = CoordinateFactory.createCoordinate(j, i);
				aux = getShapeIndex(c);
				if(shape[or][aux] == CRAFT_VALUE || shape[or][aux] == HIT_VALUE) {
					Coordinate c2 = position.copy();
					c2.set(0, c.get(0) + position.get(0));
					c2.set(1, c.get(1) + position.get(1));
					positionsToReturn.add(c2);
				}
			}
		}

		return positionsToReturn;
	}
	
	
	/**
	 * Gets the absolute positions.
	 *
	 * @return the absolute positions
	 */
	public Set<Coordinate> getAbsolutePositions(){
		return getAbsolutePositions(this.position);
	}

	
	/**
	 * Hit.
	 *
	 * @param c the c
	 * @return true, if successful
	 * @throws CoordinateAlreadyHitException the coordinate already hit exception
	 */
	public boolean hit(Coordinate c) throws CoordinateAlreadyHitException {
		boolean dev = false;
		
		if(this.getPosition() == null) {
			throw new NullPointerException();
		}
	
		int or = OrientationToInteger();
		
		Set<Coordinate> posAbsolutas = new HashSet<Coordinate>();
		
		posAbsolutas = getAbsolutePositions();

		
		// Arrays para ordenar las coordenadas
		List<Coordinate> posSinOrdenar = new ArrayList<Coordinate>();
		List<Coordinate> posOrdenadas = new ArrayList<Coordinate>();
		
		// Primero las meto sin orden
		for(Coordinate a: posAbsolutas) {
			posSinOrdenar.add(a);
		}
		
		// Aquí se ordenan solas
		for(int i = 0;i < posSinOrdenar.size();i++) {
			posOrdenadas.add(posSinOrdenar.get(i));
		}
		
		Coordinate aux = null;
			
		// Busco si la coordenada que me dan es una de mis posiciones absolutas del barco
		for(Coordinate busca: posOrdenadas) {
			
			// Si la encuentro salgo del bucle y paso a modificar el shape
			if(c.equals(busca)) {
				dev = true;
				aux = busca;
			}
		}
		
		if(dev == true) {
			Coordinate nueva = getRelativeCoordinate(c);
			int pos;

			pos = getShapeIndex(nueva);	
			
			if(shape[or][pos] == HIT_VALUE) {
				throw new CoordinateAlreadyHitException(nueva);
			}
			else {
				shape[or][pos] = HIT_VALUE;
				dev = true;
			}					
		}
		
		return dev;
	}

	
	private Coordinate getRelativeCoordinate(Coordinate position) {
		return CoordinateFactory.createCoordinate((position.get(0) - getPosition().get(0)), (position.get(1) - getPosition().get(1)));
		
	}
	
	/**
	 * Checks if is shot down.
	 *
	 * @return true, if is shot down
	 */
	public boolean isShotDown() {
		int cont = 0;
		int or = OrientationToInteger();
		boolean dev = false;
		
		if(this.getPosition() != null) {
			Set<Coordinate> posAbs = getAbsolutePositions();
			Set<Coordinate> posRel = new HashSet<Coordinate>();
			
			for(Coordinate c: posAbs) {
				posRel.add(getRelativeCoordinate(c));
			}
			
			for(Coordinate c: posRel) {
				if(shape[or][getShapeIndex(c)] == HIT_VALUE) {
					cont++;
				}
			}
			
			if(cont == posRel.size()) {
				dev = true;
			}
		}
		
		return dev;
	}

	
	/**
	 * Checks if is hit.
	 *
	 * @param c the c
	 * @return true, if is hit
	 */
	public boolean isHit(Coordinate c){
		boolean dev = false;
		boolean encontrado = false;
		int pos = 0;
		int or = OrientationToInteger();
		Coordinate relativa = null;
		
		if(this.position == null) {
			throw new NullPointerException();
		}
		
		Set<Coordinate> posAbs = new HashSet<Coordinate>();
		posAbs = getAbsolutePositions(this.position);
		
		for(Coordinate b: posAbs) {
			if(b.equals(c)){
				encontrado = true;
			}
		}
		
		if(encontrado) {
			
			relativa = getRelativeCoordinate(c);
			pos = getShapeIndex(relativa);
			if(shape[or][pos] == HIT_VALUE) {
				dev = true;
			}
		}
		
		return dev;
	
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int or = OrientationToInteger();
		int aux = 0;
		
		sb.append(this.name + " (" + this.orientation + ")\n");
		sb.append(" -----\n");
		
		for(int i = 0;i < BOUNDING_SQUARE_SIZE;i++) {
			sb.append("|");
			for(int j = 0;j < BOUNDING_SQUARE_SIZE;j++) {
				Coordinate c = CoordinateFactory.createCoordinate(j, i);
				aux = getShapeIndex(c);
				
				if(shape[or][aux] == CRAFT_VALUE) {
					sb.append(this.getSymbol());
				}
				
				if(shape[or][aux] == HIT_VALUE) {
					sb.append(Board.HIT_SYMBOL);
				}
				
				if(shape[or][aux] == 0) {
					sb.append(" ");
				}
								
			}
			
			sb.append("|");
			sb.append("\n");
		}
		
		
		sb.append(" -----");
		return sb.toString();
	}
	
	
	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	abstract public int getValue();
}
