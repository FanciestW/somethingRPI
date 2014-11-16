import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Town extends Room implements Comparable<Town>
{
	private double longitude, latitude;
	private int population;
	private String description;
	private Room[][] grid;
	private JSONArray containsInfo;
	
	public Town(String ID, String name, double longitude, double latitude, int population,
			JSONArray containsInfo)
	{
		this.name = name;
		this.longitude = longitude;
		this.latitude = latitude;
		this.population = population;
		this.containsInfo = containsInfo;
		grid = null;
		description = findDescription(ID);
	}
	
	private String findDescription(String ID)
	{
		String base = "https://www.googleapis.com/freebase/v1/search?query=";
		String end = ID + "&output=(description)&key=" + Game.KEY;
		String output = FreebaseManager.getURLData(base + end);
		try
		{
			return new JSONObject(output).getJSONArray("result").getJSONObject(0).
				getJSONObject("output").getJSONObject("description").
				getJSONArray("/common/topic/description").getString(0);
		}
		catch (JSONException e)
		{
			return ""; //TODO: deal with this
		}
	}

	@Override
	public int compareTo(Town other)
	{
		if (population < other.population)
			return -1;
		else if (population > other.population)
			return 1;
		int tiebreaker = name.compareTo(other.name);
		return tiebreaker != 0 ? tiebreaker : 1;
	}
	
	@Override
	public String getDescription()
	{
		String descr = description + "\n";
		for (GameObject obj : objects)
			descr += obj.getDescription();
		return descr;
	}
	
	public double getLatitude()
	{
		return latitude;
	}
	
	public double getLongitude()
	{
		return longitude;
	}
	
	public Room[][] getGrid()
	{
		return grid;
	}
	
	public void update()
	{
		if (grid != null)
			return;
		int numRooms = containsInfo.length();
		int sideLength = (int)Math.ceil(Math.sqrt(numRooms));
		grid = new Room[sideLength][sideLength];
		for (Room[] row : grid)
			for (int i = 0; i < row.length; ++i)
				row[i] = null;
		for (int i = 0; i < numRooms; ++i)
		{
			JSONObject obj = containsInfo.getJSONObject(i);
			String name = obj.getString("name");
			String roomID = obj.getString("id");
			String descr = findDescription(roomID);
			grid[i / sideLength][i % sideLength] = new Room(name, descr);
		}
	}
}
