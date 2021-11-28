package snake;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class TupleTest {

	Tuple testTuple1;
	Tuple testTuple2;
	ArrayList<Tuple> tuples = new ArrayList<Tuple>();
	
	@Before
	public void setUpTuple()
	{
		testTuple1 = new Tuple(3,2);
		testTuple2 = new Tuple(6,10);
		tuples.add(testTuple1);
		tuples.add(testTuple1);	
	}
	
	/*Test #1
	 * A teszt leellen�rzi, hogy a testTuple1 �s testTuple2 objektumok toString() met�dusai a teszteredm�nynek elv�rt String �rt�keket adja vissza.
	 */
	@Test
	public void testToString()
	{
		String testTuple1String = "(3,2)";
		String testTuple2String = "(6,10)";
		assertEquals("Same String", testTuple1String, testTuple1.toString());
		assertEquals("Same String", testTuple2String, testTuple2.toString());
	}
	
	/*Test #2
	 * A teszt leellen�rzi, hogy a testTuple1 �s testTuple2 objektumok �sszead�sa sor�n a tesztnek megfelel� eredm�nyt adja vissza.
	 */
	@Test
	public void add()
	{
		Tuple sumTupleTest = new Tuple(9,12);
		Tuple sumTuple = testTuple1.add(testTuple2);
		assertEquals("Same Tuple Value", sumTupleTest.toString(), sumTuple.toString());
	}
	
	/*Test #3
	 * A teszt leellen�rzi, hogy a testTuple2 objektumot sikeresen el tudja t�vol�tani az adott Tuple objektumokat
	 * tartalmaz� dinamikus t�mbb�l az objektum remove() met�dusa.
	 */
	
	@Test
	public void remove()
	{
		tuples.remove(testTuple2);
		assertFalse("Check if ArrayList contains removed Tuple", tuples.contains(testTuple2));
	}

}
