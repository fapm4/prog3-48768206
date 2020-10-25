package model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import model.aircraft.Coordinate3D;
import model.ship.Coordinate2D;

// TODO: Auto-generated Javadoc
/**
 * The Class Coordinate.
 *
 * @author Francisco Alejandro Pérez Meneses - 48768206H
 */


public abstract class Coordinate{

	
	/** The components. */
	protected int [] components;


	
	/**
	 * Instantiates a new coordinate.
	 *
	 * @param x the x
	 * @param y the y
	 */
	
	protected Coordinate(int dim){
		 components = new int[dim];
	}
	

	/**
	 * Instantiates a new coordinate.
	 *
	 * @param c the c
	 */
	protected Coordinate(Coordinate c) {
		
		if(c.components.length == 2) {
			components = new int[2];
			for(int i = 0; i < components.length; i++) {
				components[i] = c.components[i];
			}
		}
		
		else if(c.components.length == 3) {
			components = new int[3];
			for(int i = 0; i < components.length; i++) {
				components[i] = c.components[i];
			}
		}
	}
	

	/**
	 * Sets the.
	 *
	 * @param component the component
	 * @param value the value
	 */
	public void set(int component, int value) throws Exception{
		
		if(components.length == 2) {
			if(component >= 0 && component < 2) {
				components[component] = value;
			}
			else {
				throw new IllegalArgumentException();
			}
		}
		
		else if(components.length == 3) {
			if(component >= 0 && component < 3) {
				components[component] = value;
			}
			else {
				throw new IllegalArgumentException();
			}
		}
	}
	

	/**
	 * Gets the.
	 *
	 * @param component the component
	 * @return the int
	 */
	public final int get(int component) {
		
		int dev = -1;
		if(components.length == 2) {
			if(component >= 0 && component < 2) {
				dev = components[component];
			}
			else {
				throw new IllegalArgumentException();
			}
		}
		
		else if(components.length == 3) {
			if(component >= 0 && component < 3) {
				dev = components[component];
			}
			else {
				throw new IllegalArgumentException();
			}
		}
		
		return dev;
	}
	
	
	/**
	 * Adds the.
	 *
	 * @param c the c
	 * @return the coordinate
	 * @throws Exception 
	 */
	
	public Coordinate add(Coordinate c) throws Exception {
		
		if(c == null) {
			throw new NullPointerException();
		}
		Coordinate toReturn = null;
		Coordinate2D caso2D = new Coordinate2D(0, 0);
		Coordinate3D caso3D = new Coordinate3D(0, 0, 0);
		
		if(components.length == 2 && c.components.length == 2) {
			for(int i = 0; i < components.length;i++) {
				try {
					caso2D.set(i, components[i] + c.components[i]);
				} catch (Exception e) {
					e.getMessage();
				}
			}
			
			toReturn = caso2D;
		}
		
		if(components.length == 3 && c.components.length == 3) {
			for(int i = 0; i < components.length;i++) {
				try {
					caso3D.set(i, components[i] + c.components[i]);
				} catch (Exception e) {
					e.getMessage();
				}
			}
			
			toReturn = caso3D;
		}
		
		if((components.length == 2 && c.components.length == 3) | (components.length == 3 && c.components.length == 2)){
			for(int i = 0; i < 2;i++) {
				try {
					caso3D.set(i, components[i] + c.components[i]);
				} catch (Exception e) {
					e.getMessage();
				}
			}
			
			caso3D.set(2, components[2]);
			toReturn = caso3D;
		}
		
		return toReturn;
	}
	
	/**
	 * Subtract.
	 *
	 * @param c the c
	 * @return the coordinate
	 * @throws Exception 
	 */
	public Coordinate subtract(Coordinate c) throws Exception {
		
		if(c == null) {
			throw new NullPointerException();
		}
		
		Coordinate toReturn = null;
		Coordinate2D caso2D = new Coordinate2D(0, 0);
		Coordinate3D caso3D = new Coordinate3D(0, 0, 0);
		
		if(components.length == 2 && c.components.length == 2) {
			for(int i = 0; i < components.length;i++) {
				try {
					caso2D.set(i, components[i] - c.components[i]);
				} catch (Exception e) {
					e.getMessage();
				}
			}
			
			toReturn = caso2D;
		}
		
		if(components.length == 3 && c.components.length == 3) {
			for(int i = 0; i < components.length;i++) {
				try {
					caso3D.set(i, components[i] - c.components[i]);
				} catch (Exception e) {
					e.getMessage();
				}
			}
			
			toReturn = caso3D;
		}
		
		if((components.length == 2 && c.components.length == 3) | (components.length == 3 && c.components.length == 2)){
			for(int i = 0; i < 2;i++) {
				try {
					caso3D.set(i, components[i] - c.components[i]);
				} catch (Exception e) {
					e.getMessage();
				}
			}
			
			caso3D.set(2, components[2]);
			toReturn = caso3D;
		}
		
		return toReturn;
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

	public abstract Coordinate copy() throws Exception;
	public abstract Set<Coordinate> adjacentCoordinates() throws Exception;
	
}
