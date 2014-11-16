import java.util.ArrayList;

public class Room implements GameObject
{
	private ArrayList<GameObject> objects;
	
	public Room()
	{
		objects = new ArrayList<GameObject>();
	}
	
	public String getName()
	{
		return "Room";
	}
	
	public String getDescription()
	{
		String descr = "A room.\n";
		for (GameObject obj : objects)
			descr += obj.getDescription();
		return descr;
	}
	
	private GameObject find(String name)
	{
		for (GameObject obj : objects)
			if (obj.getName().equals(name))
				return obj;
		return null;
	}
	
	public boolean contains(String obj)
	{
		return find(obj) != null;
	}
	
	public GameObject get(String name)
	{
		return find(name);
	}
}
