package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.aircraft.Coordinate3D;
import model.ship.Coordinate2D;

public class CoordinatePreTest {
	Coordinate cd2, cd3;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		cd2 = new Coordinate2D(-10,7);
		cd3 = new Coordinate3D(15, 8, -2);
	}

	/* Comprueba set y get modificando algunas de las Coordinates creadas en setUp() 
	 */
	//TODO
	@Test
	public void testSetGet() {
		
		try {
			cd2.set(0, 1);
			cd2.set(1, 2);
			
			cd3.set(0, 1);
			cd3.set(1, 2);
			cd3.set(1, 3);
			
			cd2.get(2);
			cd2.set(4, 3);
			cd3.get(-1);
		}
		catch(Exception e) {
			System.out.println(e.getMessage() + " - No se puede usar esa componenet");
		}
	}
	
	//TODO
	/* Comprueba que set lanza IllegalArgumentException cuando el componente
	 * no es correcto
	 */
	@Test
	public void testSetIllegalArgumentException() {
		try {
			cd2.set(-5, 0);
		}
		catch(IllegalArgumentException e) {
			System.out.println("Estoy lanzanado IllegalArgumentException");
		}
	}
	
	//TODO
	/* Comprueba que get lanza IllegalArgumentException cuando el componente
	 * no es correcto
	 */
	@Test
	public void testGetIllegalArgumentException() {
		try {
			cd2.get(-5);
		}
		catch(IllegalArgumentException e) {
			System.out.println("Estoy lanzanado IllegalArgumentException");
		}
	}
	
	/* Comprobación de la sumas entre coordenadas de dimensiones distintas*/
	@Test
	public void testAdd2Dand3D() {
		Coordinate aux2d = new Coordinate2D (5,15);
		Coordinate aux3d = new Coordinate3D (5,15,-2);
		assertEquals ("c2+c3", aux2d, cd2.add(cd3));
		assertEquals ("c3+c2", aux3d, cd3.add(cd2));
		assertNotEquals ("aux2d!=cd2", aux2d, cd2);
		assertNotEquals ("aux3d!=cd3", aux2d, cd3);
	}
	
	//TODO
	@Test(expected=NullPointerException.class)
	public void testAddNullPointerException() {
		try {
		   cd2.add(null);
		   fail ("Error: No se lanzó la excepción NullPointerException");
		} catch (NullPointerException e) {
			cd3.add(null);
		}
	}
	
	//TODO
	/* Comprueba la correcta resta entre Coordinates de distinta dimensión */
	@Test
	public void testsubtract2Dand3D() {
		Coordinate2D restaIgual2D = new Coordinate2D(3, 1);
		Coordinate3D restaIgual3D = new Coordinate3D(-3, -1, 1);
		
		Coordinate2D coord1 = new Coordinate2D(4, 2);
		Coordinate3D coord2 = new Coordinate3D(1, 1, 1);
		
		assertEquals("coord1 - coord2", restaIgual2D, coord1.subtract(coord2));
		assertEquals("coord2 - coord1", restaIgual3D, coord2.subtract(coord1));
	}

	//TODO
	/* Comprueba que al intentar restar a una Coordinate el valor null, se lanza
	 * la excepción NullPointerException 
	 */
	@Test
	public void testSubtractNullPointerException() {
		
		Coordinate2D restaIgual2D = new Coordinate2D(3, 1);
		Coordinate3D restaIgual3D = new Coordinate3D(-3, -1, 1);
		
		Coordinate2D coord1 = new Coordinate2D(4, 2);
		Coordinate3D coord2 = new Coordinate3D(1, 1, 1);
		
		assertEquals("coord1 - coord2", restaIgual2D, coord1.subtract(coord2));
		try {
			assertEquals("coord2 - coord1", restaIgual3D, coord2.subtract(null));
		}
		catch(Exception e) {
			System.out.println("Estoy lanzando NullPointerException");
		}
		
	}


}
