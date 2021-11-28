package snake;

import java.util.ArrayList;

public class Tuple
{
	
	/*
	 * A Tuple osztály által definiált attribútumok.
	 * 		X: int típusú változó, amely a Tuple objektum 1. koordinátáját adja meg.
	 * 		Y: int típusú változó, amely a Tuple objektum 2. koordinátáját adja meg.
	 */
	
	protected int X;
	protected int Y;
	
	
	/*
	 * A Tuple osztály konstruktora, amely a megadott paramétereknek megfelelõen beállítja a Tuple osztály koordinátáit
	 * Paraméterei:
	 * 		-X: int típusú változó, amely a Tuple objektum 1. koordinátája állítható be.
	 * 		-Y: int típusú változó, amely a Tuple objektum 2. koordinátája állítható be.
	 */
	public Tuple(int X, int Y)
	{
		this.X = X;
		this.Y = Y;
	}
	
	/*
	 * A metódus a Tuple objektum által definiált koordinátákkal tér vissza "(X,Y)" formátumó String-ként. 
	 */
	public String toString()
	{
		String s = "(" + this.X + "," + this.Y + ")"; 
		return s;
	}
	
	/*
	 * A metódus a paraméterként megadott, Tupel objektumokat tartlamazó dinamukus tömbbõl törli ki az adott Tuple objektumot.
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
	 * A metódus a paraméterként megadott Tuple x és y koordinátájához hozzáadja az adott objektum x és y koordinátáit, majd az 
	 * összegzett x és y koordinátákat definiáló Tuple objektummal tér vissza. 
	 */
	public Tuple add(Tuple actTuple)
	{
		int newX = this.X + actTuple.X;
		int newY = this.Y + actTuple.Y;
		
		Tuple result = new Tuple(newX, newY);
		return result;
	}
}
