package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.aircraft.Bomber;
import model.aircraft.Coordinate3D;
import model.aircraft.Fighter;
import model.aircraft.Transport;
import model.exceptions.CoordinateAlreadyHitException;
import model.ship.Battleship;
import model.ship.Carrier;
import model.ship.Coordinate2D;
import model.ship.Cruiser;
import model.ship.Destroyer;

public abstract class Craft {
	
	private static final int BOUNDING_SQUARE_SIZE = 5;
	
	private static final int HIT_VALUE = -1;
	
	private static final int CRAFT_VALUE = 1;
	
	private char symbol;
	
	private String name;
	
	private Orientation orientation;
	
	private Coordinate position;
	
	protected int shape[][];
	
	public Craft(Orientation o, char s, String n) {
		orientation = o;
		symbol = s;
		name = n;
		position = null;		
	}


	public Coordinate getPosition() {
		if(position == null) {
			return null;
		}
		else {
			return this.position;
		}
	}

	
	public void setPosition(Coordinate position){
		
		this.position = position.copy();
		
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


	public int[][] getShape() {
		return shape;
	}


	public int getShapeIndex(Coordinate c){
		if(c == null) {
			throw new NullPointerException();
		}
		
		return (c.get(1) * BOUNDING_SQUARE_SIZE) + c.get(0);
	}

	
	private Set<Coordinate> getAbsolutePositionsTransport(Coordinate position, Orientation or){
		Set<Coordinate> positionsToReturn = new HashSet<Coordinate>();
		
		switch(or) {
			case NORTH:
			
				positionsToReturn.add(new Coordinate2D(position.get(0) + 2, position.get(1)));  	// |
				positionsToReturn.add(new Coordinate2D(position.get(0) + 2, position.get(1) + 1));  // |
				
				positionsToReturn.add(new Coordinate2D(position.get(0) + 1, position.get(1) + 2));
				positionsToReturn.add(new Coordinate2D(position.get(0) + 2, position.get(1) + 2)); // |||
				positionsToReturn.add(new Coordinate2D(position.get(0) + 3, position.get(1) + 2));
				
				positionsToReturn.add(new Coordinate2D(position.get(0), position.get(1) + 3));
				positionsToReturn.add(new Coordinate2D(position.get(0) + 2, position.get(1) + 3));// | | |
				positionsToReturn.add(new Coordinate2D(position.get(0) + 4, position.get(1) + 3));
				
				positionsToReturn.add(new Coordinate2D(position.get(0) + 2, position.get(1) + 4)); //  |
	
				break;
			
			case SOUTH:
				
				positionsToReturn.add(new Coordinate2D(position.get(0) + 2, position.get(1)));//		|
				
				positionsToReturn.add(new Coordinate2D(position.get(0), position.get(1) + 1));
				positionsToReturn.add(new Coordinate2D(position.get(0) + 2, position.get(1) + 1));//  | | |
				positionsToReturn.add(new Coordinate2D(position.get(0) + 4, position.get(1) + 1));
				
				positionsToReturn.add(new Coordinate2D(position.get(0) + 1, position.get(1) + 2));
				positionsToReturn.add(new Coordinate2D(position.get(0) + 2, position.get(1) + 2));//   |||
				positionsToReturn.add(new Coordinate2D(position.get(0) + 3, position.get(1) + 2));
				
				positionsToReturn.add(new Coordinate2D(position.get(0) + 2, position.get(1) + 3));//    |
				positionsToReturn.add(new Coordinate2D(position.get(0) + 2, position.get(1) + 4));//    |

				break;
				
			case EAST:
				positionsToReturn.add(new Coordinate2D(position.get(0), position.get(1) + 2));
				
				positionsToReturn.add(new Coordinate2D(position.get(0) + 1, position.get(1)));
				positionsToReturn.add(new Coordinate2D(position.get(0) + 1, position.get(1) + 2));
				positionsToReturn.add(new Coordinate2D(position.get(0) + 1, position.get(1) + 4));
				
				positionsToReturn.add(new Coordinate2D(position.get(0) + 2, position.get(1) + 1));
				positionsToReturn.add(new Coordinate2D(position.get(0) + 2, position.get(1) + 2));
				positionsToReturn.add(new Coordinate2D(position.get(0) + 2, position.get(1) + 3));
				
				positionsToReturn.add(new Coordinate2D(position.get(0) + 3, position.get(1) + 2));
				positionsToReturn.add(new Coordinate2D(position.get(0) + 4, position.get(1) + 2));
				break;
				
			case WEST:
				positionsToReturn.add(new Coordinate2D(position.get(0), position.get(1) + 2));
				positionsToReturn.add(new Coordinate2D(position.get(0) + 1, position.get(1) + 2));
				
				positionsToReturn.add(new Coordinate2D(position.get(0) + 2, position.get(1) + 1));
				positionsToReturn.add(new Coordinate2D(position.get(0) + 2, position.get(1) + 2));
				positionsToReturn.add(new Coordinate2D(position.get(0) + 2, position.get(1) + 3));
				
				positionsToReturn.add(new Coordinate2D(position.get(0) + 3, position.get(1)));
				positionsToReturn.add(new Coordinate2D(position.get(0) + 3, position.get(1) + 2));
				positionsToReturn.add(new Coordinate2D(position.get(0) + 3, position.get(1) + 4));

				positionsToReturn.add(new Coordinate2D(position.get(0) + 4, position.get(1) + 2));
				break;
		}
		
		return positionsToReturn;
		
	}
	
	
	private Set<Coordinate> getAbsolutePositionsFighter(Coordinate position, Orientation or){
		Set<Coordinate> positionsToReturn = new HashSet<Coordinate>();
		
		switch(or) {
			case NORTH:
				positionsToReturn.add(new Coordinate2D(position.get(0) + 2, position.get(1) + 1));
				
				positionsToReturn.add(new Coordinate2D(position.get(0) + 1, position.get(1) + 2));
				positionsToReturn.add(new Coordinate2D(position.get(0) + 2, position.get(1) + 2));
				positionsToReturn.add(new Coordinate2D(position.get(0) + 3, position.get(1) + 2));
				
				positionsToReturn.add(new Coordinate2D(position.get(0) + 2, position.get(1) + 3));
				positionsToReturn.add(new Coordinate2D(position.get(0) + 2, position.get(1) + 4));
				break;
				
			case SOUTH:
				positionsToReturn.add(new Coordinate2D(position.get(0) + 2, position.get(1)));
				positionsToReturn.add(new Coordinate2D(position.get(0) + 2, position.get(1) + 1));
				
				positionsToReturn.add(new Coordinate2D(position.get(0) + 1, position.get(1) + 2));
				positionsToReturn.add(new Coordinate2D(position.get(0) + 2, position.get(1) + 2));
				positionsToReturn.add(new Coordinate2D(position.get(0) + 3, position.get(1) + 2));
			
				positionsToReturn.add(new Coordinate2D(position.get(0) + 2, position.get(1) + 3));
				break;
				
			case EAST:
				positionsToReturn.add(new Coordinate2D(position.get(0), position.get(1) + 2));
				positionsToReturn.add(new Coordinate2D(position.get(0) + 1, position.get(1) + 2));
				
				positionsToReturn.add(new Coordinate2D(position.get(0) + 2, position.get(1) + 1));
				positionsToReturn.add(new Coordinate2D(position.get(0) + 2, position.get(1) + 2));
				positionsToReturn.add(new Coordinate2D(position.get(0) + 2, position.get(1) + 3));
				
				positionsToReturn.add(new Coordinate2D(position.get(0) + 3, position.get(1) + 2));
				break;
				
			case WEST:
				positionsToReturn.add(new Coordinate2D(position.get(0) + 1, position.get(1) + 2));
				
				positionsToReturn.add(new Coordinate2D(position.get(0) + 2, position.get(1) + 1));
				positionsToReturn.add(new Coordinate2D(position.get(0) + 2, position.get(1) + 2));
				positionsToReturn.add(new Coordinate2D(position.get(0) + 2, position.get(1) + 3));
				
				positionsToReturn.add(new Coordinate2D(position.get(0) + 3, position.get(1) + 2));
				positionsToReturn.add(new Coordinate2D(position.get(0) + 4, position.get(1) + 2));
				break;
		}
		return positionsToReturn;
	}
	
	
	private Set<Coordinate> getAbsolutePositionsBomber(Coordinate position, Orientation or){
		Set<Coordinate> positionsToReturn = new HashSet<Coordinate>();
		
		switch(or) {
			case NORTH:
				positionsToReturn.add(new Coordinate2D(position.get(0) + 2, position.get(1) + 1));
				
				positionsToReturn.add(new Coordinate2D(position.get(0), position.get(1) + 2));
				positionsToReturn.add(new Coordinate2D(position.get(0) + 1, position.get(1) + 2));
				positionsToReturn.add(new Coordinate2D(position.get(0) + 2, position.get(1) + 2));
				positionsToReturn.add(new Coordinate2D(position.get(0) + 3, position.get(1) + 2));
				positionsToReturn.add(new Coordinate2D(position.get(0) + 4, position.get(1) + 2));
				
				positionsToReturn.add(new Coordinate2D(position.get(0), position.get(1) + 3));
				positionsToReturn.add(new Coordinate2D(position.get(0) + 2, position.get(1) + 3));
				positionsToReturn.add(new Coordinate2D(position.get(0) + 4, position.get(1) + 3));
				
				positionsToReturn.add(new Coordinate2D(position.get(0) + 2, position.get(1) + 4));
				break;
				
			case SOUTH:
				positionsToReturn.add(new Coordinate2D(position.get(0) + 2, position.get(1)));
				
				positionsToReturn.add(new Coordinate2D(position.get(0), position.get(1) + 1));
				positionsToReturn.add(new Coordinate2D(position.get(0) + 2, position.get(1) + 1));
				positionsToReturn.add(new Coordinate2D(position.get(0) + 4, position.get(1) + 1));
				
				positionsToReturn.add(new Coordinate2D(position.get(0), position.get(1) + 2));
				positionsToReturn.add(new Coordinate2D(position.get(0) + 1, position.get(1) + 2));
				positionsToReturn.add(new Coordinate2D(position.get(0) + 2, position.get(1) + 2));
				positionsToReturn.add(new Coordinate2D(position.get(0) + 3, position.get(1) + 2));
				positionsToReturn.add(new Coordinate2D(position.get(0) + 4, position.get(1) + 2));
				
				positionsToReturn.add(new Coordinate2D(position.get(0) + 2, position.get(1) + 3));
				break;
				
			case EAST:
				positionsToReturn.add(new Coordinate2D(position.get(0), position.get(1) + 2));
				
				positionsToReturn.add(new Coordinate2D(position.get(0) + 1, position.get(1)));
				positionsToReturn.add(new Coordinate2D(position.get(0) + 1, position.get(1) + 2));
				positionsToReturn.add(new Coordinate2D(position.get(0) + 1, position.get(1) + 4));
				
				positionsToReturn.add(new Coordinate2D(position.get(0) + 2, position.get(1)));
				positionsToReturn.add(new Coordinate2D(position.get(0) + 2, position.get(1) + 1));
				positionsToReturn.add(new Coordinate2D(position.get(0) + 2, position.get(1) + 2));
				positionsToReturn.add(new Coordinate2D(position.get(0) + 2, position.get(1) + 3));
				positionsToReturn.add(new Coordinate2D(position.get(0) + 2, position.get(1) + 4));
				
				positionsToReturn.add(new Coordinate2D(position.get(0) + 3, position.get(1) + 2));
				break;
				
			case WEST:
				positionsToReturn.add(new Coordinate2D(position.get(0) + 1, position.get(1) + 2));
				
				positionsToReturn.add(new Coordinate2D(position.get(0) + 2, position.get(1)));
				positionsToReturn.add(new Coordinate2D(position.get(0) + 2, position.get(1) + 1));
				positionsToReturn.add(new Coordinate2D(position.get(0) + 2, position.get(1) + 2));
				positionsToReturn.add(new Coordinate2D(position.get(0) + 2, position.get(1) + 3));
				positionsToReturn.add(new Coordinate2D(position.get(0) + 2, position.get(1) + 4));
				
				positionsToReturn.add(new Coordinate2D(position.get(0) + 3, position.get(1)));
				positionsToReturn.add(new Coordinate2D(position.get(0) + 3, position.get(1) + 2));
				positionsToReturn.add(new Coordinate2D(position.get(0) + 3, position.get(1) + 4));
				
				positionsToReturn.add(new Coordinate2D(position.get(0) + 4, position.get(1) + 2));
				break;
		}
		
		return positionsToReturn;
	}
	
	
	public Set<Coordinate> getAbsolutePositions(Coordinate position){
	
		Set<Coordinate> positionsToReturn = new HashSet<Coordinate>();
		Orientation or = this.orientation;
		Coordinate nuevo = null;
		int cont = -1;
		int iterador = 1;
		
		if(this instanceof Destroyer) {
			cont = 2;
		}
		
		if(this instanceof Cruiser) {
			cont = 3;
		}
		
		if(this instanceof Carrier) {
			cont = 5;
		}
		
		if(this instanceof Battleship) {
			cont = 5;
			iterador = 2;
		}
		
		if(this instanceof Transport) {
			positionsToReturn = getAbsolutePositionsTransport(position, or);
		}
		
		if(this instanceof Fighter) {
			positionsToReturn = getAbsolutePositionsFighter(position, or);
		}
		
		if(this instanceof Bomber) {
			positionsToReturn = getAbsolutePositionsBomber(position, or);
		}
		
		if(this instanceof Destroyer | this instanceof Carrier | this instanceof Battleship | this instanceof Cruiser) {
			
			if(or.equals(Orientation.NORTH) | or.equals(Orientation.SOUTH)) {
				for(int i = iterador;i <= cont;i++) {
					if(position instanceof Coordinate2D) {
						
						nuevo = new Coordinate2D(position.get(0) + 2, position.get(1) + i);
						positionsToReturn.add(nuevo);
					}
					else if(position instanceof Coordinate3D) {
						
						nuevo = new Coordinate3D(position.get(0) + 2, position.get(1) + i, position.get(2));
						positionsToReturn.add(nuevo);
					}
					
				}
			}
			
			
			else if(or.equals(Orientation.EAST) | or.equals(Orientation.WEST)){
				for(int i = iterador;i <= cont;i++) {
					if(position instanceof Coordinate2D) {
						
						nuevo = new Coordinate2D(position.get(0) + i, position.get(1) + 2);
						positionsToReturn.add(nuevo);
					}
					else if(position instanceof Coordinate3D) {
						
						nuevo = new Coordinate3D(position.get(0) + i, position.get(1) + 2, position.get(2));
						positionsToReturn.add(nuevo);
					}
					
				}
			}	
		}
		
		return positionsToReturn;
	}
	
	
	public Set<Coordinate> getAbsolutePositions(){
		return getAbsolutePositions(this.position);
	}

	
	public boolean hit(Coordinate c) throws CoordinateAlreadyHitException {
		boolean dev = false;
		
		if(this.getPosition() == null) {
			throw new NullPointerException();
		}
		
		int[][] shapeOf = this.getShape();
		
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
		for(Coordinate busca: posOrdenadas) {
			
			// Si la encuentro salgo del bucle y paso a modificar el shapeOf
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
				
				if(aux instanceof Coordinate2D) {
					nueva = new Coordinate2D(c.get(0) - this.position.get(0), c.get(1) - this.position.get(1));
				}
				
				else if(aux instanceof Coordinate3D) {
					nueva = new Coordinate3D(c.get(0) - this.position.get(0), c.get(1) - this.position.get(1), c.get(2) - this.position.get(2));
				}
	
				pos = getShapeIndex(nueva);		

				if(pos == 27) {
					pos -= 3;
				}
				
				if(shapeOf[0][pos] == HIT_VALUE) {
					throw new CoordinateAlreadyHitException(nueva);
				}
				else {
					shapeOf[0][pos] = HIT_VALUE;
					dev = true;
				}					
				
				break;
				
			case SOUTH:
	
				if(aux instanceof Coordinate2D) {
					nueva = new Coordinate2D(c.get(0) - this.position.get(0), c.get(1) - this.position.get(1));
				}
				
				else if(aux instanceof Coordinate3D) {
					nueva = new Coordinate3D(c.get(0) - this.position.get(0), c.get(1) - this.position.get(1), c.get(2) - this.position.get(2));
				}
	
				pos = getShapeIndex(nueva);		
				
				if(pos == 27) {
					pos -= 3;
				}
				
				if(shapeOf[2][pos] == HIT_VALUE) {
					throw new CoordinateAlreadyHitException(nueva);
				}
				else {
					shapeOf[2][pos] = HIT_VALUE;
					dev = true;
				}					
				
				break;
				
			case EAST:
				
				if(aux instanceof Coordinate2D) {
					nueva = new Coordinate2D(c.get(0) - this.position.get(0), c.get(1) - this.position.get(1));
				}
				
				else if(aux instanceof Coordinate3D) {
					nueva = new Coordinate3D(c.get(0) - this.position.get(0), c.get(1) - this.position.get(1), c.get(2) - this.position.get(2));
				}
	
				pos = getShapeIndex(nueva);	
				
				if(pos == 27) {
					pos -= 3;
				}
			
				if(shapeOf[1][pos] == HIT_VALUE) {
					throw new CoordinateAlreadyHitException(nueva);
				}
				else {
					shapeOf[1][pos] = HIT_VALUE;
					dev = true;
				}					
				
				break;
				
			case WEST:
									
				if(aux instanceof Coordinate2D) {
					nueva = new Coordinate2D(c.get(0) - this.position.get(0), c.get(1) - this.position.get(1));
				}
				
				else if(aux instanceof Coordinate3D) {
					nueva = new Coordinate3D(c.get(0) - this.position.get(0), c.get(1) - this.position.get(1), c.get(2) - this.position.get(2));
				}
	
				pos = getShapeIndex(nueva);			
				
				if(pos == 27) {
					pos -= 3;
				}
				
				if(shapeOf[3][pos] == HIT_VALUE) {
					throw new CoordinateAlreadyHitException(nueva);
				}
				else {
					shapeOf[3][pos] = HIT_VALUE;
					dev = true;
				}					
				
				break;
	
			}
		}
		
		return dev;
	}
	
	
	private boolean compruebaTipoNORTH(Craft nave) {
		
		boolean dev = false;
		int[][] shapeOf = nave.getShape();
		
		// -------------------------------------------------------
		// ------------------ SUBCLASES DE SHIP ------------------
		// -------------------------------------------------------
		
		if(nave instanceof Battleship) {
			if(shapeOf[0][7] == HIT_VALUE && shapeOf[0][12] == HIT_VALUE && shapeOf[0][17] == HIT_VALUE && shapeOf[0][22] == HIT_VALUE) {
				dev = true;			
			}
		}
		
		if(nave instanceof Carrier) {
			if(shapeOf[0][2] == HIT_VALUE && shapeOf[0][7] == HIT_VALUE && shapeOf[0][12] == HIT_VALUE && shapeOf[0][17] == HIT_VALUE && shapeOf[0][22] == HIT_VALUE) {
				dev = true;			
			}
		}
		
		if(nave instanceof Cruiser) {
			if(shapeOf[0][7] == HIT_VALUE && shapeOf[0][12] == HIT_VALUE && shapeOf[0][17] == HIT_VALUE) {
				dev = true;			
			}
		}
		
		if(nave instanceof Destroyer) {
			if(shapeOf[0][7] == HIT_VALUE && shapeOf[0][12] == HIT_VALUE) {
				dev = true;			
			}
		}
		
		// --------------------------------------------------------
		// ------------------ SUBCLASES DE CRAFT ------------------ 
		// --------------------------------------------------------
		
		if(nave instanceof Bomber) {
			if(shapeOf[0][7] == HIT_VALUE && shapeOf[0][10] == HIT_VALUE && shapeOf[0][11] == HIT_VALUE && shapeOf[0][12] == HIT_VALUE
					&& shapeOf[0][13] == HIT_VALUE && shapeOf[0][14] == HIT_VALUE && shapeOf[0][15] == HIT_VALUE && shapeOf[0][17] == HIT_VALUE
					&& shapeOf[0][19] == HIT_VALUE && shapeOf[0][22] == HIT_VALUE) {
				dev = true;
			}
		}
		
		if(nave instanceof Fighter) {
			if(shape[0][7] == HIT_VALUE && shapeOf[0][10] == HIT_VALUE && shapeOf[0][11] == HIT_VALUE && shapeOf[0][12] == HIT_VALUE
					&& shapeOf[0][13] == HIT_VALUE && shapeOf[0][17] == HIT_VALUE && shapeOf[0][22] == HIT_VALUE) {
				dev = true;
			}
		}
		
		if(nave instanceof Transport) {
			if(shapeOf[0][2] == HIT_VALUE && shapeOf[0][7] == HIT_VALUE && shapeOf[0][11] == HIT_VALUE && shapeOf[0][12] == HIT_VALUE
					&& shapeOf[0][13] == HIT_VALUE && shapeOf[0][15] == HIT_VALUE && shapeOf[0][17] == HIT_VALUE && shapeOf[0][19] == HIT_VALUE
					&& shapeOf[0][22] == HIT_VALUE) {
				dev = true;
			}
		}
		return dev;
	}
	
	
	private boolean compruebaTipoSOUTH(Craft nave) {
		
		boolean dev = false;
		int[][] shapeOf = nave.getShape();
		
		// -------------------------------------------------------
		// ------------------ SUBCLASES DE SHIP ------------------
		// -------------------------------------------------------
		
		if(nave instanceof Battleship) {
			if(shapeOf[2][7] == HIT_VALUE && shapeOf[2][12] == HIT_VALUE && shapeOf[2][17] == HIT_VALUE && shapeOf[2][22] == HIT_VALUE) {
				dev = true;			
			}
		}
		
		if(nave instanceof Carrier) {
			if(shapeOf[2][2] == HIT_VALUE && shapeOf[2][7] == HIT_VALUE && shapeOf[2][12] == HIT_VALUE && shapeOf[2][17] == HIT_VALUE && shapeOf[2][22] == HIT_VALUE) {
				dev = true;			
			}
		}
		
		if(nave instanceof Cruiser) {
			if(shapeOf[2][7] == HIT_VALUE && shapeOf[2][12] == HIT_VALUE && shapeOf[2][17] == HIT_VALUE) {
				dev = true;			
			}
		}
		
		if(nave instanceof Destroyer) {
			if(shapeOf[2][7] == HIT_VALUE && shapeOf[2][12] == HIT_VALUE) {
				dev = true;			
			}
		}

		// --------------------------------------------------------
		// ------------------ SUBCLASES DE CRAFT ------------------ 
		// --------------------------------------------------------
		
		if(nave instanceof Bomber) {
			if(shapeOf[2][7] == HIT_VALUE && shapeOf[2][10] == HIT_VALUE && shapeOf[2][11] == HIT_VALUE && shapeOf[2][12] == HIT_VALUE
					&& shapeOf[2][13] == HIT_VALUE && shapeOf[2][14] == HIT_VALUE && shapeOf[2][15] == HIT_VALUE && shapeOf[2][17] == HIT_VALUE
					&& shapeOf[2][19] == HIT_VALUE && shapeOf[2][22] == HIT_VALUE) {
				dev = true;
			}
		}
		
		if(nave instanceof Fighter) {
			if(shape[2][7] == HIT_VALUE && shapeOf[2][10] == HIT_VALUE && shapeOf[2][11] == HIT_VALUE && shapeOf[2][12] == HIT_VALUE
					&& shapeOf[2][13] == HIT_VALUE && shapeOf[2][17] == HIT_VALUE && shapeOf[2][22] == HIT_VALUE) {
				dev = true;
			}
		}
		
		if(nave instanceof Transport) {
			if(shapeOf[2][2] == HIT_VALUE && shapeOf[2][7] == HIT_VALUE && shapeOf[2][11] == HIT_VALUE && shapeOf[2][12] == HIT_VALUE
					&& shapeOf[2][13] == HIT_VALUE && shapeOf[2][15] == HIT_VALUE && shapeOf[2][17] == HIT_VALUE && shapeOf[2][19] == HIT_VALUE
					&& shapeOf[2][22] == HIT_VALUE) {
				dev = true;
			}
		}
		return dev;
	}
	
	
	private boolean compruebaTipoEAST(Craft nave) {
		
		boolean dev = false;
		int[][] shapeOf = nave.getShape();
		
		// -------------------------------------------------------
		// ------------------ SUBCLASES DE SHIP ------------------
		// -------------------------------------------------------
		
		
		if(nave instanceof Battleship) {
			if(shapeOf[1][11] == HIT_VALUE && this.shape[1][12] == HIT_VALUE && this.shape[1][13] == HIT_VALUE && this.shape[1][14] == HIT_VALUE) {
				dev = true;			
			}
		}
		
		if(this instanceof Carrier) {
			if(this.shape[1][10] == HIT_VALUE && this.shape[1][11] == HIT_VALUE && this.shape[1][12] == HIT_VALUE && this.shape[1][13] == HIT_VALUE && shapeOf[1][14] == HIT_VALUE) {
				dev = true;			
			}
		}
		
		if(nave instanceof Cruiser) {
			if(shapeOf[1][11] == HIT_VALUE && shapeOf[1][12] == HIT_VALUE && shapeOf[1][13] == HIT_VALUE) {
				dev = true;			
			}
			
		}
		
		if(nave instanceof Destroyer) {
			if(shapeOf[1][11] == HIT_VALUE && shapeOf[1][12] == HIT_VALUE) {
				dev = true;			
			}
		}

		// --------------------------------------------------------
		// ------------------ SUBCLASES DE CRAFT ------------------ 
		// --------------------------------------------------------
		
		if(nave instanceof Bomber) {
			if(shapeOf[1][1] == HIT_VALUE && shapeOf[1][2] == HIT_VALUE && shapeOf[1][7] == HIT_VALUE && shapeOf[1][10] == HIT_VALUE 
					&& shapeOf[1][11] == HIT_VALUE && shapeOf[1][12] == HIT_VALUE && shapeOf[1][13] == HIT_VALUE && shapeOf[1][17] == HIT_VALUE
					&& shapeOf[1][12] == HIT_VALUE && shapeOf[1][21] == HIT_VALUE && shapeOf[1][22] == HIT_VALUE) {
				dev = true;
			}
		}
		
		if(nave instanceof Fighter) {
			if(shapeOf[1][7] == HIT_VALUE && shapeOf[1][10] == HIT_VALUE && shapeOf[1][11] == HIT_VALUE && shapeOf[1][12] == HIT_VALUE 
					&& shapeOf[1][13] == HIT_VALUE && shapeOf[1][17] == HIT_VALUE) {
				dev = true;
			}
		}
		
		if(nave instanceof Transport) {
			if(shapeOf[1][1] == HIT_VALUE && shapeOf[1][7] == HIT_VALUE && shapeOf[1][10] == HIT_VALUE && shapeOf[1][11] == HIT_VALUE
					&& shapeOf[1][12] == HIT_VALUE && shapeOf[1][13] == HIT_VALUE && shapeOf[1][14] == HIT_VALUE && shapeOf[1][17] == HIT_VALUE
					&& shapeOf[1][21] == HIT_VALUE) {
				dev = true;
			}
		}
		
		return dev;
	}
	
	
	private boolean compruebaTipoWEST(Craft nave) {
		
		boolean dev = false;
		int[][] shapeOf = nave.getShape();
		
		// -------------------------------------------------------
		// ------------------ SUBCLASES DE SHIP ------------------
		// -------------------------------------------------------
		
		if(nave instanceof Battleship) {
			if(shapeOf[3][11] == HIT_VALUE && shapeOf[3][12] == HIT_VALUE && shapeOf[3][13] == HIT_VALUE && shapeOf[3][14] == HIT_VALUE) {
				dev = true;			
			}
		}
		
		if(nave instanceof Carrier) {
			if(shapeOf[3][10] == HIT_VALUE && shapeOf[3][11] == HIT_VALUE && shapeOf[3][12] == HIT_VALUE && shapeOf[3][13] == HIT_VALUE && shapeOf[3][14] == HIT_VALUE) {
				dev = true;			
			}
		}
		
		if(nave instanceof Cruiser) {
			if(shapeOf[3][11] == HIT_VALUE && shapeOf[3][12] == HIT_VALUE && shapeOf[3][13] == HIT_VALUE) {
				dev = true;			
			}
		}
		
		if(nave instanceof Destroyer) {
			if(shapeOf[3][11] == HIT_VALUE && shapeOf[3][12] == HIT_VALUE) {
				dev = true;			
			}
		}
		
		// --------------------------------------------------------
		// ------------------ SUBCLASES DE CRAFT ------------------ 
		// --------------------------------------------------------
		
		if(nave instanceof Bomber) {
			if(shapeOf[3][1] == HIT_VALUE && shapeOf[3][2] == HIT_VALUE && shapeOf[3][7] == HIT_VALUE && shapeOf[3][10] == HIT_VALUE 
					&& shapeOf[3][11] == HIT_VALUE && shapeOf[3][12] == HIT_VALUE && shapeOf[3][13] == HIT_VALUE && shapeOf[3][17] == HIT_VALUE
					&& shapeOf[3][12] == HIT_VALUE && shapeOf[3][21] == HIT_VALUE && shapeOf[3][22] == HIT_VALUE) {
				dev = true;
			}
		}
		
		if(nave instanceof Fighter) {
			if(shapeOf[3][7] == HIT_VALUE && shapeOf[3][10] == HIT_VALUE && shapeOf[3][11] == HIT_VALUE && shapeOf[3][12] == HIT_VALUE 
					&& shapeOf[3][13] == HIT_VALUE && shapeOf[3][17] == HIT_VALUE) {
				dev = true;
			}
		}
		
		if(nave instanceof Transport) {
			if(shapeOf[3][1] == HIT_VALUE && shapeOf[3][7] == HIT_VALUE && shapeOf[3][10] == HIT_VALUE && shapeOf[3][11] == HIT_VALUE
					&& shapeOf[3][12] == HIT_VALUE && shapeOf[3][13] == HIT_VALUE && shapeOf[3][14] == HIT_VALUE && shapeOf[3][17] == HIT_VALUE
					&& shapeOf[3][21] == HIT_VALUE) {
				dev = true;
			}
		}
		return dev;
	}
	
	
	public boolean isShotDown() {
		Orientation or = this.orientation;
		boolean dev = false;
		
		if(this.getPosition() != null) {
			switch(or) {
			case NORTH:
				dev = compruebaTipoNORTH(this);
			
				break;
				
			case SOUTH:
				dev = compruebaTipoSOUTH(this);
				
				break;
				
			case EAST:
				dev = compruebaTipoEAST(this);
				
				break;
				
			case WEST:
				dev = compruebaTipoWEST(this);
				
				break;
				
			}	
		}
		return dev;
	}


