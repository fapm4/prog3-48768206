package model.ship;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.Board;
import model.Coordinate;
import model.Orientation;
import model.aircraft.Coordinate3D;
import model.exceptions.BattleshipException;
import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;
import model.exceptions.NextToAnotherCraftException;
import model.exceptions.OccupiedCoordinateException;

public class Board2DPreTest {
	Ship destroyer, carrier, battleship;
	Board board;
	
	static String sboard0, sboard1, sboard2, sboard3;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sboard0 = "??????\n" + 
				  "??????\n" + 
				  "??????\n" + 
				  "??????\n" + 
				  "??????\n" + 
				  "??????";
		
		sboard1 = "O    ®\n"
				+ "O    ®\n"
				+ "O    ®\n"
				+ "O    ®\n"
				+ "     ®\n"
				+ "  ΩΩ  "; 
		
		sboard2 = "•    •\n" + 
				  "•    •\n" + 
				  "•    •\n" + 
				  "•    •\n" + 
				  "     •\n" + 
				  "  ••  ";
		
		sboard3 = "O ?? ®\n" + 
				  "O ?? ®\n" + 
				  "O ?? ®\n" + 
				  "O ?? ®\n" + 
				  "     ®\n" + 
				  "? ΩΩ  ";
	}

	@Before
	public void setUp() throws Exception {
		board = new Board2D(6);
		destroyer = new Destroyer(Orientation.EAST);
		carrier = new Carrier(Orientation.SOUTH);
		battleship = new Battleship(Orientation.NORTH);
	}
	
	@Test
	public void testGetSize( ) {
		 assertEquals(6,board.getSize());
		 board = new Board2D(17);
		 assertEquals(17, board.getSize());
	}

	@Test
	public void testCheckCoordinateOk() {
		assertTrue(board.checkCoordinate(new Coordinate2D(0,0)));
		assertTrue(board.checkCoordinate(new Coordinate2D(0,5)));
		assertTrue(board.checkCoordinate(new Coordinate2D(5,0)));
		assertTrue(board.checkCoordinate(new Coordinate2D(5,5)));
	}
	
	//TODO
	/* Añade los Crafts correctamente en el board tal como aparecen:
	 *  ------
	 * |O	 ®|
	 * |O    ®|
	 * |O	 ®|
	 * |O	 ®|
	 * |	 ®|
	 * |  ΩΩ  |
	 *  ------
	 */
	@Test
	public void testAddCraftOk()  {
		try {
			 board.addCraft(destroyer, new Coordinate2D(1, 3));
			 Set<Coordinate> posAbs = destroyer.getAbsolutePositions(new Coordinate2D(1, 3));
			 
			 for(Coordinate c: posAbs) {
				System.out.println(c);
			 }
			 
			 //System.out.println("\nVecindario\n");
			 Set<Coordinate> vec = board.getNeighborhood(destroyer, new Coordinate2D(1, 3));
			 
			 for(Coordinate c: vec) {
				 // System.out.println(c);
			 }
			 
			 //System.out.println("-------");
		}
		catch(BattleshipException e) {
			e.getMessage();
		}
		
		try {
			board.addCraft(battleship, new Coordinate2D(-2, -1));
			Set<Coordinate> posAbs = battleship.getAbsolutePositions(new Coordinate2D(-2, -1));
			 for(Coordinate c: posAbs) {
				 // System.out.println(c);
			 }
			 
			 // System.out.println("\nVecindario\n");
			 Set<Coordinate> vec = board.getNeighborhood(destroyer, new Coordinate2D(-2, -1));
			 
			 for(Coordinate c: vec) {
				 // System.out.println(c);
			 }
			 
			 // System.out.println("-------");
		}
		
		catch(BattleshipException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			board.addCraft(carrier, new Coordinate2D(3, 0));
			Set<Coordinate> posAbs = carrier.getAbsolutePositions(new Coordinate2D(3, 0));
			 for(Coordinate c: posAbs) {
				 // System.out.println(c);
			 }
			 
			 // System.out.println("\nVecindario\n");
			 Set<Coordinate> vec = board.getNeighborhood(destroyer, new Coordinate2D(3, 0));
			 
			 for(Coordinate c: vec) {
				 //	 System.out.println(c);
			 }
			 // System.out.println("-------");
		}
		
		catch(BattleshipException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println(board.show(true));
	}
	
	//TODO
	/* Comprueba checkCoordinate con Coordinates fuera de los límites del
	 * tablero.
	 */
	@Test
	public void testCheckCoordinateOutOfLimits() {
		try {
			assertFalse(board.checkCoordinate(new Coordinate2D(20, 0)));
		}
		
		catch(Exception e) {
			System.out.println("Fueraaa");
		}
		
		try {
			assertFalse(board.checkCoordinate(new Coordinate2D(7, 0)));
		}
		
		catch(Exception e) {
			System.out.println("Fueraaa");
		}
		
		try {
			assertFalse(board.checkCoordinate(new Coordinate2D(-1, 0)));
		}
		
		catch(Exception e) {
			System.out.println("Fueraaa");
		}
	}
	
	/* Se comprueba que al pasarle una Coordinate3D a checkCoordinate aplicado sobre
	 * un Board2D, se lanza la excepción IllegalArgumentException
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCheckCoordinateException() {
		assertTrue(board.checkCoordinate(new Coordinate3D(3,4,0)));
		
	}

	//TODO
	/* Posiciona los Crafts en el tablero según aparecen en testAddCraftOk. 
	 * Compara que board.show(true) genera lo mismo que sboard1 y
	 * board.show(false) lo mismo que sboard0
	 */
	@Test
	public void testShow1() {
		testAddCraftOk();
		assertEquals(board.show(true), sboard1);
		assertEquals(board.show(false), sboard0);
	}
	
	//TODO
	/* Posiciona los Crafts según testAddCraftOk y después dispara sobre ellos 
	 * hundiéndolos todos. Comprueba que show(true) y show(false) coincide
	 * con sboard2 y sboard3 respectivamente.
	 */
	@Test
	public void testShow2() throws InvalidCoordinateException, CoordinateAlreadyHitException {
		testAddCraftOk();
		
		destroyer.setPosition(new Coordinate2D(1, 3));
		
		board.hit(new Coordinate2D(2, 5));
		board.hit(new Coordinate2D(3, 5));
		
		board.hit(new Coordinate2D(0, 0));
		board.hit(new Coordinate2D(0, 1));
		board.hit(new Coordinate2D(0, 2));
		board.hit(new Coordinate2D(0, 3));
		
		board.hit(new Coordinate2D(5, 0));
		board.hit(new Coordinate2D(5, 1));
		board.hit(new Coordinate2D(5, 2));
		board.hit(new Coordinate2D(5, 3));
		board.hit(new Coordinate2D(5, 4));
		
		System.out.println(board.show(true));
		assertEquals(board.show(true), sboard2);
		assertEquals(board.show(false), sboard3);
		
	}
	
	//TODO
	/* Realiza disparos fuera del board y comprueba que se lanza la excepción
	 * InvalidCoordinateException
	 */
	@Test
	public void testHitInvalidCoordinate() throws CoordinateAlreadyHitException, InvalidCoordinateException {
		
		try{
			board.hit(new Coordinate2D(7, 0));
		}
		catch(BattleshipException e) {
			System.out.println(e.getMessage());
		}
		
		try{
			board.hit(new Coordinate2D(2, 0));
		}
		catch(BattleshipException e) {
			System.out.println(e.getMessage());
		}
		
		try{
			board.hit(new Coordinate2D(10, 3));
		}
		catch(BattleshipException e) {
			System.out.println(e.getMessage());
		}
		
		try{
			board.hit(new Coordinate2D(9, -20));
		}
		catch(BattleshipException e) {
			System.out.println(e.getMessage());
		}
	}

	
	/* Se comprueba que al disparar sobre una Coordenada3D se lanza la excepción 
	 * IllegalArgumentException
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testHitIllegalArgument() throws CoordinateAlreadyHitException, InvalidCoordinateException {
				board.hit(new Coordinate3D(3,-1, 7));
	}
	
		
	
	//TODO
	/* Posiciona los Crafts según testAddCraftOk().
	 * Se intenta añadir un Craft Destroyer (en la imagen inferior aparecen con 'DD' para
	 * indicar dónde se intenta poner) en la que, parte está fuera del tablero y
	 * parte en una posición ocupada por un  Battleship. Se comprueba que se lanza la
	 * excepción InvalidCoordinateException
	 *    ------
	 *   |O	   ®|
	 *   |O    ®|
	 *   |O	   ®|
	 *   DD    ®|
	 *   |	   ®|
	 *   |  ΩΩ  |
	 *    ------
	 */
	@Test
	public void testAddCraftOutOccupied() {
		testAddCraftOk();		
		Coordinate2D c=new Coordinate2D(-2,1); 
		try {		
			board.addCraft(destroyer, c);
			fail("Error: debería haberlanzado la excepción InvalidCoordinateException");
		} catch (InvalidCoordinateException e) {
			assertNotNull (board.getCraft(new Coordinate2D(0,3)));
			assertTrue(board.getCraft(new Coordinate2D(0,3)).getClass().getName()=="model.ship.Battleship");
		} catch (NextToAnotherCraftException | OccupiedCoordinateException e) {
			fail ("Error. Se esperaba InvalidCoordinateException "
					+ "pero se produjo la excepcion "+e.getClass().getName());
		} 
	}
	
	
	//TODO
	/* Posiciona los Crafts según testAddCraftOk().
	 * Intenta añadir un Craft Destroyer en la que, parte está fuera del tablero y
	 * parte en una posición próxima a otro Craft del tablero. 
	 * Comprueba que se lanza la excepción InvalidCoordinateException 
	 */
	@Test
	public void testAddCraftOutNextTo() {
		testAddCraftOk();
		
		try {
			board.addCraft(destroyer, new Coordinate2D(3, 5));
		}
		catch(BattleshipException e) {
			System.out.println(e.getMessage());
		}
	}
	
	

	//TODO
	/* Posiciona los Crafts según testAddCraftOk().
	 * Intenta añadir un Craft Destroyer dentro del tablero en la que, parte está colisionando y
	 * parte en una posición próxima a otro Craft del tablero. 
	 * Comprueba que se lanza la excepción OccupiedCoordinateException
	 */
	@Test
	public void testAddCraftColisionNextTo() {
		testAddCraftOk();
		
		try {
			board.addCraft(destroyer, new Coordinate2D(1, 2));
		}
		catch(BattleshipException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			board.addCraft(destroyer, new Coordinate2D(1, 3));
		}
		catch(BattleshipException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	

	//TODO
	/* Posiciona los Crafts según testAddCraftOk().
	 * Intenta añadir un Craft Battleship en la que, parte está fuera del tablero, parte está
	 * colisionando y parte en una posición próxima a otro Craft del tablero. 
	 * Comprueba que se lanza la excepción InvalidCoordinateException
	 */
	@Test
	public void testAddCraftOutColisionNextTo() {
		testAddCraftOk();
		
		try {
			board.addCraft(battleship, new Coordinate2D(4, 4));
		}
		catch(BattleshipException e) {
			System.out.println(e.getMessage());
		}
	}
	
}
