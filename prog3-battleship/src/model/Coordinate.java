package model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Collections;

/**
* @author Francisco Alejandro Pérez Meneses - 48768206H
*/

// TODO: Auto-generated Javadoc
/**
 * The Class Coordinate.
 */
public class Coordinate {

	
	/** The components. */
	private int [] components;


	
	/**
	 * Instantiates a new coordinate.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public Coordinate(int x, int y) {
		components = new int[2];
		components[0] = x;
		components[1] = y;
	}
	

	
	/**
	 * Instantiates a new coordinate.
	 *
	 * @param c the c
	 */
	public Coordinate(Coordinate c) {
		components = new int[2];
		for(int i = 0; i < components.length; i++) {
			components[i] = c.components[i];
		}
	}
	

	/**
	 * Sets the.
	 *
	 * @param component the component
	 * @param value the value
	 */
	public void set(int component, int value) {
		if(component >= 0 && component < components.length) {
			components[component] = value;
		}
		else {
			System.out.println("Error in Coordinate.set, component " + component + " is out of range");
		}
	}
	

	/**
	 * Gets the.
	 *
	 * @param component the component
	 * @return the int
	 */
	public final int get(int component) {
		if(component >= 0 && component < components.length) {
			return components[component];
		}
		else {
			System.out.println("Error in Coordinate.get, component " + component + " is out of range");
			return -1;
		}
	}
	
	
	/**
	 * Adds the.
	 *
	 * @param c the c
	 * @return the coordinate
	 */
	public Coordinate add(Coordinate c) {
		Coordinate nuevo = new Coordinate(0, 0);
		for(int i = 0 ; i < components.length; i++) {
			nuevo.set(i, this.get(i) + c.get(i));
		}		
		return nuevo;
	}
	
	/**
	 * Substract.
	 *
	 * @param c the c
	 * @return the coordinate
	 */
	public Coordinate substract(Coordinate c) {
		Coordinate nuevo = new Coordinate(0, 0);
		for(int i = 0 ; i < components.length; i++) {
			nuevo.set(i, this.get(i) - c.get(i));
		}		
		return nuevo;
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
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(components);
		return result;
	}
	

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	public boolean equals(Object obj) {
		
		if(obj == null) {
			return false;
		}
		
		if(this == null) {
			return false;
		}
		
		if(!(obj instanceof Coordinate)) {
			return false;
		}
		
		Coordinate other = (Coordinate) obj;
		
		if(!Arrays.equals(components, other.components)) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public Coordinate copy(Coordinate copia) {
		return new Coordinate(copia.get(0), copia.get(1));
	}
	
	public Set<Coordinate> adjacentCoordinates(){
		Set<Coordinate> nuevo = new HashSet<Coordinate>();
		
		int xIn = this.get(0);
		int yIn = this.get(1);
		
		for(int i = xIn - 1;i< xIn + 2;i++) {
			for(int j = yIn - 1;j < yIn + 2;j++) {
				if(i >= 0 && j >= 0) {
					nuevo.add(new Coordinate(i, j));
				}
			}
		}
		
		nuevo.remove(new Coordinate(xIn, yIn));
		return nuevo;
	}

}
