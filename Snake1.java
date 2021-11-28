package snake;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Snake1 extends Snake implements KeyListener
{
	
	/*
	 * A Snake1 oszt�ly konstruktora. �r�kli a Grid oszt�ly actFrame �s actCellNum param�tereit.
	 * Ezen fel�l megadja a k�gy� kezdetleges poz�ci�j�t, illetve be�ll�tja a head, direction, isGrowing, isDead, isMoving �s point attrib�tomok
	 * kezdeti �rt�keit.
	 * A Grid gameFrame attrib�tom�hoz hozz�rendeli a KeyLsitener interf�szt, amely lehet�v� teszi, hogy a megfelel� gombnyom�sok hat�s�ra
	 * azoknak megfelel�en tudjuk mozgatni a k�gy�t.  
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
	 * A met�dus gondoskodik a k�gy�t reprezent�l� JPanel t�pus� objektumok kirajzol�s�r�l a k�gy� aktu�lis poz�ci�j�nak megfelel�en.
	 * A k�gy� fej eleme elt�r� sz�nnel lesz kirajzolva mint a k�gy� test�nek t�bbi r�sze. (Z�ld �s vil�gosz�ld)
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
	 * A KeyListener oszt�ly keyPressed met�dus�nak t�lterhel�se, hogy annak viselked�se a felhaszn�l�i ig�nyeknek megfelel� legyen.
	 * A keyPressed met�dus felel a k�gy� ir�ny�nak megv�ltoztat�s�r�l az �ppen lenyomott gombnak megfelel�en. 
	 * A Snake1 t�pus� objektumok ir�nya a le, fel, jobbra �s balra nyilakkal ir�ny�that�.
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
