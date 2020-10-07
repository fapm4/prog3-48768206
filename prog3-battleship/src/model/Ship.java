package model;

import java.util.HashSet;
import java.util.Set;

public class Ship {
	private static int BOUNDING_SQUARE_SIZE = 5;
	private static int HIT_VALUE = -1;
	private static int CRAFT_VALUE = 1;
	private char symbol;
	private String name;
	Orientation orientation;
	Coordinate position;
	private int shape[][] = new int[][] {
        { 0, 0, 0, 0, 0,               // NORTH    ·····
          0, 0, 1, 0, 0,               //          ··#··
          0, 0, 1, 0, 0,               //          ··#··
          0, 0, 1, 0, 0,               //          ..#..
          0, 0, 0, 0, 0},              //          ·····

        { 0, 0, 0, 0, 0,               // EAST     ·····
          0, 0, 0, 0, 0,               //          ·····
          0, 1, 1, 1, 0,               //          ·###·
          0, 0, 0, 0, 0,               //          ·····
          0, 0, 0, 0, 0},              //          ·····

        { 0, 0, 0, 0, 0,               // SOUTH    ·····
          0, 0, 1, 0, 0,               //          ··#··
          0, 0, 1, 0, 0,               //          ··#··
          0, 0, 1, 0, 0,               //          ..#..
          0, 0, 0, 0, 0},              //          ·····

        { 0, 0, 0, 0, 0,               // WEST     ·····
          0, 0, 0, 0, 0,               //          ·····
          0, 1, 1, 1, 0,               //          ·###·
          0, 0, 0, 0, 0,               //          ·····
          0, 0, 0, 0, 0}}; 
	
	public Ship(Orientation o, char s, String n) {
		orientation = o;
		symbol = s;
		name = n;
		position = null;
	}
	
	public Coordinate getPosition() {
		return new Coordinate(position);
	}
	
	public void setPosition(Coordinate position) {
		this.position = position;
	}
	
	public String getName() {
		return name;
	}
	
	public Orientation getOrientation() {
		return orientation;
	}
	
	public char getSymbol() {
		return symbol;
	}
	
	public int[][] getShape(){
		return shape;
	}
	
	
	public int getShapeIndex(Coordinate c) {
		return (c.get(1) * BOUNDING_SQUARE_SIZE) + c.get(0);
	}
	
	public Set<Coordinate> getAbsolutePositions(Coordinate position){
		Set<Coordinate> positions = new HashSet<Coordinate>();
		int posTablero = getShapeIndex(position);
		
		
		
		return positions;
	}
	
	/*public String toString() {
		
	}*/
}
