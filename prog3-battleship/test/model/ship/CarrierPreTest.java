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

public class CarrierPreTest {
	Ship carrierN, carrierE, carrierS, carrierW;
	static List<Coordinate> north;
	static String sNorth, sEast, sSouth, sWest;
	final static int shape[][] = new int[][] {
		  { 0, 0, 1, 0, 0,
		   	0, 0, 1, 0, 0,	
		   	0, 0, 1, 0, 0,	
		   	0, 0, 1, 0, 0,
		   	0, 0, 1, 0, 0},
		  { 0, 0, 0, 0, 0,
		    0, 0, 0, 0, 0,	
		    1, 1, 1, 1, 1,	
		    0, 0, 0, 0, 0,
		    0, 0, 0, 0, 0},
		  { 0, 0, 1, 0, 0,
		   	0, 0, 1, 0, 0,	
		   	0, 0, 1, 0, 0,	
		   	0, 0, 1, 0, 0,
		   	0, 0, 1, 0, 0},
		  { 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0,	
			1, 1, 1, 1, 1,	
			0, 0, 0, 0, 0,
			0, 0, 0, 0, 0}};  
		    
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
			north = new ArrayList<Coordinate>();
			for (int i=0; i < 5; i++) {
				north.add(new Coordinate2D(2,i));
			}
			 sEast = "Carrier (EAST)\n -----\n"
		        		+ "|     |\n"
		        		+ "|     |\n"
		        		+ "|®®®®®|\n"
		        		+ "|     |\n"
		        		+ "|     |\n"
		        		+ " -----";
				sNorth ="Carrier (NORTH)\n -----\n"
						+ "|  ®  |\n"
						+ "|  ®  |\n"
						+ "|  ®  |\n"
						+ "|  ®  |\n"
						+ "|  ®  |\n"
						+ " -----";
				sSouth ="Carrier (SOUTH)\n"
						+ " -----\n"
						+ "|  ®  |\n"
						+ "|  ®  |\n"
						+ "|  ®  |\n"
						+ "|  ®  |\n"
						+ "|  ®  |\n"
						+ " -----";
				sWest = "Carrier (WEST)\n"
						+ " -----\n"
						+ "|     |\n"
						+ "|     |\n"
						+ "|®®®®®|\n"
						+ "|     |\n"
						+ "|     |\n"
						+ " -----";	
	}		    
		    
		        
	@Before
	public void setUp() throws Exception {
		carrierN = new Carrier(Orientation.NORTH);
		carrierE = new Carrier(Orientation.EAST);
		carrierS = new Carrier(Orientation.SOUTH);
		carrierW = new Carrier(Orientation.WEST);
		
	}

	/* Comprobación de shape del alumno */
	@Test
	public void testGetShape() {
		int [][] shapeAux = carrierN.getShape();
		for (int i=0; i< shape.length; i++) 
			for (int j=0; j<shape[i].length; j++)
				assertEquals(shape[i][j],shapeAux[i][j]);
	}

	//TODO
	/* Comprueba que las orientaciones de los Carrier creados en el setUp son 
	 * correctas.
	 */
	@Test
	public void testGetOrientation() {
		assertEquals(carrierN.getOrientation(), Orientation.NORTH);
		assertEquals(carrierS.getOrientation(), Orientation.SOUTH);
		assertEquals(carrierE.getOrientation(), Orientation.EAST);
		assertEquals(carrierW.getOrientation(), Orientation.WEST);	}

	@Test
	public void testGetSymbol() {
		assertEquals('®', carrierN.getSymbol());
	}

	//TODO
	/* Comprueba que las posiciones absolutas para la orientación NORTH a partir de
	 * una Coordinate son correctas.
	 */
	@Test
	public void testGetAbsolutePositionsNorth() {
		Coordinate coord = new Coordinate2D(4, 2);
		Set<Coordinate> posAbs = carrierN.getAbsolutePositions(coord);
		
		for(Coordinate c: posAbs) {
			System.out.println(c.toString());
			
		}
		Set<Coordinate> posCorrectas = new HashSet<Coordinate>();
		posCorrectas.add(new Coordinate2D(6, 2));
		posCorrectas.add(new Coordinate2D(6, 3));
		posCorrectas.add(new Coordinate2D(6, 4));
		posCorrectas.add(new Coordinate2D(6, 5));
		posCorrectas.add(new Coordinate2D(6, 6));
		
		
		assertEquals(posAbs, posCorrectas);
	}
		

	//TODO
	/* Comprueba que toString() para cada Carrier creado en el setUp coincide con 
	 * los correspondientes String creados en setUpBeforeClass()
	 */
	@Test
	public void testToString() {
		assertEquals(carrierN.toString(), sNorth);
		assertEquals(carrierS.toString(), sSouth);
		assertEquals(carrierE.toString(), sEast);
		assertEquals(carrierW.toString(), sWest);
	}

}
