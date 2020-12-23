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
		
		// Variable para almacenar la clase
		Class<?> craftClass = null;
		
		// Variable para almacenar si es un ship o aircraft
		boolean typeCraft = getModelCraft(craft);
		
		// Variable para guardar el constructor
		Constructor<?> constructor = null;
		Craft newCraft = null;
		
		String type = getCraftClass(craft);
		
		try {
			if(type != "") {
				if(typeCraft) {
					craftClass = Class.forName("model.ship." + type);
				}
				
				else {
					craftClass = Class.forName("model.aircraft." + type);
				}
				
				constructor = craftClass.getConstructor(Orientation.class);
				newCraft = (Craft)constructor.newInstance(o);
			}
		}
		catch(Exception e) {
			newCraft = null;
		}
		
		
		return newCraft;
	}
	
	
	/**
	 * Gets the model craft.
	 *
	 * @param craft the craft
	 * @return the model craft
	 */
	private static boolean getModelCraft(String craft) {
		boolean t = false;
		
		String[] components = craft.split("\\.");
		if(components[0].equals("ship")) {
			t = true;
		}
		
		else if(components[0].equals("aircraft")) {
			t = false;
		}
	
		return t;
	}
	
	
	/**
	 * Gets the craft class.
	 *
	 * @param craft the craft
	 * @return the craft class
	 */
	private static String getCraftClass(String craft) {
		String[] components = craft.split("\\.");
		
		if(components.length == 1) {
			return "";
		}
		
		return components[1];
	}
}
