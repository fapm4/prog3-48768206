package model.aircraft;

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
import model.ship.Coordinate2D;

public class TransportPreTest {
	Aircraft transportN, transportE, transportS, transportW;
	static List<Coordinate> north;
	static String sNorth, sEast, sSouth, sWest;
	final static int shape[][] = new int[][] {
		 { 0, 0, 1, 0, 0,
		   0, 0, 1, 0, 0,	
		   0, 1, 1, 1, 0,	
		   1, 0, 1, 0, 1,
		   0, 0, 1, 0, 0},
		 { 0, 1, 0, 0, 0,
		   0, 0, 1, 0, 0,	
		   1, 1, 1, 1, 1,	
		   0, 0, 1, 0, 0,
		   0, 1, 0, 0, 0},
		 { 0, 0, 1, 0, 0,
		   1, 0, 1, 0, 1,	
		   0, 1, 1, 1, 0,	
		   0, 0, 1, 0, 0,
		   0, 0, 1, 0, 0},
		 { 0, 0, 0, 1, 0,
		   0, 0, 1, 0, 0,	
		   1, 1, 1, 1, 1,	
		   0, 0, 1, 0, 0,
		   0, 0, 0, 1, 0}}; 
		    
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
			north = new ArrayList<Coordinate>();
			for (int i=0; i < 5; i++) {
				north.add(new Coordinate3D(2,i,0));	
				if (i<2) {
					north.add(new Coordinate3D(i,3-i,0));
					north.add(new Coordinate3D(i+3,i+2,0));
				}
			}
			
			sNorth ="Transport (NORTH)\n"
					+ " -----\n"
					+ "|  ⇋  |\n"
					+ "|  ⇋  |\n"
					+ "| ⇋⇋⇋ |\n"
					+ "|⇋ ⇋ ⇋|\n"
					+ "|  ⇋  |\n"
					+ " -----";
			sEast = "Transport (EAST)\n"
					+ " -----\n"
					+ "| ⇋   |\n"
					+ "|  ⇋  |\n"
					+ "|⇋⇋⇋⇋⇋|\n"
					+ "|  ⇋  |\n"
					+ "| ⇋   |\n"
					+ " -----";
			sSouth ="Transport (SOUTH)\n"
					+ " -----\n"
					+ "|  ⇋  |\n"
					+ "|⇋ ⇋ ⇋|\n"
					+ "| ⇋⇋⇋ |\n"
					+ "|  ⇋  |\n"
					+ "|  ⇋  |\n"
					+ " -----";
			sWest = "Transport (WEST)\n"
					+ " -----\n"
					+ "|   ⇋ |\n"
					+ "|  ⇋  |\n"
					+ "|⇋⇋⇋⇋⇋|\n"
					+ "|  ⇋  |\n"
					+ "|   ⇋ |\n"
					+ " -----";
	}		    
		    
		    
		    
	@Before
	public void setUp() throws Exception {
		transportN = new Transport(Orientation.NORTH);
		transportE = new Transport(Orientation.EAST);
		transportS = new Transport(Orientation.SOUTH);
		transportW = new Transport(Orientation.WEST);
		
	}

	/* Comprobación de shape del alumno */
	@Test
	public void testGetShape() {
		int [][] shapeAux = transportN.getShape();
		for (int i=0; i< shape.length; i++) 
			for (int j=0; j<shape[i].length; j++)
				assertEquals(shape[i][j],shapeAux[i][j]);
	}

	//TODO
	/* Comprueba que las orientaciones de los Transport creados en el setUp son 
	 * correctas.
	 */
	@Test
	public void testGetOrientation() {
		assertEquals(transportN.getOrientation(), Orientation.NORTH);
		assertEquals(transportS.getOrientation(), Orientation.SOUTH);
		assertEquals(transportE.getOrientation(), Orientation.EAST);
		assertEquals(transportW.getOrientation(), Orientation.WEST);
	}

	@Test
	public void testGetSymbol() {
		assertEquals('⇋', transportN.getSymbol());
	}
	
	//TODO
	/* Se comprueba que las posiciones absolutas para la orientación NORTH a partir de
	 * una Coordinate son correctas.
	 */
	@Test
	public void testGetAbsolutePositionsNorth() {
		transportN.setPosition(new Coordinate2D(4, 2));
		Set<Coordinate> posAbs = transportN.getAbsolutePositions();
		Set<Coordinate> posC = new HashSet<Coordinate>();
		posC.add(new Coordinate2D(6, 2));
		posC.add(new Coordinate2D(6, 3));
		posC.add(new Coordinate2D(5, 4));
		posC.add(new Coordinate2D(6, 4));
		posC.add(new Coordinate2D(7, 4));
		posC.add(new Coordinate2D(4, 5));
		posC.add(new Coordinate2D(6, 5));
		posC.add(new Coordinate2D(8, 5));
		posC.add(new Coordinate2D(6, 6));
		
		assertEquals(posC, posAbs);
		
		for(Coordinate c: posAbs) {
			//System.out.println(c);
		}
	}
		
	//TODO
	/* Comprueba que toString() para cada Transport creado en el setUp coincide con 
	 * los correspondientes strings creados en setUpBeforeClass()
	 */
	@Test
	public void testToString() {
		assertEquals(transportN.toString(), sNorth);
		assertEquals(transportS.toString(), sSouth);
		assertEquals(transportE.toString(), sEast);
		assertEquals(transportW.toString(), sWest);
	}

}
