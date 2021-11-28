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
	 *  A teszt példányosítja a testFrame, testGrid és testName objektumokat, majd leellenõrzni, hogy a testGrid Stringeket tároló playerNames dinamikus tömbjének
	 *  0. eleme valóban megegyezik a testName változóban referált String-gel a testGrid addPlayer metódusának meghívására.
	 *  
	 *  A teszt befejezése után a teszt során használt objektumok lebontásra kerülnek.
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
	 *  A teszt példányosítja a testFrame, testGrid és testSnake objektumokat, majd leellenõrzni, hogy a testGrid Snakes objektumokat tároló snakes dinamikus tömbjének
	 *  0. eleme valóban megegyezik a testSnakes változóban referált Snake objektummal.
	 *  
	 *  A teszt befejezése után a teszt során használt objektumok lebontásra kerülnek.
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
	 *  A teszt példányosítja a testFrame, testGrid és testAppl1 objektumokat, majd leellenõrzni, hogy a testGrid Apple objektumokat tároló apples dinamikus tömbjének
	 *  0. eleme valóban megegyezik a testApple változóban referált Apple objektummal.
	 *  
	 *  A teszt befejezése után a teszt során használt objektumok lebontásra kerülnek.
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
