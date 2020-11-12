package model.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Set;

import model.Board;
import model.Coordinate;
import model.CoordinateFactory;
import model.Craft;
import model.Orientation;
import model.aircraft.Bomber;
import model.aircraft.Fighter;
import model.aircraft.Transport;
import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;
import model.exceptions.NextToAnotherCraftException;
import model.exceptions.OccupiedCoordinateException;
import model.exceptions.io.BattleshipIOException;
import model.ship.Battleship;
import model.ship.Carrier;
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
	
	private Coordinate getCoordinate(String linea, boolean desdePut) throws BattleshipIOException {
		String[] tokens = linea.split("\\s+");
		int c1 = 0, c2 = 0, c3 = 0;
		Coordinate nueva;
		
		if(desdePut) {
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
		}
		
		else {
			try {
				c1 = Integer.parseInt(tokens[1]);
				c2 = Integer.parseInt(tokens[2]);
			}
			catch(NumberFormatException e) {
				throw new BattleshipIOException("Argumentos Incorrectos: Se ha pasado un carácter que no es un número");
			}
			
			if(tokens.length == 4) {
				try {
					c3 = Integer.parseInt(tokens[3]);
					nueva = CoordinateFactory.createCoordinate(c1, c2, c3);
				}
				catch(NumberFormatException e) {
					throw new BattleshipIOException("Argumentos Incorrectos: Se ha pasado un carácter que no es un número");
				}
			}
			else {
				nueva = CoordinateFactory.createCoordinate(c1, c2);
			}
		}
		
		
		return nueva;
		
	}
	
	private String getComando(String linea) {
		String[] tokens = linea.split("\\s+");
		return tokens[0];
	}
	
	
	private void comprobarElementos(String linea, boolean desdePut) 
			throws BattleshipIOException {
		
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
				
				Set<Coordinate> posAbs = nuevoCraft.getAbsolutePositions(nuevaCoord);
				for(Coordinate c: posAbs) {
					System.out.println(c);
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
	
	
	public Coordinate nextShoot(Board b) throws BattleshipIOException, InvalidCoordinateException, CoordinateAlreadyHitException {
		
		if(b == null) {
			throw new NullPointerException();
		}
		
		Coordinate nuevaCoord;
		Coordinate golpea = null;
		
		try {
			String linea = br.readLine();
			
			while(linea != null && !linea.equals(exit)) {
				comprobarElementos(linea, false);
				nuevaCoord = getCoordinate(linea, false);
				b.hit(nuevaCoord);
				//golpea = nuevaCoord;
				//nuevaCoord = null;
				linea = br.readLine();
			}
						
		}
		catch(IOException e) {
			throw new BattleshipIOException("Se produjo un error al leer una línea");
		}
		
		return golpea;
	}
}
