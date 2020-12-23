package model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

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
	 * @param craft the craft
	 * @param o the o
	 * @return the craft
	 */
	public static Craft createCraft (String craft, Orientation o) {
		
		Class<?> craftClass = null;
		Constructor<?> constructor = null;
		Craft newCraft = null;
		
		
		try {
			craftClass = Class.forName("model." + craft);
			constructor = craftClass.getConstructor(Orientation.class);
			newCraft = (Craft)constructor.newInstance(o);
		}
		catch(Exception e) {
			newCraft = null;
		}
		
		
		return newCraft;
	}
}
