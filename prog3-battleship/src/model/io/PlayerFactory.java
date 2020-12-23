package model.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import model.exceptions.io.BattleshipIOException;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Player objects.
 * @author Francisco Alejandro Pérez Meneses - 48768206H
 */

public class PlayerFactory {
	
	/**
	 * Busca caracteres.
	 *
	 * @param s the s
	 * @return true, if successful
	 */
	private static boolean buscaCaracteres(String s) {
		boolean found = false;
		
		for(int i = 0;i < s.length() && found == false; i++) {
			if(s.charAt(i) == '.' || s.charAt(i) == '\'' || s.charAt(i) == '/') {
				found = true;
			}
		}
		return found;
	}
	
	
	/**
	 * Busca long.
	 *
	 * @param s the s
	 * @return true, if successful
	 */
	private static boolean buscaLong(String s) {
		boolean found;
			try {
				Long.parseLong(s);
				found = true;
			}
			catch(NumberFormatException e) {
				found = false;
			}
			
		return found;
	}
	
	
	/**
	 * Creates a new Player object.
	 *
	 * @param name the name
	 * @param s the s
	 * @return the i player
	 * @throws BattleshipIOException the battleship IO exception
	 */
	public static IPlayer createPlayer(String name, String s) throws BattleshipIOException {
		boolean charactersFound = buscaCaracteres(s);
		boolean longFound = buscaLong(s);
		IPlayer nuevo = null;
		
		if(s == null) {
			throw new NullPointerException();
		}
		
		File file = new File(s);
		if(s.length() > 0) {
			if(charactersFound) {
				// Creo un PlayerFile
				try {
					BufferedReader br = new BufferedReader(new FileReader(s));
					nuevo = new PlayerFile(name, br);
				}
				catch(IOException e) {
					throw new BattleshipIOException("No se pudo crear el BufferedReader");
				}
			}
			
			else {
				// Creo un PlayerRandom
				if(longFound) {
					nuevo = new PlayerRandom(name, Long.parseLong(s));
				}
			}
		}
		return nuevo;
	}
}
