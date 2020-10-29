package model.ship;

import java.util.HashSet;
import java.util.Set;

import model.Coordinate;
import model.CoordinateFactory;

public class Coordinate2D extends Coordinate{

	public Coordinate2D(int x, int y) {
		super(2);
		try {
			set(0, x);
			set(1, y);
		}
		catch(Exception e) {
			e.getMessage();
		}
	}
	
	public Coordinate2D(Coordinate2D c){
		super(2);
		
		for(int i = 0;i < 2;i++) {
			try {
				set(i, c.components[i]);
			}
			catch(Exception e) {
				
			}
		}
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		for(int i = 0; i < components.length; i++) {
			sb.append(components[i]);
			if(i < components.length - 1) {
				sb.append(", ");
			}
		}
		
		sb.append(")");
		return sb.toString();
	}
	
	/**
	 * Copy.
	 *
	 * @return the coordinate
	 * @throws Exception 
	 */
	public Coordinate copy() {
		Coordinate2D toReturn = null;
		try {
			toReturn = new Coordinate2D(this);
			return toReturn;
		}
		catch(Exception e){
			e.getMessage();
		}
		return toReturn;
	}
	
	/**
	 * Adjacent coordinates.
	 *
	 * @return the sets the
	 * @throws Exception 
	 */
	
	public Set<Coordinate> adjacentCoordinates(){
		Set<Coordinate> nuevo = new HashSet<Coordinate>();
		
		int x = this.get(0);
		int y = this.get(1);
		Coordinate coord = null;
		
		for(int i = x - 1;i< x + 2;i++) {
			for(int j = y - 1;j < y + 2;j++) {
				int coords[] = {i, j};
				
				try {
					coord = CoordinateFactory.createCoordinate(coords);
					nuevo.add(coord);
				}
				catch(Exception e) {
					e.getMessage();
				}
			}
		}
		
		nuevo.remove(new Coordinate2D(x, y));
		return nuevo;
	}

}
