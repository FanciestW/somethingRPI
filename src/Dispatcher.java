import java.util.HashMap;
import java.util.Map;

public class Dispatcher
{
	private Map<Option, Handler> options;
	private Room room;
	
	public Dispatcher()
	{
		options = new HashMap<Option, Handler>();
		register(new Option("look", "l"), new Handler() {
			public String[] getParams()
			{
				return null;
			}
			public void handle(Map<String, String> argMap)
			{
				Game.println(room);
			}
		});
	}
	
	private void register(Option option, Handler handler)
	{
		for (Option opt : options.keySet())
			if (opt.equals(option))
				throw new RuntimeException("Cannot register two matching options: " + opt.toString()
						+ " and " + option.toString());
		options.put(option, handler);
	}
}
