import java.util.Scanner;

public class Game
{
	private Room[][] grid, tempGrid;
	// TODO: load new room areas in a separate thread
	private int x, y;
	private Scanner scan;
	private Dispatcher dispatcher;
	private FreebaseManager freebase;
	private double latitude, longitude, latitudeOrig, longitudeOrig;
	private boolean inTown;
	public static double GEO_SQUARE_SIZE = 1.0;
	public static final String KEY = "AIzaSyDP9MhIpDGVP2J0hkU6yyJNdCkBs6N1DBw";

	private static Game game = new Game();
	
	private Game()
	{
		freebase = new FreebaseManager();
		dispatcher = new Dispatcher();
		scan = new Scanner(System.in);
		latitudeOrig = 42.0;
		longitudeOrig = -72.7;
		latitude = 42.0;
		longitude = -72.7;
		inTown = false;
		refreshGrid();
	}
	
	public static Game getInstance()
	{
		return game;
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
	
	public void move(int dx, int dy)
	{
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
	
	public boolean inTown()
	{
		return inTown;
	}
	
	public void enterTown()
	{
		inTown = true;
		tempGrid = grid;
		Town currRoom = (Town)getRoom();
		currRoom.update();
		grid = currRoom.getGrid();
	}
	
	public void exitTown()
	{
		inTown = false;
		grid = tempGrid;
	}
	
	private void mainLoop()
	{
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
