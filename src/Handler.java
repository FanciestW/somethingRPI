import java.util.Map;

public interface Handler
{
	public boolean handle(Map<String, String> argMap); // e.g. { 'foo': 'bar' } (false to exit)
	public Token[] getSyntax(); // e.g. for "look": { "at" [object] }
	public String[] getExtraOpts();
}
