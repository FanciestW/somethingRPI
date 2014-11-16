import java.util.Random;

public class RandomizedObject implements GameObject
{
	protected String name, description;
	
	public RandomizedObject(String[] names, String[] descriptions)
	{
		Random random = new Random();
		int choice = random.nextInt(names.length);
		name = names[choice];
		description = descriptions[choice];
	}

	@Override
	public String getDescription()
	{
		return description;
	}

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public GameObject killCondition()
	{
		return null;
	}

	@Override
	public GameObject getObject()
	{
		return null;
	}
}
