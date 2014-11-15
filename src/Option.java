public class Option
{
	private String name, abbrev;
	
	public Option(String name, String abbrev)
	{
		this.name = name;
		this.abbrev = abbrev;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getAbbrev()
	{
		return abbrev;
	}
	
	public boolean equals(Option other)
	{
		return (name.equals(other.name) || abbrev.equals(other.abbrev)
				|| name.equals(other.abbrev) || abbrev.equals(other.name));
	}
	
	public String toString()
	{
		return "{ " + name + " [" + abbrev + "]}";
	}
}
