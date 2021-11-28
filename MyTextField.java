package snake;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;

public class MyTextField extends JTextField
{
	/*
	 * A MyTextField oszt�ly konstruktora, amely az objektum p�ld�nyos�t�sakor be�ll�tja a sz�vegdoboz objektum poz�ci�j�t, default sz�veg�t, bet�t�pus�t �s f�kusz�lhat�s�g�t,
	 * illetve a sz�vegdoboz h�tter�nek sz�n�t.
	 */
	
	public MyTextField(int x, int y, String actText)
	{
		this.setBounds(x,y,250,50);
		this.setFont(new Font("Courier New",1,25));
		this.setBackground(Color.gray);
		this.setText(actText);
		this.setFocusable(true);
	}
}
