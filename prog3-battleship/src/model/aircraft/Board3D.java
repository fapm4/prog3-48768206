package model.aircraft;

import model.Board;
import model.Coordinate;
import model.Craft;
import model.ship.Coordinate2D;


public class Board3D extends Board{
	
	public Board3D(int size) {
		super(size);
	}
	
	
	@Override
	public boolean checkCoordinate(Coordinate c) {
		
		int size = getSize();
		boolean dev = true;
		
		if(c instanceof Coordinate3D) {
			if(c.get(0) < 0 | c.get(1) < 0 | c.get(2) < 0 | c.get(0) > size | c.get(1) > size | c.get(2) > size) {
				dev = false;
			}
		}
		else {
			throw new IllegalArgumentException();
		}
	
		return dev;
	}
	
	
	@Override
	public String show(boolean unveil) {
		StringBuilder sb = new StringBuilder();
		int size = getSize();
		
		Coordinate nueva = null;
		Craft craft = null;
		
		for(int i = 0;i < size;i++) {
			for(int j = 0;j < size;j++) {
				for(int k = 0;k < size;k++) {
					
					// i j k
					// i k j -
					//j i k
					//j k i
					//k i j
					//k j i
					nueva = new Coordinate3D(k, i, j);
					craft = getCraft(nueva);
					
					if(unveil) {
						if(craft != null) {
							if(craft.isHit(nueva)) {
								sb.append(HIT_SYMBOL);
							}
							else {
								sb.append(craft.getSymbol());
							}
						}
						else {
							sb.append(WATER_SYMBOL);
						}
					}
					
					else {
						if(isSeen(nueva)) {
							if(craft != null) {
								if(craft.isHit(nueva)) {
									if(craft.isShotDown()) {
										sb.append(craft.getSymbol());
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
				if(j != size -1 ) {
					sb.append(Board.Board_SEPARATOR);	
				}
			}
			if(i != size - 1) {
				sb.append("\n");
			}
		}
		return sb.toString();
	}
}

