package model.io;

import model.Board;
import model.CellStatus;
import model.Coordinate;
import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;
import model.exceptions.NextToAnotherCraftException;
import model.exceptions.OccupiedCoordinateException;
import model.exceptions.io.BattleshipIOException;

// TODO: Auto-generated Javadoc
/**
 * The Interface IPlayer.
 * @author Francisco Alejandro Pérez Meneses - 48768206H
 */

public interface IPlayer {
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	String getName();
	
	
	/**
	 * Put crafts.
	 *
	 * @param b the b
	 * @throws BattleshipIOException the battleship IO exception
	 * @throws InvalidCoordinateException the invalid coordinate exception
	 * @throws OccupiedCoordinateException the occupied coordinate exception
	 * @throws NextToAnotherCraftException the next to another craft exception
	 */
	void putCrafts(Board b) throws BattleshipIOException, InvalidCoordinateException, OccupiedCoordinateException, NextToAnotherCraftException;
	
	
	/**
	 * Next shoot.
	 *
	 * @param b the b
	 * @return the coordinate
	 * @throws BattleshipIOException the battleship IO exception
	 * @throws InvalidCoordinateException the invalid coordinate exception
	 * @throws CoordinateAlreadyHitException the coordinate already hit exception
	 */
	Coordinate nextShoot(Board b) throws BattleshipIOException, InvalidCoordinateException, CoordinateAlreadyHitException;
	
	
	/**
	 * Gets the last shot status.
	 *
	 * @return the last shot status
	 */
	public CellStatus getLastShotStatus();
}
