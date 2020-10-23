package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.Coordinate;
import model.aircraft.Coordinate3D;
import model.ship.Coordinate2D;

public abstract class Craft {
	/** The Constant BOUNDING_SQUARE_SIZE. */
	private final static int BOUNDING_SQUARE_SIZE = 5;
	
	/** The Constant HIT_VALUE. */
	private final static int HIT_VALUE = -1;
	
	/** The Constant CRAFT_VALUE. */
	private final static int CRAFT_VALUE = 1;
	
	/** The symbol. */
	private char symbol;
	
	/** The name. */
	private String name;
	
	/** The orientation. */
	Orientation orientation;
	
	/** The position. */
	Coordinate position;
	
	/** The shape. */
	protected int shape[][] = new int[][] {
        { 0, 0, 0, 0, 0,               // NORTH    ·····
          0, 0, 1, 0, 0,               //          ··#·· 7
          0, 0, 1, 0, 0,               //          ··#·· 12
          0, 0, 1, 0, 0,               //          ..#.. 17
          0, 0, 0, 0, 0},              //          ·····

        { 0, 0, 0, 0, 0,               // EAST     ·····
          0, 0, 0, 0, 0,               //          ·····
          0, 1, 1, 1, 0,               //          ·###· 11 12 13
          0, 0, 0, 0, 0,               //          ·····
          0, 0, 0, 0, 0},              //          ·····

        { 0, 0, 0, 0, 0,               // SOUTH    ·····
          0, 0, 1, 0, 0,               //          ··#·· 7
          0, 0, 1, 0, 0,               //          ··#·· 12
          0, 0, 1, 0, 0,               //          ..#.. 17
          0, 0, 0, 0, 0},              //          ·····

        { 0, 0, 0, 0, 0,               // WEST     ·····
          0, 0, 0, 0, 0,               //          ·····
          0, 1, 1, 1, 0,               //          ·###· 11 12 13
          0, 0, 0, 0, 0,               //          ·····
          0, 0, 0, 0, 0}};             //          ·····
	
	/**
	 * Instantiates a new ship.
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
	 * @throws Exception 
	 */
	public Coordinate getPosition() throws Exception {
		Coordinate positionToReturn = null;

		
		if(position == null) {
			return null;
		}
		else {
			if(position instanceof Coordinate2D) {
				Coordinate2D Position = (Coordinate2D) position;
				positionToReturn = Position.copy();
			}
			else if(position instanceof Coordinate3D) {
				Coordinate3D Position = (Coordinate3D) position;
				positionToReturn = Position.copy();
			}
		}
		
		return positionToReturn;
	}
	
	/**
	 * Sets the position.
	 *
	 * @param position the new position
	 * @throws Exception 
	 */
	public void setPosition(Coordinate position) throws Exception{
		
		if(position instanceof Coordinate2D) {
			this.position = new Coordinate2D(position.get(0), position.get(1));
		}
		
		else if(position instanceof Coordinate3D) {
			this.position = new Coordinate3D(position.get(0), position.get(1), position.get(2));
		}		
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
	public int[][] getShape(){
		return shape;
	}
	
	
	/**
	 * Gets the shape index.
	 *
	 * @param c the c
	 * @return the shape index
	 */
	public int getShapeIndex(Coordinate c) {
		return (c.get(1) * BOUNDING_SQUARE_SIZE) + c.get(0);
	}
	
	/**
	 * Gets the absolute positions.
	 *
	 * @param position the position
	 * @return the absolute positions
	 */
	public Set<Coordinate> getAbsolutePositions(Coordinate position){
		
		Set<Coordinate> positionsToReturn = new HashSet<Coordinate>();
		
		Orientation or = this.orientation;
		
		switch(or) {
			case NORTH:
				for(int i = 1;i <= 3;i++) {
					Coordinate nuevo = new Coordinate(position.get(0) + 2, position.get(1) + i);
					positionsToReturn.add(nuevo);
				}
				
				break;
				
			case SOUTH:
				for(int i = 1;i <= 3;i++) {
					Coordinate nuevo = new Coordinate(position.get(0) + 2, position.get(1) + i);
					positionsToReturn.add(nuevo);
				}

				break;
				
			case EAST:
				for(int i = 1;i <= 3;i++) {
					Coordinate nuevo = new Coordinate(position.get(0) + i, position.get(1) + 2);
					positionsToReturn.add(nuevo);
				}
				
				break;
				
			case WEST:
				for(int i = 1;i <= 3;i++) {
					Coordinate nuevo = new Coordinate(position.get(0) + i, position.get(1) + 2);
					positionsToReturn.add(nuevo);
				}
				
				break;
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
	 */
	public boolean hit(Coordinate c) {
		boolean dev = false;
		
		if(this.getPosition() == null) {
			return false;
		}
		
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
					
				nueva = new Coordinate(aux.get(0) - this.position.get(0), aux.get(1) - this.position.get(1));

				pos = getShapeIndex(nueva);
							
				if(shape[0][pos] == HIT_VALUE) {
					dev = false;
				}
				else {
					this.shape[0][pos] = HIT_VALUE;
					dev = true;
				}					
				
				
				break;
				
			case SOUTH:

				nueva = new Coordinate(aux.get(0) - this.position.get(0), aux.get(1) - this.position.get(1));

				pos = getShapeIndex(nueva);
				
				if(nueva.get(0) < 2) {
					shape[2][7] = HIT_VALUE;
				}
				
				if(shape[2][pos] == HIT_VALUE) {
					dev = false;
				}
				else {
					this.shape[2][pos] = HIT_VALUE;
					dev = true;
				}
					
				break;
				
			case EAST:
									
				nueva = new Coordinate(aux.get(0) - this.position.get(0), aux.get(1) - this.position.get(1));

				pos = getShapeIndex(nueva);					
									
				if(shape[1][pos] == HIT_VALUE) {
					dev = false;
				}
				else {
					this.shape[1][pos] = HIT_VALUE;
					dev = true;
				}					
				
				
				break;
				
			case WEST:
									
				nueva = new Coordinate(aux.get(0) - this.position.get(0), aux.get(1) - this.position.get(1));

				pos = getShapeIndex(nueva);					
									
				if(shape[3][pos] == HIT_VALUE) {
					dev = false;
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
	 */
	public boolean isHit(Coordinate c) {
		boolean dev = false;
		Orientation or = this.orientation;
		boolean encontrado = false;
		int pos = 0;
		
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
			Coordinate relativa = new Coordinate(c.get(0) - this.position.get(0), c.get(1) - this.position.get(1));
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
