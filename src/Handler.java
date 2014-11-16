import java.util.Map;

public abstract class Handler
{
	public abstract boolean handle(Map<String, String> argMap); // e.g. { 'foo': 'bar' } (false to exit)
	public abstract Token[] getSyntax(); // e.g. for "look": { "at" [object] }
	public String[] getExtraOpts() // no optional arguments by default
	{
		return new String[] { };
	}
}
