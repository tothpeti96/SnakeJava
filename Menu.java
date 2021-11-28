package snake;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Menu implements ActionListener
{
	
	/*
	 * A Menu osztály által definiált attribútumok.
	 * A fõbb attribútumok a következõek:
	 * 
	 * 		menuFrame: statikus adattag. A Frame osztály egy példánya, amelyre a különbözõ menüelemek kerülnek kiírásra.
	 * 
	 * 		onePlayerScores: Player típusú objektumoktat tartalmazó dinamikus tömb. Az egyjátékos mód során elért eredmények kerülnek ide elmentésre.
	 * 
	 * 		twoPlayerSocres: Player típusú objektumoktat tartalmazó dinamikus tömb. A kétjátékos mód során elért eredmények kerülnek ide elmentésre.
	 * 
	 * 		timer: Timer típusú objektum, ami a menüfelület ciklikus vezérlése miatt fontos.
	 * 
	 * 		grid: Grid típusú objektum. A játék elindításakkor kerül példányosításra. 
	 * 
	 * 		isRunning: boolean típusú objektum, amellyel ellenõrizhetjük, hogy a menüt vezérlõ eseményhurok legalább egyszer mér lefutott.
	 * 
	 * 		MyButton típusú objektumok: A JButton típusú objektumok leszármazottja. Mivel a menüfelületen hasonló típusú funkciójú gombok szerepelnek majd, így a 
	 * 			MyButton osztály a JButton osztály generalizációjának fogható fel.
	 * 
	 * 		MyTextField típusú objektumok: A JTextField típusú objektumok leszármazottja. Mivel a menüfelületen hasonló típusú funkciójú szövegdobozok szerepelnek majd, így a 
	 * 			MyTextField osztály a JTextField osztály generalizációjának fogható fel.
	 * 
	 */
	
	public static Frame menuFrame;
	private List <Player> onePlayerScores;
	private List <Player> twoPlayerScores;
	
	//Timer
	public Timer timer;
	
	//Grid
	public Grid grid;
	
	public boolean isRunnig;
	
	// main menu components
	public MyButton startMain;
	public MyButton scoreMain;
	public MyButton helpMain;
	public MyButton exitMain;
	
	// selection menu components
	public MyButton oneSelect;
	public MyButton twoSelect;
	public MyButton backSelect;
	
	//First player name selection
	public MyButton backName1;
	public MyButton playName1;
	public MyTextField playerName1;
	
	// Second player name selection
	public MyButton backName2;
	public MyButton playName2;
	public JTextField playerName2;
	
	//Score board buttons
	public MyButton backScore;
	
	//Help menu
	public MyButton backHelp;
	
	/*
	 * A Menu osztály konstruktora. Inicializálja a menuFrame, a timer és az isRunning változókat. Ezenfelül elindítja az idõzítõt, illetve meghívja az osztályon
	 * definiált mainMenu() metódust is. Leelenõrzi, hogy a játéks során elért pontszámok tárolására szolgáló dinamikus tömbök léteznek-e. Amennyiben nem akkor ezen
	 * objektumokat létrehozza.
	 */
	public Menu()
	{
		this.menuFrame = new Frame();
		this.timer = new Timer(75, this);
		this.timer.start();
		this.mainMenu();
		this.isRunnig = false;
		
		if(this.onePlayerScores == null)
		{
			this.onePlayerScores = new ArrayList<Player>();
		}
		if(twoPlayerScores == null)
		{
			this.twoPlayerScores = new ArrayList<Player>();
		}	
	}
	
	/*
	 * A metódus leelenõrzi, hogy az egyjátékos üzemmód pontszámait taralmazó .ser kiterjesztésû fájl létezik. 
	 * Ha létezik, akkor a bitfájlból betölti a már létezõ Player objektumokat a onePlayerScores dinamikus tömbbe.
	 * Ellenkezõ esetben a függvény visszatér.
	 */
	public void LoadPlayerDataOne() throws IOException, ClassNotFoundException
	{
		Path path1 = FileSystems.getDefault().getPath("onePlayer.ser");
		File file = new File(path1.toString());
		
		if (file.exists())
		{
			FileInputStream fileIn = new FileInputStream(path1.toString());
			ObjectInputStream in = new ObjectInputStream(fileIn);
			onePlayerScores = (ArrayList<Player>) in.readObject();
			in.close();
			fileIn.close();
		}
		else{return;}
	}
	/*
	 * A metódus leelenõrzi, hogy a kétjátékos üzemmód pontszámait taralmazó .ser kiterjesztésû fájl létezik. 
	 * Ha létezik, akkor a bitfájlból betölti a már létezõ Player objektumokat a twoPlayerScores dinamikus tömbbe.
	 * Ellenkezõ esetben a függvény visszatér.
	 */
	public void LoadPlayerDataTwo() throws IOException, ClassNotFoundException
	{
		Path path2 = FileSystems.getDefault().getPath("twoPlayer.ser");
		File file = new File(path2.toString());
		
		if(file.exists())
		{
			FileInputStream fileIn = new FileInputStream(path2.toString());
			ObjectInputStream in = new ObjectInputStream(fileIn);
			twoPlayerScores = (ArrayList<Player>) in.readObject();
			in.close();
			fileIn.close();
		}
		else
		{
			return;
		}
	}
	
	/*
	 * A metódus meghívásának hatására a játékfelületen illetve a fejlécen található objektumokat törli.
	 */
	public void clearFrame(Frame actFrame)
	{
		actFrame.getContentPane().removeAll();
		actFrame.repaint();
		actFrame.header.removeAll();
	}
	
	/*
	 * A függvény meghívásának hatására kirajzolódnak a játékképernyõre a "Fõmenü" elemei. 
	 */
	public void mainMenu()
	{
		this.clearFrame(menuFrame);
		
		startMain = new MyButton(200, 150, "Start", this);
		scoreMain = new MyButton(200, 250, "Score", this);
		helpMain = new MyButton(200, 350, "Help", this);
		exitMain = new MyButton(200, 450, "Exit", this);
		MyLabel headerText = new MyLabel("Fõmenü", 240, 0);
		
		menuFrame.add(startMain);
		menuFrame.add(scoreMain);
		menuFrame.add(helpMain);
		menuFrame.add(exitMain);
		menuFrame.header.add(headerText);
		
		SwingUtilities.updateComponentTreeUI(menuFrame);
				
	}
	
	/*
	 * A függvény meghívásának hatására kirajzolódnak a játékképernyõre a "Játékmód választás" menüpont elemei. 
	 */
	public void selectMode()
	{
		this.clearFrame(menuFrame);
		
		oneSelect = new MyButton(200, 150, "One player", this);
		twoSelect = new MyButton(200, 250, "Two player", this);
		backSelect = new MyButton(200, 350, "Back", this);
		MyLabel headerText = new MyLabel("Játékmód", 220, 0);
		
		menuFrame.add(oneSelect);
		menuFrame.add(twoSelect);
		menuFrame.add(backSelect);
		menuFrame.header.add(headerText);
	}
	
	/*
	 * A függvény meghívásának hatására kirajzolódnak a játékképernyõre a névválasztás menüpont elemei egyjátékos üzemmód esetében. 
	 */
	public void selectName1()
	{
		this.clearFrame(menuFrame);
		
		playerName1 = new MyTextField(175, 275, "Player1");
		backName1 = new MyButton(300, 500, "Back", this);
		playName1 = new MyButton(100, 500, "Play", this);
		MyLabel headerText = new MyLabel("Név választás", 190, 0);
		
		menuFrame.add(playerName1);
		menuFrame.add(backName1);
		menuFrame.add(playName1);
		menuFrame.header.add(headerText);
	}
	
	/*
	 * A függvény meghívásának hatására kirajzolódnak a játékképernyõre a névválasztás menüpont elemei kétjátékos üzemmód esetében. 
	 */
	public void selectName2()
	{
		this.clearFrame(menuFrame);
		
		playerName1 = new MyTextField(175, 275, "Player1");
		playerName2 = new MyTextField(175, 350, "Player2");
		backName2 = new MyButton(300, 500, "Back", this);
		playName2 = new MyButton(100, 500, "Play", this);
		MyLabel headerText = new MyLabel("Név választás", 190, 0);
		
		menuFrame.add(playerName1);
		menuFrame.add(playerName2);
		menuFrame.add(backName2);
		menuFrame.add(playName2);
		menuFrame.header.add(headerText);
	}
	
	/*
	 * A checkName1 metódus felel az egyjátékos névválasztás során a szövegdobozba írt szöveg formátumának helyességérõl.
	 */
	public void checkName1()
	{
		if(playerName1.getText().length() > 12 || playerName1.getText().length() == 0 || playerName1.getText().contains(" "))
		{			
			playName1.setEnabled(false);
		}
		else if (playerName1.getText().length() <= 12)
		{
			playName1.setEnabled(true);
		}
	}
	
	/*
	 * A checkName2 metódus felel a kétjátékos névválasztás során a szövegdobozba írt szöveg formátumának helyességérõl.
	 */
	public void checkName2()
	{
		if(playerName1.getText().length() > 12 || playerName1.getText().length() == 0 || playerName1.getText().contains(" ")
				|| playerName2.getText().length() > 12 || playerName2.getText().length() == 0 || playerName2.getText().contains(" "))
		{			
			playName2.setEnabled(false);
		}
		else if (playerName1.getText().length() <= 12)
		{
			playName2.setEnabled(true);
		}
	}
	
	/*
	 * A metódus példányosítja az egyjátékos üzemmód lejátszásához szükséges objektumokat.
	 * Elõször törli a képernyõn található menüelemeket, majd példányosítja a Grid osztályt. 
	 * Ezután példányosítja a Snake1, Apple1 és Apple2 osztályokat, majd a példányosított osztályokra meghívja
	 * a Grid addSnake és addApple osztályait, amelynek hatására elindul a játékot vezérlõ esemény hurok elindulása.
	 * Végezetül a program a fókuszt átadja a Grid osztály játékfelületeként használt menuFrame-nek, hogy a program kikerüljön a
	 * menüfelületen lévõ gombok mûködtetéséhez szükséges eseményhurokból. 
	 */
	public void onePlayerMode()
	{
		this.clearFrame(menuFrame);
		this.grid = new Grid(menuFrame, 25);
		
		Snake1 snake1 = new Snake1(menuFrame, 25);
		Apple1 apple1 = new Apple1(11,8, menuFrame, 25);
		Apple2 apple2 = new Apple2(11,8, menuFrame, 25);
	
		grid.addSnake(snake1);
		grid.addApple(apple1);
		grid.addApple(apple2);
	
		grid.addPlayer(playerName1.getText());
		this.clearFrame(menuFrame);
		menuFrame.toFront();
		menuFrame.requestFocus();
	}
	
	/*
	 * A metódus példányosítja a kétjátékos üzemmód lejátszásához szükséges objektumokat.
	 * Elõször törli a képernyõn található menüelemeket, majd példányosítja a Grid osztályt. 
	 * Ezután példányosítja a Snake1, Snake2, Apple1 és Apple2 osztályokat, majd a példányosított osztályokra meghívja
	 * a Grid addSnake és addApple osztályait, amelynek hatására elindul a játékot vezérlõ esemény hurok elindulása.
	 * Végezetül a program a fókuszt átadja a Grid osztály játékfelületeként használt menuFrame-nek, hogy a program kikerüljön a
	 * menüfelületen lévõ gombok mûködtetéséhez szükséges eseményhurokból. 
	 */
	public void twoPlayerMode()
	{
		this.clearFrame(menuFrame);
		this.grid = new Grid(menuFrame, 25);
		
		Snake1 snake1 = new Snake1(menuFrame, 25);
		Snake2 snake2 = new Snake2(menuFrame, 25);
		Apple1 apple1 = new Apple1(11,8, menuFrame, 25);
		Apple2 apple2 = new Apple2(11,8, menuFrame, 25);
		
		grid.addPlayer(playerName1.getText());
		grid.addPlayer(playerName2.getText());
		
		grid.addSnake(snake1);
		grid.addSnake(snake2);
		grid.addApple(apple1);
		grid.addApple(apple2);
		
		menuFrame.toFront();
		menuFrame.requestFocus();
	}
	
	/*
	 *A metódus szerializálja a onePlayerScores dinamikus tömbben tárolt Player objektumokat. 
	 */
	public void savePointsOne() throws IOException
	{
		FileOutputStream fileOut = new FileOutputStream("onePlayer.ser");
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(onePlayerScores);
		out.close();
		fileOut.close();
	}
	

	/*
	 *A metódus szerializálja a twoPlayerScores dinamikus tömbben tárolt Player objektumokat. 
	 */
	public void savePointsTwo() throws IOException
	{
		FileOutputStream fileOut = new FileOutputStream("twoPlayer.ser");
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(twoPlayerScores);
		out.close();
		fileOut.close();
	}
	
	/*
	 * A metódus leellenõrzi, hogy a paraméterként megadott, Player objektumokat tároló score dinamikus tömbben már szerepel-e 
	 * a szintén paraméterként megadott, Player típusú newScore objektum attribútumaival megegyezõ Player objektum. Amennyiben igen,
	 * akkor a newScore nem kerül hozzáadásra a score dinamikus tömbhöz. Ellenkezõ esetben igen.
	 */
	public boolean isIncluded(List <Player> scores, Player newScore)
	{		
		for(int i = 0; i < scores.size(); i++)
		{
			Player actPlayer = onePlayerScores.get(i);
			if (actPlayer.name.equals(newScore.name) && actPlayer.point == (newScore.point))
			{
				return true;
			}
		}
		return false;
	}
	
	/*
	 * A metódus meghívásának hatására a "Pontszámok" menüpont elemei kerülnek kirajzolásra a játékfelületre.
	 */
	public void scoreBoard()
	{
		this.clearFrame(menuFrame);
		
		MyTable onePlayerTabel = new MyTable(onePlayerScores, menuFrame, 100, 100);
		MyTable twoPlayerTabel = new MyTable(twoPlayerScores, menuFrame, 100, 350);
		
		MyLabel headerText = new MyLabel("Pontszámok", 220, 0);
		MyLabel onePlayerLab = new MyLabel("Egy játékos mód:", 160, 50);
		MyLabel twoPlayerLab = new MyLabel("Két játékos mód:", 160, 300);
		
		backScore = new MyButton(200, 530, "Back", this);
		menuFrame.add(backScore);
		menuFrame.header.add(headerText);
		menuFrame.getContentPane().add(onePlayerLab);
		menuFrame.getContentPane().add(twoPlayerLab);
	}
	
	/*
	 * A metódus meghívásának hatására a "Help" menüpont elemei kerülnek kirajzolásra a játékfelületre.
	 */
	public void help() throws IOException
	{
		this.clearFrame(menuFrame);
		
		BufferedImage myPicture = ImageIO.read(new File("helpImage.png"));
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		picLabel.setBounds(0,0,600,600);
		MyLabel headerText = new MyLabel("Útmutató", 220, 0);
		backHelp = new MyButton(200, 535, "Back", this);
		
		menuFrame.header.add(headerText);
		menuFrame.getContentPane().add(picLabel);
		menuFrame.add(backHelp);
	}

	/*
	 * A menüeseményket vezérlõ eseményhurok.
	 * 1. 	Az eseményhurok elsõként leelenõrzi, hogy az eseményhurok legalább egyszer már lfeutott már. Amennyiben nem, akkor
	 * 		meghívja a LoadPlayerDataOne és LoadPlayerDataTwo metódusokat, majd az isRunning változót hamisra állítja át.
	 * 2.	A felhasználó ezután szabadon traverzálhat a különbözõ menüpontok között. 
	 * 		Fõmenü:
	 * 			--> Start:
	 * 				--> Egy játékos üzemmód:
	 * 					Játékos nevének megadása:
	 * 						--> Start
	 * 							(A játék befejeztével):
	 * 							--> Új játék (onePlayerScores dinamikus tömb frissítése az új pontszámokkal)
	 * 							--> Fõmenü (onePlayerScores dinamikus tömb frissítése az új pontszámokkal)
	 * 						--> Vissza		
	 * 				--> Két játékos üzemmód
	 * 					Játékosok neveinek megadása:
	 * 						--> Start
	 * 							(A játék befejeztével)
	 * 							--> Új játék (twoPlayerScores dinamikus tömb frissítése az új pontszámokkal)
	 * 							--> Fõmenü (twoPlayerScores dinamikus tömb frissítése az új pontszámokkal)
	 * 						--> Vissza
	 * 				--> Vissza
	 * 			--> Scores:
	 * 				--> Vissza
	 * 			--> Help:
	 * 				--> Vissza
	 * 			--> Exit: (A onePlayerScore és twoPlayerScore tömbök szerializációja, a játékablakok bezárása, majd a használt memóriaterület felszabadítása)
	 * 
	 * 3. A játékprogram befejeztével a program szerializálja az elért pontszámokat. 
	 */
	@Override
	public void actionPerformed(ActionEvent e) 
	{		
		if (!this.isRunnig)
		{
			
			try {this.LoadPlayerDataOne();} 
			catch (ClassNotFoundException | IOException e1) {e1.printStackTrace();}
			
			try {this.LoadPlayerDataTwo();} 
			catch (ClassNotFoundException | IOException e1) {e1.printStackTrace();}	
			
			this.isRunnig = true;
		}
		
		try{this.checkName1();}
		catch(Exception error){;}
		
		try{this.checkName2();}
		catch(Exception error){;}
		
		try
		{
			if (this.grid.newGame == 1)
			{
				if(grid.playerNames.size() == 1)
				{
					Player player1 = new Player(grid.playerNames.get(0), grid.points.get(0));
					
					if(!this.isIncluded(onePlayerScores, player1))
					{
						onePlayerScores.add(player1);
					}
				}
				else if(grid.playerNames.size() == 2)
				{
					Player player1 = new Player(grid.playerNames.get(0), grid.points.get(0));
					Player player2 = new Player(grid.playerNames.get(1), grid.points.get(1));
					
					if(!this.isIncluded(onePlayerScores, player1))
					{
						twoPlayerScores.add(player1);
					}
					if(!this.isIncluded(onePlayerScores, player2))
					{
						twoPlayerScores.add(player2);
					}
				}
				
				this.mainMenu();
				this.grid = null;
				menuFrame.toFront();
				menuFrame.requestFocus();
			}
		}
		catch(Exception error){;}
		
		try
		{
			if (this.grid.newGame == 2)
			{
				Player player = new Player(grid.playerNames.get(0), grid.points.get(0));
				if(!this.isIncluded(onePlayerScores, player))
				{
					onePlayerScores.add(player);
				}
				this.onePlayerMode();
			}
		}
		catch(Exception error){;}
		
		try
		{
			if (this.grid.newGame == 3)
			{
				Player player1 = new Player(grid.playerNames.get(0), grid.points.get(0));
				Player player2 = new Player(grid.playerNames.get(1), grid.points.get(1));
				if(!this.isIncluded(onePlayerScores, player1)){twoPlayerScores.add(player1);}
				if(!this.isIncluded(onePlayerScores, player2)){twoPlayerScores.add(player2);}
				this.twoPlayerMode();
			}
		}
		catch(Exception error){;}
		
		try{this.checkName2();}
		catch(Exception error){;}
		
		if(e.getSource()==startMain)
		{
			this.selectMode();
		}
		
		if(e.getSource()==backSelect)
		{
			this.mainMenu();
		}
		
		if(e.getSource()==backHelp)
		{
			this.mainMenu();
		}
		
		if(e.getSource()==backScore)
		{
			this.mainMenu();
		}
		
		if(e.getSource()==backName1)
		{
			this.selectMode();
		}
		
		if(e.getSource()==scoreMain)
		{
			this.scoreBoard();
		}
		
		if(e.getSource()==helpMain)
		{
			try {this.help();}
			catch (IOException e1) {;}
		}
		
		if(e.getSource()==backName2)
		{
			this.selectMode();
		}
		
		if(e.getSource()==oneSelect){this.selectName1();}
		
		if(e.getSource()==playName1)
		{	
		
			this.onePlayerMode();
		}
		
		if(e.getSource()==playName2)
		{	
			this.twoPlayerMode();
		}
		
		if(e.getSource()==twoSelect)
		{
			this.selectName2();
		}
		
		if(e.getSource()==backSelect)
		{
			this.mainMenu();
		}
		
		if(e.getSource()==exitMain)
			
		{
			try{this.savePointsOne();} catch (IOException e1) {e1.printStackTrace();}
			try {this.savePointsTwo();} catch (IOException e1) {e1.printStackTrace();}
			menuFrame.dispose();
			timer.stop();
			System.exit(0);
		}
	}
}
