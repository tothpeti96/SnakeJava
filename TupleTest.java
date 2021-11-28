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
	 * A teszt leellenõrzi, hogy a testTuple1 és testTuple2 objektumok toString() metódusai a teszteredménynek elvárt String értékeket adja vissza.
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
	 * A teszt leellenõrzi, hogy a testTuple1 és testTuple2 objektumok összeadása során a tesztnek megfelelõ eredményt adja vissza.
	 */
	@Test
	public void add()
	{
		Tuple sumTupleTest = new Tuple(9,12);
		Tuple sumTuple = testTuple1.add(testTuple2);
		assertEquals("Same Tuple Value", sumTupleTest.toString(), sumTuple.toString());
	}
	
	/*Test #3
	 * A teszt leellenõrzi, hogy a testTuple2 objektumot sikeresen el tudja távolítani az adott Tuple objektumokat
	 * tartalmazó dinamikus tömbböl az objektum remove() metódusa.
	 */
	
	@Test
	public void remove()
	{
		tuples.remove(testTuple2);
		assertFalse("Check if ArrayList contains removed Tuple", tuples.contains(testTuple2));
	}

}
