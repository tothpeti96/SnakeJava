package snake;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Snake1 extends Snake implements KeyListener
{
	
	/*
	 * A Snake1 osztály konstruktora. Örökli a Grid osztály actFrame és actCellNum paramétereit.
	 * Ezen felül megadja a kígyó kezdetleges pozícióját, illetve beállítja a head, direction, isGrowing, isDead, isMoving és point attribútomok
	 * kezdeti értékeit.
	 * A Grid gameFrame attribútomához hozzárendeli a KeyLsitener interfészt, amely lehetõvé teszi, hogy a megfelelõ gombnyomások hatására
	 * azoknak megfelelõen tudjuk mozgatni a kígyót.  
	 */
	
	public Snake1(Frame actFrame, int actCellNum)
	{	
		super(actFrame, actCellNum);
		
		snake.add(new Tuple(8,5));
		snake.add(new Tuple(7,5));
		snake.add(new Tuple(6,5));
		snake.add(new Tuple(5,5));
		
		this.head = snake.get(0);
		this.direction = "right";
		this.isGrowing = false;
		this.isDead = false;
		this.isMoving = false;
		this.point = 0;
		gameFrame.addKeyListener(this);
		
		this.draw();
	}
		
	/*
	 * A metódus gondoskodik a kígyót reprezentáló JPanel típusú objektumok kirajzolásáról a kígyó aktuális pozíciójának megfelelõen.
	 * A kígyó fej eleme eltérõ színnel lesz kirajzolva mint a kígyó testének többi része. (Zöld és világoszöld)
	 */
	protected void draw()
	{	
			JPanel headPanel = new JPanel();
			headPanel.setBackground(Color.green);
			headPanel.setBounds(snake.get(0).X * this.cellSize, snake.get(0).Y * this.cellSize + 50, this.cellSize, this.cellSize);
			bodyElements.add(headPanel);
			gameFrame.getLayeredPane().add(headPanel, JLayeredPane.POPUP_LAYER);
		
			for (int i = 1; i < snake.size(); i++)
			{
				JPanel snakePanel = new JPanel();
				snakePanel.setBackground(new Color(0,200,0));
				snakePanel.setBounds(snake.get(i).X * this.cellSize, snake.get(i).Y * this.cellSize + 50, this.cellSize, this.cellSize);
				bodyElements.add(snakePanel);
				gameFrame.getLayeredPane().add(snakePanel, JLayeredPane.POPUP_LAYER);
			}
	}
	
	/*
	 * A KeyListener osztály keyPressed metódusának tõlterhelése, hogy annak viselkedése a felhasználói igényeknek megfelelõ legyen.
	 * A keyPressed metódus felel a kígyó irányának megváltoztatásáról az éppen lenyomott gombnak megfelelõen. 
	 * A Snake1 típusú objektumok iránya a le, fel, jobbra és balra nyilakkal irányítható.
	 */
	
	@Override
	public void keyPressed(KeyEvent e) 
	{
		// Start moving
		if ((e.getKeyCode() == 37 || e.getKeyCode() == 38 || e.getKeyCode() == 39 || e.getKeyCode() == 40) && this.isMoving == false){this.isMoving = true;}
		
		// Multiple Key hits --> Do nothing
		if ((e.getKeyCode() == 37 || e.getKeyCode() == 38 || e.getKeyCode() == 39 || e.getKeyCode() == 40) && this.isMoving == true){;}
				
		// left 
		if (e.getKeyCode() == 37 && !this.direction.equals("right")) {this.direction = "left";}
		// up
		else if (e.getKeyCode() == 38 && !this.direction.equals("down")){this.direction = "up";}
		// right
		else if (e.getKeyCode() == 39 && !this.direction.equals("left")){this.direction = "right";}
		// down
		else if (e.getKeyCode() == 40 && !this.direction.equals("up")){this.direction = "down";}
	}
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}
	
}
