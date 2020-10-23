package model;

import model.aircraft.Coordinate3D;
import model.ship.Coordinate2D;

public class CoordinateFactory {
	public static Coordinate createCoordinate(int coords[]) throws Exception{
		
		int p1 = 0, p2 = 0, p3 = 0;
		Coordinate normal = null;
		
		if(coords.length < 2 | coords.length > 3) {
			throw new IllegalArgumentException();
		}
		
		else {
			if(coords.length == 2) {
				p1 = coords[0];
				p2 = coords[1];
				Coordinate2D caso2D = new Coordinate2D(p1, p2);
				
				normal = caso2D;
			}
			
			if(coords.length == 3) {
				p1 = coords[0];
				p2 = coords[1];
				p3 = coords[2];
				Coordinate3D caso3D = new Coordinate3D(p1, p2, p3);
		
				normal = caso3D;
			}
		}
		
		return normal;
	}
}
