package snake;

import java.awt.Color;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Apple2 extends Apple
{
	/*
	 * A Apple2 osztály konstruktora. Örökli a Grid osztály actFrame és actCellNum paramétereit. Ezenfelül paramétere az alma kezdetleges x, y koordinátái.
	 * Beállítja az alma kezdetleges pozícióját, illetve inicializálja az alma értékét. Az alma értéke az Apple2 osztály esetében 3.
	 */
	public Apple2(int x, int y, Frame actFrame, int actCellNum)
	{	
		super(actFrame, actCellNum);
		
		this.X = x;
		this.Y = y;
		this.value = 3;
	}
		
	/*
	 * A metódus gondoskodik az almát reprezentáló JPanel típusú objektum kirajzolásáról az alma aktuális pozíciójának megfelelõen.
	 * Az alma színe az Apple2 osztály esetében lila. 
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