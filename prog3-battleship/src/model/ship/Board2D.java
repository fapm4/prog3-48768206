package model.ship;

import java.util.HashMap;
import java.util.HashSet;

import model.Board;
import model.Coordinate;
import model.CoordinateFactory;
import model.Craft;

// TODO: Auto-generated Javadoc
/**
 * The Class Board.
 *
 * @author Francisco Alejandro Pérez Meneses - 48768206H
 */

public abstract class Board2D extends Board {
	
	/**
	 * Instantiates a new board.
	 *
	 * @param size the size
	 */
	public Board2D(int size) {
		super(size);
	}
	
	/**
	 * Check coordinate.
	 *
	 * @param c the c
	 * @return true, if successful
	 */
	@Override
	public boolean checkCoordinate(Coordinate c) {
		
		int size = getSize();
		
		if(c instanceof Coordinate2D) {
			if(c.get(0) < 0 | c.get(1) < 0 | c.get(0) > (size - 1) | c.get(1) > (size - 1)) {
				return false;
			}
			else {
				return true;
			}	
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Show.
	 *
	 * @param unveil the unveil
	 * @return the string
	 * @throws Exception 
	 */
	@Override
	public String show(boolean unveil) throws Exception {
		StringBuilder sb = new StringBuilder();
		int size = getSize();
		
		for(int i = 0;i < size;i++) {
			for(int j = 0;j < size;j++) {
				int coords[] = {i, j};
				Coordinate nueva = CoordinateFactory.createCoordinate(coords);
				Craft barco = getCraft(nueva);
				
				if(unveil) {
					if(barco != null) {
						if(barco.isHit(nueva)) {
							sb.append(HIT_SYMBOL);
						}
						else {
							sb.append(barco.getSymbol());
						}
					}
					else {
						sb.append(WATER_SYMBOL);
					}
					
				}
				
				else {
					
					if(isSeen(nueva)) {
						if(barco != null) {
							if(barco.isHit(nueva)) {
								if(barco.isShotDown()) {
									sb.append(barco.getSymbol());
								}
								else {
									sb.append(HIT_SYMBOL);
								}
							}
						}
						else {
							sb.append(WATER_SYMBOL);
						}
					}
					else {
						sb.append(NOTSEEN_SYMBOL);
					}
				}
			}
			
			if(i < size - 1) {
				sb.append("\n");
			}
		}
		
		return sb.toString();
	}
}
