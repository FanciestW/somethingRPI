import java.util.Scanner;

public class Game
{
	private Room[][] grid, tempGrid;
	// TODO: load new room areas in a separate thread
	private int x, y, tempX, tempY;
	private Scanner scan;
	private Dispatcher dispatcher;
	private FreebaseManager freebase;
	private double latitude, longitude, latitudeOrig, longitudeOrig;
	private boolean inTown;
	private Inventory inventory = new Inventory();
	public static double GEO_SQUARE_SIZE = 0.6;
	public static final String KEY = "AIzaSyDP9MhIpDGVP2J0hkU6yyJNdCkBs6N1DBw";
	public static boolean ADVENTURE_MODE = true;
	private static final int GRID_SIZE = 10;

	private static Game game = new Game();
	
	private Game()
	{
		dispatcher = new Dispatcher();
		scan = new Scanner(System.in);
		
		if (ADVENTURE_MODE)
		{
			x = 0;
			y = 0;
			grid = new Room[GRID_SIZE][GRID_SIZE]; // square grid
			for (int x = 0; x < GRID_SIZE; ++x)
				for (int y = 0; y < GRID_SIZE; ++y)
					grid[y][x] = new Room();
		}
		else
		{
			freebase = new FreebaseManager();
			latitudeOrig = 42.0;
			longitudeOrig = -72.7;
			latitude = 42.0;
			longitude = -72.7;
			inTown = false;
			refreshGrid();
		}
	}
	
	public static Game getInstance()
	{
		return game;
	}
	
	public Inventory getInventory()
	{
		return inventory;
	}
	
	public static void print(Object o)
	{
		System.out.print(o.toString());
	}
	
	public static void println(Object o)
	{
		System.out.println(o.toString());
	}
	
	public static void println()
	{
		println("");
	}
	
	private String getInput()
	{
		print("> ");
		return scan.nextLine().toLowerCase();
	}
	
	public Room getRoom()
	{
		return grid[y][x];
	}
	
