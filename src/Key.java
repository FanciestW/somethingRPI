public class Key implements GameObject
{
	private String color;
	
	Key(String s){
		color = s;
	}
	
	public String getDescription(){
		String descr = "A " + color + " Key.";
		return descr;
	}
	
	public String getName(){
		return "key";
	}
	
	public GameObject killCondition(){
		return null;
	}
	
	public GameObject getObject(){
		return null;
	}
}