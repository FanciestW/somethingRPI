public class Option
{
	private String name, abbrev;
	
	public Option(String name, String abbrev)
	{
		this.name = name.toLowerCase();
		this.abbrev = abbrev.toLowerCase();
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getAbbrev()
	{
		return abbrev;
	}
	
	public String toString()
	{
		return "{ " + name + " [" + abbrev + "]}";
	}
	
	public boolean equals(String str)
	{
		return (name.equals(str) || abbrev.equals(str));
	}
}
