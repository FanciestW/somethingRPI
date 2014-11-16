import java.util.Scanner;

public class Game
{
	private Room[][] grid;
	private int x, y;
	private static final int GRID_SIZE = 10;
	private Scanner scan;
	private Dispatcher dispatcher;\
	
	private static Game game = new Game();
	
	private Game()
	{
		dispatcher = new Dispatcher();
		scan = new Scanner(System.in);
		// begin at center of grid
		x = GRID_SIZE / 2;
		y = GRID_SIZE / 2;
		grid = new Room[GRID_SIZE][GRID_SIZE]; // square grid
		for (int x = 0; x < GRID_SIZE; ++x)
			for (int y = 0; y < GRID_SIZE; ++y)
				grid[y][x] = new Room();
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
		//Free
	}
	
	public void move(int dx, int dy)
	{
		x += dx;
		y += dy;
		if (x < 0 || x >= GRID_SIZE || y < 0 || y >= GRID_SIZE)
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
