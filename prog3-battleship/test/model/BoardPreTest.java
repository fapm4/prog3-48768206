package model;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;
import model.exceptions.NextToAnotherCraftException;
import model.exceptions.OccupiedCoordinateException;
import model.ship.Board2D;
import model.ship.Cruiser;
import model.ship.Ship;

public class BoardPreTest {

	static final int MAX_BOARD_SIZE = 20;
	static final int  MIN_BOARD_SIZE = 5;
	final static int DIM = 10;
	Board2D board2D;
	Ship fragata, galeon, bergantin, goleta;
	static String sboardEmpty,sboard, sboardHide1, sboardHits1,
				sboardHits2,sboardHits3, sboardHide2; //= new String();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sboardHide1 = "?????\n?????\n?????\n?????\n?????";
		
		sboardHide2 = "Ø ??•\n" + 
			      "Ø ?? \n" + 
			      "Ø  ??\n" + 
			      "   ?•\n" + 
			      "?•??•";
		
		sboardEmpty = "     \n     \n     \n     \n     ";
		
		sboard =	"Ø ØØØ\n" + 
					"Ø    \n" + 
					"Ø   Ø\n" + 
					"    Ø\n" + 
					"ØØØ Ø";
		
		sboardHits1 ="• ØØØ\n" + 
				     "•    \n" + 
				     "•   Ø\n" + 
				     "    Ø\n" + 
				     "ØØØ Ø";
		
		sboardHits2 ="• ØØ•\n" + 
				     "•    \n" + 
				     "•   Ø\n" + 
				     "    •\n" + 
				     "ØØØ •";
		
