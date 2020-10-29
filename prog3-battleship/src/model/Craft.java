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

	
	public Set<Coordinate> getAbsolutePositions(Coordinate position){
		
	
		Set<Coordinate> positionsToReturn = new HashSet<Coordinate>();
		Orientation or = this.orientation;
		Coordinate nuevo = null;
		
		if(or.equals(Orientation.NORTH) | or.equals(Orientation.SOUTH)) {
			for(int i = 1;i <= 3;i++) {
				if(position instanceof Coordinate2D) {
					
					nuevo = new Coordinate2D(position.get(0) + 2, position.get(1) + i);
					positionsToReturn.add(nuevo);
				}
				if(position instanceof Coordinate3D) {
					
					nuevo = new Coordinate3D(position.get(0) + 2, position.get(1) + i, position.get(2));
					positionsToReturn.add(nuevo);
				}
				
			}
		}
		
		else if(or.equals(Orientation.EAST) | or.equals(Orientation.WEST)){
			for(int i = 1;i <= 3;i++) {
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
		for(Coordinate busca: posAbsolutas) {
			
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
							
				if(shapeOf[0][pos] == HIT_VALUE) {
					dev = false;
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
				
				if(nueva.get(0) < 2) {
					shapeOf[2][7] = HIT_VALUE;
				}
				
				if(shapeOf[2][pos] == HIT_VALUE) {
					dev = false;
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

				if(shapeOf[1][pos] == HIT_VALUE) {
					dev = false;
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
									
				if(shapeOf[3][pos] == HIT_VALUE) {
					dev = false;
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
					if(shapeOf[0][pos] == HIT_VALUE) {
						dev = true;
					}
					break;
					
				case SOUTH:
					if(shapeOf[2][pos] == HIT_VALUE) {
						dev = true;
					}
					break;
					
				case EAST:
					if(shapeOf[1][pos] == HIT_VALUE) {
						dev = true;
					}
					break;
					
				case WEST:
					if(shapeOf[3][pos] == HIT_VALUE) {
						dev = true;
					}
					break;
			}
		}
		return dev;
	
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		Orientation or = this.orientation;
		
		sb.append(this.name + " (" + this.orientation + ")\n");
		sb.append(" -----\n");
		
		for(int i = 0;i < BOUNDING_SQUARE_SIZE;i++) {
			sb.append("|");
			for(int j = 0;j < BOUNDING_SQUARE_SIZE;j++) {
				if(or.equals(Orientation.NORTH) | or.equals(Orientation.SOUTH)){
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
				else if(or.equals(Orientation.EAST) | or.equals(Orientation.WEST)){
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
			}
			
			sb.append("|");
			sb.append("\n");
		}
		
		
		sb.append(" -----");
		return sb.toString();
	}

}