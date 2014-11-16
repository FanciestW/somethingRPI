import java.util.ArrayList;

public class Room implements GameObject
{
	protected ArrayList<GameObject> objects;
	protected String name = "a room";
	protected String description = "A nondescript room.";
	
	public Room()
	{
		objects = new ArrayList<GameObject>();
		String lower = description.toLowerCase();
		if (lower.contains("book") || lower.contains("education"))
			addObject(new Book());
		if (lower.contains("food"))
			addObject(new Food());
		if (lower.contains("sport"))
			addObject(new SportsItem());
	}
	
	public Room(String name, String description)
	{
		this.name = name;
		this.description = description;
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
		if (description.length() == 0)
			return "Not much to see here.";
		return description;
	}
	
	private String display()
	{
		if (objects.size() == 0)
			return name + ".";
		String descr = name + ". You can see ";
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
		return "You are in " + display();
	}
}
