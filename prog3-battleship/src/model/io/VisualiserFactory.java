package model.io;

import model.Game;

public class VisualiserFactory {
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
