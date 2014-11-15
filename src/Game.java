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
		scan = new Scanner(System.in);
		x = GRID_SIZE / 2;
		y = GRID_SIZE / 2;
		for (Room[] row : grid)
			for (Room room : row)
				room.load();
	}
	
	public static void print(String str)
	{
		System.out.print(str);
	}
	
	public static void println(String str)
	{
		System.out.println(str);
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
	
	private void mainLoop()
	{
		Room currRoom = grid[y][x];
		println(currRoom.getDescription());
		String input = getInput();
	}
	
	public static void main(String[] args)
	{
		Game game = new Game();
		game.mainLoop();
	}
}
