package model;

import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;
import model.exceptions.NextToAnotherCraftException;
import model.exceptions.OccupiedCoordinateException;
import model.exceptions.io.BattleshipIOException;
import model.io.IPlayer;
import model.io.IVisualiser;

public class Game {
	
	private boolean gameStarted;
	private int nextToShoot;
	private int shootCounter;
	
	private IPlayer player1, player2;
	private Board board1, board2;
	
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
	
	
	public IPlayer getPlayer1() {
		return player1;
	}
	
	
	public IPlayer getPlayer2() {
		return player2;
	}
	
	
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
	
	
	public Board getBoard1() {
		return board1;
	}
	
	
	public Board getBoard2() {
		return board2;
	}
	
	
	public void start() {
		
		shootCounter = 0;
		nextToShoot = 1;
		gameStarted = true;
		
		try {
			player1.putCrafts(board1);
			nextToShoot = 2;
			player2.putCrafts(board2);
		} catch (InvalidCoordinateException | OccupiedCoordinateException | NextToAnotherCraftException
				| BattleshipIOException e1) {
			throw new RuntimeException();
		}
				
	}
	
	
	public boolean gameEnded() {
		
		boolean destroyed = false;
		if(board1.areAllCraftsDestroyed() && board2.areAllCraftsDestroyed() && gameStarted) {
			destroyed = true;
		}
		
		return destroyed;
	}
	
	
	public boolean playNext() {
		Coordinate golpeado;
		boolean dev = false;
		
		try {
			golpeado = player1.nextShoot(board2);
			if(golpeado != null) {
				shootCounter ++;
				nextToShoot ++;
				dev = true;
			}
		} catch (InvalidCoordinateException | CoordinateAlreadyHitException | BattleshipIOException e) {
			if(e instanceof CoordinateAlreadyHitException) {
				System.out.println("Action by " + player1.getName() + " Coordinate ya golpeada");
			}
			throw new RuntimeException();
		}
		
		try {
			golpeado = player2.nextShoot(board1);
			if(golpeado != null) {
				shootCounter ++;
				nextToShoot ++;
				dev = true;
			}
		} catch (InvalidCoordinateException | CoordinateAlreadyHitException | BattleshipIOException e) {
			if(e instanceof CoordinateAlreadyHitException) {
				System.out.println("Action by " + player2.getName() + " Coordinate ya golpeada");
			}
			throw new RuntimeException();
		}
		
		
		return dev;
	}
	
	
	public void playGame(IVisualiser visualiser) {
		
	}
	
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		final String characters = "==================================\n";
		
	
		if(gameStarted) {
			sb.append("=== ONGOING GAME ===\n");
		}
		else {
			if(!gameStarted) {
				sb.append("=== GAME NOT STARTED ===\n");
			}
			if(gameEnded()) {
				sb.append("=== GAME ENDED ===\n");
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
		
		if(board1.areAllCraftsDestroyed() && !board2.areAllCraftsDestroyed()) {
			sb.append(player2.getName() + " wins");
		}
		
		else if(!board1.areAllCraftsDestroyed() && board2.areAllCraftsDestroyed()) {
			sb.append(player1.getName() + " wins");
		}
		
		return sb.toString();
	}
}
