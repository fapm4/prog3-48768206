package model.io;

import java.io.BufferedReader;
import java.io.IOException;

import model.Board;
import model.Coordinate;
import model.CoordinateFactory;
import model.Craft;
import model.Orientation;
import model.aircraft.Board3D;
import model.aircraft.Bomber;
import model.aircraft.Fighter;
import model.aircraft.Transport;
import model.exceptions.InvalidCoordinateException;
import model.exceptions.NextToAnotherCraftException;
import model.exceptions.OccupiedCoordinateException;
import model.exceptions.io.BattleshipIOException;
import model.ship.Battleship;
import model.ship.Board2D;
import model.ship.Carrier;
import model.ship.Coordinate2D;
import model.ship.Cruiser;
import model.ship.Destroyer;

public class PlayerFile implements IPlayer{
	private BufferedReader br;
	String name;
	
	private static final String endput = "endput";
	private static final String exit = "exit";
	private static final String shoot = "shoot";
	private static final String put = "put";
	
	
	public PlayerFile(String name, BufferedReader reader) {
		this.name = name;
		
		if(reader == null) {
			throw new NullPointerException();
		}
		
		br = reader;
	}
	
	
	public String getName() {
		return name + " (PlayerFile)";
	}
	
	
	private String getCraftName(String linea) {
		String[] tokens = linea.split("\\s+");
		return tokens[1];
	}
	
	
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
	
	
	private Craft getPutCraft(String craftName, Orientation or) {
		Craft nuevo;
		
		switch(craftName) {
			case "Destroyer":
				nuevo = new Destroyer(or);
				break;
				
			case "Cruiser":
				nuevo = new Cruiser(or);
				break;
				
			case "Carrier":
				nuevo = new Carrier(or);
				break;
				
			case "Battleship":
				nuevo = new Battleship(or);
				break;
				
			case "Transport":
				nuevo = new Transport(or);
				break;
				
			case "Fighter":
				nuevo = new Fighter(or);
				break;
				
			case "Bomber":
				nuevo = new Bomber(or);
				break;
				
			default:
				nuevo = null;
		}
		
		return nuevo;
	}
	
	private Coordinate getPutCoordinate(String linea, Board b, int c1, int c2, int c3) 
			throws BattleshipIOException {
		
		String comando = "";
		Coordinate nueva = null;		
		String[] tokens = linea.split("\\s+");
		
		comando = tokens[0];
		
		if(!comando.equals(put) && !comando.equals(endput) && !comando.equals(exit)) {
			// Lanzo excepcion de comando
			throw new BattleshipIOException("Argumentos Incorrectos: Se ha pasado un argumento que no es exit, put o endput");
		}
		
		try {
			c1 = Integer.parseInt(tokens[3]);
			c2 = Integer.parseInt(tokens[4]);
		}
		catch(NumberFormatException e) {
			throw new BattleshipIOException("Argumentos Incorrectos: Se ha pasado un carácter que no es un número");
		}
		
		if(tokens.length == 6) {
			try {
				c3 = Integer.parseInt(tokens[5]);
				nueva = CoordinateFactory.createCoordinate(c1, c2, c3);
			}
			catch(NumberFormatException e) {
				throw new BattleshipIOException("Argumentos Incorrectos: Se ha pasado un carácter que no es un número");
			}
		}
		else {
			nueva = CoordinateFactory.createCoordinate(c1, c2);
		}
			
		return nueva;
	}
	
	public void putCrafts(Board b) throws BattleshipIOException, InvalidCoordinateException, OccupiedCoordinateException, 
	NextToAnotherCraftException {
		
		if(b == null) {
			throw new NullPointerException();
		}
		
		Craft nuevoCraft = null;
		Orientation or = null;
		Coordinate nuevaCoord = null;
		String craftName = "";
		int c1 = 0, c2 = 0, c3 = 0;
		
		try {
			String linea = br.readLine();
			
			while(linea != null && !linea.equals(exit) && !linea.equals(endput)) {
				nuevaCoord = getPutCoordinate(linea, b, c1, c2, c3);
				craftName = getCraftName(linea);
				or = getPutOrientation(linea);
				nuevoCraft = getPutCraft(craftName, or);
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
	
	
	public Coordinate nextShoot(Board b) {
		Coordinate nuevo = null;
		return nuevo;
	}
}
