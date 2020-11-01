package model.ship;

import java.util.HashSet;
import java.util.Set;

import model.Coordinate;
import model.CoordinateFactory;

public class Coordinate2D extends Coordinate{

	public Coordinate2D(int x, int y) {
		super(2);
		set(0, x);
		set(1, y);
	}
	
	public Coordinate2D(Coordinate2D c){
		super(2);
		
		for(int i = 0;i < 2;i++) {
			set(i, c.components[i]);
		}
	}
	
	
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
	

	public Coordinate copy() {
		Coordinate2D toReturn = new Coordinate2D(this);

		return toReturn;
	}
	

	public Set<Coordinate> adjacentCoordinates(){
		Set<Coordinate> nuevo = new HashSet<Coordinate>();
		
		int x = this.get(0);
		int y = this.get(1);
		Coordinate coord = null;
		
		for(int i = x - 1;i< x + 2;i++) {
			for(int j = y - 1;j < y + 2;j++) {
				coord = CoordinateFactory.createCoordinate(i, j);
				nuevo.add(coord);
			}
		}
		
		nuevo.remove(new Coordinate2D(x, y));
		return nuevo;
	}
}
