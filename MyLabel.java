package snake;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class MyLabel extends JLabel
{	
	/*
	 * A MyLabel osztály konstruktora, amely az objektum példányosításakor beállítja a címke objektum pozícióját, szövegét,
	 * betûtípusát illetve betûtípus színét. Paraméterei:
	 * 		actText: String típusú változó, amellyel beállítható, hogy milyen szöveg jelenjen meg a címke objektumon.
	 * 		x: int típusú változó, amellyel a címke objektum bal felsõ sarkának x koordinátája állítható be a képernyõn.
	 * 		y: int típusú változó, amellyel a címke objektum bal felsõ sarkának y koordinátája állítható be a képernyõn.
	 */
	
	public MyLabel(String actText, int x, int y)
	{
		this.setText(actText);
		this.setBounds(x, y, 400, 50);
		this.setForeground(Color.white);
		this.setFont(new Font("Courier New",1,30));
	}	
}
