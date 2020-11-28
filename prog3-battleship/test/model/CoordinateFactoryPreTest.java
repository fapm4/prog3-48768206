package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import model.aircraft.Coordinate3D;
import model.ship.Coordinate2D;

public class CoordinateFactoryPreTest {

	   
	//TODO
	/* Crea una Coordinate2D con su constructor. Crea la misma Coordinate2D con el 
	 * método createCoordinate. Comprueba que ambas Coordinate2D creadas son iguales.
	 * Realiza lo mismo para Coordinate3D
	 */
	@Test
	public void testCreateCoordinateOk() {
		Coordinate2D original = new Coordinate2D(1, 2);
		Coordinate copia = CoordinateFactory.createCoordinate(1, 2);
		
		assertEquals(original, copia);
		
		Coordinate3D original3D = new Coordinate3D(1, 2, 3);
		Coordinate copia3D = CoordinateFactory.createCoordinate(1, 2, 3);
		
		assertEquals(original3D, copia3D);
		
	}
	
	/* Comprueba que si a createCoordinate se le pasa una cantidad de argumentos enteros 
	 * incorrecto, lanza la excepción IllegalArgumentException
	 */
	@Test
	public void testCreateCoordinateException() {
		try {
			Coordinate throwExcep1 = CoordinateFactory.createCoordinate(1);
			Coordinate throwExcep2 = CoordinateFactory.createCoordinate(1, 2, 3, 4);
		}
		catch(IllegalArgumentException e) {
			System.out.println("Se lanazó correctamente la excepción");
		}
	}

}
