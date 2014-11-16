public class Token
{
	private String name;
	private boolean literal;
	
	public Token(String name, boolean literal)
	{
		this.name = name;
		this.literal = literal;
	}
	
	public String getName()
	{
		return name;
	}
	
	public boolean isLiteral()
	{
		return literal;
	}
}
