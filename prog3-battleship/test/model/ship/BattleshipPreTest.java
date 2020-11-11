package model.ship;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.Coordinate;
import model.Craft;
import model.Orientation;

public class BattleshipPreTest {
	Ship battleshipN, battleshipE, battleshipS, battleshipW;
	static List<Coordinate> north;
	static String sNorth, sEast, sSouth, sWest;
	final static int shape[][] = new int[][] {
	      { 0, 0, 0, 0, 0,
	    	0, 0, 1, 0, 0,	
	    	0, 0, 1, 0, 0,	
	    	0, 0, 1, 0, 0,
	    	0, 0, 1, 0, 0},
	      { 0, 0, 0, 0, 0,
		    0, 0, 0, 0, 0,	
		    0, 1, 1, 1, 1,	
		    0, 0, 0, 0, 0,
		    0, 0, 0, 0, 0},
	      { 0, 0, 0, 0, 0,
	    	0, 0, 1, 0, 0,	
	    	0, 0, 1, 0, 0,	
	    	0, 0, 1, 0, 0,
	    	0, 0, 1, 0, 0},
	      { 0, 0, 0, 0, 0,
		    0, 0, 0, 0, 0,	
		    0, 1, 1, 1, 1,	
		    0, 0, 0, 0, 0,
		    0, 0, 0, 0, 0}}; 
		    
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
			north = new ArrayList<Coordinate>();
			for (int i=1; i < 5; i++) {
				north.add(new Coordinate2D(2,i));
			}
			 sEast = "Battleship (EAST)\n"
		        		+ " -----\n"
		        		+ "|     |\n"
		        		+ "|     |\n"
		        		+ "| OOOO|\n"
		        		+ "|     |\n"
		        		+ "|     |\n"
		        		+ " -----";
				sNorth ="Battleship (NORTH)\n"
						+ " -----\n"
						+ "|     |\n"
						+ "|  O  |\n"
						+ "|  O  |\n"
						+ "|  O  |\n"
						+ "|  O  |\n"
						+ " -----";
				sSouth ="Battleship (SOUTH)\n"
						+ " -----\n"
						+ "|     |\n"
						+ "|  O  |\n"
						+ "|  O  |\n"
						+ "|  O  |\n"
						+ "|  O  |\n"
						+ " -----";
				sWest = "Battleship (WEST)\n"
						+ " -----\n"
						+ "|     |\n"
						+ "|     |\n"
						+ "| OOOO|\n"
						+ "|     |\n"
						+ "|     |\n"
						+ " -----";
	}		    
		    
		    
		    
	@Before
	public void setUp() throws Exception {
		battleshipN = new Battleship(Orientation.NORTH);
		battleshipE = new Battleship(Orientation.EAST);
		battleshipS = new Battleship(Orientation.SOUTH);
		battleshipW = new Battleship(Orientation.WEST);	
	}

	/* Comprobación de shape del alumno */
	@Test
	public void testGetShape() {
		int [][] shapeAux = battleshipN.getShape();
		for (int i=0; i< shape.length; i++) 
			for (int j=0; j<shape[i].length; j++)
				assertEquals(shape[i][j],shapeAux[i][j]);
	}

	//TODO
	/* Comprueba que las orientaciones de los Battleship creados en el setUp son 
	 * correctas.
	 */
	@Test
	public void testGetOrientation() {
		assertEquals(battleshipN.getOrientation(), Orientation.NORTH);
		assertEquals(battleshipS.getOrientation(), Orientation.SOUTH);
		assertEquals(battleshipE.getOrientation(), Orientation.EAST);
		assertEquals(battleshipW.getOrientation(), Orientation.WEST);
	}

	@Test
	public void testGetSymbol() {
		assertEquals('O', battleshipN.getSymbol());
	}

	//TODO
	/* Comprueba que las posiciones absolutas para la orientación NORTH a partir de
	 * una Coordinate son correctas.
	 */
	@Test
	public void testGetAbsolutePositionsNorth() {
		Craft nuevo = new Cruiser(Orientation.NORTH);
		Coordinate coord = new Coordinate2D(4, 2);
		Set<Coordinate> posAbs = nuevo.getAbsolutePositions(coord);
		
		for(Coordinate c: posAbs) {
			System.out.println(c.toString());
			
		}
		Set<Coordinate> posCorrectas = new HashSet<Coordinate>();
		posCorrectas.add(new Coordinate2D(6, 3));
		posCorrectas.add(new Coordinate2D(6, 4));
		posCorrectas.add(new Coordinate2D(6, 5));
		assertEquals(posAbs, posCorrectas);
		
		/*
		 * posCorrectas.add(new Coordinate2D(6, 3));
		posCorrectas.add(new Coordinate2D(6, 4));
		posCorrectas.add(new Coordinate2D(6, 5));
		posCorrectas.add(new Coordinate2D(6, 6));
		assertEquals(posAbs, posCorrectas);*/
	}

	//TODO
	/* Comprueba que toString() para cada Battleship creado en el setUp coincide con 
	 * los correspondientes String creados en setUpBeforeClass()
	 */
	@Test
	public void testToString() {
		assertEquals(battleshipN.toString(), sNorth);
		assertEquals(battleshipS.toString(), sSouth);
		assertEquals(battleshipE.toString(), sEast);
		assertEquals(battleshipW.toString(), sWest);
	}

}
