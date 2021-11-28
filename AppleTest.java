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
	 * A teszt inicializ�la az Apple1 �s a Frame oszt�lyokat. A teszt sor�n a program kirajzolja a testApple objektumot 
	 * a testFrame-re, majd leellen�rzi, hogy az alm�t reprezent�l� JPanel koordin�t�i megegyeznek-e a testApple koordin�t�ival illetve, hogy
	 * a testApple appleReference dinamikus t�mbj�nek a m�rete megegyezik 1-el.
	 * 
	 * A teszt befejez�se ut�n a teszt sor�n haszn�lt objektumok lebont�sra ker�lnek.
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
	 * A teszt inicializ�la az Apple1 �s a Frame oszt�lyokat. A teszt sor�n a program kirajzolja a testApple objektumot 
	 * a testFrame-re, majd leellen�rzi, hogy az alm�t reprezent�l� JPanel-t a testApple removeApple() met�dusa le tudta-e t�r�lni a testFrame-r�l. 
	 * Ebben az esetben a testApple appleReference dinamikus t�mbje 0 nagys�g� lesz.
	 * 
	 * A teszt befejez�se ut�n a teszt sor�n haszn�lt objektumok lebont�sra ker�lnek.
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
