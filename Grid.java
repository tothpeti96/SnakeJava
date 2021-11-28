package snake;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.Timer;

public class Grid implements ActionListener
{	

	/*
	 * A Grid osztályhoz tartozó attribútumok, amelyek közvetlen szükségesek a játék mûködéséhez.
	 * 
	 * 		cellNum: A Grid-hez tartozó pályadimenzió. A pályán található cellák száma megkapható ha a cellNum négyzetét vesszük.
	 * 
	 * 		cellSize: A Grid-hez tartozó egyes cellák dimenziója. Az egyes cellák területét megkapjuk, ha a cellSize négyzetét vesszük
	 * .
	 * 		gameOver: boolean változó. Hamis értékkel inicializálja a konstruktor. Igaz értékre változik ha már egyik kígyó sincsen játékban
	 * 			Igaz érték esetén a játék megáll.
	 * 
	 * 		actApple: Az éppen használatban lévõ alma referenciáját tartalmazó változó.
	 * 
	 * 		timer: A játékciklushoz tartozó idõzítõ, amelynek ütemének (DELAY) állításával a játék gyorsasága állítható.
	 * 
	 * 		BackButton: A játék vége során látható játékösszesítõ felület "Back" gombja. Megnyomásával a játékos a fõmenüben találja magát.
	 * 
	 * 		PlayButton: A játék vége során látható játékösszesítõ felület "Play Again" gombja. A megnyomásával a játékos új játékot kezdhet 
	 * 			az eredetileg megadott felhasználónevekkel
	 * 
	 * 		newGame: Állapotjelzõ. Új játék esetén ez a változó jelzi a Menu osztálynak, hogy egy- két játékos üzemmódodt indítson, vagy lépjen ki a fõmenübe.
	 * 			A "PlayButton" és "BackButton" a newGame állapotára vannak hatással. 
	 * 				0: Alapállapot
	 * 				1: Vissza a fõmenübe
	 * 				2: Új játék egyjátékos üzemmódban
	 * 				3: Két játék egyjátékos üzemmódban
	 * 
	 * 		gameFrame: A "Frame" osztálynak egy példénya. A játék során a grafika erre a felületre fog kirajzolódni. Statikus változó, ellenkezõ esetben 
	 * 			a különbözõ grafikák megjelenítése a Frame osztály külön-külön példányain történne meg.
	 * 		
	 * 		snakes: A játékterületen található kígyók referenciáját tartalmazó dinamikus tömb.
	 * 		
	 * 		apples: A játékterületen található alma referenciáját tartalmazó dinamikus tömb.
	 * 
	 * 		labelContainer: A játékosok éppen aktuális pontszámait tartalmazó MyLabel típusú objektum referenciáit tartalmazó dinamikus tömb.
	 * 		
	 * 		playerNames: Az egyes játékosok neveti tartalmazó dinamikus tömb.
	 * 		
	 * 		points: Az egyes játékosok pontszámait tartalmazó dinamikus tömb. 
	 * 			 
	 */
	
	private int cellNum;
	protected int cellSize;
	private boolean gameOver;
	protected Apple actApple;
	private Timer timer;
	private MyButton BackToMenu;
	private MyButton PlayAgain;
	protected int newGame;
	protected static Frame gameFrame;
	
	protected ArrayList<Snake> snakes = new ArrayList<Snake>();
	protected ArrayList<Apple> apples = new ArrayList<Apple>();
	protected ArrayList<MyLabel> labelContainer = new ArrayList<MyLabel>();
	protected ArrayList<String> playerNames = new ArrayList<String>();
	protected ArrayList<Integer> points = new ArrayList<Integer>();
	
	/*
	 * A Grid osztály konstruktora. Paramétere a Frame osztály egy példánya amire a különbözõ játékelemek kerülnek majd kirajzolásra illetve az actCellNum,
	 * amivel a játékfelület dimenziója állítható. 
	 * 
	 *  A konstruktor a fenti értékek beállítása mellett inicializálja a newGame, a gameOver, az actApple és cellSize attribútumokat is.
	 */
	
	public Grid(Frame actFrame, int actCellNum)
	{
		this.gameOver = false;
		this.actApple = null;
		this.newGame = 0;
		this.gameFrame = actFrame;
		this.cellNum = actCellNum;
		this.cellSize = (int)(600/cellNum);
	}
	
