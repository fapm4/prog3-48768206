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
import model.Orientation;

public class DestroyerPreTest {
	Ship destroyerN, destroyerE, destroyerS, destroyerW;
	static List<Coordinate> north;
	static String sNorth, sEast, sSouth, sWest;
	final static int shape[][] = new int[][] {
	      { 0, 0, 0, 0, 0,
	    	0, 0, 1, 0, 0,	
	    	0, 0, 1, 0, 0,	
	    	0, 0, 0, 0, 0,
	    	0, 0, 0, 0, 0},
	      { 0, 0, 0, 0, 0,
		    0, 0, 0, 0, 0,	
		    0, 1, 1, 0, 0,	
		    0, 0, 0, 0, 0,
		    0, 0, 0, 0, 0},
	      { 0, 0, 0, 0, 0,
		    0, 0, 1, 0, 0,	
	    	0, 0, 1, 0, 0,	
	    	0, 0, 0, 0, 0,
	    	0, 0, 0, 0, 0},
	      { 0, 0, 0, 0, 0,
		    0, 0, 0, 0, 0,	
		    0, 1, 1, 0, 0,	
		    0, 0, 0, 0, 0,
		    0, 0, 0, 0, 0}}; 
		    
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
			north = new ArrayList<Coordinate>();
			for (int i=1; i < 3; i++) {
				north.add(new Coordinate2D(2,i));
			}
	       
			sNorth ="Destroyer (NORTH)\n"
					+ " -----\n"
					+ "|     |\n"
					+ "|  Ω  |\n"
					+ "|  Ω  |\n"
					+ "|     |\n"
					+ "|     |\n"
					+ " -----";
			sEast = "Destroyer (EAST)\n"
		       		+ " -----\n"
		       		+ "|     |\n"
		       		+ "|     |\n"
		       		+ "| ΩΩ  |\n"
		       		+ "|     |\n"
		       		+ "|     |\n"
		       		+ " -----";
			sSouth ="Destroyer (SOUTH)\n"
					+ " -----\n"
					+ "|     |\n"
					+ "|  Ω  |\n"
					+ "|  Ω  |\n"
					+ "|     |\n"
					+ "|     |\n"
					+ " -----";
			sWest = "Destroyer (WEST)\n"
					+ " -----\n"
					+ "|     |\n"
					+ "|     |\n"
					+ "| ΩΩ  |\n"
					+ "|     |\n"
					+ "|     |\n"
					+ " -----";
	}		    
		    
		    
		    
	@Before
	public void setUp() throws Exception {
		destroyerN = new Destroyer(Orientation.NORTH);
		destroyerE = new Destroyer(Orientation.EAST);
		destroyerS = new Destroyer(Orientation.SOUTH);
		destroyerW = new Destroyer(Orientation.WEST);
		
	}

	/* Comprobación de shape del alumno */
	@Test
	public void testGetShape() {
		int [][] shapeAux = destroyerN.getShape();
		for (int i=0; i< shape.length; i++) 
			for (int j=0; j<shape[i].length; j++)
				assertEquals(shape[i][j],shapeAux[i][j]);
	}

	//TODO
	/* Comprueba que las orientaciones de los Destroyer creados en el setUp son 
	 * correctas.
	 */
	@Test
	public void testGetOrientation() {
		assertEquals(destroyerN.getOrientation(), Orientation.NORTH);
		assertEquals(destroyerS.getOrientation(), Orientation.SOUTH);
		assertEquals(destroyerE.getOrientation(), Orientation.EAST);
		assertEquals(destroyerW.getOrientation(), Orientation.WEST);
	}

	@Test
	public void testGetSymbol() {
		assertEquals('Ω', destroyerN.getSymbol());
	}

	//TODO
	/* Comprueba que las posiciones absolutas para la orientación NORTH a partir de
	 * una Coordinate son correctas.
	 */
	@Test
	public void testGetAbsolutePositionsNorth() {
		Coordinate coord = new Coordinate2D(4, 2);
		Set<Coordinate> posAbs = destroyerN.getAbsolutePositions(coord);
		Set<Coordinate> posCorrectas = new HashSet<Coordinate>();
		
		for(Coordinate c: posAbs) {
			System.out.println(c.toString());
			
		}
		posCorrectas.add(new Coordinate2D(6, 3));
		posCorrectas.add(new Coordinate2D(6, 4));
		
		assertEquals(posAbs, posCorrectas);
	}


	//TODO
	/* Comprueba que toString() para cada Destroyer creado en el setUp coincide con 
	 * los correspondientes String creados en setUpBeforeClass()
	 */
	@Test
	public void testToString() {
		assertEquals(destroyerN.toString(), sNorth);
		assertEquals(destroyerS.toString(), sSouth);
		assertEquals(destroyerE.toString(), sEast);
		assertEquals(destroyerW.toString(), sWest);
	}

}
