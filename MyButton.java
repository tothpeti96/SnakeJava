package snake;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class MyButton extends JButton implements ActionListener
{	
	
	/*
	 * A MyButton osztály konstruktora, amely az objektum példányosításakor beállítja a gomb objektum pozícióját, szövegét, betûtípusát és fókuszálhatóságát.
	 * Emellett az objektumhoz hozzáadja az ActionListener interfészt is, amely lehetõvé teszi, hogy a gomb lenyomásakor annak megfelelõ hatások legyenek kiválthatóak.
	 */
	
	public MyButton(int x, int y, String actText, Object actObject)
	{
		this.setBounds(x, y, 200, 100);
		this.addActionListener((ActionListener) actObject);
		this.setText(actText);
		this.setFocusable(false);
		this.setFont(new Font("Courier New",1,25));	
	}

	@Override
	public void actionPerformed(ActionEvent e) {}	
}

