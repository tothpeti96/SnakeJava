package snake;

import java.util.ArrayList;
import javax.swing.JPanel;

public class Apple extends Grid
{
	/*
	 * Az Apple oszt�ly a Grid oszt�ly lesz�rmazottja. 
	 * Heterog�n kollekci�ja az Apple1 �s az Apple2 lesz�rmazott oszt�lyoknak.
	 * 
	 * Az Apple oszt�ly �ltal defini�lt attrib�tumok:
	 * 
	 * 		X: int t�pus� v�ltoz�. Az alma aktiu�lis X poz�ci�j�t tartalmazza.
	 * 
	 * 		Y: int t�pus� v�ltoz�. Az alma aktiu�lis Y poz�ci�j�t tartalmazza.
	 * 
	 * 		isEaten: boolean t�pus� v�ltoz�. Az alma �llapot�t t�rolja, hogy megev�re ker�lt-e valamely k�gy� �ltal.
	 * 
	 * 		grid: A Grid oszt�ly egy p�ld�nya.
	 * 
	 * 		value: int t�pus� v�ltoz�. Az alma �rt�k�t t�rolja, amely az alm�t megev� k�gy� pontsz�maihoz ker�l majd regisztr�l�sra.
	 * 
	 * 		appleReference: Dinamikus t�mb ami Tuple t�pus� objektumokat t�rol. Az apple dinamikus t�mb tartalmazza az aktu�lis
	 * 				alma koordin�t�it.
	 */	
	
	protected int X;
	protected int Y;
	protected boolean isEaten;
	private Grid grid;
	protected int value;
	protected ArrayList<JPanel> appleReference = new ArrayList<JPanel>();
	
	/*
	 * Az Apple oszt�ly konstruktora. Az Apple oszt�ly �r�kli a Grid oszt�ly actFrame �s actCellNum param�tereit. 
	 */
	public Apple(Frame actFrame, int actCellNum)
	{
		super(actFrame, actCellNum);
	}
	
	/*
	 * A met�dus megh�v�s�nak hat�s�ra be�ll�tja az Apple oszt�ly grid attrib�tum�hoz be�ll�tja a param�terk�nt �tvett Grid objektumot.
	 */
	protected void setGrid(Grid g){this.grid = g;}
	
	
	/*
	 * A met�dus megh�v�s�nak hat�s�ra a j�t�kfel�letr�l t�rl�dik az appleReference-ben t�rolt JPanel refernci�nak megfelel� JPanel objektum
	 * a j�t�kfel�letr�l.
	 */
	protected void remove()
	{
		for (int i = 0; i < appleReference.size(); i++)
		{
			gameFrame.getLayeredPane().remove(appleReference.get(i));
		}
		appleReference = new ArrayList<JPanel>();
	}
	
	/*
	 * Az Apple oszt�ly draw met�dusa, amely az Apple1 �s Apple2 oszt�lyokban elt�r�en vannak defini�lva.
	 */
	protected void drawApple(){}
}
