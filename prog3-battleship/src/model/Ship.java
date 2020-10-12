package model;

import java.util.HashSet;
import java.util.Set;

// TODO: Auto-generated Javadoc
/**
 * The Class Ship.
 */
public class Ship {
	
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
	private int shape[][] = new int[][] {
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
          0, 0, 1, 0, 0,               //          ..#.. 11
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
	public Ship(Orientation o, char s, String n) {
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
			return new Coordinate(position);
		}
	}
	
	/**
	 * Sets the position.
	 *
	 * @param position the new position
	 */
	public void setPosition(Coordinate position) {
		this.position = position;
		
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
		int pos;
		
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
		Coordinate aux = null;
		int i = 1;
		for(Coordinate busca: posAbsolutas) {
			if(busca.equals(c)) {
				dev = true;
				aux = busca;
				break;
			}
			else {
				i++;
			}
		}
		
		if(dev == true) {
			int pos;
			Coordinate nueva = null;
			switch(or) {
			case NORTH:
				nueva = new Coordinate((aux.get(0) - 2), (aux.get(1) - i));
				pos = getShapeIndex(nueva) - 1;
				if(shape[0][pos] == HIT_VALUE) {
					dev = false;
				}
				else {
					shape[0][pos] = HIT_VALUE;
				}
				break;
				
			case SOUTH:
				nueva = new Coordinate((aux.get(0) - 2), (aux.get(1) - i));
				pos = getShapeIndex(nueva) - 1;
				if(shape[2][pos] == HIT_VALUE) {
					dev = false;
				}
				else {
					shape[2][pos] = HIT_VALUE;
				}
				break;
				
			case EAST:
				nueva = new Coordinate((aux.get(0) - i), (aux.get(1) - 2));
				pos = getShapeIndex(nueva);
				if(shape[1][pos] == HIT_VALUE) {
					dev = false;
				}
				else {
					shape[1][pos] = HIT_VALUE;
				}
				break;
				
			case WEST:
				nueva = new Coordinate((aux.get(0) - i), (aux.get(1) - 2));
				pos = getShapeIndex(nueva);
				if(shape[3][pos] == HIT_VALUE) {
					dev = false;
				}
				else {
					shape[3][pos] = HIT_VALUE;
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
			if(shape[0][7] == HIT_VALUE && shape[0][12] == HIT_VALUE && shape[0][17] == HIT_VALUE) {
				dev = true;
			}
			break;
			
		case SOUTH:
			if(shape[2][7] == HIT_VALUE && shape[2][12] == HIT_VALUE && shape[2][17] == HIT_VALUE) {
				dev = true;
			}
			break;
			
		case EAST:
			if(shape[1][11] == HIT_VALUE && shape[1][12] == HIT_VALUE && shape[1][13] == HIT_VALUE) {
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
		
		if(hit(c) == false) {
			return true;
		}
		else {
			return false;
		}
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
		
		if(or.equals(Orientation.NORTH) | or.equals(Orientation.SOUTH)){
			for(int i = 0;i < 5;i++) {
				sb.append("|");
				for(int j = 0;j < 5;j++) {
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
				sb.append("|");
				sb.append("\n");
			}
		}
		
		else if(or.equals(Orientation.EAST) | or.equals(Orientation.WEST)) {
			for(int i = 0;i < 5;i++) {
				sb.append("|");
				for(int j = 0;j < 5;j++) {
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
				sb.append("|");
				sb.append("\n");
			}
		}
		
		sb.append(" -----");
		return sb.toString();
	}
}
