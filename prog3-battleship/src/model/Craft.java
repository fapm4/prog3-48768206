package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.aircraft.Coordinate3D;
import model.exceptions.CoordinateAlreadyHitException;
import model.ship.Coordinate2D;

public abstract class Craft {

	/** The Constant BOUNDING_SQUARE_SIZE. */
	private static final int BOUNDING_SQUARE_SIZE = 5;
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
	 * @throws Exception 
	 */
	public void setPosition(Coordinate position) throws Exception {
		
		this.position = position.copy();
		
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
	public int getShapeIndex(Coordinate c) {
		if(c == null) {
			throw new NullPointerException();
		}
		return (c.get(1) * BOUNDING_SQUARE_SIZE) + c.get(0);
	}

	/**
	 * Gets the absolute positions.
	 *
	 * @param position the position
	 * @return the absolute positions
	 * @throws Exception 
	 */
	public Set<Coordinate> getAbsolutePositions(Coordinate position) throws Exception {
		if(position == null) {
			throw new NullPointerException();
		}
		
		Set<Coordinate> positionsToReturn = new HashSet<Coordinate>();
		Orientation or = this.orientation;
		Coordinate nuevo = null;
		
		if(or.equals(Orientation.NORTH) | or.equals(Orientation.SOUTH)) {
			for(int i = 1;i <= 3;i++) {
				if(position instanceof Coordinate2D) {
					int coords[] = {position.get(0) + 2, position.get(1) + i};
					nuevo = CoordinateFactory.createCoordinate(coords);
				}
				if(position instanceof Coordinate3D) {
					int coords[] = {position.get(0) + 2, position.get(1) + i, position.get(2)};
					nuevo = CoordinateFactory.createCoordinate(coords);
				}
				
				positionsToReturn.add(nuevo);
			}
		}
		
		else if(or.equals(Orientation.EAST) | or.equals(Orientation.WEST)){
			for(int i = 1;i <= 3;i++) {
				if(position instanceof Coordinate2D) {
					int coords[] = {position.get(0) + 2, position.get(1) + i};
					nuevo = CoordinateFactory.createCoordinate(coords);
				}
				
				else if(position instanceof Coordinate3D) {
					int coords[] = {position.get(0) + 2, position.get(1) + i, position.get(2)};
					nuevo = CoordinateFactory.createCoordinate(coords);
				}
				
				positionsToReturn.add(nuevo);
			}
		}
		
		return positionsToReturn;
	}

	/**
	 * Gets the absolute positions.
	 *
	 * @return the absolute positions
	 * @throws Exception 
	 */
	
	public Set<Coordinate> getAbsolutePositions() throws Exception {
		return getAbsolutePositions(this.position);
	}

	/**
	 * Hit.
	 *
	 * @param c the c
	 * @return true, if successful
	 * @throws Exception 
	 */
	public boolean hit(Coordinate c) throws Exception {
		boolean dev = false;
		
		if(this.getPosition() == null) {
			return false;
		}
		
		int coords[] = null;
		
		Orientation or = this.orientation;
		
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
		for(Coordinate busca: posAbsolutas) {
			
			// Si la encuentro salgo del bucle y paso a modificar el shape
			if(c.equals(busca)) {
				dev = true;
				aux = busca;
			}
		}
		
		if(dev == true) {
			Coordinate nueva = null;
			int pos;
			
			switch(or) {
			case NORTH:
				
				if(aux instanceof Coordinate2D) {
					coords[0] = aux.get(0) - this.position.get(0);
					coords[1] = aux.get(1) - this.position.get(1);
					nueva = CoordinateFactory.createCoordinate(coords);
				}
				
				else if(aux instanceof Coordinate3D) {
					coords[0] = aux.get(0) - this.position.get(0);
					coords[1] = aux.get(1) - this.position.get(1);
					coords[2] = aux.get(2) - this.position.get(2);
					nueva = CoordinateFactory.createCoordinate(coords);
				}
				
				pos = getShapeIndex(nueva);
							
				if(shape[0][pos] == HIT_VALUE) {
					dev = false;
					throw new CoordinateAlreadyHitException(nueva);
				}
				else {
					this.shape[0][pos] = HIT_VALUE;
					dev = true;
				}					
				
				
				break;
				
			case SOUTH:
	
				if(aux instanceof Coordinate2D) {
					coords[0] = aux.get(0) - this.position.get(0);
					coords[1] = aux.get(1) - this.position.get(1);
					nueva = CoordinateFactory.createCoordinate(coords);
				}
				
				else if(aux instanceof Coordinate3D) {
					coords[0] = aux.get(0) - this.position.get(0);
					coords[1] = aux.get(1) - this.position.get(1);
					coords[2] = aux.get(2) - this.position.get(2);
					nueva = CoordinateFactory.createCoordinate(coords);
				}
	
				pos = getShapeIndex(nueva);
				
				if(nueva.get(0) < 2) {
					shape[2][7] = HIT_VALUE;
				}
				
				if(shape[2][pos] == HIT_VALUE) {
					dev = false;
					throw new CoordinateAlreadyHitException(nueva);
				}
				else {
					this.shape[2][pos] = HIT_VALUE;
					dev = true;
				}
					
				break;
				
			case EAST:
									
				if(aux instanceof Coordinate2D) {
					coords[0] = aux.get(0) - this.position.get(0);
					coords[1] = aux.get(1) - this.position.get(1);
					nueva = CoordinateFactory.createCoordinate(coords);
				}
				
				else if(aux instanceof Coordinate3D) {
					coords[0] = aux.get(0) - this.position.get(0);
					coords[1] = aux.get(1) - this.position.get(1);
					coords[2] = aux.get(2) - this.position.get(2);
					nueva = CoordinateFactory.createCoordinate(coords);
				}
	
				pos = getShapeIndex(nueva);					
									
				if(shape[1][pos] == HIT_VALUE) {
					dev = false;
					throw new CoordinateAlreadyHitException(nueva);
				}
				else {
					this.shape[1][pos] = HIT_VALUE;
					dev = true;
				}					
				
				
				break;
				
			case WEST:
									
				if(aux instanceof Coordinate2D) {
					coords[0] = aux.get(0) - this.position.get(0);
					coords[1] = aux.get(1) - this.position.get(1);
					nueva = CoordinateFactory.createCoordinate(coords);
				}
				
				else if(aux instanceof Coordinate3D) {
					coords[0] = aux.get(0) - this.position.get(0);
					coords[1] = aux.get(1) - this.position.get(1);
					coords[2] = aux.get(2) - this.position.get(2);
					nueva = CoordinateFactory.createCoordinate(coords);
				}
	
				pos = getShapeIndex(nueva);					
									
				if(shape[3][pos] == HIT_VALUE) {
					dev = false;
					throw new CoordinateAlreadyHitException(nueva);
				}
				else {
					this.shape[3][pos] = HIT_VALUE;
					dev = true;
				}					
				
				break;
	
			}
		}
		
		return dev;
	}

	/**
	 * Checks if is shot down.
	 *
	 * @return true, if is shot down
	 */
	public boolean isShotDown() {
		Orientation or = this.orientation;
		boolean dev = false;
		
		switch(or) {
		case NORTH:
			//System.out.println(shape[0][7] + " " + shape[0][12] + " " + shape[0][17]);
			if(this.shape[0][7] == HIT_VALUE && this.shape[0][12] == HIT_VALUE && this.shape[0][17] == HIT_VALUE) {
				dev = true;			
			}
			break;
			
		case SOUTH:
			//System.out.println(shape[2][7] + " " + shape[2][12] + " " + shape[2][17]);
			if(shape[2][7] == HIT_VALUE && shape[2][12] == HIT_VALUE && shape[2][17] == HIT_VALUE) {
				dev = true;
			}
			
			break;
			
		case EAST:
			//System.out.println(shape[1][11] + " " + shape[1][12] + " " + shape[1][13]);
			if(this.shape[1][11] == HIT_VALUE && this.shape[1][12] == HIT_VALUE && this.shape[1][13] == HIT_VALUE) {
				dev = true;
			}
			break;
			
		case WEST:
			if(shape[3][11] == HIT_VALUE && shape[3][12] == HIT_VALUE && shape[3][13] == HIT_VALUE) {
				dev = true;
			}
			break;
			
		}
		
		return dev;
	}

	/**
	 * Checks if is hit.
	 *
	 * @param c the c
	 * @return true, if is hit
	 * @throws Exception 
	 */
	public boolean isHit(Coordinate c) throws Exception {
		boolean dev = false;
		Orientation or = this.orientation;
		boolean encontrado = false;
		int pos = 0;
		int coords[] = null;
		Coordinate relativa = null;
		
		if(this.position == null) {
			return false;
		}
		
		Set<Coordinate> posAbs = new HashSet<Coordinate>();
		posAbs = getAbsolutePositions(this.position);
		
		for(Coordinate b: posAbs) {
			if(b.equals(c)){
				encontrado = true;
			}
		}
		
		if(encontrado) {
			
			if(c instanceof Coordinate2D) {
				coords[0] = c.get(0) - this.position.get(0);
				coords[1] = c.get(1) - this.position.get(1);
				relativa = CoordinateFactory.createCoordinate(coords);
			}
			
			else if(c instanceof Coordinate3D) {
				coords[0] = c.get(0) - this.position.get(0);
				coords[1] = c.get(1) - this.position.get(1);
				coords[2] = c.get(2) - this.position.get(2);
				relativa = CoordinateFactory.createCoordinate(coords);
			}

			pos = getShapeIndex(relativa);
			
			switch(or) {
				case NORTH:
					if(shape[0][pos] == HIT_VALUE) {
						dev = true;
					}
					break;
					
				case SOUTH:
					if(shape[2][pos] == HIT_VALUE) {
						dev = true;
					}
					break;
					
				case EAST:
					if(shape[1][pos] == HIT_VALUE) {
						dev = true;
					}
					break;
					
				case WEST:
					if(shape[3][pos] == HIT_VALUE) {
						dev = true;
					}
					break;
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
		Orientation or = this.orientation;
		
		sb.append(this.name + " (" + this.orientation + ")\n");
		sb.append(" -----\n");
		
		for(int i = 0;i < BOUNDING_SQUARE_SIZE;i++) {
			sb.append("|");
			for(int j = 0;j < BOUNDING_SQUARE_SIZE;j++) {
				if(or.equals(Orientation.NORTH) | or.equals(Orientation.SOUTH)){
					if(j == 2) {
						if(i == 1 | i == 2 | i == 3) {
							sb.append(this.symbol);
						}
						else {
							sb.append(Board.WATER_SYMBOL);	
						}
					}
					else {
						sb.append(Board.WATER_SYMBOL);	
					}
				}
				else if(or.equals(Orientation.EAST) | or.equals(Orientation.WEST)){
					if(i == 2) {
						if(j == 1 | j == 2 | j == 3) {
							sb.append(this.symbol);
						}
						else {
							sb.append(Board.WATER_SYMBOL);
						}
					}
					else {
						sb.append(Board.WATER_SYMBOL);	
					}
				}
			}
			
			sb.append("|");
			sb.append("\n");
		}
		
		
		sb.append(" -----");
		return sb.toString();
	}

}