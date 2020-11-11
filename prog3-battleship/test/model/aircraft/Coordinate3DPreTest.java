package model.aircraft;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import model.Coordinate;
import model.CoordinateFactory;
import model.ship.Coordinate2D;

public class Coordinate3DPreTest{
	
    List<int[]> vcoordinates = new ArrayList<int[]>();
    int []vcoor= {0,0,-70,-2,20,75}; //Para crear coordenadas
    final int DIM = vcoor.length;
    List<Coordinate> lcoor;
    
	@Before
	public void setUp() throws Exception {
		lcoor = new ArrayList<Coordinate>();
		//Se crean las Coordinate (0,0,-70),(0,-70, -2), (-70,-2,20),(-2,20,75);
				for (int i=0; i<DIM-2; i++) {
					lcoor.add(new Coordinate3D(vcoor[i],vcoor[i+1],vcoor[i+2]));
				}
		
	}
    /* Adapta los test de Coordinate2D (CoordinateP1Test y CoordinateP2Test) para Coordinate3D */
	
	//TODO
	@Test
	public void testHashCode() {
		Coordinate c1 = lcoor.get(0);
		Coordinate c2 = new Coordinate3D(lcoor.get(0));
		
		assertEquals(c1, c2);
		assertEquals(c1.hashCode(), c2.hashCode());
		
		
	}

	//TODO
	/* Se comprueba que el Constructor funciona bien. Para ello se analiza que 
	 * las componentes '0', '1' y '2' de cada Coordinate3D creada en el setUp() son las 
	 * correctas.
	 */
	@Test
	public void testCoordinateConstructor() {
		assertEquals(lcoor.get(0).get(0), 0);
		assertEquals(lcoor.get(0).get(1), 0);
		assertEquals(lcoor.get(0).get(2), -70);
		
		assertEquals(lcoor.get(1).get(0), 0);
		assertEquals(lcoor.get(1).get(1), -70);
		assertEquals(lcoor.get(1).get(2), -2);
		
		assertEquals(lcoor.get(2).get(0), -70);
		assertEquals(lcoor.get(2).get(1), -2);
		assertEquals(lcoor.get(2).get(2), 20);
		
		assertEquals(lcoor.get(3).get(0), -2);
		assertEquals(lcoor.get(3).get(1), 20);
		assertEquals(lcoor.get(3).get(2), 75);
		
	}

	//TODO
	/* Se comprueba que el constructor de copia crea una nueva Coordinate3D con
	 * los mismos valores que las componentes respectivas del Coordinate3D copiado.
	 * Y eso se hace para cada Coordinate3D creada en setUp();
	 */
	@Test
	public void testCoordinateConstructorCopy() {
		
		for(int i = 0;i < lcoor.size();i++) {
			Coordinate3D copia = new Coordinate3D(lcoor.get(i));
			assertEquals(copia, lcoor.get(i));
		}
	}

	//TODO
	/* Se comprueba que el método get(int) para cada componente de una Coordinate 
	 * funciona correctamente.
	 * Se modifican los valores de las componentes de la Coordinate anterior con 
	 * el método set(int, int) y se comprueba con get(int) que los valores de sus 
	 * componentes han cambiado a dichos valores.
	 * OBSERVACION: No podrás usar el método set de Coordinate, ANALIZA POR QUÉ NO PUEDES.
	 * Al final del fichero encontrarás una clase auxiliar con la que podrás modificar los
	 * valores.
	 */
	class Coordinate3DAux extends Coordinate3D {
		protected Coordinate3DAux(Coordinate3D c) {
			super(c);
			// TODO Auto-generated constructor stub
		}
		
		public void set(int component, int value) {
			super.set(component,value);
		}
		
		public int get(int component) {
			return super.get(component);
		}		
	}
	@Test
	public void testGetSet() {
		// 1
		
		Coordinate3D aux = new Coordinate3D(1, 2, 3);
		Coordinate3DAux nueva1 = new Coordinate3DAux(aux);
		assertEquals(nueva1.get(0), 1);
		assertEquals(nueva1.get(1), 2);
		assertEquals(nueva1.get(2), 3);
		
		nueva1.set(0, 4);
		nueva1.set(1, 5);
		nueva1.set(2, 6);
		
		assertNotEquals(nueva1.get(0), 1);
		assertNotEquals(nueva1.get(1), 2);
		assertNotEquals(nueva1.get(2), 3);
	}

