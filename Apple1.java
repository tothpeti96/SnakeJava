package snake;

import java.awt.Color;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Apple1 extends Apple
{
	/*
	 * A Apple1 osztály konstruktora. Örökli a Grid osztály actFrame és actCellNum paramétereit. Ezenfelül paramétere az alma kezdetleges x, y koordinátái.
	 * Beállítja az alma kezdetleges pozícióját, illetve inicializálja az alma értékét. Az alma értéke az Apple1 osztály esetében 1.
	 */
	public Apple1(int x, int y, Frame actFrame, int actCellNum)
	{		
		
		super(actFrame, actCellNum);
		
		this.X = x;
		this.Y = y;
		this.value = 1;
	}
	
	
	/*
	 * A metódus gondoskodik az almát reprezentáló JPanel típusú objektum kirajzolásáról az alma aktuális pozíciójának megfelelõen.
	 * Az alma színe az Apple1 osztály esetében piros. 
	 */
	protected void drawApple()
	{
				
		JPanel applePanel = new JPanel();
		applePanel.setBackground(Color.red);
		applePanel.setBounds(this.X * this.cellSize, this.Y * this.cellSize + 50, this.cellSize, this.cellSize);
		appleReference.add(applePanel);
		gameFrame.getLayeredPane().add(applePanel, JLayeredPane.DEFAULT_LAYER);
				
		SwingUtilities.updateComponentTreeUI(gameFrame);
	}
}
