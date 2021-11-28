package snake;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;

public class MyTextField extends JTextField
{
	/*
	 * A MyTextField osztály konstruktora, amely az objektum példányosításakor beállítja a szövegdoboz objektum pozícióját, default szövegét, betûtípusát és fókuszálhatóságát,
	 * illetve a szövegdoboz hátterének színét.
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