	/*
	 * A metódus paramétere a String típusú "name" változó. A metódus meghívásának hatására a playerNames dinamikus tömbhöz a 
	 * "name" paraméter hozzáadásra kerül.
	 */
	protected void addPlayer(String name){playerNames.add(name);}
	
	
	/*
	 * A metódus meghívásának hatására a játéktér fejlécére kiírásra kerül a játékos neve és aktuális pontszáma.
	 * Az adott MyLabel típusú objektumnak a referenciája hozzáadásra kerül a labelContainer dinamikus tömbhöz. 
	 * 
	 * Az új pontszám kiírása elõtt azonban az elõzõ pontszámot el kell tünteni a fejlécrõl. Így a metódus legelõször végigiterál a labelContainer 
	 * dinamukus tömbön, és törli az elõzõ pontszámokhoz tartozó MyLabel típusó objektumokat. 
	 */
	protected void drawPoints()
	{
		if (labelContainer.size() != 0)
		{
			for(int i = 0; i < labelContainer.size(); i++)
			{
				JLabel actLabel = labelContainer.get(i);
				gameFrame.header.remove(actLabel);
			}
		}
		
		if (playerNames.size() == 1)
		{
			MyLabel strPoints = new MyLabel(playerNames.get(0) + ": " + String.valueOf(snakes.get(0).point), 0, 0);
			labelContainer.add(strPoints);
			gameFrame.header.add(strPoints);
		}
		
		if (playerNames.size() == 2)
		{
			
			MyLabel strPoints1 = new MyLabel(playerNames.get(0) + ": " + String.valueOf(snakes.get(0).point), 0, 0);
			labelContainer.add(strPoints1);
			gameFrame.header.add(strPoints1);
			
			MyLabel strPoints2 = new MyLabel(playerNames.get(1) + ": " + String.valueOf(snakes.get(1).point), 300, 0);
			labelContainer.add(strPoints2);
			gameFrame.header.add(strPoints2);
		}
	}
	
	/*
	 * A metódus paramétere a Snake típusú actSnake objektum.
	 * A metódus meghívásának hatására a snakes dinamikus tömbhöz hozzáadásra kerül az éppen hozzáadni kívánt Snake objektum referenciája. 
	 */
	protected void addSnake(Snake actSnake)
	{
		snakes.add(actSnake);
		actSnake.setGrid(this);
	}
	
	/*
	 * A metódus paramétere az Apple típusú actApple objektum.
	 * A metódus meghívásának hatására az apples dinamikus tömbhöz hozzáadásra kerül az éppen hozzáadni kívánt Apple objektum referenciája.
	 * 
	 *  Mivel egy- és két játékos üzemmódban is mind a két típusó Apple objektumot használjuk véletlenszerûen (Apple1 és Apple2 objektumok),
	 *  így a játék idõzítõje és így a játék is csak a második Apple objektum hozzáadása után indul el, amelyrõl így szintén az "addApple" metódusnak kell
	 *  gondoskodnia.
	 */
	protected void addApple(Apple actApple)
	{
		apples.add(actApple);
		actApple.setGrid(this);
		
		if(apples.size() == 2)
		{
		this.timer = new Timer(75, this);
		this.timer.start();
		}
	}
	
	/*
	 * A játék során az aodtt kígyó a mozgásának látszatát úgy érjük el, hogy a haladási iránynak megfelelõen egy egységet beszúrunk a kígyó 
	 * testelemeinek referenciáit tartalmazó dinamikus tömb elejére, míg ennek a dinamikus tömbnek az utolsó elemét töröljük. 
	 * Kígyó-alma ütközés esetén azonban a kígyó egy egységgel nõ. Ekkor az utolsó elem törlésére nem kerül sor. 
	 * 
	 * A "growSnake" metódus a kígyó növekedésérõl felel.
	 * Abban az esetben ha a metódus paramétereként szolgáló Snake típusú actSnake objektum isGrowing attribútuma az igaz értéket veszi fel,
	 * akkor a kígyó utolsó testeleme nem kerül törlésre, ellenkezõ esetben igen.
	 */
	
	private void growSnake(Snake actSnake)
	{
		if (actSnake.isGrowing == false)
		{
			actSnake.snake.remove(actSnake.snake.size() - 1);
		}
		else if (actSnake.isGrowing == true)
		{
			actSnake.isGrowing = false;
		}
	}
	
	/*
	 * A metódus végigiterál a Grid-en lévõ egyes kígyók referenciáit tartalmazó "snakes" dinamikus tömbön. 
	 * Ha bármelyik kígyó fejének koordinátáai megegyezik az éppen aktuális alma koordinátáival, akkor az adott játékost 
	 * reprezentáló kígyó pontszáma eggyel nõ, illetve az éppen használatban lévõ alma "isEaten" attribútumát igazra állítja át a metódus.
	 */
	
