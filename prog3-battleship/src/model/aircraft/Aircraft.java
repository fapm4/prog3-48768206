package model.aircraft;

import model.Craft;
import model.Orientation;

public abstract class Aircraft extends Craft{
	public Aircraft(Orientation o, char s, String n) {
		super(o, s, n);
	}
}
