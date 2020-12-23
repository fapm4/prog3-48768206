package model.score;

import model.io.IPlayer;

// TODO: Auto-generated Javadoc
/**
 * The Class Score.
 * @author Francisco Alejandro Pérez Meneses - 48768206H
 *
 * @param <T> the generic type
 */

public abstract class Score<T> implements Comparable<Score<T>>{
	
	/** The score. */
	protected int score;
	
	/** The player. */
	private IPlayer player;
	
	/**
	 * Instantiates a new score.
	 *
	 * @param player the player
	 */
	public Score(IPlayer player) {
		
		if(player == null) {
			throw new NullPointerException();
		}
		
		this.player = player;
		score = 0;
	}
	
	
	/**
	 * Gets the score.
	 *
	 * @return the score
	 */
	public int getScore() {
		return score;
	}
	
	
	/**
	 * Compare to.
	 *
	 * @param other the other
	 * @return the int
	 */
	public int compareTo(Score<T> other) {
		int value = -1;
		
		if(this.getScore() < other.getScore()) {
			value = 1;
		}
		else if(this.getScore() == other.getScore()) {
			value = player.getName().compareTo(other.player.getName());
		}
		
		return value;
	}
	
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(player.getName() + ": " + score);
		
		return sb.toString();
	}
	
	
	/**
	 * Score.
	 *
	 * @param sc the sc
	 */
	abstract void score(T sc);
}
