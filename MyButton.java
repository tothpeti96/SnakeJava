package snake;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class MyButton extends JButton implements ActionListener
{	
	
	/*
	 * A MyButton oszt�ly konstruktora, amely az objektum p�ld�nyos�t�sakor be�ll�tja a gomb objektum poz�ci�j�t, sz�veg�t, bet�t�pus�t �s f�kusz�lhat�s�g�t.
	 * Emellett az objektumhoz hozz�adja az ActionListener interf�szt is, amely lehet�v� teszi, hogy a gomb lenyom�sakor annak megfelel� hat�sok legyenek kiv�lthat�ak.
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

