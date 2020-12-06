package model.io;

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
		IVisualiser nuevo = null;		
		
		if(n.equals("Console")) {
			VisualiserConsole nuevo2 = new VisualiserConsole(g);
			nuevo = nuevo2;
		}
		else if(n.equals("GIF")) {
			VisualiserGIF nuevo2 = new VisualiserGIF(g);
			nuevo = nuevo2;
		}
		
		return nuevo;
	}
}
