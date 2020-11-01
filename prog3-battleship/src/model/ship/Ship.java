package model.ship;

import model.Craft;
import model.Orientation;

public abstract class Ship extends Craft {
	
	public Ship(Orientation o, char s, String n) {
		super(o, s, n);
	}
}
