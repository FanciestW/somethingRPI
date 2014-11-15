public class Game
{
	private Room[][] grid;
	private int x, y;
	private static final int GRID_SIZE = 10;
	
	public Game()
	{
		x = GRID_SIZE / 2;
		y = GRID_SIZE / 2;
		for (Room[] row : grid)
			for (Room room : row)
				room.load();
	}
	
	private void mainLoop()
	{
		
	}
	
	public static void main(String[] args)
	{
		Game game = new Game();
		game.mainLoop();
	}
}
