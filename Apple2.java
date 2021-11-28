package snake;

import java.awt.Color;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Apple2 extends Apple
{
	/*
	 * A Apple2 oszt�ly konstruktora. �r�kli a Grid oszt�ly actFrame �s actCellNum param�tereit. Ezenfel�l param�tere az alma kezdetleges x, y koordin�t�i.
	 * Be�ll�tja az alma kezdetleges poz�ci�j�t, illetve inicializ�lja az alma �rt�k�t. Az alma �rt�ke az Apple2 oszt�ly eset�ben 3.
	 */
	public Apple2(int x, int y, Frame actFrame, int actCellNum)
	{	
		super(actFrame, actCellNum);
		
		this.X = x;
		this.Y = y;
		this.value = 3;
	}
		
	/*
	 * A met�dus gondoskodik az alm�t reprezent�l� JPanel t�pus� objektum kirajzol�s�r�l az alma aktu�lis poz�ci�j�nak megfelel�en.
	 * Az alma sz�ne az Apple2 oszt�ly eset�ben lila. 
	 */
	
	public void drawApple()
	{		
		JPanel applePanel = new JPanel();
		applePanel.setBackground(new Color(153, 50, 204));
		applePanel.setBounds(this.X * this.cellSize, this.Y * this.cellSize + 50, this.cellSize, this.cellSize);
		appleReference.add(applePanel);
		gameFrame.getLayeredPane().add(applePanel, JLayeredPane.DEFAULT_LAYER);
		
		SwingUtilities.updateComponentTreeUI(gameFrame);
	}
}