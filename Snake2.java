package snake;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Snake2 extends Snake implements KeyListener
{
	/*
	 * A Snake2 osztály konstruktora. Örökli a Grid osztály actFrame és actCellNum paramétereit.
	 * Ezen felül megadja a kígyó kezdetleges pozícióját, illetve beállítja a head, direction, isGrowing, isDead, isMoving és point attribútomok
	 * kezdeti értékeit.
	 * A Grid gameFrame attribútomához hozzárendeli a KeyLsitener interfészt, amely lehetõvé teszi, hogy a megfelelõ gombnyomások hatására
	 * azoknak megfelelõen tudjuk mozgatni a kígyót.  
	 */
	public Snake2(Frame actFrame, int actCellNum)
	{
		
		super(actFrame, actCellNum);
		
		snake.add(new Tuple(8,11));
		snake.add(new Tuple(7,11));
		snake.add(new Tuple(6,11));
		snake.add(new Tuple(5,11));
		
		this.head = snake.get(0);
		this.direction = "rigth";
		this.isGrowing = false;
		this.isDead = false;
		this.isMoving = false;
		this.point = 0;
		gameFrame.addKeyListener(this);
		this.draw();
	}
	
	/*
	 * A metódus gondoskodik a kígyót reprezentáló JPanel típusú objektumok kirajzolásáról a kígyó aktuális pozíciójának megfelelõen.
	 * A kígyó fej eleme eltérõ színnel lesz kirajzolva mint a kígyó testének többi része. (sárga és világossárga)
	 */
	protected void draw()
	{
			
			JPanel headPanel = new JPanel();
			headPanel.setBackground(Color.yellow);
			headPanel.setBounds(snake.get(0).X * this.cellSize, snake.get(0).Y * this.cellSize + 50, this.cellSize, this.cellSize);
			bodyElements.add(headPanel);
			gameFrame.getLayeredPane().add(headPanel, JLayeredPane.POPUP_LAYER);
		
			
			for (int i = 1; i < snake.size(); i++)
			{
			
				JPanel snakePanel = new JPanel();
				snakePanel.setBackground(new Color(255,204,0));
				snakePanel.setBounds(snake.get(i).X * this.cellSize, snake.get(i).Y * this.cellSize + 50, this.cellSize, this.cellSize);
				bodyElements.add(snakePanel);
				gameFrame.getLayeredPane().add(snakePanel, JLayeredPane.POPUP_LAYER);
	
			}
			SwingUtilities.updateComponentTreeUI(gameFrame);
	}
	
	/*
	 * A KeyListener osztály keyPressed metódusának túlterhelése, hogy annak viselkedése a felhasználói igényeknek megfelelõ legyen.
	 * A keyPressed metódus felel a kígyó irányának megváltoztatásáról az éppen lenyomott gombnak megfelelõen. 
	 * A Snake2 típusú objektumok iránya a W, A, S és D gombok lenyomásával irányítható.
	 */
	
	@Override
	public void keyPressed(KeyEvent e) 
	{
		
		// Start moving
		if ((e.getKeyCode() == 65 || e.getKeyCode() == 87 || e.getKeyCode() == 68 || e.getKeyCode() == 83) && this.isMoving == false)
		{
			this.isMoving = true;
		}
				
		// Multiple Key hits --> Do nothing
		if ((e.getKeyCode() == 65 || e.getKeyCode() == 87 || e.getKeyCode() == 68 || e.getKeyCode() == 83) && this.isMoving == true){;}
		
		// left 
		if (e.getKeyCode() == 65 && !this.direction.equals("right")) 
		{
			this.direction = "left";		
		}
		// up
		else if (e.getKeyCode() == 87 && !this.direction.equals("down"))
		{
			this.direction = "up";
		}
		// right
		else if (e.getKeyCode() == 68 && !this.direction.equals("left"))
		{			
			this.direction = "right";
		}
		// down
		else if (e.getKeyCode() == 83 && !this.direction.equals("up"))
		{
			this.direction = "down";
		}	
	}
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}
	
}
