import java.util.ArrayList;
import java.util.Random;

public class Room implements GameObject
{
	private boolean south, east, north, west;
	protected ArrayList<GameObject> objects;
	protected String name = "a room";
	protected String description = "A nondescript room.";
	
	public Room()
	{
		objects = new ArrayList<GameObject>();
		south = false;
		north = false;
		east = false;
		west = false;
		String[] descriptions = {
				"You are in a small, dimly lit room. You can see nothing of interest.",
				"You find yourself in a mysterious room with candles and an ominous silence.",
				"You are in an ordinary room with a chair and table.",
				"Nothing to report here."
		};
		Random r = new Random();
		description = descriptions[r.nextInt(4)];
	}
	
	public Room(String name, String description)
	{
		this.name = name;
		this.description = description;
		objects = new ArrayList<GameObject>();
		objects = new ArrayList<GameObject>();
		String lower = description.toLowerCase();
		if (lower.contains("book") || lower.contains("education"))
			addObject(new Book());
		if (lower.contains("food"))
			addObject(new Food());
		if (lower.contains("sport"))
			addObject(new SportsItem());
	}
	
	public GameObject killCondition(){
		return null;
	}
	
	public GameObject getObject(){
		return null;
	}
	
	public void setSouth(){
		south = true;
	}
	
	public void setNorth(){
		north = true;
	}
	
	public void setEast(){
		east = true;
	}
	
	public void setWest(){
		west = true;
	}
	
	public boolean getSouth(){
		return south;
	}
	
	public boolean getNorth(){
		return north;
	}
	
	public boolean getEast(){
		return east;
	}
	
	public boolean getWest(){
		return west;
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
		if(Game.ADVENTURE_MODE)
		{
			for (GameObject obj : objects)
				if (obj.getName().equals("thief")){
					Game.println("Oh no, a thief! He steals all of your items.");
					Game.getInstance().getInventory().deleteAll();
				}
		}
		else if (description.length() == 0)
			return "Not much to see here.";
		return description;
	}
	
	private GameObject find(String name)
	{
		for (GameObject obj : objects)
		{
			if (obj.getName().equals(name) || obj.getName().equals("a " + name))
				return obj;
		}
		return null;
	}
	
	private String display()
	{
		if (objects.size() == 0)
			return name + ".";
		String descr = name + ". You can see ";
		if (objects.size() == 1)
			return descr + objects.get(0).getName() + ".";
		else if (objects.size() == 2)
			return descr + objects.get(0).getName() + " and " + objects.get(1).getName();
		for (int i = 0; i < objects.size() - 1; ++i)
			descr += objects.get(i).getName() + ", ";
		return descr + "and " + objects.get(objects.size() - 1) + ".";
	}
	
	public void delete(String s){
		objects.remove(find(s));
	}
	
	public boolean contains(String obj)
	{
		return (find(obj) != null);
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
