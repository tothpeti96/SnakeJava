package snake;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AppleTest {

	Apple testApple;
	Frame testFrame;
	
	/* Test #1
	 * 
	 * A teszt inicializála az Apple1 és a Frame osztályokat. A teszt során a program kirajzolja a testApple objektumot 
	 * a testFrame-re, majd leellenõrzi, hogy az almát reprezentáló JPanel koordinátái megegyeznek-e a testApple koordinátáival illetve, hogy
	 * a testApple appleReference dinamikus tömbjének a mérete megegyezik 1-el.
	 * 
	 * A teszt befejezése után a teszt során használt objektumok lebontásra kerülnek.
	 */
	
	@Before
	public void setApple1()
	{
		testFrame = new Frame();
		testApple = new Apple1(10,13, testFrame, 25);
	}
	
	@Test
	public void drawApple()
	{
		testApple.drawApple();
		
		int cellSize = 600 / 25;
		int actX = testApple.appleReference.get(0).getX()/cellSize;
		int actY = (testApple.appleReference.get(0).getY() - 50)/cellSize;
		
		assertTrue("Couldn't draw Apple", testApple.appleReference.size() == 1);
		assertEquals("Couldn't draw Apple", 10, actX);
		assertEquals("Couldn't draw Apple", 13, actY);
	}
	
	@After
	public void eatApple1()
	{
		testFrame = null;
		testApple = null;
	}
	
	
	/* Test #2
	 *
	 * A teszt inicializála az Apple1 és a Frame osztályokat. A teszt során a program kirajzolja a testApple objektumot 
	 * a testFrame-re, majd leellenõrzi, hogy az almát reprezentáló JPanel-t a testApple removeApple() metódusa le tudta-e törölni a testFrame-rõl. 
	 * Ebben az esetben a testApple appleReference dinamikus tömbje 0 nagyságú lesz.
	 * 
	 * A teszt befejezése után a teszt során használt objektumok lebontásra kerülnek.
	 */
	@Before
	public void setApple2()
	{
		testFrame = new Frame();
		testApple = new Apple1(10,13, testFrame, 25);
	}
	
	@Test
	public void removeApple()
	{
		testApple.remove();
		assertTrue("Couldn't remove Apple", testApple.appleReference.size() == 0);
	}
	
	@After
	public void eatApple2()
	{
		testFrame = null;
		testApple = null;
	}
}
