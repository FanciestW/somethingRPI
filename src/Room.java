import java.util.ArrayList;

public class Room implements GameObject
{
	protected ArrayList<GameObject> objects;
	protected String name = "a room";
	
	public Room()
	{
		objects = new ArrayList<GameObject>();
	}
	
	public void addObject(GameObject obj)
	{
		objects.add(obj);
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getDescription()
	{
		String descr = "a room.";
		for (GameObject obj : objects)
			descr += obj.getDescription();
		return descr;
	}
	
	private GameObject find(String name)
	{
		for (GameObject obj : objects)
		{
			if (obj.getName().equals(name))
				return obj;
		}
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
	
	public String toString()
	{
		return "You are in " + getName() + ".";
	}
}
