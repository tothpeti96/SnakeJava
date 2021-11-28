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
	 * A Snake2 oszt�ly konstruktora. �r�kli a Grid oszt�ly actFrame �s actCellNum param�tereit.
	 * Ezen fel�l megadja a k�gy� kezdetleges poz�ci�j�t, illetve be�ll�tja a head, direction, isGrowing, isDead, isMoving �s point attrib�tomok
	 * kezdeti �rt�keit.
	 * A Grid gameFrame attrib�tom�hoz hozz�rendeli a KeyLsitener interf�szt, amely lehet�v� teszi, hogy a megfelel� gombnyom�sok hat�s�ra
	 * azoknak megfelel�en tudjuk mozgatni a k�gy�t.  
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
	 * A met�dus gondoskodik a k�gy�t reprezent�l� JPanel t�pus� objektumok kirajzol�s�r�l a k�gy� aktu�lis poz�ci�j�nak megfelel�en.
	 * A k�gy� fej eleme elt�r� sz�nnel lesz kirajzolva mint a k�gy� test�nek t�bbi r�sze. (s�rga �s vil�goss�rga)
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
	 * A KeyListener oszt�ly keyPressed met�dus�nak t�lterhel�se, hogy annak viselked�se a felhaszn�l�i ig�nyeknek megfelel� legyen.
	 * A keyPressed met�dus felel a k�gy� ir�ny�nak megv�ltoztat�s�r�l az �ppen lenyomott gombnak megfelel�en. 
	 * A Snake2 t�pus� objektumok ir�nya a W, A, S �s D gombok lenyom�s�val ir�ny�that�.
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
