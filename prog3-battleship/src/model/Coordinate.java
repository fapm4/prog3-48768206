package model;

import java.util.Arrays;
import java.util.Set;

import model.aircraft.Coordinate3D;
import model.ship.Coordinate2D;



public abstract class Coordinate{

	protected int [] components;
	
	
	protected Coordinate(int dim){
		 components = new int[dim];
	}
	

	protected Coordinate(Coordinate c) {
		
		if(c instanceof Coordinate2D) {
			components = new int[2];
			for(int i = 0; i < components.length; i++) {
				components[i] = c.components[i];
			}
		}
		
		else if(c instanceof Coordinate3D) {
			components = new int[3];
			for(int i = 0; i < components.length; i++) {
				components[i] = c.components[i];
			}
		}
	}
	

	public void set(int component, int value){
		
		boolean exp = false;
		
		if(this instanceof Coordinate2D) {
			if(component >= 0 && component < 2) {
				components[component] = value;
			}
			else {
				exp = true;
			}
		}
		
		else if(this instanceof Coordinate3D) {
			if(component >= 0 && component < 3) {
				components[component] = value;
			}
			else {
				exp = true;
			}
		}
		
		if(exp) {
			throw new IllegalArgumentException();
		}
	}
	

	public int get(int component){
		
		int dev = -1;
		boolean exp = false;
		
		if(this instanceof Coordinate2D) {
			if(component >= 0 && component < 2) {
				dev = components[component];
			}
			else {
				exp = true;
			}
		}
		
		else if(this instanceof Coordinate3D) {
			if(component >= 0 && component < 3) {
				dev = components[component];
			}
			else {
				exp = true;
			}
		}
		
		if(exp) {
			throw new IllegalArgumentException();
		}
		
		return dev;
	}
	
	
	public Coordinate add(Coordinate c){
		
		if(c == null) {
			throw new NullPointerException();
		}
		
		Coordinate toReturn = null;
		Coordinate2D caso2D = new Coordinate2D(0, 0);
		Coordinate3D caso3D = new Coordinate3D(0, 0, 0);
		
		if(this instanceof Coordinate2D && c instanceof Coordinate2D) {
			for(int i = 0; i < components.length;i++) {
				caso2D.set(i, components[i] + c.components[i]);
			}
			
			toReturn = caso2D;
		}
		
		if(this instanceof Coordinate3D && c instanceof Coordinate3D) {
			for(int i = 0; i < components.length;i++) {
				caso3D.set(i, components[i] + c.components[i]);
			}
			
			toReturn = caso3D;
		}
		
		if(this instanceof Coordinate2D && c instanceof Coordinate3D){
			for(int i = 0; i < 2;i++) {
				caso2D.set(i, this.components[i] + c.components[i]);
			}
		
			toReturn = caso2D;
		}
		
		if(this instanceof Coordinate3D && c instanceof Coordinate2D){
			for(int i = 0; i < 2;i++) {
				caso3D.set(i, components[i] + c.components[i]);
			}
			
			caso3D.set(2, components[2]);
			toReturn = caso3D;
		}
		
		return toReturn;
	}

	
	public Coordinate subtract(Coordinate c){
		
		if(c == null) {
			throw new NullPointerException();
		}
		
		Coordinate toReturn = null;
		Coordinate2D caso2D = new Coordinate2D(0, 0);
		Coordinate3D caso3D = new Coordinate3D(0, 0, 0);
		
		if(this instanceof Coordinate2D && c instanceof Coordinate2D) {
			for(int i = 0; i < components.length;i++) {
				caso2D.set(i, components[i] - c.components[i]);
			}
			
			toReturn = caso2D;
		}
		
		if(this instanceof Coordinate3D && c instanceof Coordinate3D) {
			for(int i = 0; i < components.length;i++) {
				caso3D.set(i, components[i] - c.components[i]);
			}
			
			toReturn = caso3D;
		}
		
		if(this instanceof Coordinate2D && c instanceof Coordinate3D){
			for(int i = 0; i < 2;i++) {
				caso2D.set(i, components[i] - c.components[i]);
			}
			
			toReturn = caso2D;
		}
		
		if(this instanceof Coordinate3D && c instanceof Coordinate2D){
			for(int i = 0; i < 2;i++) {
				caso3D.set(i, components[i] - c.components[i]);
			}
			
			caso3D.set(2, components[2]);
			toReturn = caso3D;
		}
		
		return toReturn;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(components);
		return result;
	}
	
	
	public boolean equals(Object obj) {
		
		if(obj == null) {
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

	
	public abstract Coordinate copy();
	public abstract Set<Coordinate> adjacentCoordinates();
	
}
