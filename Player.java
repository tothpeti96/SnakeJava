package snake;
import java.io.Serializable;

public class Player implements Serializable
{
	/*
	 * A Player osztály implementálja a "Serializable" interfészt, hogy a Player osztály egyes példányai a program futása során
	 * bitfájlként szerializálhatóak legyenek. A Player osztály az alábbi attribútumokat definiálja:
	 * 		name: String típusú, az egyes játékosnevet tároló változó.
	 * 		point: Integer típusú változó. Az egyes játékoshoz tartozó ponszámot tárolja.
	 */
	
	protected String name;
	protected int point;
	
	/*
	 * A Player osztály konstruktora. Paramétere a String típusú name és int típusú point változók.
	 * A konstruktor beállítja az osztályon definiált attribútumokat a megadott paramétereknek megfelelõen.
	 */
	
	public Player(String name, int point)
	{
		this.name = name;
		this.point = point;
	}
	
	/*
	 * A metódus a primitív point attribútum Integer típussá kasztolt változatával tér vissza. 
	 */
	
	public Integer getPoint()
	{
		return (Integer) this.point;
	}
}
