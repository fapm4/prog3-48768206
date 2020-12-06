package model.io;

import java.util.Random;

import model.Board;
import model.Coordinate;
import model.CoordinateFactory;
import model.Craft;
import model.Orientation;
import model.aircraft.Board3D;
import model.aircraft.Bomber;
import model.aircraft.Fighter;
import model.aircraft.Transport;
import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;
import model.exceptions.NextToAnotherCraftException;
import model.exceptions.OccupiedCoordinateException;
import model.ship.Battleship;
import model.ship.Board2D;
import model.ship.Carrier;
import model.ship.Cruiser;
import model.ship.Destroyer;

// TODO: Auto-generated Javadoc
/**
 * The Class PlayerRandom.
 * @author Francisco Alejandro Pérez Meneses - 48768206H
 */
public class PlayerRandom implements IPlayer{
	
	/** The random. */
	private Random random;
	
	/** The name. */
	private String name;
	
	
	/**
	 * Instantiates a new player random.
	 *
	 * @param name the name
	 * @param seed the seed
	 */
	public PlayerRandom(String name, long seed) {
		this.name = name;
		random = new Random(seed);
	}
	
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name + " (PlayerRandom)";
	}
	
	
	/**
	 * Gets the random orientation.
	 *
	 * @return the random orientation
	 */
	private Orientation getRandomOrientation() {
		Orientation nueva = null;
		int r = random.nextInt(4);
		
		switch(r) {
			case 0:
				nueva = Orientation.NORTH;
				break;
				
			case 1:
				nueva = Orientation.EAST;
				break;
			
			case 2:
				nueva = Orientation.SOUTH;
				break;
				
			case 3:
				nueva = Orientation.WEST;
				break;
		}
	
		
		return nueva;
	}
	
	
	/**
	 * Genera craft.
	 *
	 * @param craft the craft
	 * @param or the or
	 * @param is3D the is 3 D
	 * @return the craft
	 */
	private Craft generaCraft(String craft, Orientation or, boolean is3D) {
		Craft nuevo = null;
		
		switch(craft) {
		case "Battleship":
			nuevo = new Battleship(or);
			break;
			
		case "Carrier":
			nuevo = new Carrier(or);
			break;
			
		case "Cruiser":
			nuevo = new Cruiser(or);
			break;
			
		case "Destroyer":
			nuevo = new Destroyer(or);
			break;
		}
		
		if(is3D) {
			switch(craft) {
			case "Bomber":
				nuevo = new Bomber(or);
				break;
				
			case "Fighter":
				nuevo = new Fighter(or);
				break;
				
			case "Transport":
				nuevo = new Transport(or);
				break;
			}
		}
		
		return nuevo;
	}
	
	
	/**
	 * Aux put crafts.
	 *
	 * @param b the b
	 * @param craft the craft
	 * @param is3D the is 3 D
	 */
	private void auxPutCrafts(Board b, String craft, boolean is3D) {
		Orientation or = null;
		Coordinate coord = null;
		Craft nuevo = null;
		int cont = 0;
		
		or = getRandomOrientation();
		nuevo = generaCraft(craft, or, is3D);
		coord = getRandomCoordinate(b, Craft.BOUNDING_SQUARE_SIZE);
		
		try {
			b.addCraft(nuevo, coord);
		} 
		catch (InvalidCoordinateException | OccupiedCoordinateException | NextToAnotherCraftException e) {
			while(cont != 99) {
				cont++;
				try {
					b.addCraft(nuevo, coord);
					break;
				} 
				catch (InvalidCoordinateException | OccupiedCoordinateException | NextToAnotherCraftException e1) {
					coord = getRandomCoordinate(b, Craft.BOUNDING_SQUARE_SIZE);
				}
			}
		}
	}
	
	
	/**
	 * Put crafts.
	 *
	 * @param b the b
	 */
	public void putCrafts(Board b) {	
		// Añado Battleship
		auxPutCrafts(b, "Battleship", false);
		// ----------------
		
		// Añado Carrier
		auxPutCrafts(b, "Carrier", false);
		// ----------------
		
		// Añado Cruiser
		auxPutCrafts(b, "Cruiser", false);
		// ----------------
		
		// Añado Destroyer
		auxPutCrafts(b, "Destroyer", false);
		// ----------------
		
		
		if(b instanceof Board3D) {
			// Añado Bomber
			auxPutCrafts(b, "Bomber", true);
			// ----------------
			
			// Añado Fighter
			auxPutCrafts(b, "Fighter", true);
			// ----------------
			
			// Añado Transport
			auxPutCrafts(b, "Transport", true);
			// ----------------
		}
	}
	
	
	/**
	 * Next shoot.
	 *
	 * @param b the b
	 * @return the coordinate
	 * @throws InvalidCoordinateException the invalid coordinate exception
	 * @throws CoordinateAlreadyHitException the coordinate already hit exception
	 */
	public Coordinate nextShoot(Board b) throws InvalidCoordinateException, CoordinateAlreadyHitException {
		Coordinate nueva = getRandomCoordinate(b, 0);
		b.hit(nueva);
		return nueva;
	}
	
	
	/**
	 * Gets the random int.
	 *
	 * @param min the min
	 * @param max the max
	 * @return the random int
	 */
	private int getRandomInt(int min, int max) {
		return random.nextInt(max - min) + min;
	}
	
	
	/**
	 * Gets the random coordinate.
	 *
	 * @param b the b
	 * @param offset the offset
	 * @return the random coordinate
	 */
	private Coordinate getRandomCoordinate(Board b, int offset) {
		Coordinate nueva = null;
		int x = 0, y = 0, z = 0;
		x = getRandomInt(0 - offset, b.getSize());
		y = getRandomInt(0 - offset, b.getSize());
		
		if(b instanceof Board2D) {
			nueva = CoordinateFactory.createCoordinate(x, y);
		}
		
		else if(b instanceof Board3D) {
			z = getRandomInt(0 - offset, b.getSize());
			nueva = CoordinateFactory.createCoordinate(x, y, z);
		}
		return nueva;
	}
}
