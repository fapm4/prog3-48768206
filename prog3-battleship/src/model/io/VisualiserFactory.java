package model.io;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import model.Game;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Visualiser objects.
 * @author Francisco Alejandro Pérez Meneses - 48768206H
 */
public class VisualiserFactory {
	
	/**
	 * Creates a new Visualiser object.
	 *
	 * @param n the n
	 * @param g the g
	 * @return the i visualiser
	 */
	public static IVisualiser createVisualiser(String n, Game g) {
		Class<?> visualiserClass = null;
		Constructor[] constructor = null;
		IVisualiser nueva = null;
		
		
		try {
			visualiserClass = Class.forName("model.io.Visualiser" + n);
			constructor = visualiserClass.getConstructors();
			nueva = (IVisualiser)constructor[0].newInstance(g);
		}
		catch(Exception e) {
			nueva = null;
		}
		
		return nueva;
	}
}