	public boolean isHit(Coordinate c){
		boolean dev = false;
		Orientation or = this.orientation;
		boolean encontrado = false;
		int pos = 0;
		Coordinate relativa = null;
		int[][] shapeOf = this.getShape();
		
		if(this.position == null) {
			throw new NullPointerException();
		}
		
		Set<Coordinate> posAbs = new HashSet<Coordinate>();
		posAbs = getAbsolutePositions(this.position);
		
		for(Coordinate b: posAbs) {
			if(b.equals(c)){
				encontrado = true;
			}
		}
		
		if(encontrado) {
			
			if(c instanceof Coordinate2D) {
				relativa = new Coordinate2D(c.get(0) - this.position.get(0), c.get(1) - this.position.get(1));
			}
			
			else if(c instanceof Coordinate3D) {
				relativa = new Coordinate3D(c.get(0) - this.position.get(0), c.get(1) - this.position.get(1), c.get(2) - this.position.get(2));
			}

			pos = getShapeIndex(relativa);
			
			switch(or) {
				case NORTH:
					
					if(this instanceof Battleship | this instanceof Carrier) {
						if(pos > 24) {
							pos -= 3;
						}
					}
					
					if(shapeOf[0][pos] == HIT_VALUE) {
						dev = true;
					}
					break;
					
				case SOUTH:
					
					if(this instanceof Battleship | this instanceof Carrier) {
						if(pos > 24) {
							pos -= 3;
						}
					}
					
					if(shapeOf[2][pos] == HIT_VALUE) {
						dev = true;
					}
					
					break;
					
				case EAST:
					
					if(this instanceof Battleship | this instanceof Carrier) {
						if(pos > 24) {
							pos -= 3;
						}
					}
					
					if(shapeOf[1][pos] == HIT_VALUE) {
						dev = true;
					}
					break;
					
				case WEST:
					
					if(this instanceof Battleship | this instanceof Carrier) {
						if(pos > 24) {
							pos -= 3;
						}
					}
					
					if(shapeOf[3][pos] == HIT_VALUE) {
						dev = true;
					}
					break;
			}
		}
		return dev;
	
	}
	
	
	private char toStringDestroyer(Orientation or, int i, int j) {
		char toReturn = 0;
		
		
		if(or.equals(Orientation.NORTH) | or.equals(Orientation.SOUTH)){
			if(j == 2) {
				if(i == 1 | i == 2) {
					toReturn = this.getSymbol();
				}
				else {
					toReturn = Board.WATER_SYMBOL;
				}
			}
			else {
				toReturn = Board.WATER_SYMBOL;
			}
		}
		
		else if(or.equals(Orientation.EAST) | or.equals(Orientation.WEST)){
			if(i == 2) {
				if(j == 1 | j == 2) {
					toReturn = this.getSymbol();
				}
				else {
					toReturn = Board.WATER_SYMBOL;
				}
			}
			else {
				toReturn = Board.WATER_SYMBOL;	
			}
		}
		
		return toReturn;
	}
	
	
	private char toStringCruiser(Orientation or, int i, int j) {
		char toReturn = 0;
		
		if(or.equals(Orientation.NORTH) | or.equals(Orientation.SOUTH)){
			if(j == 2) {
				if(i == 1 | i == 2 | i == 3) {
					toReturn = this.getSymbol();
				}
				else {
					toReturn = Board.WATER_SYMBOL;
				}
			}
			else {
				toReturn = Board.WATER_SYMBOL;
			}
		}
		
		else if(or.equals(Orientation.EAST) | or.equals(Orientation.WEST)){
			if(i == 2) {
				if(j == 1 | j == 2 | j == 3) {
					toReturn = this.getSymbol();
				}
				else {
					toReturn = Board.WATER_SYMBOL;
				}
			}
			else {
				toReturn = Board.WATER_SYMBOL;	
			}
		}
		
		return toReturn;
	}

	
	private char toStringCarrier(Orientation or, int i, int j) {
		char toReturn = 0;
		
		if(or.equals(Orientation.NORTH) | or.equals(Orientation.SOUTH)){
			if(j == 2) {
				
				toReturn = this.getSymbol();
			}
			else {
				toReturn = Board.WATER_SYMBOL;
			}
		}
		
		else if(or.equals(Orientation.EAST) | or.equals(Orientation.WEST)){
			if(i == 2) {
				
				toReturn = this.getSymbol();
			}
			else {
				toReturn = Board.WATER_SYMBOL;	
			}
		}
		
		return toReturn;
	}
	
	
	private char toStringBattleship(Orientation or, int i, int j) {
		char toReturn = 0;
		
		if(or.equals(Orientation.NORTH) | or.equals(Orientation.SOUTH)){
			if(j == 2) {
				if(i >= 1) {
					toReturn = this.getSymbol();
				}
				else {
					toReturn = Board.WATER_SYMBOL;
				}
			}
			else {
				toReturn = Board.WATER_SYMBOL;
			}
		}
		
		else if(or.equals(Orientation.EAST) | or.equals(Orientation.WEST)){
			if(i == 2) {
				if(j >= 1) {
					toReturn = this.getSymbol();
				}
				else {
					toReturn = Board.WATER_SYMBOL;
				}
			}
			else {
				toReturn = Board.WATER_SYMBOL;	
			}
		}
		
		return toReturn;
	}

	
	private char toStringTransport(Orientation or, int i, int j) {
		char toReturn = 0;
		
		if(or.equals(Orientation.NORTH) | or.equals(Orientation.SOUTH)){
			if(j == 2) {
				toReturn = this.getSymbol();
			}
			
			else {
				toReturn = Board.WATER_SYMBOL;
			}
			
			if(i == 2) {
				if(j == 1 | j == 2 | j == 3) {
					toReturn = this.getSymbol();
				}
				else {
					toReturn = Board.WATER_SYMBOL;
				}
			}
			
			if(or.equals(Orientation.NORTH)){
				if((i == 3 && j == 0) | (i == 3 && j == 4)){
					toReturn = this.getSymbol();
				}
			}
			
			if(or.equals(Orientation.SOUTH)) {
				if((i == 1 && j == 0) | (i == 1 && j == 4)){
					toReturn = this.getSymbol();
				}
			}
		}
		
		else if(or.equals(Orientation.EAST) | or.equals(Orientation.WEST)){
			if(i == 2) {
				toReturn = this.getSymbol();
			}
			
			else {
				toReturn = Board.WATER_SYMBOL;
			}
			
			if(j == 2) {
				if(i == 1 | i == 2 | i == 3) {
					toReturn = this.getSymbol();
				}
				else {
					toReturn = Board.WATER_SYMBOL;
				}
			}
			
			if(or.equals(Orientation.EAST)) {
				if((i == 0 && j == 1) | (i == 4 && j == 1)){
					toReturn = this.getSymbol();
				}
			}
			
			if(or.equals(Orientation.WEST)){
				if((i == 0 && j == 3) | (i == 4 && j == 3)){
					toReturn = this.getSymbol();
				}
			}
		}
		
		return toReturn;
	}
	
	
	private char toStringFighter(Orientation or, int i, int j) {
		char toReturn = 0;
		
		if(or.equals(Orientation.NORTH) | or.equals(Orientation.SOUTH)){
			
			if(or.equals(Orientation.NORTH)) {
				if(j == 2 && i > 0) {
					toReturn = this.getSymbol();
				}
				
				else {
					toReturn = Board.WATER_SYMBOL;
				}
			}
			
			if(or.equals(Orientation.SOUTH)) {
				if(j == 2 && i != 4) {
					toReturn = this.getSymbol();
				}
				
				else {
					toReturn = Board.WATER_SYMBOL;
				}
			}
			
			if(i == 2) {
				if(j == 1 | j == 2 | j == 3) {
					toReturn = this.getSymbol();
				}
			}
			
		}
		
		else if(or.equals(Orientation.EAST) | or.equals(Orientation.WEST)) {
			
			if(or.equals(Orientation.EAST)) {
				if(i == 2 && j != 4) {
					toReturn = this.getSymbol();
				}
				
				else {
					toReturn = Board.WATER_SYMBOL;
				}
			}
			
			if(or.equals(Orientation.WEST)) {
				if(i == 2 && j != 0) {
					toReturn = this.getSymbol();
				}
				
				else {
					toReturn = Board.WATER_SYMBOL;
				}
			}
			
			if(j == 2) {
				if(i == 1 | i == 2 | i == 3) {
					toReturn = this.getSymbol();
				}
			}
		}
		
		return toReturn;
	}
	
	
	private char toStringBomber(Orientation or, int i, int j) {
		char toReturn = 0;
		
		if(or.equals(Orientation.NORTH) | or.equals(Orientation.SOUTH)) {
			
			if(or.equals(Orientation.NORTH)) {
				if(j == 2 && i > 0) {
					toReturn = this.getSymbol();
				}
				else {
					toReturn = Board.WATER_SYMBOL;
				}
				
				if(i == 3) {
					if(j == 0 | j == 2 | j == 4) {
						toReturn = this.getSymbol();
					}
				}
			}
			
			if(or.equals(Orientation.SOUTH)) {
				if(j == 2 && i != 4) {
					toReturn = this.getSymbol();
				}
				else {
					toReturn = Board.WATER_SYMBOL;
				}
				
				if(i == 1) {
					if(j == 0 | j == 2 | j == 4) {
						toReturn = this.getSymbol();
					}
				}
			}
			
			if(i == 2) {
				toReturn = this.getSymbol();
			}
		}
		
		else if(or.equals(Orientation.EAST) | or.equals(Orientation.WEST)){
			
			if(or.equals(Orientation.EAST)) {
				if(i == 2 && j != 4) {
					toReturn = this.getSymbol();	
				}
				else {
					toReturn = Board.WATER_SYMBOL;
				}
				
				if(j == 1) {
					if(i == 0 | i == 4) {
						toReturn = this.getSymbol();
					}
				}
			}
			
			if(or.equals(Orientation.WEST)) {
				if(i == 2 && j != 0) {
					toReturn = this.getSymbol();	
				}
				else {
					toReturn = Board.WATER_SYMBOL;
				}
				
				if(j == 3) {
					if(i == 0 | i == 4) {
						toReturn = this.getSymbol();
					}
				}
			}
			
			if(j == 2) {
				toReturn = this.getSymbol();
			}
		}
		
		return toReturn;
	}
	
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Orientation or = this.orientation;
		char toAppend;
		