	private void checkIfEaten()
	{
		int actAppleX = actApple.X;
		int actAppleY = actApple.Y;
		
		for (int i = 0; i < snakes.size(); i++)
		{
			Snake actSnake = snakes.get(i);
			int actHeadX = actSnake.head.X;
			int actHeadY = actSnake.head.Y;
					
			if (actAppleX == actHeadX && actAppleY == actHeadY)
			{
				actSnake.isGrowing = true;
				actApple.isEaten = true;
				actSnake.point = actSnake.point + actApple.value;
			}
		}
	}
	
	/*
	 * A metódus meghívásának hatására kiválasztásra kerül, hogy melyik típusú almát használja a játék.
	 * Két féle alma lehet a játékmezõn. Egy piros színû, ami 1 pontot ér, illetve egy lila színû ami 3 pontot ér.
	 * Annak az esélye, hogy új alma generálásakor lila almát generál a játék 25%. Piros alma generálásának az esélye 75%. 
	 */
	protected void chooseApple()
	{
		if (apples.size() == 1)
		{
			this.actApple = apples.get(0);
		}
		else if(apples.size() == 2)
		{
			Random rand = new Random();
			int luckyIndex = rand.nextInt(5);
			
			if (luckyIndex == 0)
			{
				this.actApple = apples.get(1);
			}
			else
			{
				this.actApple = apples.get(0);
			}
		}
	}
	
	/*
	 * A "generateApple" metódus felel az új alma generálásáról.
	 * 
	 * A metódus meghívásának hatására létrejön egy freeSlot nevû dinamikus tömb ami Tuple objektumok referenciáját tárolja.
	 * A metódus ezután feltölti ezt a dinamikus tömböta játékmezõn található cellaelemek sor- és oszlopindexeinek megfelelõ 
	 * Tuple objektumokkal. 
	 * 
	 * A metódus ezután végigiterál az egyes kígyók testeleminek koordinátáin és a freeSlot dinamikus tömbbõl eltávolítja az éppen 
	 * soron lévõ testelem koordinátánaik megfelelõ Tuple objektumot. Így az új alma generálásakkor csak olyan cellapozíciók lesznek figyelembe
	 * véve, amelyeken nem található kígyó. 
	 * 
	 * A metódus ezek után véletlenszerûen kiválasztja, hogy a következõ generálandó alma piros vagy lila alma legyen, majd átállítja az actApple
	 * referenciáját a választott típusú Apple objektumra (Apple1 vagy Apple2). A régi almát törli a játékfelületrõl.
	 * 
	 * A használatban lévõ actApple új X és Y koordinátáinak véletlenszerûen választ egyet a még szabadon maradt cellaelemek egyikébõl,
	 * majd kirajzolja az almát.
	 */
	protected void generateApple()
	{
		ArrayList<Tuple> freeSlot = new ArrayList<Tuple>();
		
		for (int i = 0; i < this.cellNum; i++)
		{
			for (int j = 0; j < this.cellNum; j++)
			{
				Tuple gridCell = new Tuple(i,j);
				freeSlot.add(gridCell);
			}
		}
		
		for (int i = 0; i < snakes.size(); i++)
		{
			Snake actSnake = snakes.get(i);
			ArrayList<Tuple> actSnakePos = actSnake.snake;
							
			for (int j = 0; j < actSnakePos.size(); j++)
			{
				int actBodyElementX = actSnakePos.get(j).X;
				int actBodyElementY = actSnakePos.get(j).Y;
				Tuple slotIndex = new Tuple(actBodyElementX, actBodyElementY);
				slotIndex.remove(freeSlot);
			}
		}
		
		Random rand = new Random();
		
		int openSlotIndex = rand.nextInt(freeSlot.size());
		
		int newX = freeSlot.get(openSlotIndex).X;
		int newY = freeSlot.get(openSlotIndex).Y;
		
		actApple.remove();
		this.chooseApple();
		actApple.X = newX;
		actApple.Y = newY;
		actApple.isEaten = false;
		actApple.drawApple();
	}
	
	/*
	 * A metódus leellenõrzi, hogy az egyes kígyók nem ütköztek-e bele valamelyik falba, önmagukba vagy kétjátékos üzemmód esetén valamelyik másik kígyóba.
	 * Amennyiben valamelyik ütközés bekövetkezik, akkor az éppen vizsgált kígyó "isDead" attribútumát igazra állítja át.  
	 */
	
