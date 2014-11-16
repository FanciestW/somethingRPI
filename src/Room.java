import java.util.ArrayList;

public class Room implements GameObject
{
	protected ArrayList<GameObject> objects;
	protected String name = "a room";
	
	public Room()
	{
		objects = new ArrayList<GameObject>();
	}
	
	public Room(String name)
	{
		this.name = name;
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
		if (objects.size() == 0)
			return "a room.";
		String descr = "a room. You can see ";
		if (objects.size() == 1)
			descr += objects.get(0).getName() + ".";
		else if (objects.size() == 2)
			descr += objects.get(0).getName() + " and " + objects.get(1).getName();
		for (int i = 0; i < objects.size() - 1; ++i)
			descr += objects.get(i).getName() + ", ";
		return descr + "and " + objects.get(objects.size() - 1) + ".";
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
