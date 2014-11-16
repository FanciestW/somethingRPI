public class QuickSand implements GameObject
{
	QuickSand(){
	}
	
	public String getDescription(){
		String descr = "Quicksand!!";
		return descr;
	}
	
	public GameObject killCondition(){
		return null;
	}
	
	public GameObject getObject(){
		return null;
	}
	
	public String getName(){
		return "quicksand";
	}
}