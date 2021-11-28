package snake;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SnakeTest {

	Snake1 testSnake1;
	Frame testFrame;
	
	/*Test #1
	 *  A teszt p�ld�nyos�tja a testFrame �s testSnake1 objektumokat, majd leellen�rzni, hogy a testSnakes objektum remove() met�dusa sikeresen elt�vol�tja a k�gy�t
	 *  a p�ly�r�l. Sikeres teszt eset�n a k�gy� test�t reprezent�l�, JPanel t�pus� objektumokat tartalmaz� bodyElements dinamikus t�mb �res.
	 *  
	 *  A teszt befejez�se ut�n a teszt sor�n haszn�lt objektumok lebont�sra ker�lnek.
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
	 *  A teszt p�ld�nyos�tja a testFrame �s testSnake1 objektumokat, majd leellen�rzni, hogy a testSnakes objektum draw() met�dusa sikeresen kirajzolja a k�gy�t
	 *  a p�ly�ra. Sikeres teszt eset�n a k�gy� test�t reprezent�l�, JPanel t�pus� objektumokat tartalmaz� bodyElements dinamikus t�mb pontosan 4 elemet tartalmaz.
	 *  
	 *  A teszt befejez�se ut�n a teszt sor�n haszn�lt objektumok lebont�sra ker�lnek.
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
