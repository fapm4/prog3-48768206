package model.io;

import java.io.BufferedReader;
import java.io.IOException;
import model.Board;
import model.CellStatus;
import model.Coordinate;
import model.CoordinateFactory;
import model.Craft;
import model.CraftFactory;
import model.Orientation;
import model.aircraft.Coordinate3D;
import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;
import model.exceptions.NextToAnotherCraftException;
import model.exceptions.OccupiedCoordinateException;
import model.exceptions.io.BattleshipIOException;
import model.ship.Board2D;


// TODO: Auto-generated Javadoc
/**
 * The Class PlayerFile.
 * @author Francisco Alejandro Pérez Meneses - 48768206H
 */
public class PlayerFile implements IPlayer{
	
	/** The br. */
	private BufferedReader br;
	
	/** The name. */
	String name;
	
	/** The Constant endput. */
	private static final String endput = "endput";
	
	/** The Constant exit. */
	private static final String exit = "exit";
	
	/** The Constant shoot. */
	private static final String shoot = "shoot";
	
	/** The Constant put. */
	private static final String put = "put";
	
	/** The leido. */
	private boolean leido = false;
	
	/** The last shot status. */
	private CellStatus lastShotStatus;
	
	
	/**
	 * Instantiates a new player file.
	 *
	 * @param name the name
	 * @param reader the reader
	 */
	public PlayerFile(String name, BufferedReader reader) {
		
		if(reader == null) {
			throw new NullPointerException();
		}
		this.name = name;
		br = reader;
	}
	
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name + " (PlayerFile)";
	}
	
	
	/**
	 * Gets the craft name.
	 *
	 * @param linea the linea
	 * @return the craft name
	 */
	private String getCraftName(String linea) {
		String[] tokens = linea.split("\\s+");
		return tokens[1];
	}
	
	
	/**
	 * Gets the put orientation.
	 *
	 * @param linea the linea
	 * @return the put orientation
	 * @throws BattleshipIOException the battleship IO exception
	 */
	private Orientation getPutOrientation(String linea) throws BattleshipIOException {
		String[] tokens = linea.split("\\s+");
		
		Orientation nueva = null;
		
		switch(tokens[2]) {
			 case "NORTH":
				 nueva = Orientation.NORTH;
				 break;
				 
			 case "SOUTH":
				 nueva = Orientation.SOUTH;
				 break;
				 
			 case "EAST":
				 nueva = Orientation.EAST;
				 break;
				 
			 case "WEST":
				 nueva = Orientation.WEST;
				 break;
				 
			default: 
				nueva = null;
				break;
		}
		
		if(nueva == null) {
			// Lanzo excepcion Orientation no correcta
			throw new BattleshipIOException("Orientación incorrecta: La orientación no es NORTH, SOUTH, EAST o WEST");
		}
		
		return nueva;
	}
	
	
	/**
	 * Gets the put craft.
	 *
	 * @param craftName the craft name
	 * @param or the or
	 * @return the put craft
	 */
	private Craft getPutCraft(String craftName, Orientation or) {
		Craft nuevo = null;		
		nuevo = CraftFactory.createCraft(craftName, or);
		
		return nuevo;
	}
	
	
	/**
	 * Correct coordinates.
	 *
	 * @param linea the linea
	 * @param desdePut the desde put
	 * @return true, if successful
	 */
	private boolean correctCoordinates(String[] linea, boolean desdePut) {
		boolean toReturn = false;
		
		if(desdePut) {
			if(linea.length < 5 | linea.length > 6) {
				toReturn = true;
			}
		}
		else {
			if(linea.length < 3 | linea.length > 4) {
				toReturn = true;
			}
		}
		
		return toReturn;
	}
	
	
	/**
	 * Construct coordinate.
	 *
	 * @param tokens the tokens
	 * @param desdePut the desde put
	 * @return the coordinate
	 * @throws BattleshipIOException the battleship IO exception
	 */
	private Coordinate constructCoordinate(String[] tokens, boolean desdePut) throws BattleshipIOException {
		Coordinate nueva = null;
		int c1 = 0, c2 = 0, c3 = 0;
		
		if(desdePut) {
			
			// Comprueba la longitud de los argumentos
			if(correctCoordinates(tokens, true)) {
				throw new BattleshipIOException("Argumentos Incorrectos: Se han pasado un número erróneo de coordenadas");
			}
			
			
			// Creo las dos primeras coordenadas
			try {
				c1 = Integer.parseInt(tokens[3]);
				c2 = Integer.parseInt(tokens[4]);
			}
			catch(NumberFormatException e) {
				throw new BattleshipIOException("Argumentos Incorrectos: Se ha pasado un carácter que no es un número");
			}
			
			// Si el tamaño = 6, significa que hay una tercera coordenada
			if(tokens.length == 6) {
				try {
					
					// Creo la coordenada 3D
					c3 = Integer.parseInt(tokens[5]);
					nueva = CoordinateFactory.createCoordinate(c1, c2, c3);
				}
				catch(NumberFormatException e) {
					throw new BattleshipIOException("Argumentos Incorrectos: Se ha pasado un carácter que no es un número");
				}
			}
			else {
				
				// Sino, 2D
				nueva = CoordinateFactory.createCoordinate(c1, c2);
			}
		}
		
		else {
			
			// Comprueba la longitud de los argumentos
			if(correctCoordinates(tokens, false)) {
				throw new BattleshipIOException("Argumentos Incorrectos: Se han pasado un número erróneo de coordenadas");
			}
			
			try {
				
				// Creo las dos primeras coordenadas
				c1 = Integer.parseInt(tokens[1]);
				c2 = Integer.parseInt(tokens[2]);
			}
			catch(NumberFormatException e) {
				throw new BattleshipIOException("Argumentos Incorrectos: Se ha pasado un carácter que no es un número");
			}
			
			// Si el tamaño = 4, significa que hay una tercera coordenada
			if(tokens.length == 4) {
				try {
					
					// Creo la coordenada 3D
					c3 = Integer.parseInt(tokens[3]);
					nueva = CoordinateFactory.createCoordinate(c1, c2, c3);
				}
				catch(NumberFormatException e) {
					throw new BattleshipIOException("Argumentos Incorrectos: Se ha pasado un carácter que no es un número");
				}
			}
			else {
				
				// Sino, 2D
				nueva = CoordinateFactory.createCoordinate(c1, c2);
			}
		}
		
		return nueva;
	}
	
	
	/**
	 * Gets the coordinate.
	 *
	 * @param linea the linea
	 * @param desdePut the desde put
	 * @return the coordinate
	 * @throws BattleshipIOException the battleship IO exception
	 */
	private Coordinate getCoordinate(String linea, boolean desdePut) throws BattleshipIOException {
		String[] tokens = linea.split("\\s+");
		Coordinate nueva;
		
		nueva = constructCoordinate(tokens, desdePut);
		
		return nueva;
	}
	
	
	/**
	 * Gets the comando.
	 *
	 * @param linea the linea
	 * @return the comando
	 */
	private String getComando(String linea) {
		String[] tokens = linea.split("\\s+");
		return tokens[0];
	}
	
	
	/**
	 * Comprobar elementos.
	 *
	 * @param linea the linea
	 * @param desdePut the desde put
	 * @throws BattleshipIOException the battleship IO exception
	 */
	private void comprobarElementos(String linea, boolean desdePut) throws BattleshipIOException {
		String comando = getComando(linea);
		
		if(desdePut) {
			if(!comando.equals(put) && !comando.equals(endput) && !comando.equals(exit)) {
				// Lanzo excepcion de comando
				throw new BattleshipIOException("Argumentos Incorrectos: Se ha pasado un argumento que no es exit, put o endput");
			}
		}
		
		else if(!desdePut){
			if(!comando.equals(shoot) && !comando.equals(exit)) {
				throw new BattleshipIOException("Argumentos Incorrectos: Se ha pasado un argumento que no es shoot o exit");
			}
		}
	}
	
	
	/**
	 * Put crafts.
	 *
	 * @param b the b
	 * @throws BattleshipIOException the battleship IO exception
	 * @throws InvalidCoordinateException the invalid coordinate exception
	 * @throws OccupiedCoordinateException the occupied coordinate exception
	 * @throws NextToAnotherCraftException the next to another craft exception
	 */
	public void putCrafts(Board b) throws BattleshipIOException, InvalidCoordinateException, OccupiedCoordinateException, 
	NextToAnotherCraftException {
		
		if(b == null) {
			throw new NullPointerException();
		}
		
		Craft nuevoCraft = null;
		Orientation or = null;
		Coordinate nuevaCoord = null;
		String craftName = "";
		
		try {
			String linea = br.readLine();
			
			while(linea != null && !linea.equals(exit) && !linea.equals(endput)) {
				comprobarElementos(linea, true);
				nuevaCoord = getCoordinate(linea, true);
				craftName = getCraftName(linea);
				or = getPutOrientation(linea);
				nuevoCraft = getPutCraft(craftName, or);
				
				if(b instanceof Board2D && nuevaCoord instanceof Coordinate3D) {
					throw new IllegalArgumentException();
				}
				
				b.addCraft(nuevoCraft, nuevaCoord);
				
				nuevaCoord = null;
				craftName = "";
				or = null;
				nuevoCraft = null;
				
				linea = br.readLine();
			}
		}
		
		catch(IOException e) {
			throw new BattleshipIOException("Se produjo un error al leer una línea");
		}
	}
	
	
	/**
	 * Next shoot.
	 *
	 * @param b the b
	 * @return the coordinate
	 * @throws BattleshipIOException the battleship IO exception
	 * @throws InvalidCoordinateException the invalid coordinate exception
	 * @throws CoordinateAlreadyHitException the coordinate already hit exception
	 */
	public Coordinate nextShoot(Board b) throws BattleshipIOException, InvalidCoordinateException, CoordinateAlreadyHitException {
		
		if(b == null) {
			throw new NullPointerException();
		}
		
		Coordinate nuevaCoord;
		Coordinate golpea = null;
		
		try {
			String linea = br.readLine();
			
			if(linea == null) {
				return null;
			}
			
			if(linea != null && !linea.equals(exit)) {
				comprobarElementos(linea, false);
				nuevaCoord = getCoordinate(linea, false);
				lastShotStatus = b.hit(nuevaCoord);
				golpea = nuevaCoord;
				nuevaCoord = null;
			}
			if(linea.equals(exit) | linea == null){
				leido = true;
			}
			
						
		}
		catch(IOException e) {
			throw new BattleshipIOException("Se produjo un error al leer una línea");
		}
	
		if(leido) {
			golpea = null;
		}
		
		return golpea;
	}


	/**
	 * Gets the last shot status.
	 *
	 * @return the last shot status
	 */
	@Override
	public CellStatus getLastShotStatus() {
		CellStatus dev = null;
		
		if(!leido) {
			dev = lastShotStatus;
		}
		
		return dev;
	}
}