	private void checkCollisions()
	{
		for (int i = 0; i < snakes.size(); i++)
		{
			Snake actSnake = snakes.get(i);
			Tuple actHead = actSnake.head;
			ArrayList<Tuple> actBody = actSnake.snake;
			
			// out of boundaries
			if(actHead.X > this.cellNum-1 || actHead.X < 0 || actHead.Y > this.cellNum-1 || actHead.Y < 0)
			{
				actSnake.isDead = true;
			}
			
			// self collision
			for(int j = 1; j < actBody.size(); j++)
			{
				int actX = actBody.get(j).X ;
				int actY = actBody.get(j).Y;
				
				if(actHead.X == actX && actHead.Y == actY)
				{
					actSnake.isDead = true;
				}
			}
			
			// other snake collision
			for (int j = 0; j < snakes.size(); j++)
			{
				Snake otherSnake = snakes.get(j);
				
				if (otherSnake != actSnake)
				{
					for(int k = 0; k < otherSnake.snake.size(); k++)
					{
						int otherX = otherSnake.snake.get(k).X;
						int otherY = otherSnake.snake.get(k).Y;
						
						if (actHead.X == otherX && actHead.Y == otherY)
						{
							actSnake.isDead = true;
						}
					}
				}
			}
		}
	}
	
	/*
	 * A metódus meghívásának hatására megjelenik a játék összesítõ felület, ahol kiírásra kerülnek az egyes játékosok pontszámai, 
	 * illetve a felhasználó választhat, hogy vissza szeretne lépni a fõmenübe, vagy ugyanezekkel a játékosnevekkel, ugyanebben a 
	 * játékmódban egy új játékot szeretne-e kezdeni. 
	 */
	
	private void gameOverScreen()
	{
		gameFrame.header.removeAll();
		for(int i = 0; i < snakes.size(); i++)
		{
			snakes.get(i).remove();
		}
		
		for(int i = 0; i < apples.size(); i++)
		{
			apples.get(i).remove();
		}
		
		MyLabel endHeader = new MyLabel("Vége a játéknak!", 210, 0);
		labelContainer.add(endHeader);
		gameFrame.header.add(endHeader);
		
		MyLabel endMessage = new MyLabel("Elért pontszámod:", 115, 120);
		gameFrame.getLayeredPane().add(endMessage, JLayeredPane.POPUP_LAYER);
		labelContainer.add(endMessage);
		
		BackToMenu = new MyButton(100, 500, "Menu", this);
		gameFrame.add(BackToMenu);
		
		PlayAgain = new MyButton(300, 500, "Play Again", this);
		gameFrame.add(PlayAgain);
		
		if(playerNames.size() == 1)
		{
			points.add(snakes.get(0).point);
			MyLabel p1Result = new MyLabel(playerNames.get(0).toString() + ": " + points.get(0).toString(), 115, 220);
			gameFrame.getLayeredPane().add(p1Result, JLayeredPane.POPUP_LAYER);
			labelContainer.add(p1Result);
		}
		
		if(playerNames.size() == 2)
		{
			points.add(snakes.get(0).point);
			MyLabel p1Result = new MyLabel(playerNames.get(0).toString() + ": " + points.get(0).toString(), 115, 220);
			gameFrame.getLayeredPane().add(p1Result, JLayeredPane.POPUP_LAYER);
			labelContainer.add(p1Result);
			
			points.add(snakes.get(1).point);
			MyLabel p2Result = new MyLabel(playerNames.get(1).toString() + ": " + points.get(1).toString(), 115, 270);
			gameFrame.getLayeredPane().add(p2Result, JLayeredPane.POPUP_LAYER);
			labelContainer.add(p2Result);
		}
	}
	
	
	/*
	 * A metódus leellenõrzi, hogy a játékos felületen elhelyezett kígyók még életben vannak-e.
	 * Abban az esetben ha már mind a két kígyó meghalt a metódus igaz értékre állítja át a Grid osztály "gameOver" attribútumát.
	 */
	
