import java.util.Scanner;

public class Game
{
	private Room[][] grid;
	private int x, y;
	private Scanner scan;
	private Dispatcher dispatcher;
	private FreebaseManager freebase;
	private double latitude, longitude;
	public static double GEO_SQUARE_SIZE = 0.2;

	private static Game game = new Game();
	
	private Game()
	{
		freebase = new FreebaseManager();
		dispatcher = new Dispatcher();
		scan = new Scanner(System.in);
		// begin at center of grid
		latitude = 42.0;
		longitude = -72.7;
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
		grid = freebase.getGrid(latitude, longitude);
	}
	
	public void move(int dx, int dy)
	{
		x += dx;
		y += dy;
		if (x >= 0 && x < grid.length && y >= 0 && y < grid.length)
		{
			println(getRoom().getDescription());
			return;
		}
		else if (x < 0)
			longitude -= GEO_SQUARE_SIZE;
		else if (x >= grid.length)
			longitude += GEO_SQUARE_SIZE;
		else if (y < 0)
			latitude -= GEO_SQUARE_SIZE;
		else
			latitude += GEO_SQUARE_SIZE;
		refreshGrid();
		println(getRoom().getDescription());
	}
	
	private void mainLoop()
	{
		println(getRoom().getDescription()); // start out by printing initial description
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
