package snake;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GridTest {

	Snake1 testSnake;
	Apple1 testApple1;
	Apple2 testApple2;
	Grid testGrid;
	Frame testFrame;
	String testName;
	
	
	/*Test #1
	 *  A teszt p�ld�nyos�tja a testFrame, testGrid �s testName objektumokat, majd leellen�rzni, hogy a testGrid Stringeket t�rol� playerNames dinamikus t�mbj�nek
	 *  0. eleme val�ban megegyezik a testName v�ltoz�ban refer�lt String-gel a testGrid addPlayer met�dus�nak megh�v�s�ra.
	 *  
	 *  A teszt befejez�se ut�n a teszt sor�n haszn�lt objektumok lebont�sra ker�lnek.
	 */
	@Before
	public void setUpGrid1()
	{
		testFrame = new Frame();
		testGrid = new Grid(testFrame, 25);
		testName = "testPlayer";
	}
	
	@Test
	public void addPlayer()
	{
		testGrid.addPlayer(testName);
		assertEquals("Check Same Name", testName, testGrid.playerNames.get(0));
	}
	
	@After
	public void eatUpGrid1()
	{
		testGrid = null;
		testFrame = null;
		testName = null;
	}
	
	/*Test #2
	 *  A teszt p�ld�nyos�tja a testFrame, testGrid �s testSnake objektumokat, majd leellen�rzni, hogy a testGrid Snakes objektumokat t�rol� snakes dinamikus t�mbj�nek
	 *  0. eleme val�ban megegyezik a testSnakes v�ltoz�ban refer�lt Snake objektummal.
	 *  
	 *  A teszt befejez�se ut�n a teszt sor�n haszn�lt objektumok lebont�sra ker�lnek.
	 */
	@Before
	public void setUpGrid2()
	{
		testFrame = new Frame();
		testSnake = new Snake1(testFrame, 25);
		testGrid = new Grid(testFrame, 25);
	}
	
	
	@Test
	public void addSnake()
	{
		testGrid.addSnake(testSnake);
		assertSame("Same Snake Object", testSnake, testGrid.snakes.get(0));
	}
	
	@After
	public void eatUpGrid2()
	{
		testSnake = null;
		testFrame = null;
		testGrid = null;
	}
	
	/*Test #3
	 *  A teszt p�ld�nyos�tja a testFrame, testGrid �s testAppl1 objektumokat, majd leellen�rzni, hogy a testGrid Apple objektumokat t�rol� apples dinamikus t�mbj�nek
	 *  0. eleme val�ban megegyezik a testApple v�ltoz�ban refer�lt Apple objektummal.
	 *  
	 *  A teszt befejez�se ut�n a teszt sor�n haszn�lt objektumok lebont�sra ker�lnek.
	 */
	@Before
	public void setUpGrid3()
	{
		testFrame = new Frame();
		testApple1 = new Apple1(10, 5, testFrame, 25);
		testGrid = new Grid(testFrame, 25);
	}
	
	@Test
	public void addApple()
	{
		testGrid.addApple(testApple1);
		assertSame("Same Apple Object", testApple1, testGrid.apples.get(0));
	}
	
	@After
	public void eatUpGrid3()
	{
		testApple1 = null;
		testGrid = null;
		testFrame = null;
	}
}
