package model;

import model.aircraft.Bomber;
import model.aircraft.Fighter;
import model.aircraft.Transport;
import model.ship.Battleship;
import model.ship.Carrier;
import model.ship.Cruiser;
import model.ship.Destroyer;


// TODO: Auto-generated Javadoc
/**
 * A factory for creating Craft objects.
 * @author Francisco Alejandro Pérez Meneses - 48768206H
 */
public class CraftFactory {

	/**
	 * Creates a new Craft object.
	 *
	 * @param type the type
	 * @param o the o
	 * @return the craft
	 */
	public static Craft createCraft (String type, Orientation o) {
		Craft newCraft = null;
		
		switch(type) {
			case "Battleship":
				newCraft = new Battleship(o);
				break;
				
			case "Carrier":
				newCraft = new Carrier(o);
				break;
				
			case "Cruiser":
				newCraft = new Cruiser(o);
				break;
				
			case "Destroyer":
				newCraft = new Destroyer(o);
				break;
				
			case "Bomber":
				newCraft = new Bomber(o);
				break;
				
			case "Fighter":
				newCraft = new Fighter(o);
				break;
				
			case "Transport":
				newCraft = new Transport(o);
				break;
				
			default:
				newCraft = null;
		}
		
		return newCraft;
	}
}
