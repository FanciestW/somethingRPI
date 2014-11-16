import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Dispatcher
{
	private Map<Option, Handler> options;
	private Room room;
	
	public Dispatcher()
	{
		// make sure to put "X Y ..." before "X ...", etc.
		// TODO: maybe ensure this programatically?
		
		options = new LinkedHashMap<Option, Handler>();
		
		options.put(new Option("help", "h"), new Handler() {
			public Token[] getSyntax()
			{
				return new Token[] { };
			}
			public boolean handle(Map<String, String> argMap)
			{
				Game.print("Possible commands:");
				for (Map.Entry<Option, Handler> pair : options.entrySet())
				{
					Option opt = pair.getKey();
					Handler handler = pair.getValue();
					Game.print("\n  (\"" + opt.getName() + "\" or \"" + opt.getAbbrev() +
							"\") ");
					for (Token token : handler.getSyntax())
					{
						if (token.isLiteral())
							Game.print(token.getName() + " ");
						else
							Game.print("[" + token.getName() + "] ");
					}
					for (String optionalArg : handler.getExtraOpts())
						Game.print("[" + optionalArg + "]? ");
				}
				
				Game.println();
				return true;
			}
		});
		
		options.put(new Option("east", "e"), new Handler() {
			public Token[] getSyntax()
			{
				return new Token[] { };
			}
			public boolean handle(Map<String, String> argMap)
			{
				Game.getInstance().move(1, 0);
				return true;
			}
		});
		
		options.put(new Option("west", "w"), new Handler() {
			public Token[] getSyntax()
			{
				return new Token[] { };
			}
			public boolean handle(Map<String, String> argMap)
			{
				Game.getInstance().move(-1, 0);
				return true;
			}
		});
		
		options.put(new Option("north", "n"), new Handler() {
			public Token[] getSyntax()
			{
				return new Token[] { };
			}
			public boolean handle(Map<String, String> argMap)
			{
				Game.getInstance().move(0, -1);
				return true;
			}
		});
		
		options.put(new Option("south", "s"), new Handler() {
			public Token[] getSyntax()
			{
				return new Token[] { };
			}
			public boolean handle(Map<String, String> argMap)
			{
				Game.getInstance().move(0, 1);
				return true;
			}
		});
		
		options.put(new Option("exit", "quit"), new Handler() {
			public Token[] getSyntax()
			{
				return new Token[] { };
			}
			public boolean handle(Map<String, String> argMap)
			{
				Game.println("Goodbye!");
				return false;
			}
		});
		
		options.put(new Option("look", "l"), new Handler() {
			public Token[] getSyntax()
			{
				return new Token[] { new Token("at", true), new Token("object", false) };
			}
			public boolean handle(Map<String, String> argMap)
			{
				String objName = argMap.get("object");
				if (room.contains(objName))
				{
					GameObject obj = room.get(objName);
					Game.println(obj.getDescription());
				}
				else
					Game.println("I don't understand what you want to look at.");
				return true;
			}
		});
		
		options.put(new Option("look", "l"), new Handler() {
			public Token[] getSyntax()
			{
				return new Token[] { };
			}
			public boolean handle(Map<String, String> argMap)
			{
				Game.println(room.getDescription());
				return true;
			}
		});
	}
	
	public boolean dispatch(String command, String[] args, Room room)
	{
		// Return false to exit
		
		this.room = room;
		String err = null;
		
		MainLoop:
		for (Map.Entry<Option, Handler> pair : options.entrySet())
		{
			Option opt = pair.getKey();
			Handler handler = pair.getValue();

			if (opt.equals(command))
			{
				Map<String, String> argMap = new HashMap<String, String>();
				Token[] tokens = handler.getSyntax();
				String[] extraOpts = handler.getExtraOpts();
				if (args.length < tokens.length)
				{
					err = "Please specify more arguments for the command \""
						+ command + "\".";
					continue;
				}
				else if (args.length > tokens.length + extraOpts.length)
				{
					err = "Too many arguments for the command \""
						+ command + "\".";
					continue;
				}
				for (int i = 0; i < tokens.length; ++i)
				{
					Token token = tokens[i];
					if (token.isLiteral() && !token.getName().equals(args[i]))
						{
							err = "The syntax of \"" + command + "\" requires the use of \""
								+ token.getName() + "\", not \"" + args[i] + "\".";
							continue MainLoop;
						}
					else
						argMap.put(token.getName(), args[i]);
				}
				for (int i = 0; i < extraOpts.length; ++i)
				{
					int argsIndex = i + tokens.length;
					if (argsIndex >= args.length)
						break;
					argMap.put(extraOpts[i], args[argsIndex]);
				}
				return handler.handle(argMap);
			}
		}
		
		if (err != null)
			Game.println(err);
		else
			Game.println("Command not recognized.");
		return true;
	}
}
