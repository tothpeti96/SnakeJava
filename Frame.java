package snake;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
public class Frame extends JFrame
{	
	/*
	 * A Frame osztály által definiált attribútumok:
	 * 
	 * x: integer típusú változó. A képernyõ szélességi dimenzióját adja meg.
	 * y: integer típusú változó. A képernyõ hosszusági dimenzióját adja meg.
	 * layeredPane: JLayeredPane típusú objektum, amely segítségével a képernyõn elhelyezkedõ játékelemeknek mélységet lehet adni.
	 * header: JPanel típusú objektum, amely egy különálló és szerkeszthetõ panelként jelenik meg a képernyõn.
	 */
	
	private int x;
	private int y;
	private JLayeredPane layeredPane;
	protected JPanel header;
	
	/*
	 * A Frame osztály konstruktora. Beállítja az objektum x és y attribútumait, illetve a Frame objektum legfontosabb tulajdonságait.
	 * 		-Beállítja a képernyõ nagyságát.
	 * 		-Beállítja a képernyõ megjelenését:
	 * 			- A játékos ne tudja bezárni a játékablakot a program futásának bármely adott pillanatában.
	 * 			- A játékfelület legyen az objektum példányosítás pillanatában látható.
	 * 			- A képernyõ hátterének színét állítsa be feketére.
	 * 			- A játékfelület a felhasználó képernyõjének közepére kerüljön.
	 * 			- A játékfelületen az egyes objektumok legyenek módosíthatóak. 
	 * Ezen felül a konstruktor a Frame objektumhoz még hozzáadja a JLayeredPane típusú objektumot, illetve a játékfelület fejléceként szolgáló 
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
