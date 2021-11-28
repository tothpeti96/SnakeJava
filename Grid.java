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
	 * A Grid oszt�lyhoz tartoz� attrib�tumok, amelyek k�zvetlen sz�ks�gesek a j�t�k m�k�d�s�hez.
	 * 
	 * 		cellNum: A Grid-hez tartoz� p�lyadimenzi�. A p�ly�n tal�lhat� cell�k sz�ma megkaphat� ha a cellNum n�gyzet�t vessz�k.
	 * 
	 * 		cellSize: A Grid-hez tartoz� egyes cell�k dimenzi�ja. Az egyes cell�k ter�let�t megkapjuk, ha a cellSize n�gyzet�t vessz�k
	 * .
	 * 		gameOver: boolean v�ltoz�. Hamis �rt�kkel inicializ�lja a konstruktor. Igaz �rt�kre v�ltozik ha m�r egyik k�gy� sincsen j�t�kban
	 * 			Igaz �rt�k eset�n a j�t�k meg�ll.
	 * 
	 * 		actApple: Az �ppen haszn�latban l�v� alma referenci�j�t tartalmaz� v�ltoz�.
	 * 
	 * 		timer: A j�t�kciklushoz tartoz� id�z�t�, amelynek �tem�nek (DELAY) �ll�t�s�val a j�t�k gyorsas�ga �ll�that�.
	 * 
	 * 		BackButton: A j�t�k v�ge sor�n l�that� j�t�k�sszes�t� fel�let "Back" gombja. Megnyom�s�val a j�t�kos a f�men�ben tal�lja mag�t.
	 * 
	 * 		PlayButton: A j�t�k v�ge sor�n l�that� j�t�k�sszes�t� fel�let "Play Again" gombja. A megnyom�s�val a j�t�kos �j j�t�kot kezdhet 
	 * 			az eredetileg megadott felhaszn�l�nevekkel
	 * 
	 * 		newGame: �llapotjelz�. �j j�t�k eset�n ez a v�ltoz� jelzi a Menu oszt�lynak, hogy egy- k�t j�t�kos �zemm�dodt ind�tson, vagy l�pjen ki a f�men�be.
	 * 			A "PlayButton" �s "BackButton" a newGame �llapot�ra vannak hat�ssal. 
	 * 				0: Alap�llapot
	 * 				1: Vissza a f�men�be
	 * 				2: �j j�t�k egyj�t�kos �zemm�dban
	 * 				3: K�t j�t�k egyj�t�kos �zemm�dban
	 * 
	 * 		gameFrame: A "Frame" oszt�lynak egy p�ld�nya. A j�t�k sor�n a grafika erre a fel�letre fog kirajzol�dni. Statikus v�ltoz�, ellenkez� esetben 
	 * 			a k�l�nb�z� grafik�k megjelen�t�se a Frame oszt�ly k�l�n-k�l�n p�ld�nyain t�rt�nne meg.
	 * 		
	 * 		snakes: A j�t�kter�leten tal�lhat� k�gy�k referenci�j�t tartalmaz� dinamikus t�mb.
	 * 		
	 * 		apples: A j�t�kter�leten tal�lhat� alma referenci�j�t tartalmaz� dinamikus t�mb.
	 * 
	 * 		labelContainer: A j�t�kosok �ppen aktu�lis pontsz�mait tartalmaz� MyLabel t�pus� objektum referenci�it tartalmaz� dinamikus t�mb.
	 * 		
	 * 		playerNames: Az egyes j�t�kosok neveti tartalmaz� dinamikus t�mb.
	 * 		
	 * 		points: Az egyes j�t�kosok pontsz�mait tartalmaz� dinamikus t�mb. 
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
	 * A Grid oszt�ly konstruktora. Param�tere a Frame oszt�ly egy p�ld�nya amire a k�l�nb�z� j�t�kelemek ker�lnek majd kirajzol�sra illetve az actCellNum,
	 * amivel a j�t�kfel�let dimenzi�ja �ll�that�. 
	 * 
	 *  A konstruktor a fenti �rt�kek be�ll�t�sa mellett inicializ�lja a newGame, a gameOver, az actApple �s cellSize attrib�tumokat is.
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
	 * A met�dus param�tere a String t�pus� "name" v�ltoz�. A met�dus megh�v�s�nak hat�s�ra a playerNames dinamikus t�mbh�z a 
	 * "name" param�ter hozz�ad�sra ker�l.
	 */
	protected void addPlayer(String name){playerNames.add(name);}
	
	
	/*
	 * A met�dus megh�v�s�nak hat�s�ra a j�t�kt�r fejl�c�re ki�r�sra ker�l a j�t�kos neve �s aktu�lis pontsz�ma.
	 * Az adott MyLabel t�pus� objektumnak a referenci�ja hozz�ad�sra ker�l a labelContainer dinamikus t�mbh�z. 
	 * 
	 * Az �j pontsz�m ki�r�sa el�tt azonban az el�z� pontsz�mot el kell t�nteni a fejl�cr�l. �gy a met�dus legel�sz�r v�gigiter�l a labelContainer 
	 * dinamukus t�mb�n, �s t�rli az el�z� pontsz�mokhoz tartoz� MyLabel t�pus� objektumokat. 
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
	 * A met�dus param�tere a Snake t�pus� actSnake objektum.
	 * A met�dus megh�v�s�nak hat�s�ra a snakes dinamikus t�mbh�z hozz�ad�sra ker�l az �ppen hozz�adni k�v�nt Snake objektum referenci�ja. 
	 */
	protected void addSnake(Snake actSnake)
	{
		snakes.add(actSnake);
		actSnake.setGrid(this);
	}
	
	/*
	 * A met�dus param�tere az Apple t�pus� actApple objektum.
	 * A met�dus megh�v�s�nak hat�s�ra az apples dinamikus t�mbh�z hozz�ad�sra ker�l az �ppen hozz�adni k�v�nt Apple objektum referenci�ja.
	 * 
	 *  Mivel egy- �s k�t j�t�kos �zemm�dban is mind a k�t t�pus� Apple objektumot haszn�ljuk v�letlenszer�en (Apple1 �s Apple2 objektumok),
	 *  �gy a j�t�k id�z�t�je �s �gy a j�t�k is csak a m�sodik Apple objektum hozz�ad�sa ut�n indul el, amelyr�l �gy szint�n az "addApple" met�dusnak kell
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
	 * A j�t�k sor�n az aodtt k�gy� a mozg�s�nak l�tszat�t �gy �rj�k el, hogy a halad�si ir�nynak megfelel�en egy egys�get besz�runk a k�gy� 
	 * testelemeinek referenci�it tartalmaz� dinamikus t�mb elej�re, m�g ennek a dinamikus t�mbnek az utols� elem�t t�r�lj�k. 
	 * K�gy�-alma �tk�z�s eset�n azonban a k�gy� egy egys�ggel n�. Ekkor az utols� elem t�rl�s�re nem ker�l sor. 
	 * 
	 * A "growSnake" met�dus a k�gy� n�veked�s�r�l felel.
	 * Abban az esetben ha a met�dus param�terek�nt szolg�l� Snake t�pus� actSnake objektum isGrowing attrib�tuma az igaz �rt�ket veszi fel,
	 * akkor a k�gy� utols� testeleme nem ker�l t�rl�sre, ellenkez� esetben igen.
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
	 * A met�dus v�gigiter�l a Grid-en l�v� egyes k�gy�k referenci�it tartalmaz� "snakes" dinamikus t�mb�n. 
	 * Ha b�rmelyik k�gy� fej�nek koordin�t�ai megegyezik az �ppen aktu�lis alma koordin�t�ival, akkor az adott j�t�kost 
	 * reprezent�l� k�gy� pontsz�ma eggyel n�, illetve az �ppen haszn�latban l�v� alma "isEaten" attrib�tum�t igazra �ll�tja �t a met�dus.
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
	 * A met�dus megh�v�s�nak hat�s�ra kiv�laszt�sra ker�l, hogy melyik t�pus� alm�t haszn�lja a j�t�k.
	 * K�t f�le alma lehet a j�t�kmez�n. Egy piros sz�n�, ami 1 pontot �r, illetve egy lila sz�n� ami 3 pontot �r.
	 * Annak az es�lye, hogy �j alma gener�l�sakor lila alm�t gener�l a j�t�k 25%. Piros alma gener�l�s�nak az es�lye 75%. 
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
	 * A "generateApple" met�dus felel az �j alma gener�l�s�r�l.
	 * 
	 * A met�dus megh�v�s�nak hat�s�ra l�trej�n egy freeSlot nev� dinamikus t�mb ami Tuple objektumok referenci�j�t t�rolja.
	 * A met�dus ezut�n felt�lti ezt a dinamikus t�mb�ta j�t�kmez�n tal�lhat� cellaelemek sor- �s oszlopindexeinek megfelel� 
	 * Tuple objektumokkal. 
	 * 
	 * A met�dus ezut�n v�gigiter�l az egyes k�gy�k testeleminek koordin�t�in �s a freeSlot dinamikus t�mbb�l elt�vol�tja az �ppen 
	 * soron l�v� testelem koordin�t�naik megfelel� Tuple objektumot. �gy az �j alma gener�l�sakkor csak olyan cellapoz�ci�k lesznek figyelembe
	 * v�ve, amelyeken nem tal�lhat� k�gy�. 
	 * 
	 * A met�dus ezek ut�n v�letlenszer�en kiv�lasztja, hogy a k�vetkez� gener�land� alma piros vagy lila alma legyen, majd �t�ll�tja az actApple
	 * referenci�j�t a v�lasztott t�pus� Apple objektumra (Apple1 vagy Apple2). A r�gi alm�t t�rli a j�t�kfel�letr�l.
	 * 
	 * A haszn�latban l�v� actApple �j X �s Y koordin�t�inak v�letlenszer�en v�laszt egyet a m�g szabadon maradt cellaelemek egyik�b�l,
	 * majd kirajzolja az alm�t.
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
	 * A met�dus leellen�rzi, hogy az egyes k�gy�k nem �tk�ztek-e bele valamelyik falba, �nmagukba vagy k�tj�t�kos �zemm�d eset�n valamelyik m�sik k�gy�ba.
	 * Amennyiben valamelyik �tk�z�s bek�vetkezik, akkor az �ppen vizsg�lt k�gy� "isDead" attrib�tum�t igazra �ll�tja �t.  
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
	 * A met�dus megh�v�s�nak hat�s�ra megjelenik a j�t�k �sszes�t� fel�let, ahol ki�r�sra ker�lnek az egyes j�t�kosok pontsz�mai, 
	 * illetve a felhaszn�l� v�laszthat, hogy vissza szeretne l�pni a f�men�be, vagy ugyanezekkel a j�t�kosnevekkel, ugyanebben a 
	 * j�t�km�dban egy �j j�t�kot szeretne-e kezdeni. 
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
		
		MyLabel endHeader = new MyLabel("V�ge a j�t�knak!", 210, 0);
		labelContainer.add(endHeader);
		gameFrame.header.add(endHeader);
		
		MyLabel endMessage = new MyLabel("El�rt pontsz�mod:", 115, 120);
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
	 * A met�dus leellen�rzi, hogy a j�t�kos fel�leten elhelyezett k�gy�k m�g �letben vannak-e.
	 * Abban az esetben ha m�r mind a k�t k�gy� meghalt a met�dus igaz �rt�kre �ll�tja �t a Grid oszt�ly "gameOver" attrib�tum�t.
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
	 * Az actionPerformed met�dus az ActionListener oszt�ly egy t�lterhelt met�dusa. Ez a met�dus felel�s a j�t�kciklus�rt.
	 * A j�t�k egy ciklusa az al�bbi m�don fut le:
	 * 
	 * 		1. A met�dus leelen�rzni, hogy a j�t�k m�g fut-e, a Grid "gameOver" attrib�tuma hamis �rt�ket vesz fel.
	 * 		2. A j�t�kciklus ezut�n v�gigiter�l a p�ly�n tal�lhat� �sszes k�gy�n. Abban az esetben ha az �ppen 
	 * 			vizsg�lt k�gy� "isMoving" attrib�tuma hamis �rt�ket, vagy az "isDead" �rt�ke igaz �rt�kez vesz fel,
	 * 			akkor a j�t�kciklus nem tesz a tov�bbiakban semmit a k�gy�val. Ellenkez� esetben a k�gy�t a fej ir�ny�nak megfelel�en elmozd�tja,
	 * 			illetve ha a k�gy� "isGrowing" attrib�tuma igaz �rt�ket mutat, akkor a k�gy� hossz�t m�g eggyel n�veli is.
	 * 		3. A j�t�kciklus ezut�n ellen�rzni, hogy a p�ly�n tal�lhat� egy�ltal�n Apple objektum. Ha nem akkor v�laszt a Grid ost�lyon defini�lt
	 * 			"apple" dinamikus t�mb elem�b�l egyet, ellenkez� esetben tov�bbl�p.
	 * 		4.  A j�t�k leelen�rzi, hogy a p�ly�n tal�lhat� alm�t �ppen megette-e valamelyik k�gy� vagy nem. Amennyiben az �tek elfogyaszts�raa ker�lt,
	 * 			akkor v�letlenszer�en egy �j alma gener�l�dik. 
	 * 		5. A j�t�kciklus ellen�rzi, hogy ek�vetkezet-e a j�t�kfel�leten valamilyen �tk�z�s.
	 * 		6. A j�t�kfel�let fejl�re k��r�sa ker�lnek az �ppen aktu�lis pontsz�mok.
	 * 		7. A j�t�kciklus elen�rzi, hogy a j�t�k m�g folytat�dik, vagy m�r egyik k�gy� sincsen j�t�kban.
	 * 
	 * Hab�r a j�t�kciklusnak nem k�zvetlen elemei a j�t�k v�get �r�sekor megjeln� gombok, azonban a gomboknak is implement�lniuk kell
	 * az ActionListener oszt�lyt, hogy az adott gombok lenyom�s�val a k�v�nt hat�st �rhesse el a felhaszn�l�. 
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