	private void refreshGrid()
	{
		int i = 0;
		do
		{
			grid = freebase.getGrid(latitude, longitude);
			++i;
		} while (grid == null && i < 5);
		
		if (i == 5)
		{
			Game.print("No available towns to navigate in this area...");
			Game.println("Teleporting back to original square.");
			latitude = latitudeOrig;
			longitude = longitudeOrig;
			refreshGrid();
			return;
		}
		
		x = grid.length / 2;
		y = grid.length / 2;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public void moveAdventure(int dx, int dy)
	{
		if(getRoom().contains("monster")){
			println("You must fight the monster to advance!");
			return;
		}
		if(getRoom().contains("quicksand")){
			println("You can't move as you slowly die to quicksand");
			return;
		}
		if(x == 9 && y == 9){
			println("Congradulations, you win.");
		}
		
		if (x + dx >= 0 && x + dx < grid.length && y + dy >= 0 && y + dy < grid.length)
		{
			x += dx;
			y += dy;
			println(getRoom().getDescription());

			return;
		}else{
			println("You can't move outside the map.");
		}
	}
	
	public void move(int dx, int dy)
	{
		if (ADVENTURE_MODE) { moveAdventure(dx, dy); return; }
		x += dx;
		y += dy;
		if (x >= 0 && x < grid.length && y >= 0 && y < grid.length)
		{
			if (getRoom() == null)
			{
				x -= dx;
				y -= dy;
				Game.println("You have no place to go.");
			}
			else
				println(getRoom().toString());
			return;
		}
		else if (inTown)
		{
			x -= dx;
			y -= dy;
			Game.println("You have no place to go.");
			return;
		}
		else if (x < 0)
			longitude -= GEO_SQUARE_SIZE;
		else if (x >= grid.length)
			longitude += GEO_SQUARE_SIZE;
		else if (y < 0)
			latitude += GEO_SQUARE_SIZE;
		else
			latitude -= GEO_SQUARE_SIZE;
		refreshGrid();
		println(getRoom().toString());
	}
	
	public void addObject(int x, int y, GameObject s){
				grid[y][x].addObject(s);
	}
	
	public boolean inTown()
	{
		return inTown;
	}
	
	public void enterTown()
	{
		inTown = true;
		tempGrid = grid;
		tempX = x;
		tempY = y;
		Town currRoom = (Town)getRoom();
		currRoom.update();
		grid = currRoom.getGrid();
		println("You enter " + currRoom.getName() + ".");
		x = 0;
		y = 0;
		println(getRoom().toString());
	}
	
	public void exitTown()
	{
		inTown = false;
		grid = tempGrid;
		x = tempX;
		y = tempY;
		println("You exit " + getRoom().getName() + ".");
	}
	
	private void addWallEast(int x, int y){
		grid[y][x].setEast();
		if(x + 1 < grid.length){
			grid[y][x + 1].setWest();
		}
	}
	private void addWallSouth(int x, int y){
		grid[y][x].setSouth();
		if(y + 1 < grid.length){
			grid[y + 1][x].setNorth();
		}
	}
	
	private void beginAdventure()
	{
		addWallEast(3,0);
		addWallEast(3,1);
		addWallEast(3,2);
		addWallEast(3,3);
		addWallSouth(0,3);
		//addWallSouth(1,3);
		addWallSouth(2,3);
		addWallSouth(3,3);
		
		addWallEast(0,4);
		addWallEast(0,5);
		addWallEast(0,6);
		
		addWallEast(1,4);
		addWallEast(1,5);
		addWallEast(1,6);
		addWallEast(1,7);
		addWallEast(1,8);
		//addWallEast(1,9);
		
		addWallEast(2,6);
		//addWallEast(2,7);
		addWallEast(2,8);
		addWallEast(2,9);
		
		addWallEast(3,4);
		addWallEast(3,5);
		addWallEast(3,6);
		//addWallEast(3,7);
		addWallEast(3,8);
		addWallEast(3,9);
		
		addWallSouth(4,4);
		
		addWallEast(5,1);
		addWallEast(5,2);
		addWallEast(5,3);
		addWallEast(5,4);
		addWallEast(5,5);
		addWallEast(5,6);
		addWallEast(5,8);
		
		addWallEast(6,0);
		addWallEast(6,1);
		addWallEast(6,2);
		addWallEast(6,3);
		addWallEast(6,4);
		addWallEast(6,5);
		
		addWallEast(7,6);
		addWallEast(7,7);
		addWallEast(7,8);
		addWallEast(7,9);
		
		addWallSouth(8,5);
		addWallSouth(8,7);
		addWallSouth(9,6);
		addWallSouth(9,8);
		
		Key redKey = new Key("red");
		addObject(0,0,redKey);
		Key blueKey = new Key("blue");
		addObject(2,3,blueKey);
		Weapon sword = new Weapon("sword");
		addObject(3,2,sword);
		Monster scary = new Monster("scary", false, sword, null);
		addObject(1,5,scary);
		Weapon bow = new Weapon("bow");
		Chest blueChest = new Chest("blue", blueKey, bow);
		addObject(0,9,blueChest);
		Thief thief = new Thief();
		addObject(2,7, thief);
		
		Monster monster1 = new Monster("happy", false, sword, null);
		Monster monster2 = new Monster("sad", false, sword, null);
		addObject(4,5,monster1);
		addObject(5,6,monster2);
		Thief thief2 = new Thief();
		addObject(6,5,thief2);
		
		QuickSand quicksand = new QuickSand();
		QuickSand quicksand2 = new QuickSand();
		QuickSand quicksand3 = new QuickSand();
		QuickSand quicksand4 = new QuickSand();
		QuickSand quicksand5 = new QuickSand();
		QuickSand quicksand6 = new QuickSand();
		addObject(6,7,quicksand);
		addObject(6,9,quicksand2);
		addObject(7,2,quicksand3);
		addObject(8,0,quicksand4);
		addObject(8,4,quicksand5);
		addObject(9,2,quicksand6);
		
		Weapon bazooka = new Weapon("Bazoooka");
		Monster monster3 = new Monster("tiny", true, bow, null); 
		addObject(9,0, bazooka);
		addObject(9,0, monster3);
		
		Monster finalMonster = new Monster("SATANIC", true, bazooka, null);
		addObject(8,9, finalMonster);
	}
	
	private void mainLoop()
	{
		if (ADVENTURE_MODE)
			beginAdventure();
		println(getRoom().toString()); // start out by printing initial description
		while (true)
		{
			String input = getInput();
			String[] strSplit = input.split(" ");
			String[] args = new String[strSplit.length - 1];
			for (int i = 1; i < strSplit.length; ++i)
				args[i - 1] = strSplit[i];
			if (!dispatcher.dispatch(strSplit[0], args, getRoom()))
				break;
		}
	}
	
	public static void main(String[] args)
	{
		Game.getInstance().mainLoop();
	}
}