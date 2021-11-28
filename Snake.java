package snake;


import java.util.ArrayList;
import javax.swing.JPanel;

public class Snake extends Grid
{
	/*
	 * A Snake osztály a Grid osztály leszármazottja. 
	 * Heterogén kollekciója a Snake1 és Snake2 leszármazott osztályoknak.
	 * 
	 * A Snake osztály által definiált attribútumok:
	 * 
	 * 		direction: A kígyó aktuális haladási irányát tartalmazó, String típusú változó.
	 * 
	 * 		head: A kígyó fejének pozícióját tartalmazó, Tuple típusú változó.
	 * 
	 * 		isGrowing: Boolean típusú változó, amely megadja, hogy a kígyó éppen növekszik-e vagy sem.
	 * 
	 * 		isDead: Boolean típusú változó, amely megadja, hogy a kígyó még játékban van-e vagy sem.
	 * 
	 * 		grid: A Grid osztály egy példánya.
	 * 
	 * 		point: Integer típusú változó. A kígyó aktuális pontszámát tárolja. 
	 * 
	 * 		isMoving: Boolean típusú változó. Számon tartja, hogy a kígyó elkezdett már, vagy még mozog-e.
	 * 
	 * 		snake: Dinamikus tömb ami Tuple típusú objektumokat tárol. A snake dinamikus tömb tartalmazza a
	 * 				kígyó testelemeinek egyes koordinátáit.
	 * 
	 * 		bodyElements: dinamikus tömb amely JPanel típusú objektumokat tárol. A kígyó testelemeinek megjelenéséért felelõs 
	 * 				JPanel objektumok referenciáját tárolja. 
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
	 * A Snake osztály konstruktora. A Snake osztály örökli a Grid osztály actFrame és actCellNum paramétereit. 
	 */
	public Snake(Frame actFrame, int actCellNum)
	{
		super(actFrame, actCellNum);
	}
	
	/*
	 * A metódus meghívásának hatására beállítja a Snake osztály grid attribútumához beállítja a paraméterként átvett Grid objektumot.
	 */
	public void setGrid(Grid g){this.grid = g;}
	
	/*
	 * A metódus meghívásának hatására a játékfelületrõl törlõdnek a bodyElements-ben tárolt JPanel refernciának megfelelõ JPanel objektumok
	 * a játékfelületrõl.
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
	 * A Snake osztály draw metódusa, amely a Snake1 és Snake2 osztályokban eltérõen vannak definiálva.
	 */
	protected void draw(){}
}
