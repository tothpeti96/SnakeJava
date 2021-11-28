package snake;

import java.util.ArrayList;
import javax.swing.JPanel;

public class Apple extends Grid
{
	/*
	 * Az Apple osztály a Grid osztály leszármazottja. 
	 * Heterogén kollekciója az Apple1 és az Apple2 leszármazott osztályoknak.
	 * 
	 * Az Apple osztály által definiált attribútumok:
	 * 
	 * 		X: int típusú változó. Az alma aktiuális X pozícióját tartalmazza.
	 * 
	 * 		Y: int típusú változó. Az alma aktiuális Y pozícióját tartalmazza.
	 * 
	 * 		isEaten: boolean típusú változó. Az alma állapotát tárolja, hogy megevére került-e valamely kígyó által.
	 * 
	 * 		grid: A Grid osztály egy példánya.
	 * 
	 * 		value: int típusú változó. Az alma értékét tárolja, amely az almát megevõ kígyó pontszámaihoz kerül majd regisztrálásra.
	 * 
	 * 		appleReference: Dinamikus tömb ami Tuple típusú objektumokat tárol. Az apple dinamikus tömb tartalmazza az aktuális
	 * 				alma koordinátáit.
	 */	
	
	protected int X;
	protected int Y;
	protected boolean isEaten;
	private Grid grid;
	protected int value;
	protected ArrayList<JPanel> appleReference = new ArrayList<JPanel>();
	
	/*
	 * Az Apple osztály konstruktora. Az Apple osztály örökli a Grid osztály actFrame és actCellNum paramétereit. 
	 */
	public Apple(Frame actFrame, int actCellNum)
	{
		super(actFrame, actCellNum);
	}
	
	/*
	 * A metódus meghívásának hatására beállítja az Apple osztály grid attribútumához beállítja a paraméterként átvett Grid objektumot.
	 */
	protected void setGrid(Grid g){this.grid = g;}
	
	
	/*
	 * A metódus meghívásának hatására a játékfelületrõl törlõdik az appleReference-ben tárolt JPanel refernciának megfelelõ JPanel objektum
	 * a játékfelületrõl.
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
	 * Az Apple osztály draw metódusa, amely az Apple1 és Apple2 osztályokban eltérõen vannak definiálva.
	 */
	protected void drawApple(){}
}
