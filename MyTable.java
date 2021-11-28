package snake;

import java.awt.Color;
import java.awt.Font;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MyTable extends JTable
{
	/*
	 * A MyTable osztály konstruktora, amely az objektum példányosításakor beállítja a táblázat objektum különbözõ attribútumait.
	 * A MyTable osztály paraméterei:
	 * 
	 * 		-scores: Player típusú objektumokat tároló List típusú objektum.
	 * 		-actFrame: Frame típusú objektum. Megadja az objektumnak, hogy a MyTable objektumot melyik képrenyõre rajzolja majd ki.
	 * 		-x: integer típusú változó. A táblázat bal felsõ sarkának x koordinátáját adja meg.
	 * 		-y: integer típusú változó. A táblázat bal felsõ sarkának y koordinátáját adja meg.
	 * 
	 * Az objektum példányosítása során a MyTable osztály a paraméterként kapott scores osztály elemeit növekvõ sorrendbe rendezi a benne elhelyezkedõ
	 * Player objektumok "point" attribútuma alapján. A sorbarendezéshez a Collections osztály sort metódusát használtam, a Player osztály adott attribútuma 
	 * szerinti sorbrendezés pedig a Comperator osztály segítségével történt. A sorbarendezés után a scores elemeit megfordítja a konstruktor, hogy immáron 
	 * csökkenõ sorrendben szerepeljenek a pontszámok, a legmagasabb pontszám legyen elõl.
	 * 
	 * Eztután a konstruktor létrehoz egy DefaulTableModel típusú tableModel objektumot, amely a táblázat adatait tartalmazza. A tableModel-hez adatok egy
	 * JTable típusó objektumnak kerülnek majd átadásra. Ezenfelül túlterheljük a DefaulTableModel isCellEditAble metódusát, hogy a felhasználó ne tudja
	 * módosítani a DefaluTableModel objektumon definiált adatokat. 
	 * 
	 * A tableModel objektumon beállítjuk a táblázat fejlécét, majd a már rendezett scores egyes elemeibõl az elkészített objektumokat hozzáadjuk 
	 * a tableModel objektumhoz.
	 * 
	 * Ezután történik meg a table objektum paramétereinek a beállítása. Beállításra kerül a táblázat hátterének, betûtípusának, illetve a kijelölés
	 * sorá történõ kiemelésnek a színe. Emellett még beállításra kerül a táblázat rácsának színe, az egyes cellák magassága, illetve, hogy a táblázat egyes
	 * oszlopai rögzítve legyenek. 
	 * 
	 * Az elkészült táblázatot végül hozzáadjuk egy JScrollPane típusú objektumhoz, hogy a felhasználó szabadon tudjon görgetni a táblázaton, abban az esetben ha
	 * a táblázaton megjelenõ adathalmazra a táblázat default mérete nem adna megfelelõ betekintést.
	 */
	
	public MyTable(List<Player> scores, Frame actFrame, int x, int y)
	{
		Collections.sort(scores, new Comparator<Player>()
		{
			public int compare(Player p1, Player p2)
			{
				return p1.getPoint().compareTo(p2.getPoint());
			}
		});
		Collections.reverse(scores);
		
		Object[] columns = {"Helyezés", "Név", "Pontszám"};
		DefaultTableModel tableModel = new DefaultTableModel(columns, 0) {
			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		
		JTable table = new JTable(tableModel);
		
		for (int i = 0; i < scores.size(); i++)
		{
			Player actScore = scores.get(i);
			int actPosition = i + 1;
			String actName = actScore.name;
			int actPoints = actScore.point;
			
			Object[] actData = {actPosition, actName, actPoints};
			tableModel.addRow(actData);
		}
		
		table.setModel(tableModel);
		table.setBackground(Color.black);
		table.setForeground(Color.white);
		table.setSelectionBackground(Color.blue);
		table.setGridColor(Color.blue);
		table.setSelectionForeground(Color.white);
		table.setFont(new Font("New Courier", Font.BOLD, 17));
		table.setRowHeight(30);
		table.setAutoCreateRowSorter(true);
		table.getTableHeader().setReorderingAllowed(false);
	
		table.getTableHeader().setBackground(Color.black);
		table.getTableHeader().setForeground(Color.white);
		table.getTableHeader().setFont(new Font("New Courier", Font.BOLD, 17));
		tableModel.setColumnIdentifiers(columns);
		
		int paneLength = (scores.size() + 1) * 30; 
		JScrollPane pane = new JScrollPane(table);
		pane.setForeground(Color.blue);
		pane.setBackground(Color.black);
		
		if (paneLength < 150){pane.setBounds(x,y,400,paneLength);}
		else{pane.setBounds(x,y,400,150);}
		
		actFrame.getContentPane().add(pane);
			
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
