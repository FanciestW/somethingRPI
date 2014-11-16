import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

import org.json.JSONArray;
import org.json.JSONObject;

public class FreebaseManager
{
	private static final String BASE = "https://www.googleapis.com/freebase/v1/mqlread?query=";
	private TreeSet<Town> towns;
	
	public FreebaseManager()
	{
		towns = new TreeSet<Town>();
	}
	
	public static String getURLData(String site)
	{
		URL url;
		BufferedReader in;
		String output = null;
		
		try
		{
			url = new URL(site);
			in = new BufferedReader(new InputStreamReader(url.openStream()));
			output = "";
			String line;
			while ((line = in.readLine()) != null)
				output += line;
			in.close();
		}
		catch (IOException e)
		{
			Game.println("Fatal error: internet connection failed.");
			System.exit(1);
		}
		
		return output;
	}
	
	public Room[][] getGrid(double latitude, double longitude)
	{
		towns.clear();
		double latMin = latitude - Game.GEO_SQUARE_SIZE / 2;
		double latMax = latitude + Game.GEO_SQUARE_SIZE / 2;
		double longMin = longitude - Game.GEO_SQUARE_SIZE / 2;
		double longMax = longitude + Game.GEO_SQUARE_SIZE / 2;
		String query = "[{" +
				"\"mid\":null," +
				"\"name\":null," +
				"\"/location/location/geolocation\":[{" +
				"\"latitude>=\":" + latMin + "," +
				"\"latitude<=\":" + latMax + "," +
				"\"latitude\":null," +
				"\"longitude>=\":" + longMin + "," +
				"\"longitude<=\":" + longMax + "," +
				"\"longitude\":null" +
				"}]," +
				"\"/location/location/contains\":[{}]," +
				"\"/location/statistical_region/population\":[{\"number\":null}]," +
				"\"type\":\"/location/citytown\"}]";
		
		String output = getURLData(BASE + query);
		JSONObject obj = new JSONObject(output);
		JSONArray arr = obj.getJSONArray("result");
		if (arr.length() == 0) // no towns found
			return null;
		
		for (int i = 0; i < arr.length(); ++i)
			addTown(arr.getJSONObject(i));

		int gridLength = (int)(Math.sqrt(towns.size()));
		Room[][] grid = new Room[gridLength][gridLength];
		int remainder = towns.size() - gridLength * gridLength;
		for (int i = 0; i < remainder; ++i)
			towns.pollFirst();
		
		TreeSet<Town> rowSet = new TreeSet<Town>(new Comparator<Town>() {
			@Override
			public int compare(Town t, Town t2)
			{
				if (t.getLatitude() < t2.getLatitude())
					return -1;
				else
					return 1;
			}
		});
		rowSet.addAll(towns);
		Comparator<Town> longComparator = new Comparator<Town>() {
			@Override
			public int compare(Town t, Town t2)
			{
				if (t.getLongitude() < t2.getLongitude())
					return -1;
				else
					return 1;
			}
		};
		ArrayList<TreeSet<Town>> colSets = new ArrayList<TreeSet<Town>>();
		for (int i = 0; i < gridLength; ++i)
			colSets.add(new TreeSet<Town>(longComparator));
		for (int i = 0; i < gridLength * gridLength; ++i)
			colSets.get(i / gridLength).add(rowSet.pollFirst());
		for (int row = 0; row < gridLength; ++row)
		{
			Iterator<Town> it = colSets.get(row).iterator();
			for (int col = 0; col < gridLength; ++col)
				grid[row][col] = it.next();
		}
		
		return grid;
	}
	
	private void addTown(JSONObject obj)
	{
		String id = obj.getString("mid");
		String name = obj.getString("name");
		JSONObject loc = obj.getJSONArray("/location/location/geolocation").getJSONObject(0);
		double longitude = loc.getDouble("longitude");
		double latitude = loc.getDouble("latitude");
		int population = obj.getJSONArray(
				"/location/statistical_region/population").getJSONObject(0).getInt("number");
		JSONArray containsInfo = obj.getJSONArray("/location/location/contains");
		towns.add(new Town(id, name, longitude, latitude, population, containsInfo));
	}
}
