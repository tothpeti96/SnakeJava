package snake;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
public class Frame extends JFrame
{	
	/*
	 * A Frame oszt�ly �ltal defini�lt attrib�tumok:
	 * 
	 * x: integer t�pus� v�ltoz�. A k�perny� sz�less�gi dimenzi�j�t adja meg.
	 * y: integer t�pus� v�ltoz�. A k�perny� hosszus�gi dimenzi�j�t adja meg.
	 * layeredPane: JLayeredPane t�pus� objektum, amely seg�ts�g�vel a k�perny�n elhelyezked� j�t�kelemeknek m�lys�get lehet adni.
	 * header: JPanel t�pus� objektum, amely egy k�l�n�ll� �s szerkeszthet� panelk�nt jelenik meg a k�perny�n.
	 */
	
	private int x;
	private int y;
	private JLayeredPane layeredPane;
	protected JPanel header;
	
	/*
	 * A Frame oszt�ly konstruktora. Be�ll�tja az objektum x �s y attrib�tumait, illetve a Frame objektum legfontosabb tulajdons�gait.
	 * 		-Be�ll�tja a k�perny� nagys�g�t.
	 * 		-Be�ll�tja a k�perny� megjelen�s�t:
	 * 			- A j�t�kos ne tudja bez�rni a j�t�kablakot a program fut�s�nak b�rmely adott pillanat�ban.
	 * 			- A j�t�kfel�let legyen az objektum p�ld�nyos�t�s pillanat�ban l�that�.
	 * 			- A k�perny� h�tter�nek sz�n�t �ll�tsa be feket�re.
	 * 			- A j�t�kfel�let a felhaszn�l� k�perny�j�nek k�zep�re ker�lj�n.
	 * 			- A j�t�kfel�leten az egyes objektumok legyenek m�dos�that�ak. 
	 * Ezen fel�l a konstruktor a Frame objektumhoz m�g hozz�adja a JLayeredPane t�pus� objektumot, illetve a j�t�kfel�let fejl�cek�nt szolg�l� 
	 * JPanel objektumot.
	 */
	
	public Frame()
	{
		this.x = 600;
		this.y = 650;
		this.setSize(this.x,this.y);
		this.setUndecorated(true);
		this.setVisible(true);
		this.getContentPane().setBackground(new Color(0,0,0));
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		
		this.layeredPane = new JLayeredPane();
		this.layeredPane.setBounds(0,0, this.x, this.y);
		this.add(layeredPane);
		
		this.header = new JPanel();
		this.header.setBackground(new Color(0,0,255));
		this.header.setBounds(0, 0, this.x, 50);
		this.header.setLayout(null);
		this.getLayeredPane().add(this.header, JLayeredPane.DRAG_LAYER);
	}
}
