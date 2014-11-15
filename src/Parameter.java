public class Parameter
{
	private String name;
	private boolean optional;
	
	public Parameter(String name, boolean optional)
	{
		this.name = name;
		this.optional = optional;
	}
	
	public String getName()
	{
		return name;
	}
	
	public boolean isOptional()
	{
		return optional;
	}
}
