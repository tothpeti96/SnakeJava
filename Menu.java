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
	 * A Menu oszt�ly �ltal defini�lt attrib�tumok.
	 * A f�bb attrib�tumok a k�vetkez�ek:
	 * 
	 * 		menuFrame: statikus adattag. A Frame oszt�ly egy p�ld�nya, amelyre a k�l�nb�z� men�elemek ker�lnek ki�r�sra.
	 * 
	 * 		onePlayerScores: Player t�pus� objektumoktat tartalmaz� dinamikus t�mb. Az egyj�t�kos m�d sor�n el�rt eredm�nyek ker�lnek ide elment�sre.
	 * 
	 * 		twoPlayerSocres: Player t�pus� objektumoktat tartalmaz� dinamikus t�mb. A k�tj�t�kos m�d sor�n el�rt eredm�nyek ker�lnek ide elment�sre.
	 * 
	 * 		timer: Timer t�pus� objektum, ami a men�fel�let ciklikus vez�rl�se miatt fontos.
	 * 
	 * 		grid: Grid t�pus� objektum. A j�t�k elind�t�sakkor ker�l p�ld�nyos�t�sra. 
	 * 
	 * 		isRunning: boolean t�pus� objektum, amellyel ellen�rizhetj�k, hogy a men�t vez�rl� esem�nyhurok legal�bb egyszer m�r lefutott.
	 * 
	 * 		MyButton t�pus� objektumok: A JButton t�pus� objektumok lesz�rmazottja. Mivel a men�fel�leten hasonl� t�pus� funkci�j� gombok szerepelnek majd, �gy a 
	 * 			MyButton oszt�ly a JButton oszt�ly generaliz�ci�j�nak foghat� fel.
	 * 
	 * 		MyTextField t�pus� objektumok: A JTextField t�pus� objektumok lesz�rmazottja. Mivel a men�fel�leten hasonl� t�pus� funkci�j� sz�vegdobozok szerepelnek majd, �gy a 
	 * 			MyTextField oszt�ly a JTextField oszt�ly generaliz�ci�j�nak foghat� fel.
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
	 * A Menu oszt�ly konstruktora. Inicializ�lja a menuFrame, a timer �s az isRunning v�ltoz�kat. Ezenfel�l elind�tja az id�z�t�t, illetve megh�vja az oszt�lyon
	 * defini�lt mainMenu() met�dust is. Leelen�rzi, hogy a j�t�ks sor�n el�rt pontsz�mok t�rol�s�ra szolg�l� dinamikus t�mb�k l�teznek-e. Amennyiben nem akkor ezen
	 * objektumokat l�trehozza.
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
	 * A met�dus leelen�rzi, hogy az egyj�t�kos �zemm�d pontsz�mait taralmaz� .ser kiterjeszt�s� f�jl l�tezik. 
	 * Ha l�tezik, akkor a bitf�jlb�l bet�lti a m�r l�tez� Player objektumokat a onePlayerScores dinamikus t�mbbe.
	 * Ellenkez� esetben a f�ggv�ny visszat�r.
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
	 * A met�dus leelen�rzi, hogy a k�tj�t�kos �zemm�d pontsz�mait taralmaz� .ser kiterjeszt�s� f�jl l�tezik. 
	 * Ha l�tezik, akkor a bitf�jlb�l bet�lti a m�r l�tez� Player objektumokat a twoPlayerScores dinamikus t�mbbe.
	 * Ellenkez� esetben a f�ggv�ny visszat�r.
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
	 * A met�dus megh�v�s�nak hat�s�ra a j�t�kfel�leten illetve a fejl�cen tal�lhat� objektumokat t�rli.
	 */
	public void clearFrame(Frame actFrame)
	{
		actFrame.getContentPane().removeAll();
		actFrame.repaint();
		actFrame.header.removeAll();
	}
	
	/*
	 * A f�ggv�ny megh�v�s�nak hat�s�ra kirajzol�dnak a j�t�kk�perny�re a "F�men�" elemei. 
	 */
	public void mainMenu()
	{
		this.clearFrame(menuFrame);
		
		startMain = new MyButton(200, 150, "Start", this);
		scoreMain = new MyButton(200, 250, "Score", this);
		helpMain = new MyButton(200, 350, "Help", this);
		exitMain = new MyButton(200, 450, "Exit", this);
		MyLabel headerText = new MyLabel("F�men�", 240, 0);
		
		menuFrame.add(startMain);
		menuFrame.add(scoreMain);
		menuFrame.add(helpMain);
		menuFrame.add(exitMain);
		menuFrame.header.add(headerText);
		
		SwingUtilities.updateComponentTreeUI(menuFrame);
				
	}
	
	/*
	 * A f�ggv�ny megh�v�s�nak hat�s�ra kirajzol�dnak a j�t�kk�perny�re a "J�t�km�d v�laszt�s" men�pont elemei. 
	 */
	public void selectMode()
	{
		this.clearFrame(menuFrame);
		
		oneSelect = new MyButton(200, 150, "One player", this);
		twoSelect = new MyButton(200, 250, "Two player", this);
		backSelect = new MyButton(200, 350, "Back", this);
		MyLabel headerText = new MyLabel("J�t�km�d", 220, 0);
		
		menuFrame.add(oneSelect);
		menuFrame.add(twoSelect);
		menuFrame.add(backSelect);
		menuFrame.header.add(headerText);
	}
	
	/*
	 * A f�ggv�ny megh�v�s�nak hat�s�ra kirajzol�dnak a j�t�kk�perny�re a n�vv�laszt�s men�pont elemei egyj�t�kos �zemm�d eset�ben. 
	 */
	public void selectName1()
	{
		this.clearFrame(menuFrame);
		
		playerName1 = new MyTextField(175, 275, "Player1");
		backName1 = new MyButton(300, 500, "Back", this);
		playName1 = new MyButton(100, 500, "Play", this);
		MyLabel headerText = new MyLabel("N�v v�laszt�s", 190, 0);
		
		menuFrame.add(playerName1);
		menuFrame.add(backName1);
		menuFrame.add(playName1);
		menuFrame.header.add(headerText);
	}
	
	/*
	 * A f�ggv�ny megh�v�s�nak hat�s�ra kirajzol�dnak a j�t�kk�perny�re a n�vv�laszt�s men�pont elemei k�tj�t�kos �zemm�d eset�ben. 
	 */
	public void selectName2()
	{
		this.clearFrame(menuFrame);
		
		playerName1 = new MyTextField(175, 275, "Player1");
		playerName2 = new MyTextField(175, 350, "Player2");
		backName2 = new MyButton(300, 500, "Back", this);
		playName2 = new MyButton(100, 500, "Play", this);
		MyLabel headerText = new MyLabel("N�v v�laszt�s", 190, 0);
		
		menuFrame.add(playerName1);
		menuFrame.add(playerName2);
		menuFrame.add(backName2);
		menuFrame.add(playName2);
		menuFrame.header.add(headerText);
	}
	
	/*
	 * A checkName1 met�dus felel az egyj�t�kos n�vv�laszt�s sor�n a sz�vegdobozba �rt sz�veg form�tum�nak helyess�g�r�l.
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
	 * A checkName2 met�dus felel a k�tj�t�kos n�vv�laszt�s sor�n a sz�vegdobozba �rt sz�veg form�tum�nak helyess�g�r�l.
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
	 * A met�dus p�ld�nyos�tja az egyj�t�kos �zemm�d lej�tsz�s�hoz sz�ks�ges objektumokat.
	 * El�sz�r t�rli a k�perny�n tal�lhat� men�elemeket, majd p�ld�nyos�tja a Grid oszt�lyt. 
	 * Ezut�n p�ld�nyos�tja a Snake1, Apple1 �s Apple2 oszt�lyokat, majd a p�ld�nyos�tott oszt�lyokra megh�vja
	 * a Grid addSnake �s addApple oszt�lyait, amelynek hat�s�ra elindul a j�t�kot vez�rl� esem�ny hurok elindul�sa.
	 * V�gezet�l a program a f�kuszt �tadja a Grid oszt�ly j�t�kfel�letek�nt haszn�lt menuFrame-nek, hogy a program kiker�lj�n a
	 * men�fel�leten l�v� gombok m�k�dtet�s�hez sz�ks�ges esem�nyhurokb�l. 
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
	 * A met�dus p�ld�nyos�tja a k�tj�t�kos �zemm�d lej�tsz�s�hoz sz�ks�ges objektumokat.
	 * El�sz�r t�rli a k�perny�n tal�lhat� men�elemeket, majd p�ld�nyos�tja a Grid oszt�lyt. 
	 * Ezut�n p�ld�nyos�tja a Snake1, Snake2, Apple1 �s Apple2 oszt�lyokat, majd a p�ld�nyos�tott oszt�lyokra megh�vja
	 * a Grid addSnake �s addApple oszt�lyait, amelynek hat�s�ra elindul a j�t�kot vez�rl� esem�ny hurok elindul�sa.
	 * V�gezet�l a program a f�kuszt �tadja a Grid oszt�ly j�t�kfel�letek�nt haszn�lt menuFrame-nek, hogy a program kiker�lj�n a
	 * men�fel�leten l�v� gombok m�k�dtet�s�hez sz�ks�ges esem�nyhurokb�l. 
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
	 *A met�dus szerializ�lja a onePlayerScores dinamikus t�mbben t�rolt Player objektumokat. 
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
	 *A met�dus szerializ�lja a twoPlayerScores dinamikus t�mbben t�rolt Player objektumokat. 
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
	 * A met�dus leellen�rzi, hogy a param�terk�nt megadott, Player objektumokat t�rol� score dinamikus t�mbben m�r szerepel-e 
	 * a szint�n param�terk�nt megadott, Player t�pus� newScore objektum attrib�tumaival megegyez� Player objektum. Amennyiben igen,
	 * akkor a newScore nem ker�l hozz�ad�sra a score dinamikus t�mbh�z. Ellenkez� esetben igen.
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
	 * A met�dus megh�v�s�nak hat�s�ra a "Pontsz�mok" men�pont elemei ker�lnek kirajzol�sra a j�t�kfel�letre.
	 */
	public void scoreBoard()
	{
		this.clearFrame(menuFrame);
		
		MyTable onePlayerTabel = new MyTable(onePlayerScores, menuFrame, 100, 100);
		MyTable twoPlayerTabel = new MyTable(twoPlayerScores, menuFrame, 100, 350);
		
		MyLabel headerText = new MyLabel("Pontsz�mok", 220, 0);
		MyLabel onePlayerLab = new MyLabel("Egy j�t�kos m�d:", 160, 50);
		MyLabel twoPlayerLab = new MyLabel("K�t j�t�kos m�d:", 160, 300);
		
		backScore = new MyButton(200, 530, "Back", this);
		menuFrame.add(backScore);
		menuFrame.header.add(headerText);
		menuFrame.getContentPane().add(onePlayerLab);
		menuFrame.getContentPane().add(twoPlayerLab);
	}
	
	/*
	 * A met�dus megh�v�s�nak hat�s�ra a "Help" men�pont elemei ker�lnek kirajzol�sra a j�t�kfel�letre.
	 */
	public void help() throws IOException
	{
		this.clearFrame(menuFrame);
		
		BufferedImage myPicture = ImageIO.read(new File("helpImage.png"));
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		picLabel.setBounds(0,0,600,600);
		MyLabel headerText = new MyLabel("�tmutat�", 220, 0);
		backHelp = new MyButton(200, 535, "Back", this);
		
		menuFrame.header.add(headerText);
		menuFrame.getContentPane().add(picLabel);
		menuFrame.add(backHelp);
	}

	/*
	 * A men�esem�nyket vez�rl� esem�nyhurok.
	 * 1. 	Az esem�nyhurok els�k�nt leelen�rzi, hogy az esem�nyhurok legal�bb egyszer m�r lfeutott m�r. Amennyiben nem, akkor
	 * 		megh�vja a LoadPlayerDataOne �s LoadPlayerDataTwo met�dusokat, majd az isRunning v�ltoz�t hamisra �ll�tja �t.
	 * 2.	A felhaszn�l� ezut�n szabadon traverz�lhat a k�l�nb�z� men�pontok k�z�tt. 
	 * 		F�men�:
	 * 			--> Start:
	 * 				--> Egy j�t�kos �zemm�d:
	 * 					J�t�kos nev�nek megad�sa:
	 * 						--> Start
	 * 							(A j�t�k befejezt�vel):
	 * 							--> �j j�t�k (onePlayerScores dinamikus t�mb friss�t�se az �j pontsz�mokkal)
	 * 							--> F�men� (onePlayerScores dinamikus t�mb friss�t�se az �j pontsz�mokkal)
	 * 						--> Vissza		
	 * 				--> K�t j�t�kos �zemm�d
	 * 					J�t�kosok neveinek megad�sa:
	 * 						--> Start
	 * 							(A j�t�k befejezt�vel)
	 * 							--> �j j�t�k (twoPlayerScores dinamikus t�mb friss�t�se az �j pontsz�mokkal)
	 * 							--> F�men� (twoPlayerScores dinamikus t�mb friss�t�se az �j pontsz�mokkal)
	 * 						--> Vissza
	 * 				--> Vissza
	 * 			--> Scores:
	 * 				--> Vissza
	 * 			--> Help:
	 * 				--> Vissza
	 * 			--> Exit: (A onePlayerScore �s twoPlayerScore t�mb�k szerializ�ci�ja, a j�t�kablakok bez�r�sa, majd a haszn�lt mem�riater�let felszabad�t�sa)
	 * 
	 * 3. A j�t�kprogram befejezt�vel a program szerializ�lja az el�rt pontsz�mokat. 
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
