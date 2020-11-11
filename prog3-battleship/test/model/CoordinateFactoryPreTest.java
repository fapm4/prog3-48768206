package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.aircraft.Coordinate3D;
import model.ship.Coordinate2D;

public class CoordinateFactoryPreTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	//TODO
	/* Crea coordenadas correctas con el método createCoordinate y comprueba 
	 * que se han creado bien
	 */
	@Test
	public void testCreateCoordinateOk() {
		Coordinate2D prueba1 = new Coordinate2D(1, 2);
		Coordinate pruebaFactory1 = CoordinateFactory.createCoordinate(1, 2);
		
		Coordinate2D prueba2 = new Coordinate2D(-1, 2);
		Coordinate pruebaFactory2 = CoordinateFactory.createCoordinate(-1, 2);
		
		Coordinate3D prueba3 = new Coordinate3D(1, 2, 3);
		Coordinate pruebaFactory3 = CoordinateFactory.createCoordinate(1, 2, 3);
		
		assertEquals(prueba1, pruebaFactory1);
		assertEquals(prueba2, pruebaFactory2);
		assertEquals(prueba3, pruebaFactory3);
	}
	
	//TODO
	/* Comprueba que en los distintos casos de creación de coordenadas incorrectas
	 * createCoordinateException lanza la excepción IllegalArgument exception
	 */
	@Test
	public void testCreateCoordinateException() {
		try {
		   CoordinateFactory.createCoordinate(-1);
		   fail("Error: debió lanzarse la excepción IllegalArgumentException");
		} catch (IllegalArgumentException e1) {
			System.out.println(e1.getMessage());
		}
	}

}
