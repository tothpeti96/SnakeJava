package snake;


import java.util.ArrayList;
import javax.swing.JPanel;

public class Snake extends Grid
{
	/*
	 * A Snake oszt�ly a Grid oszt�ly lesz�rmazottja. 
	 * Heterog�n kollekci�ja a Snake1 �s Snake2 lesz�rmazott oszt�lyoknak.
	 * 
	 * A Snake oszt�ly �ltal defini�lt attrib�tumok:
	 * 
	 * 		direction: A k�gy� aktu�lis halad�si ir�ny�t tartalmaz�, String t�pus� v�ltoz�.
	 * 
	 * 		head: A k�gy� fej�nek poz�ci�j�t tartalmaz�, Tuple t�pus� v�ltoz�.
	 * 
	 * 		isGrowing: Boolean t�pus� v�ltoz�, amely megadja, hogy a k�gy� �ppen n�vekszik-e vagy sem.
	 * 
	 * 		isDead: Boolean t�pus� v�ltoz�, amely megadja, hogy a k�gy� m�g j�t�kban van-e vagy sem.
	 * 
	 * 		grid: A Grid oszt�ly egy p�ld�nya.
	 * 
	 * 		point: Integer t�pus� v�ltoz�. A k�gy� aktu�lis pontsz�m�t t�rolja. 
	 * 
	 * 		isMoving: Boolean t�pus� v�ltoz�. Sz�mon tartja, hogy a k�gy� elkezdett m�r, vagy m�g mozog-e.
	 * 
	 * 		snake: Dinamikus t�mb ami Tuple t�pus� objektumokat t�rol. A snake dinamikus t�mb tartalmazza a
	 * 				k�gy� testelemeinek egyes koordin�t�it.
	 * 
	 * 		bodyElements: dinamikus t�mb amely JPanel t�pus� objektumokat t�rol. A k�gy� testelemeinek megjelen�s��rt felel�s 
	 * 				JPanel objektumok referenci�j�t t�rolja. 
	 */
	
	protected String direction;
	protected Tuple head;
	protected boolean isGrowing;
	protected boolean isDead;
	private Grid grid;
	protected int point;
	protected boolean isMoving;
	protected ArrayList<Tuple> snake = new ArrayList<Tuple>();
	protected ArrayList<JPanel> bodyElements = new ArrayList<JPanel>();
	
	/*
	 * A Snake oszt�ly konstruktora. A Snake oszt�ly �r�kli a Grid oszt�ly actFrame �s actCellNum param�tereit. 
	 */
	public Snake(Frame actFrame, int actCellNum)
	{
		super(actFrame, actCellNum);
	}
	
	/*
	 * A met�dus megh�v�s�nak hat�s�ra be�ll�tja a Snake oszt�ly grid attrib�tum�hoz be�ll�tja a param�terk�nt �tvett Grid objektumot.
	 */
	public void setGrid(Grid g){this.grid = g;}
	
	/*
	 * A met�dus megh�v�s�nak hat�s�ra a j�t�kfel�letr�l t�rl�dnek a bodyElements-ben t�rolt JPanel refernci�nak megfelel� JPanel objektumok
	 * a j�t�kfel�letr�l.
	 */
	protected void remove()
	{
		for (int i = 0; i < bodyElements.size(); i++)
		{
			gameFrame.getLayeredPane().remove(bodyElements.get(i));
		}
		bodyElements = new ArrayList<JPanel>();
	}
	
	/*
	 * A Snake oszt�ly draw met�dusa, amely a Snake1 �s Snake2 oszt�lyokban elt�r�en vannak defini�lva.
	 */
	protected void draw(){}
}
