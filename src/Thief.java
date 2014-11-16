public class Thief implements GameObject
{
	
	Thief(){
	}
	
	public String getDescription(){
		String descr = "A mischievous thief.";
		return descr;
	}
	
	public String getName(){
		return "thief";
	}
	public GameObject killCondition(){
		return null;
	}
	
	public GameObject getObject(){
		return null;
	}
	
}