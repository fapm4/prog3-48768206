package model;

import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;
import model.exceptions.NextToAnotherCraftException;
import model.exceptions.OccupiedCoordinateException;
import model.exceptions.io.BattleshipIOException;
import model.io.IPlayer;
import model.io.IVisualiser;


// TODO: Auto-generated Javadoc
/**
 * The Class Game.
 * @author Francisco Alejandro Pérez Meneses - 48768206H
 */
public class Game {
	
	/** The game started. */
	private boolean gameStarted;
	
	/** The next to shoot. */
	private int nextToShoot;
	
	/** The shoot counter. */
	private int shootCounter;
	
	/** The player 2. */
	private IPlayer player1, player2;
	
	/** The board 2. */
	private Board board1, board2;
	

	/**
	 * Instantiates a new game.
	 *
	 * @param b1 the b 1
	 * @param b2 the b 2
	 * @param p1 the p 1
	 * @param p2 the p 2
	 */
	public Game(Board b1, Board b2, IPlayer p1, IPlayer p2) {
		if(b1 == null | b2 == null | p1 == null | p2 == null) {
			throw new NullPointerException();
		}
		
		gameStarted = false;		
		board1 = b1;
		board2 = b2;
		player1 = p1;
		player2 = p2;
	}
	
	
	/**
	 * Gets the player 1.
	 *
	 * @return the player 1
	 */
	public IPlayer getPlayer1() {
		return player1;
	}
	
	
	/**
	 * Gets the player 2.
	 *
	 * @return the player 2
	 */
	public IPlayer getPlayer2() {
		return player2;
	}
	
	
	/**
	 * Gets the player last shoot.
	 *
	 * @return the player last shoot
	 */
	public IPlayer getPlayerLastShoot() {
		IPlayer toReturn = null;
		
		if(shootCounter != 0) {
			if(nextToShoot == 1) {
				toReturn = player1;
			}
			
			else if(nextToShoot == 2) {
				toReturn = player2;
			}
		}
		
		return toReturn;
	}
	
	
	/**
	 * Gets the board 1.
	 *
	 * @return the board 1
	 */
	public Board getBoard1() {
		return board1;
	}
	

	/**
	 * Gets the board 2.
	 *
	 * @return the board 2
	 */
	public Board getBoard2() {
		return board2;
	}
	
	
	/**
	 * Start.
	 */
	public void start() {
		try {
			player1.putCrafts(board1);
			player2.putCrafts(board2);
			shootCounter = 0;
			nextToShoot = 1;
			gameStarted = true;
		} catch (InvalidCoordinateException | OccupiedCoordinateException | NextToAnotherCraftException
				| BattleshipIOException e1) {
			throw new RuntimeException();
		}
	}
	
	
	/**
	 * Game ended.
	 *
	 * @return true, if successful
	 */
	public boolean gameEnded() {
		boolean destroyed = false;
		
		if((board1.areAllCraftsDestroyed() | board2.areAllCraftsDestroyed()) && gameStarted) {
			destroyed = true;
		}		
		
		return destroyed;
	}
	

	/**
	 * Aux shoot.
	 *
	 * @param player the player
	 * @param b the b
	 * @param shoot the shoot
	 * @return true, if successful
	 */
	private boolean auxShoot(IPlayer player, Board b, int shoot) {
		boolean dev = false;
		
		try {
			if(shoot == 1) {
				player1.nextShoot(board2);
				nextToShoot = 2;
			}
			else if(shoot == 2) {
				player2.nextShoot(board1);
				nextToShoot = 1;
			}
			
			dev = true;
			shootCounter ++;
		}
		catch (InvalidCoordinateException | CoordinateAlreadyHitException | BattleshipIOException e) {
			if(e instanceof CoordinateAlreadyHitException) {
				System.out.println("Action by " + player.getName() + " Coordinate ya golpeada");
			}
			else if(e instanceof InvalidCoordinateException  | e instanceof BattleshipIOException) {
				throw new RuntimeException();
			}
		}
		
		return dev;
	}
	
	
	/**
	 * Play next.
	 *
	 * @return true, if successful
	 */
	public boolean playNext() {
		boolean dev = false;
		
		if(nextToShoot == 1) {
			dev = auxShoot(player1, board2, 1);
		}
		
		else if(nextToShoot == 2) {
			dev = auxShoot(player2, board1, 2);
		}
	
		
		return dev;
	}
	

	/**
	 * Play game.
	 *
	 * @param visualiser the visualiser
	 */
	public void playGame(IVisualiser visualiser) {
		boolean stop = false;
		boolean play = true;
		start();
		visualiser.show();
		while(play == true && stop == false) {
			play = playNext();
			
			if(play) {
				visualiser.show();
			}
			
			stop = gameEnded();
		}
		
		visualiser.close();
	}
	
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		final String characters = "==================================\n";

		if(gameEnded()) {
			sb.append("=== GAME ENDED ===\n");
		}
		
		if(gameStarted && !gameEnded()) {
			sb.append("=== ONGOING GAME ===\n");
		}
		else {
			if(!gameStarted) {
				sb.append("=== GAME NOT STARTED ===\n");
			}
		}
		
		sb.append(characters);
		sb.append(player1.getName() + "\n");
		sb.append(characters);
		sb.append(board1.show(false) + "\n");
		sb.append(characters);
		
		sb.append(player2.getName() + "\n");
		sb.append(characters);
		sb.append(board2.show(false) + "\n");
		sb.append(characters);
		sb.append("Number of shots: " + shootCounter);
		
		
		if(gameEnded()) {
			sb.append("\n" + getPlayerLastShoot().getName() + " wins");
		}
	
		
		return sb.toString();
	}
}