	//TODO
	/* Se suman las Coordinate creadas en el setUp() y se comprueba, conforme 
	 * se van sumando, que los valores de sus componentes van tomando los 
	 * valores correctos y que el Coordinate3D que devuelve no es el mismo que
	 * el Coordinate3D que invoca al método.
	 */
	@Test  //{0,0,-70,-2,20, 75}
	public void testAdd() {
		Coordinate caux1 = new Coordinate3D(lcoor.get(0));
		Coordinate caux2;
		
		int sumx = caux1.get(0);
		int sumy = caux1.get(1);
		int sumz = caux1.get(2);
		
		for(int i = 0;i < DIM - 3;i++) {
			caux2 = caux1;
			caux1 = caux1.add(lcoor.get(i+1));
			sumx += (vcoor[i + 1]);
			sumy += (vcoor[i + 2]);
			sumz += (vcoor[i + 3]);
			
			assertEquals(sumx, caux1.get(0));
			assertEquals(sumy, caux1.get(1));
			assertEquals(sumz, caux1.get(2));
			assertNotSame(caux2, caux1);
			
		}
		
	}

	//TODO
	/* Se van restando las Coordinate3D creadas en el setUp() y se comprueba, 
	 * conforme se van restando, que los valores de sus componentes van tomando 
	 * los valores correctos y que el Coordinate3D que devuelve no es el mismo que
	 * el Coordinate3D que invoca al método.
	 */
	@Test
	public void testSubtract() {
		Coordinate caux1 = new Coordinate3D(lcoor.get(0));
		Coordinate caux2;
		
		int sumx = caux1.get(0);
		int sumy = caux1.get(1);
		int sumz = caux1.get(2);
		
		for(int i = 0;i < DIM - 3;i++) {
			caux2 = caux1;
			caux1 = caux1.subtract(lcoor.get(i+1));
			sumx -= (vcoor[i + 1]);
			sumy -= (vcoor[i + 2]);
			sumz -= (vcoor[i + 3]);
			
			assertEquals(sumx, caux1.get(0));
			assertEquals(sumy, caux1.get(1));
			assertEquals(sumz, caux1.get(2));
			assertNotSame(caux2, caux1);
			
		}
	}
	
	//TODO
	/* Se comprueba, para el método toString(), que las Coordinate creadas en el setUp() 
	 * tienen el formato correcto.
	 */
	@Test
	public void testToString() {
		
		assertEquals(lcoor.get(0).toString(), "(0, 0, -70)");
		assertEquals(lcoor.get(1).toString(), "(0, -70, -2)");
		assertEquals(lcoor.get(2).toString(), "(-70, -2, 20)");
		assertEquals(lcoor.get(3).toString(), "(-2, 20, 75)");
		// ADD (-72, -52, 23) 
	}
	
	//TODO
	/* Creamos copias de las Coordinates3D creadas en el setUp() y comprobamos que:
	 * 1 - La copia y el original no son la misma.
	 * 2 - La copia tiene los mismos valores en las componentes respectivas que el objeto copiado.
	 */
	@Test
	public void testCopy() {
		Coordinate toCopy;
		
		for(int i = 0;i < lcoor.size();i++) {
			toCopy = ((Coordinate3D) lcoor.get(i)).copy();
			assertEquals(toCopy, lcoor.get(i));
			assertNotSame(toCopy, lcoor.get(i));
			
		}
	}

	//TODO
	/* Se crea una Coordinate3D y a partir de ella se obtienen las Coordinate3D adyacentes 
	 * que se guardan en un Set<Coordinate>. Para cada una de las posiciones adyacentes
	 * a la Coordinate3D inicial se crea una Coordinate3D, y se va comprobando están
	 * contenidas en el Set.
	 * 
	 */
	@Test
	public void testAdjacentCoordinates() {
		Coordinate c = new Coordinate3D(1, 2, 3);
		Set<Coordinate> adjacents = c.adjacentCoordinates();
		
		for(int i = -1;i < 2; i++) {
			for(int j = -1;j < 2;j++) {
				for(int k = -1; k < 2;k++) {
					if((i == 0) && (j == 0) && (k == 0)) {
						assertFalse(adjacents.contains(new Coordinate3D(c.get(0) + i, c.get(1) + j, c.get(2) + k)));
					}
					else {
						assertTrue(adjacents.contains(new Coordinate3D(c.get(0) + i, c.get(1) + j, c.get(2) + k)));
					}
				}
			}
		}
		
		
	}
	
	//TODO
	/* Se toma una Coordinate3D y se comprueba todas las posibles condiciones bajo 
	 * las cuales nuestra función equals() devuelve true o false
	 */
	@Test
	public void testEqualsObject() {
		Coordinate comp = new Coordinate3D(1, 2, 3);
		Coordinate comp3 = new Coordinate3D(1, 2, 4);
		Coordinate comp2 = new Coordinate2D(1, 2);
		
		assertFalse(comp.equals(null));
		assertFalse(comp.equals(comp2));
		assertFalse(comp.equals(comp3));
	}
	
/********************************************/
	//FUNCIÓN DE APOYO
		String removeSpaces (String str) {
			String exp[]=str.split(" ");
			String nstr=new String("");
			for (String s: exp) {
				if (! s.equals(" ") ) nstr+=s; 
			}
			return nstr;
		}

	//CLASE AUXILIAR PARA USAR set DE Coordinate
	
}
