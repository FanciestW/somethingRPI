import java.util.Scanner;

public class Game
{
	private Room[][] grid;
	private int x, y;
	private static final int GRID_SIZE = 10;
	private Scanner scan;
	private Dispatcher dispatcher;
	
	public Game()
	{
		dispatcher = new Dispatcher();
		scan = new Scanner(System.in);
		x = GRID_SIZE / 2;
		y = GRID_SIZE / 2;
		for (Room[] row : grid)
			for (Room room : row)
				room.load();
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
		return scan.nextLine();
	}
	
	public Room getRoom()
	{
		return grid[y][x];
	}
	
	private void mainLoop()
	{
		String input = getInput();
	}
	
	public static void main(String[] args)
	{
		Game game = new Game();
		game.mainLoop();
	}
}
