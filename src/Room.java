import java.util.ArrayList;

public class Room implements GameObject
{
	private ArrayList<GameObject> objects;
	private boolean south, east, north, west;
	
	public Room()
	{
		objects = new ArrayList<GameObject>();
		south = false;
		north = false;
		east = false;
		west = false;
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
