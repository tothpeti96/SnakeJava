package snake;

import java.util.ArrayList;

public class Tuple
{
	
	/*
	 * A Tuple oszt�ly �ltal defini�lt attrib�tumok.
	 * 		X: int t�pus� v�ltoz�, amely a Tuple objektum 1. koordin�t�j�t adja meg.
	 * 		Y: int t�pus� v�ltoz�, amely a Tuple objektum 2. koordin�t�j�t adja meg.
	 */
	
	protected int X;
	protected int Y;
	
	
	/*
	 * A Tuple oszt�ly konstruktora, amely a megadott param�tereknek megfelel�en be�ll�tja a Tuple oszt�ly koordin�t�it
	 * Param�terei:
	 * 		-X: int t�pus� v�ltoz�, amely a Tuple objektum 1. koordin�t�ja �ll�that� be.
	 * 		-Y: int t�pus� v�ltoz�, amely a Tuple objektum 2. koordin�t�ja �ll�that� be.
	 */
	public Tuple(int X, int Y)
	{
		this.X = X;
		this.Y = Y;
	}
	
	/*
	 * A met�dus a Tuple objektum �ltal defini�lt koordin�t�kkal t�r vissza "(X,Y)" form�tum� String-k�nt. 
	 */
	public String toString()
	{
		String s = "(" + this.X + "," + this.Y + ")"; 
		return s;
	}
	
	/*
	 * A met�dus a param�terk�nt megadott, Tupel objektumokat tartlamaz� dinamukus t�mbb�l t�rli ki az adott Tuple objektumot.
	 */
	public void remove(ArrayList<Tuple> tupleArray)
	{
		int actX = this.X;
		int actY = this.Y;
		
		for (int i = 0; i < tupleArray.size(); i++)
		{
			Tuple actTuple = tupleArray.get(i);
			
			if(actTuple.X == actX && actTuple.Y == actY)
			{
				tupleArray.remove(actTuple);
			}
		}
	}
	
	/*
	 * A met�dus a param�terk�nt megadott Tuple x �s y koordin�t�j�hoz hozz�adja az adott objektum x �s y koordin�t�it, majd az 
	 * �sszegzett x �s y koordin�t�kat defini�l� Tuple objektummal t�r vissza. 
	 */
	public Tuple add(Tuple actTuple)
	{
		int newX = this.X + actTuple.X;
		int newY = this.Y + actTuple.Y;
		
		Tuple result = new Tuple(newX, newY);
		return result;
	}
}
