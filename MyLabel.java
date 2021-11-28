package snake;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class MyLabel extends JLabel
{	
	/*
	 * A MyLabel oszt�ly konstruktora, amely az objektum p�ld�nyos�t�sakor be�ll�tja a c�mke objektum poz�ci�j�t, sz�veg�t,
	 * bet�t�pus�t illetve bet�t�pus sz�n�t. Param�terei:
	 * 		actText: String t�pus� v�ltoz�, amellyel be�ll�that�, hogy milyen sz�veg jelenjen meg a c�mke objektumon.
	 * 		x: int t�pus� v�ltoz�, amellyel a c�mke objektum bal fels� sark�nak x koordin�t�ja �ll�that� be a k�perny�n.
	 * 		y: int t�pus� v�ltoz�, amellyel a c�mke objektum bal fels� sark�nak y koordin�t�ja �ll�that� be a k�perny�n.
	 */
	
	public MyLabel(String actText, int x, int y)
	{
		this.setText(actText);
		this.setBounds(x, y, 400, 50);
		this.setForeground(Color.white);
		this.setFont(new Font("Courier New",1,30));
	}	
}
