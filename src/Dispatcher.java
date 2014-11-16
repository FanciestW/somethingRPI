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
				if(!room.getEast()){
					Game.getInstance().move(1, 0);
				}else{
					Game.println("There is a wall in the way.");
				}

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
				if(!room.getWest()){
					Game.getInstance().move(-1, 0);
				}else{
					Game.println("There is a wall in the way.");
				}
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
				if(!room.getNorth()){
					Game.getInstance().move(0, -1);
				}else{
					Game.println("There is a wall in the way.");
				}
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
				if(!room.getSouth()){
					Game.getInstance().move(0, 1);
				}else{
					Game.println("There is a wall in the way.");
				}
				return true;
			}
		});

		options.put(new Option("northeast", "ne"), new Handler() {
			public Token[] getSyntax()
			{
				return new Token[] { };
			}
			public boolean handle(Map<String, String> argMap)
			{
				if(!room.getNorth()){
					Game.getInstance().move(1, -1);
				}else{
					Game.println("There is a wall in the way.");
				}
				return true;
			}
		});

		options.put(new Option("northwest", "nw"), new Handler() {
			public Token[] getSyntax()
			{
				return new Token[] { };
			}
			public boolean handle(Map<String, String> argMap)
			{
				if(!room.getNorth()){
					Game.getInstance().move(-1, -1);
				}else{
					Game.println("There is a wall in the way.");
				}
				return true;
			}
		});

		options.put(new Option("southeast", "se"), new Handler() {
			public Token[] getSyntax()
			{
				return new Token[] { };
			}
			public boolean handle(Map<String, String> argMap)
			{
				if(!room.getSouth()){
					Game.getInstance().move(1, 1);
				}else{
					Game.println("There is a wall in the way.");
				}
				return true;
			}
		});

		options.put(new Option("southwest", "sw"), new Handler() {
			public Token[] getSyntax()
			{
				return new Token[] { };
			}
			public boolean handle(Map<String, String> argMap)
			{
				if(!room.getSouth()){
					Game.getInstance().move(-1, 1);
				}else{
					Game.println("There is a wall in the way.");
				}
				return true;
			}
		});

		options.put(new Option("enter", "investigate"), new Handler() {
			public Token[] getSyntax()
			{
				return new Token[] { };
			}
			public String[] getExtraOpts()
			{
				return new String[] { "town" };
			}
			public boolean handle(Map<String, String> argMap)
			{
				if (argMap.containsKey("town") && !argMap.get("town").equalsIgnoreCase(
						Game.getInstance().getRoom().getName()))
					Game.println("You can't go there right now.");
				else if (Game.getInstance().inTown())
					Game.println("You're already in town.");
				else
					Game.getInstance().enterTown();
				return true;
			}
		});

		options.put(new Option("exit", "leave"), new Handler() {
			public Token[] getSyntax()
			{
				return new Token[] { };
			}
			public String[] getExtraOpts()
			{
				return new String[] { "town" };
			}
			public boolean handle(Map<String, String> argMap)
			{
				if (argMap.containsKey("town") && !argMap.get("town").equalsIgnoreCase(
						Game.getInstance().getRoom().getName()))
					Game.println("You aren't in that town.");
				else if (!Game.getInstance().inTown())
					Game.println("You aren't in town.");
				else
					Game.getInstance().exitTown();
				return true;
			}
		});

		options.put(new Option("end", "quit"), new Handler() {
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


		options.put(new Option("pick", "p"), new Handler() {
			public Token[] getSyntax()
			{
				return new Token[] { new Token("up", true), new Token("object", false) };
			}
			public boolean handle(Map<String, String> argMap)
			{
				String objName = argMap.get("object");
				if (room.contains(objName) && !objName.equals("monster") && !objName.equals("quicksand"))
				{
					GameObject obj = room.get(objName);
					room.delete(objName);
					Game.getInstance().getInventory().addToInventory(obj);
					Game.println("Taken.");
				}else{
					Game.println("I don't understand what you want to pick up.");
				}
				return true;
			}
		});

		options.put(new Option("drop", "d"), new Handler() {
			public Token[] getSyntax()
			{
				return new Token[] { new Token("object", false) };
			}
			public boolean handle(Map<String, String> argMap)
			{
				String objName = argMap.get("object");
				if (Game.getInstance().getInventory().contains(objName))
				{
					GameObject obj = Game.getInstance().getInventory().get(objName);
					Game.getInstance().addObject(Game.getInstance().getX(), Game.getInstance().getY(), obj);
					Game.getInstance().getInventory().dropInventory(obj);
					Game.println("Dropped.");
				}
				else
					Game.println("I don't understand what you want to drop.");
				return true;
			}
		});


		options.put(new Option("inventory", "inv"), new Handler() {
			public Token[] getSyntax()
			{
				return new Token[] { };
			}
			public boolean handle(Map<String, String> argMap)
			{
				Game.getInstance().getInventory().viewInventory();
				return true;
			}
		});

		options.put(new Option("fight", "destroy"), new Handler() {
			public Token[] getSyntax()
			{
				return new Token[] { };
			}
			public boolean handle(Map<String, String> argMap)
			{
				if(room.contains("monster")){
					GameObject obj = room.get("monster").killCondition();
					if(Game.getInstance().getInventory().contains(obj)){
						Game.println("You successfully killed the monster! You can proceed.");
						room.delete("monster");
					}else{
						Game.println("You died, try again.");
						return false;
					}
				}else{
					Game.println("There is nothing to fight!");
				}
				return true;
			}
		});

		options.put(new Option("open", "unlock"), new Handler() {
			public Token[] getSyntax()
			{
				return new Token[] { new Token("object", false)};
			}
			public boolean handle(Map<String, String> argMap)
			{
				String objName = argMap.get("object");
				if (room.contains(objName))
				{
					GameObject obj = room.get(objName);
					if(obj.getName().equals("chest") && Game.getInstance().getInventory().contains(obj.killCondition())){
						room.delete(objName);
						Game.println("Found a " + obj.getObject().getName() + "!");
						Game.getInstance().getInventory().addToInventory(obj.getObject());
					}
					else{
						Game.println("You can't open that.");
					}
				}
				else{
					Game.println("I don't understand what you want to open.");
				}
				return true;
			}
			});
			
		if (!Game.ADVENTURE_MODE)
		{
			options.put(new Option("teleport", "tel"), new Handler() {
				public Token[] getSyntax()
				{
					return new Token[] { new Token("lat", false), new Token("long", false) };
				}
				public boolean handle(Map<String, String> argMap)
				{
					Game.getInstance().teleport(Double.parseDouble(argMap.get("lat")),
							Double.parseDouble(argMap.get("long")));
					return true;
				}
			});
		}
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