		sb.append(this.name + " (" + this.orientation + ")\n");
		sb.append(" -----\n");
		
		for(int i = 0;i < BOUNDING_SQUARE_SIZE;i++) {
			sb.append("|");
			for(int j = 0;j < BOUNDING_SQUARE_SIZE;j++) {
				
				if(this instanceof Destroyer) {
					toAppend = toStringDestroyer(or, i, j);
					sb.append(toAppend);
				}
				
				
				if(this instanceof Cruiser) {
					toAppend = toStringCruiser(or, i, j);
					sb.append(toAppend);
				}
				
				if(this instanceof Carrier) {
					toAppend = toStringCarrier(or, i, j);
					sb.append(toAppend);
				}
				
				if(this instanceof Battleship) {
					toAppend = toStringBattleship(or, i, j);
					sb.append(toAppend);
				}
				
				if(this instanceof Transport) {
					toAppend = toStringTransport(or, i, j);
					sb.append(toAppend);
				}
				
				if(this instanceof Fighter) {
					toAppend = toStringFighter(or, i, j);
					sb.append(toAppend);
				}
				
				if(this instanceof Bomber) {
					toAppend = toStringBomber(or, i, j);
					sb.append(toAppend);
				}
			}
			
			sb.append("|");
			sb.append("\n");
		}
		
		
		sb.append(" -----");
		return sb.toString();
	}
}