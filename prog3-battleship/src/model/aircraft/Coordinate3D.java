package model.aircraft;

import java.util.HashSet;
import java.util.Set;

import model.Coordinate;
import model.CoordinateFactory;

public class Coordinate3D extends Coordinate{
	public Coordinate3D(int x, int y, int z) {
		super(3);
		
		try {
			set(0, x);
			set(1, y);
			set(2, z);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Coordinate3D(Coordinate3D c){
		super(3);
		for(int i = 0;i < 3;i++) {
			set(i, c.components[i]);
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
	public Coordinate copy(){
		return new Coordinate3D(this.get(0), this.get(1), this.get(2));
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
		int z = this.get(2);
		Coordinate coord = null;
		
		for(int i = x - 1;i< x + 2;i++) {
			for(int j = y - 1;j < y + 2;j++) {
				for(int k = z - 1;k < z + 2;k++) {
					int coords[] = {i, j, k};
					coord = CoordinateFactory.createCoordinate(coords);
					nuevo.add(coord);	
				}
			}
		}
		
		nuevo.remove(new Coordinate3D(x, y, z));
		return nuevo;
	}
}
