package snake;
import java.io.Serializable;

public class Player implements Serializable
{
	/*
	 * A Player oszt�ly implement�lja a "Serializable" interf�szt, hogy a Player oszt�ly egyes p�ld�nyai a program fut�sa sor�n
	 * bitf�jlk�nt szerializ�lhat�ak legyenek. A Player oszt�ly az al�bbi attrib�tumokat defini�lja:
	 * 		name: String t�pus�, az egyes j�t�kosnevet t�rol� v�ltoz�.
	 * 		point: Integer t�pus� v�ltoz�. Az egyes j�t�koshoz tartoz� ponsz�mot t�rolja.
	 */
	
	protected String name;
	protected int point;
	
	/*
	 * A Player oszt�ly konstruktora. Param�tere a String t�pus� name �s int t�pus� point v�ltoz�k.
	 * A konstruktor be�ll�tja az oszt�lyon defini�lt attrib�tumokat a megadott param�tereknek megfelel�en.
	 */
	
	public Player(String name, int point)
	{
		this.name = name;
		this.point = point;
	}
	
	/*
	 * A met�dus a primit�v point attrib�tum Integer t�puss� kasztolt v�ltozat�val t�r vissza. 
	 */
	
	public Integer getPoint()
	{
		return (Integer) this.point;
	}
}
