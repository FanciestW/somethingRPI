public class Chest implements GameObject
{
	private String color;
	private GameObject keyObject;
	private GameObject containsObject;
	
	Chest(String s, GameObject object, GameObject contains){
		color = s;
		keyObject = object;
		containsObject = contains;
	}
	
	public String getDescription(){
		String descr = "A " + color + " Chest.";
		return descr;
	}
	
	public String getName(){
		return "chest";
	}
	public GameObject killCondition(){
		return keyObject;
	}
	
	public GameObject getObject(){
		return containsObject;
	}
	
	
	
	
	
}