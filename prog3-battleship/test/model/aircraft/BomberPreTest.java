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

public class BomberPreTest {
	Aircraft bomberN, bomberE, bomberS, bomberW;
	static List<Coordinate> north;
	static String sNorth, sEast, sSouth, sWest;
	final static int shape[][] = new int[][] {
	    { 0, 0, 0, 0, 0,
	      0, 0, 1, 0, 0,	
	      1, 1, 1, 1, 1,	
	      1, 0, 1, 0, 1,
	      0, 0, 1, 0, 0},
	    { 0, 1, 1, 0, 0,
		  0, 0, 1, 0, 0,	
		  1, 1, 1, 1, 0,	
		  0, 0, 1, 0, 0,
		  0, 1, 1, 0, 0},
	    { 0, 0, 1, 0, 0,
		  1, 0, 1, 0, 1,	
		  1, 1, 1, 1, 1,	
		  0, 0, 1, 0, 0,
		  0, 0, 0, 0, 0},
		{ 0, 0, 1, 1, 0,
		  0, 0, 1, 0, 0,	
		  0, 1, 1, 1, 1,	
		  0, 0, 1, 0, 0,
		  0, 0, 1, 1, 0}
	}; 
		    
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
			north = new ArrayList<Coordinate>();
			for (int i=0; i < 5; i++) {
				north.add(new Coordinate3D(i,2,0));	
				if (i<4) {
					north.add(new Coordinate3D(2,i+1,0));
				}
				if ((i==0)||(i==4)) {
					north.add(new Coordinate3D(i,3,0));
				}
			}
			sNorth = "Bomber (NORTH)\n"
					+ " -----\n"
					+ "|     |\n"
					+ "|  ⇶  |\n"
					+ "|⇶⇶⇶⇶⇶|\n"
					+ "|⇶ ⇶ ⇶|\n"
					+ "|  ⇶  |\n"
					+ " -----";
			
			sEast = "Bomber (EAST)\n"
					+ " -----\n"
					+ "| ⇶⇶  |\n"
					+ "|  ⇶  |\n"
					+ "|⇶⇶⇶⇶ |\n"
					+ "|  ⇶  |\n"
					+ "| ⇶⇶  |\n"
					+ " -----";
			sSouth ="Bomber (SOUTH)\n"
					+ " -----\n"
					+ "|  ⇶  |\n"
					+ "|⇶ ⇶ ⇶|\n"
					+ "|⇶⇶⇶⇶⇶|\n"
					+ "|  ⇶  |\n"
					+ "|     |\n"
					+ " -----";
			sWest = "Bomber (WEST)\n"
					+ " -----\n"
					+ "|  ⇶⇶ |\n"
					+ "|  ⇶  |\n"
					+ "| ⇶⇶⇶⇶|\n"
					+ "|  ⇶  |\n"
					+ "|  ⇶⇶ |\n"
					+ " -----";
	}		    
		    
		    
		    
	@Before
	public void setUp() throws Exception {
		bomberN = new Bomber(Orientation.NORTH);
		bomberE = new Bomber(Orientation.EAST);
		bomberS = new Bomber(Orientation.SOUTH);
		bomberW = new Bomber(Orientation.WEST);
		
	}

	/* Comprobación de shape del alumno */
	@Test
	public void testGetShape() {
		int [][] shapeAux = bomberN.getShape();
		for (int i=0; i< shape.length; i++) 
			for (int j=0; j<shape[i].length; j++)
				assertEquals(shape[i][j],shapeAux[i][j]);
	}

	//TODO
	/* Comprueba que las orientaciones de los Bomber creados en el setUp son 
	 * correctas.
	 */
	@Test
	public void testGetOrientation() {
		assertEquals(bomberN.getOrientation(), Orientation.NORTH);
		assertEquals(bomberS.getOrientation(), Orientation.SOUTH);
		assertEquals(bomberE.getOrientation(), Orientation.EAST);
		assertEquals(bomberW.getOrientation(), Orientation.WEST);
	}

	@Test
	public void testGetSymbol() {
		assertEquals('⇶', bomberN.getSymbol());
	}

	//TODO
	/* Comprueba que las posiciones absolutas para la orientación NORTH a partir de
	 * una Coordinate son correctas.
	 */
	@Test
	public void testGetAbsolutePositionsNorth() {
		bomberN.setPosition(new Coordinate2D(4, 3));
		
		Set<Coordinate> posAbs = bomberN.getAbsolutePositions();
		Set<Coordinate> posC = new HashSet<Coordinate>();
		posC.add(new Coordinate2D(6, 4));
		posC.add(new Coordinate2D(4, 5));
		posC.add(new Coordinate2D(5, 5));
		posC.add(new Coordinate2D(6, 5));
		posC.add(new Coordinate2D(7, 5));
		posC.add(new Coordinate2D(8, 5));
		posC.add(new Coordinate2D(4, 6));
		posC.add(new Coordinate2D(6, 6));
		posC.add(new Coordinate2D(8, 6));
		posC.add(new Coordinate2D(6, 7));
		
		for(Coordinate c: posAbs) {
			//System.out.println(c);
		}
		
		assertEquals(posAbs, posC);
	}
		
	//TODO
	/* Comprueba que toString() para cada Bomber creado en el setUp coincide con 
	 * los correspondientes strings creados en setUpBeforeClass()
	 */
	@Test
	public void testToString() {
		assertEquals(bomberN.toString(), sNorth);
		assertEquals(bomberS.toString(), sSouth);
		assertEquals(bomberE.toString(), sEast);
		assertEquals(bomberW.toString(), sWest);
	}

}
