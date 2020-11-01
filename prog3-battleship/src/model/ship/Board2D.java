package model.ship;

import model.Board;
import model.Coordinate;
import model.Craft;

public class Board2D extends Board {
	
	public Board2D(int size) {
		super(size);
	}
	

	@Override
	public boolean checkCoordinate(Coordinate c) {
		
		int size = getSize();
		boolean dev = true;
		
		if(c instanceof Coordinate2D) {
			if(c.get(0) < 0 | c.get(1) < 0 | c.get(0) > (size - 1) | c.get(1) > (size - 1)) {
				return false;
			}
		}
		else {
			throw new IllegalArgumentException();
		}
		
		return dev;
	}
	

	@Override
	public String show(boolean unveil){
		
		StringBuilder sb = new StringBuilder();
		int size = getSize();
		Craft barco = null;
		Coordinate nueva = null;
		
		for(int i = 0;i < size;i++) {
			for(int j = 0;j < size;j++) {
				nueva = new Coordinate2D(j, i);
				barco = getCraft(nueva);
				
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
