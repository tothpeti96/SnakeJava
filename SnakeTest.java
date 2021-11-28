package snake;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SnakeTest {

	Snake1 testSnake1;
	Frame testFrame;
	
	/*Test #1
	 *  A teszt példányosítja a testFrame és testSnake1 objektumokat, majd leellenõrzni, hogy a testSnakes objektum remove() metódusa sikeresen eltávolítja a kígyót
	 *  a pályáról. Sikeres teszt esetén a kígyó testét reprezentáló, JPanel típusú objektumokat tartalmazó bodyElements dinamikus tömb üres.
	 *  
	 *  A teszt befejezése után a teszt során használt objektumok lebontásra kerülnek.
	 */
	@Before
	public void makeSnake1()
	{
		testFrame = new Frame();
		testSnake1 = new Snake1(testFrame, 25);
	}
	
	@Test
	public void remove()
	{
		testSnake1.remove();
		assertTrue("Couldn't remove Snake", testSnake1.bodyElements.size() == 0);
	}

	@After
	public void deleteSnake()
	{
		testFrame = null;
		testSnake1 = null;
	}
	
	/*Test #3
	 *  A teszt példányosítja a testFrame és testSnake1 objektumokat, majd leellenõrzni, hogy a testSnakes objektum draw() metódusa sikeresen kirajzolja a kígyót
	 *  a pályára. Sikeres teszt esetén a kígyó testét reprezentáló, JPanel típusú objektumokat tartalmazó bodyElements dinamikus tömb pontosan 4 elemet tartalmaz.
	 *  
	 *  A teszt befejezése után a teszt során használt objektumok lebontásra kerülnek.
	 */
	@Before
	public void makeSnake2()
	{
		testFrame = new Frame();
		testSnake1 = new Snake1(testFrame, 25);
	}
	
	@Test
	public void draw()
	{
		testSnake1.remove();
		testSnake1.draw();
		assertTrue("Couldn't draw Snake", testSnake1.bodyElements.size() == 4);
	}
		
}
