package model.score;

import model.CellStatus;
import model.io.IPlayer;

// TODO: Auto-generated Javadoc
/**
 * The Class HitScore.
 * @author Francisco Alejandro Pérez Meneses - 48768206H
 */

public class HitScore extends Score<CellStatus>{

	/**
	 * Instantiates a new hit score.
	 *
	 * @param player the player
	 */
	public HitScore(IPlayer player) {
		super(player);
	}
	

	/**
	 * Score.
	 *
	 * @param sc the sc
	 */
	@Override
	public void score(CellStatus sc) {
		if(sc != null) {
			if(sc.equals(CellStatus.HIT) | sc.equals(CellStatus.DESTROYED)) {
				score ++;
			}
		}
	}
}
