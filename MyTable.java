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
	 * A MyTable oszt�ly konstruktora, amely az objektum p�ld�nyos�t�sakor be�ll�tja a t�bl�zat objektum k�l�nb�z� attrib�tumait.
	 * A MyTable oszt�ly param�terei:
	 * 
	 * 		-scores: Player t�pus� objektumokat t�rol� List t�pus� objektum.
	 * 		-actFrame: Frame t�pus� objektum. Megadja az objektumnak, hogy a MyTable objektumot melyik k�preny�re rajzolja majd ki.
	 * 		-x: integer t�pus� v�ltoz�. A t�bl�zat bal fels� sark�nak x koordin�t�j�t adja meg.
	 * 		-y: integer t�pus� v�ltoz�. A t�bl�zat bal fels� sark�nak y koordin�t�j�t adja meg.
	 * 
	 * Az objektum p�ld�nyos�t�sa sor�n a MyTable oszt�ly a param�terk�nt kapott scores oszt�ly elemeit n�vekv� sorrendbe rendezi a benne elhelyezked�
	 * Player objektumok "point" attrib�tuma alapj�n. A sorbarendez�shez a Collections oszt�ly sort met�dus�t haszn�ltam, a Player oszt�ly adott attrib�tuma 
	 * szerinti sorbrendez�s pedig a Comperator oszt�ly seg�ts�g�vel t�rt�nt. A sorbarendez�s ut�n a scores elemeit megford�tja a konstruktor, hogy imm�ron 
	 * cs�kken� sorrendben szerepeljenek a pontsz�mok, a legmagasabb pontsz�m legyen el�l.
	 * 
	 * Eztut�n a konstruktor l�trehoz egy DefaulTableModel t�pus� tableModel objektumot, amely a t�bl�zat adatait tartalmazza. A tableModel-hez adatok egy
	 * JTable t�pus� objektumnak ker�lnek majd �tad�sra. Ezenfel�l t�lterhelj�k a DefaulTableModel isCellEditAble met�dus�t, hogy a felhaszn�l� ne tudja
	 * m�dos�tani a DefaluTableModel objektumon defini�lt adatokat. 
	 * 
	 * A tableModel objektumon be�ll�tjuk a t�bl�zat fejl�c�t, majd a m�r rendezett scores egyes elemeib�l az elk�sz�tett objektumokat hozz�adjuk 
	 * a tableModel objektumhoz.
	 * 
	 * Ezut�n t�rt�nik meg a table objektum param�tereinek a be�ll�t�sa. Be�ll�t�sra ker�l a t�bl�zat h�tter�nek, bet�t�pus�nak, illetve a kijel�l�s
	 * sor� t�rt�n� kiemel�snek a sz�ne. Emellett m�g be�ll�t�sra ker�l a t�bl�zat r�cs�nak sz�ne, az egyes cell�k magass�ga, illetve, hogy a t�bl�zat egyes
	 * oszlopai r�gz�tve legyenek. 
	 * 
	 * Az elk�sz�lt t�bl�zatot v�g�l hozz�adjuk egy JScrollPane t�pus� objektumhoz, hogy a felhaszn�l� szabadon tudjon g�rgetni a t�bl�zaton, abban az esetben ha
	 * a t�bl�zaton megjelen� adathalmazra a t�bl�zat default m�rete nem adna megfelel� betekint�st.
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
		
		Object[] columns = {"Helyez�s", "N�v", "Pontsz�m"};
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
