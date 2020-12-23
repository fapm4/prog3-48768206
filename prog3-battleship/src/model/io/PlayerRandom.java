package model.io;

import java.util.Random;

import model.Board;
import model.CellStatus;
import model.Coordinate;
import model.CoordinateFactory;
import model.Craft;
import model.CraftFactory;
import model.Orientation;
import model.aircraft.Board3D;
import model.aircraft.Coordinate3D;
import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;
import model.exceptions.NextToAnotherCraftException;
import model.exceptions.OccupiedCoordinateException;
import model.ship.Board2D;


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
	
	/** The last shot status. */
	private CellStatus lastShotStatus;
	
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
		nuevo = CraftFactory.createCraft(craft, or);
		
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
		
		if(b instanceof Board2D && coord instanceof Coordinate3D) {
			throw new IllegalArgumentException();
		}
		
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
		auxPutCrafts(b, "ship.Battleship", false);
		// ----------------
		
		// Añado Carrier
		auxPutCrafts(b, "ship.Carrier", false);
		// ----------------
		
		// Añado Cruiser
		auxPutCrafts(b, "ship.Cruiser", false);
		// ----------------
		
		// Añado Destroyer
		auxPutCrafts(b, "ship.Destroyer", false);
		// ----------------
		
		
		if(b instanceof Board3D) {
			// Añado Bomber
			auxPutCrafts(b, "aircraft.Bomber", true);
			// ----------------
			
			// Añado Fighter
			auxPutCrafts(b, "aircraft.Fighter", true);
			// ----------------
			
			// Añado Transport
			auxPutCrafts(b, "aircraft.Transport", true);
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
		lastShotStatus = b.hit(nueva);
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


	/**
	 * Gets the last shot status.
	 *
	 * @return the last shot status
	 */
	@Override
	public CellStatus getLastShotStatus() {
		return lastShotStatus;
	}
}