	protected void isGameOver()
	{
		for(int i = 0; i < snakes.size(); i++)
		{
			Snake actSnake = snakes.get(i);
			if (actSnake.isDead == false)
			{
				return;
			}
		}
		this.gameOver = true;
		this.gameOverScreen();
	}
	
	
	/*
	 * Az actionPerformed metódus az ActionListener osztály egy túlterhelt metódusa. Ez a metódus felelõs a játékciklusért.
	 * A játék egy ciklusa az alábbi módon fut le:
	 * 
	 * 		1. A metódus leelenõrzni, hogy a játék még fut-e, a Grid "gameOver" attribútuma hamis értéket vesz fel.
	 * 		2. A játékciklus ezután végigiterál a pályán található összes kígyón. Abban az esetben ha az éppen 
	 * 			vizsgált kígyó "isMoving" attribútuma hamis értéket, vagy az "isDead" értéke igaz értékez vesz fel,
	 * 			akkor a játékciklus nem tesz a továbbiakban semmit a kígyóval. Ellenkezõ esetben a kígyót a fej irányának megfelelõen elmozdítja,
	 * 			illetve ha a kígyó "isGrowing" attribútuma igaz értéket mutat, akkor a kígyó hosszát még eggyel növeli is.
	 * 		3. A játékciklus ezután ellenõrzni, hogy a pályán található egyáltalán Apple objektum. Ha nem akkor választ a Grid ostályon definiált
	 * 			"apple" dinamikus tömb elemébõl egyet, ellenkezõ esetben továbblép.
	 * 		4.  A játék leelenõrzi, hogy a pályán található almát éppen megette-e valamelyik kígyó vagy nem. Amennyiben az étek elfogyasztsáraa került,
	 * 			akkor véletlenszerûen egy új alma generálódik. 
	 * 		5. A játékciklus ellenõrzi, hogy ekövetkezet-e a játékfelületen valamilyen ütközés.
	 * 		6. A játékfelület fejlére kíírása kerülnek az éppen aktuális pontszámok.
	 * 		7. A játékciklus elenõrzi, hogy a játék még folytatódik, vagy már egyik kígyó sincsen játékban.
	 * 
	 * Habár a játékciklusnak nem közvetlen elemei a játék véget érésekor megjelnõ gombok, azonban a gomboknak is implementálniuk kell
	 * az ActionListener osztályt, hogy az adott gombok lenyomásával a kívánt hatást érhesse el a felhasználó. 
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{		
		if (!this.gameOver)
		{
			for (int i = 0; i < snakes.size(); i++)
			{
				Snake actSnake = snakes.get(i);
				
				if (actSnake.isMoving == false){continue;}
				
				if(actSnake.isDead){continue;}
				
				if(actSnake.direction.equals("left"))
				{	
					actSnake.head = actSnake.head.add(new Tuple(-1, 0));
					actSnake.snake.add(0, actSnake.head);
					this.growSnake(actSnake);
					actSnake.remove();
					actSnake.draw();
				}
				
				else if(actSnake.direction.equals("up"))
				{
					actSnake.head = actSnake.head.add(new Tuple(0, -1));
					actSnake.snake.add(0, actSnake.head);
					this.growSnake(actSnake);
					actSnake.remove();
					actSnake.draw();
				}
				
				else if(actSnake.direction.equals("right"))
				{
					actSnake.head = actSnake.head.add(new Tuple(1, 0));
					actSnake.snake.add(0, actSnake.head);
					this.growSnake(actSnake);
					actSnake.remove();
					actSnake.draw();
				}
				
				else if(actSnake.direction.equals("down"))
				{
					actSnake.head = actSnake.head.add(new Tuple(0, 1));
					actSnake.snake.add(0, actSnake.head);
					this.growSnake(actSnake);
					actSnake.remove();
					actSnake.draw();
				}
			}
			
			if (this.actApple == null)
			{
				this.chooseApple();
				actApple.remove();
				actApple.drawApple();
			}
			
			this.checkIfEaten();
			if (actApple.isEaten)
			{ 
				this.generateApple();
			}
			else
			{
				actApple.remove();
				actApple.drawApple();
			}
			
			this.checkCollisions();
			this.drawPoints();
			this.isGameOver();
		}
		
		if(e.getSource()==BackToMenu)
		{
			gameFrame.header.removeAll();
			
			for(int i = 0; i < labelContainer.size(); i++)
			{
				gameFrame.getLayeredPane().remove(labelContainer.get(i));
			}
			gameFrame.remove(BackToMenu);
			gameFrame.remove(PlayAgain);
			this.newGame = 1;		
		}
		
		if(e.getSource()==PlayAgain)
		{
			gameFrame.header.removeAll();
			
			for(int i = 0; i < labelContainer.size(); i++)
			{
				gameFrame.getLayeredPane().remove(labelContainer.get(i));
			}
			gameFrame.remove(BackToMenu);
			gameFrame.remove(PlayAgain);
			
			if(this.playerNames.size() == 1)
			{
				this.newGame = 2;
			}
			else if(this.playerNames.size() == 2)
			{
				this.newGame = 3;
			}
		}
	}
}