		sboardHits3 = "• ØØ•\n" + 
				      "•    \n" + 
				      "•   Ø\n" + 
				      "    •\n" + 
				      "Ø•Ø •";
		
	}

	@Before
	public void setUp() throws Exception {
		fragata = 	new Cruiser(Orientation.WEST);
		galeon = 	new Cruiser(Orientation.SOUTH);
		bergantin = new Cruiser(Orientation.EAST);
		goleta = 	new Cruiser(Orientation.NORTH);
		board2D = 	new Board2D(DIM);
		
	}

	/* Comprueba los límites del tamaño en el constructor,
	 * tanto dentro como justo fuera. Comprueba que al superarlos
	 * el tamaño que toma el Board es el mínimo 
	 */
	@Test
	public void testBoardGetSize() {
		System.out.println("testBoardGetSize: ");
		//Dentro de los límites
		board2D = new Board2D(MIN_BOARD_SIZE + 1);
		assertEquals(MIN_BOARD_SIZE + 1, board2D.getSize());
		board2D = new Board2D(MAX_BOARD_SIZE - 1);
		assertEquals(MAX_BOARD_SIZE - 1, board2D.getSize());
		try {
			board2D = new Board2D(MIN_BOARD_SIZE - 1);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		assertNotEquals(MIN_BOARD_SIZE - 1, board2D.getSize());
		try {
			board2D = new Board2D(MAX_BOARD_SIZE + 1);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		assertNotEquals(MAX_BOARD_SIZE + 1, board2D.getSize());
		//fail ("Termina el test superando los límites en 1");		
	}
	
	@Test
	public void jordiTest() throws InvalidCoordinateException, OccupiedCoordinateException, NextToAnotherCraftException {
		try {
			this.board2D = new Board2D(32);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		this.board2D.addCraft(bergantin, CoordinateFactory.createCoordinate(0, 0));
		this.board2D.toString();
	}
	
	/* Se comprueba checkCoordinate en los límites del tamaño 
	 * del Board 
	 */
	@Test
	public void testCheckCoordinate() {
		System.out.println("testCheckCoordinate");
		final int SIZE = 15;
		Board2D board2D = new Board2D(SIZE);
		assertFalse(board2D.checkCoordinate(CoordinateFactory.createCoordinate(0,SIZE)));
		assertFalse(board2D.checkCoordinate(CoordinateFactory.createCoordinate(-1,SIZE-1)));
		assertFalse(board2D.checkCoordinate(CoordinateFactory.createCoordinate(-1,SIZE)));
		assertFalse(board2D.checkCoordinate(CoordinateFactory.createCoordinate(SIZE,0)));
		assertFalse(board2D.checkCoordinate(CoordinateFactory.createCoordinate(SIZE-1,-1)));
		assertFalse(board2D.checkCoordinate(CoordinateFactory.createCoordinate(SIZE,-1)));
		assertTrue(board2D.checkCoordinate(CoordinateFactory.createCoordinate(0,SIZE-1)));
		assertTrue(board2D.checkCoordinate(CoordinateFactory.createCoordinate(SIZE-1,0)));
	}

	/* Posicionamientos correctos entre Ships. Posiciona de forma correcta 
	 * los 4 ships galeon, fragata, goleta y bergantín y comprueba que  se
	 * han posicionado los Ships en el Board.
	 */
	@Test
	public void testAddShipsOk() throws InvalidCoordinateException, OccupiedCoordinateException, NextToAnotherCraftException {
		System.out.println("testAddShipsOk");
		assertTrue(board2D.addCraft(galeon, CoordinateFactory.createCoordinate(0, 1)));
		for (int i=2; i<5; i++)	
			assertNotNull("x, y = 2," + i, board2D.getCraft(CoordinateFactory.createCoordinate(2, i)));
		
		assertTrue(board2D.addCraft(goleta, CoordinateFactory.createCoordinate(2, 1)));
		for(int i=2; i<5; i++)
			assertNotNull("x, y = 4," + i, board2D.getCraft(CoordinateFactory.createCoordinate(4, i)));
		
		assertTrue(board2D.addCraft(bergantin, CoordinateFactory.createCoordinate(0, 6)));
		for(int i=1; i<3; i++)
			assertNotNull("x, y = " + i + ", 8", board2D.getCraft(CoordinateFactory.createCoordinate(i, 8)));
		
		assertTrue(board2D.addCraft(fragata, CoordinateFactory.createCoordinate(5, 1)));
		for(int i=6; i<8; i++)
			assertNotNull("x, y = " + i + ", 1", board2D.getCraft(CoordinateFactory.createCoordinate(i, 3)));
		
		//fail("Sigue comprobando addShip igualmente para fragata, goleta y bergantín");
	}

	/* Posiciona los 4 Ships fuera del tablero y comprueba que
	 * addShip devuelve false y que además no se han posicionado
	 * los Ships en el Board
	 */
	@Test
	public void testAddShipsOutOfBoard() throws InvalidCoordinateException, OccupiedCoordinateException, NextToAnotherCraftException {
		System.out.println("testAddShipsOutOfBoard");
		try {
			assertFalse("Error 1 en testAddShipsOutOfBoard", board2D.addCraft(bergantin,
					CoordinateFactory.createCoordinate(MIN_BOARD_SIZE * -1, MIN_BOARD_SIZE * -1)));
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		try {
			assertFalse("Error 2 en testAddShipsOutOfBoard", board2D.addCraft(bergantin,
					CoordinateFactory.createCoordinate(MAX_BOARD_SIZE + 5, MAX_BOARD_SIZE + 5)));
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}
	
	/* Posiciona un Ship junto a otro y comprueba que addShip devuelve
	 * false y que además no se ha posicionado el Ship en el Board
	 */
	@Test
	public void testAddShipNextOther() throws InvalidCoordinateException, OccupiedCoordinateException, NextToAnotherCraftException {
		System.out.println("testAddShipNextOther");
		this.board2D = new Board2D(10);
		Coordinate goletaCoor = CoordinateFactory.createCoordinate(0, 0);
		Coordinate galeonCoor = CoordinateFactory.createCoordinate(1, 0);
		assertTrue(board2D.addCraft(goleta, goletaCoor));
		try {
			assertFalse(board2D.addCraft(galeon, galeonCoor));
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		assertNull(board2D.getCraft(galeonCoor));
		//fail("Realiza el test testAddShipNextOther()");
	}
	
	/* Se posiciona un Ship en una Coordinate.
	 * 1- Prueba getShip en una Coordinate que no contiene al Ship
	 * 2- Prueba getShip en todas las posiciones que ocupa el Ship
	 */
	@Test
	public void testGetShip() throws InvalidCoordinateException, OccupiedCoordinateException, NextToAnotherCraftException {
		System.out.println("testGetShip");
		this.board2D = new Board2D(10);
		assertTrue(board2D.addCraft(fragata, CoordinateFactory.createCoordinate(3, 1)));
		Set<Coordinate> shipPositions = fragata.getAbsolutePositions();
		for(Coordinate it : shipPositions) {
			assertEquals(fragata, this.board2D.getCraft(it));
		}
		//fail ("Termina el test testGetShip()");
	}
	
	
    /* Se comprueba isSeen antes y después de disparar al agua
     * en un Board sin Ships */
	@Test
	public void testIsSeen1() throws InvalidCoordinateException, CoordinateAlreadyHitException {
		System.out.println("testIsSeen1");
		for (int i=0; i<board2D.getSize(); i++)
			for (int j=0; j<board2D.getSize(); j++) {
				assertFalse(board2D.isSeen(CoordinateFactory.createCoordinate(i, j)));
				board2D.hit(CoordinateFactory.createCoordinate(i, j));
				assertTrue(board2D.isSeen(CoordinateFactory.createCoordinate(i, j)));
			}	
	}

	//TODO testIsSeen2
  /* Posiciona un Ship en el Board y comprueba isSeen 
   * antes y después de disparar a las distintas partes del Ship.
   * Cuando el Ship se ha hundido entonces comprueba que las
   * Coordinates vecinas del Ship también se han marcado como
   * vistas */
	@Test
	public void testIsSeen2() throws InvalidCoordinateException, CoordinateAlreadyHitException, OccupiedCoordinateException, NextToAnotherCraftException {
		System.out.println("testIsSeen2");
		board2D = new Board2D(5);
		board2D.addCraft(goleta, CoordinateFactory.createCoordinate(2, 1));
		
		assertFalse(board2D.isSeen(CoordinateFactory.createCoordinate(3, 1)));
		for(int i = 2; i <= 4; i++) {
			assertFalse(board2D.isSeen(CoordinateFactory.createCoordinate(4, i)));
			board2D.hit(CoordinateFactory.createCoordinate(4, i));
			assertTrue(board2D.isSeen(CoordinateFactory.createCoordinate(4, i)));
		}
		assertTrue(board2D.isSeen(CoordinateFactory.createCoordinate(3, 1)));
		
		//fail ("Realiza el test testIsSeen2()");
	}

	/* Coloca un Ship en el Board en una Coordinate. Comprueba que:
	 * 1- al disparar (hit) sobre las posiciones alrededor del Ship el 
	 *    resultado es WATER.
	 * 2- al disparar (hit) sobre las posiciones del Ship, excepto la última,
	 *    el resultado es HIT.
	 * 3- al disparar (hit) sobre la última posición del Ship, el resultado 
	 *    es DESTROYED
	 * 
	 */
	@Test
	public void testHit() throws InvalidCoordinateException, CoordinateAlreadyHitException, OccupiedCoordinateException, NextToAnotherCraftException {
		System.out.println("testHit");
		board2D = new Board2D(5);
		board2D.addCraft(goleta, CoordinateFactory.createCoordinate(2, 1));
		assertEquals(CellStatus.WATER, this.board2D.hit(CoordinateFactory.createCoordinate(3, 1)));
		assertEquals(CellStatus.HIT, this.board2D.hit(CoordinateFactory.createCoordinate(4, 2)));
		assertEquals(CellStatus.HIT, this.board2D.hit(CoordinateFactory.createCoordinate(4, 3)));
		assertEquals(CellStatus.WATER, this.board2D.hit(CoordinateFactory.createCoordinate(3, 4)));
		assertEquals(CellStatus.DESTROYED, this.board2D.hit(CoordinateFactory.createCoordinate(4, 4)));
	}

	/* Comprueba que:
	 * 1- en un Board sin Ships, areAllCraftsDestroyed devuelve true.
	 * 2- al posicionar dos Ships en el Board, tras cada posicionamiento,
	 *    areAllCraftsDestroyed devuelve false.
	 * 3- tras cada disparo sobre el primer Ship, hundiéndolo, areAllCraftsDestroyed 
	 *    devuelve false.
	 * 4- tras cada disparo sobre el segundo Ship, areAllCraftsDestroyed devuelve
	 *    false, excepto tras el último disparo que debe devolver true.
	 * 5- añade un nuevo Ship, entonces areAllCraftsDestroyed debe devolver
	 *    false.
	 */
	@Test
	public void testAreAllCraftsDestroyed() throws InvalidCoordinateException, CoordinateAlreadyHitException, OccupiedCoordinateException, NextToAnotherCraftException {
		System.out.println("testAreAllCraftsDestroyed");
		assertTrue("numCrafts=destroyedCrafts=0", board2D.areAllCraftsDestroyed());
		board2D.addCraft(galeon, CoordinateFactory.createCoordinate(0,1));
		assertFalse("numCrafts=1; destroyedCrafts=0", board2D.areAllCraftsDestroyed());
		board2D.addCraft(fragata, CoordinateFactory.createCoordinate(3,1));
		assertFalse("numCrafts=2; destroyedCrafts=0", board2D.areAllCraftsDestroyed());
		
		// 3
		board2D.hit(CoordinateFactory.createCoordinate(2, 2));
		board2D.hit(CoordinateFactory.createCoordinate(2, 3));
		board2D.hit(CoordinateFactory.createCoordinate(2, 4));
		assertFalse("numCrafts = 2; destroyedCrafts = 1", board2D.areAllCraftsDestroyed());
		
		// 4
		board2D.hit(CoordinateFactory.createCoordinate(4, 3));
		board2D.hit(CoordinateFactory.createCoordinate(5, 3));
		board2D.hit(CoordinateFactory.createCoordinate(6, 3));
		assertTrue("numCraft = 2; destroyedCrafts = 2", board2D.areAllCraftsDestroyed());
		
		//5
		board2D.addCraft(goleta, CoordinateFactory.createCoordinate(5, 5));
		assertFalse("numCrafts = 3; destroyedCrafts = 3", board2D.areAllCraftsDestroyed());
		//fail ("Termina las pruebas 3, 4 y 5");
	}

	
	/* Se comprueba getNeighborhood(Ship) donde el Ship y todas sus 
	 * Coordinate vecinas están dentro de Board.
	 */
	@Test
	public void testGetNeighborhoodShipCompletelyIn1() throws InvalidCoordinateException, OccupiedCoordinateException, NextToAnotherCraftException {
		System.out.println("testGetNeighborhoodShipCompletelyIn1");
		board2D.addCraft(fragata, CoordinateFactory.createCoordinate(5, 1));
		Set<Coordinate> neighborhood = board2D.getNeighborhood(fragata);
		assertEquals(12,neighborhood.size());
		for (int i=5; i<10; i++) {
			for (int j=2; j<4; j++) {
				if ((j==3) && (i>=6)&&(i<=8))
					assertFalse("x,y = "+i+","+j,	neighborhood.contains(CoordinateFactory.createCoordinate(i, j)));
				else 
					assertTrue("x,y = "+i+","+j,	neighborhood.contains(CoordinateFactory.createCoordinate(i, j)));	
			}
		}
	}
	
	/* Comprueba:
	 * 1- getNeighborhood(Ship) para un Ship que no se ha puesto en el Board 
	 *    debe devolver un Set vacío.
	 * 
	 * 2- getNeighborhood(Ship, Coordinate) donde el Ship sale de los límites
	 *    del Board. El conjunto de Coordinate vecinas solo recoge aquellas
	 *    que están dentro del Board
	 */
	@Test
	public void testGetNeighborhoodShipOutOfBounds() throws InvalidCoordinateException, OccupiedCoordinateException, NextToAnotherCraftException {
		System.out.println("testGetNeighborhoodShipOutOfBounds");
		board2D = new Board2D(5);
		try {
			assertEquals(new HashSet<Coordinate>(), this.board2D.getNeighborhood(goleta));
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		board2D.addCraft(goleta, CoordinateFactory.createCoordinate(2, 1));
		Set<Coordinate> esperades = new HashSet<Coordinate>();
		esperades.add(CoordinateFactory.createCoordinate(3, 1));
		esperades.add(CoordinateFactory.createCoordinate(4, 1));
		esperades.add(CoordinateFactory.createCoordinate(3, 2));
		esperades.add(CoordinateFactory.createCoordinate(3, 3));
		esperades.add(CoordinateFactory.createCoordinate(3, 4));
		assertTrue(esperades.containsAll(board2D.getNeighborhood(goleta, CoordinateFactory.createCoordinate(2, 1))));
		//fail ("Realiza el test testGetNeighborhoodShipOutOfBounds() ");
	}
	
	/* Se crea un tablero de tamaño 5 sin Ships. Se comprueba que lo devuelto
	 * por show(true) y show(false) es correcto.
	 * 
	 */
	@Test
	public void testShowBoardEmty() {
		System.out.println("testShowBoardEmty");
		board2D = new Board2D(5);
		String hideShips = board2D.show(false);
		assertEquals(sboardHide1, hideShips);
		String showShips = board2D.show(true);
		assertEquals(sboardEmpty, showShips);
	}
	
	/* Se crea un tablero de tamaño 5.
	 * 1- Se añaden los 4 ships en las posiciones indicadas en la variable estática 'sboard'
	 *    definida en setUp().
	 * 2- Se comprueba que show(false) devuelve lo mismo que la variable estática sboardHide1
	 *    y que show(true) lo mismo que el contenido de la variable estática 'sboard' 
	 */
	@Test
	public void testShowBoardWithShips() throws InvalidCoordinateException, OccupiedCoordinateException, NextToAnotherCraftException {
		System.out.println("testShowBoardWithShips");
		this.board2D = new Board2D(5);
		board2D.addCraft(galeon, CoordinateFactory.createCoordinate(-2, -1));
		board2D.addCraft(fragata, CoordinateFactory.createCoordinate(1, -2));
		board2D.addCraft(goleta, CoordinateFactory.createCoordinate(2, 1));
		board2D.addCraft(bergantin, CoordinateFactory.createCoordinate(-1, 2));
		
		assertEquals(sboardHide1, this.board2D.show(false));
		assertEquals(sboard, this.board2D.show(true));
		//fail ("Sigue añadiendo la goleta y el bergantín en sus posiciones y haz las comprobaciones indicadas");

	}	
	
	/* Añade los 4 Ships del setUp() en el Board y comprueba toString() con
	 * la salida correcta.
	 */
	@Test
	public void testToString1() throws InvalidCoordinateException, OccupiedCoordinateException, NextToAnotherCraftException {
		//fail("Añade los 4 Ships en el Board");
		System.out.println("testToString1");
		board2D.addCraft(bergantin, CoordinateFactory.createCoordinate(-1, 2));
		board2D.addCraft(fragata, CoordinateFactory.createCoordinate(1, -2));
		board2D.addCraft(galeon, CoordinateFactory.createCoordinate(-2, -1));
		board2D.addCraft(goleta, CoordinateFactory.createCoordinate(2, 1));
		assertEquals("Board 10; crafts: 4; destroyed: 0", board2D.toString());
	}
	
	//TODO testToString2
    /* Se toma el ejemplo del test testAreAllCraftsDestroyed().
     * 1- Destruye el galeón y comprueba que la salida que debe dar es correcta.
     * 2- Realiza disparos sobre la fragata comprobando que las salidas que debe
     *    dar son correctas.
    */
	@Test
	public void testToString2() throws InvalidCoordinateException, OccupiedCoordinateException, NextToAnotherCraftException, CoordinateAlreadyHitException {		
		//fail ("Realiza el test testToString2()");
		System.out.println("testToString2");
		board2D.addCraft(galeon, CoordinateFactory.createCoordinate(0, 1));
		board2D.addCraft(fragata, CoordinateFactory.createCoordinate(3, 1));
		assertEquals("Board 10; crafts: 2; destroyed: 0", board2D.toString());
		
		board2D.hit(CoordinateFactory.createCoordinate(2, 2));
		board2D.hit(CoordinateFactory.createCoordinate(2, 3));
		board2D.hit(CoordinateFactory.createCoordinate(2, 4));
		assertEquals("Board 10; crafts: 2; destroyed: 1", board2D.toString());
		
		board2D.hit(CoordinateFactory.createCoordinate(4, 3));
		board2D.hit(CoordinateFactory.createCoordinate(5, 3));
		board2D.hit(CoordinateFactory.createCoordinate(6, 3));
		assertEquals("Board 10; crafts: 2; destroyed: 2", board2D.toString());
	}

}