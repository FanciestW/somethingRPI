import java.util.Map;

public interface Handler
{
	public void handle(Map<String, String> argMap); // e.g. { 'foo': 'bar' }
	public String[] getParams(); // array of params, e.g. [ foo ]
}
