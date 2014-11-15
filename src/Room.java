import java.util.ArrayList;

public class Room extends GameObject
{
	private ArrayList<GameObject> objects;
	
	public Room()
	{
		objects = new ArrayList<GameObject>();
	}
	
	public void load()
	{
		
	}
	
	public String getDescription()
	{
		String descr = "A room.\n";
		for (GameObject obj : objects)
			descr += obj.getDescription();
		return descr;
	}
}
