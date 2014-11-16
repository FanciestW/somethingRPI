public class Weapon implements GameObject
{
	private String weapon;
	
	Weapon(String s){
		weapon = s;
	}
	
	public String getDescription(){
		String descr = "A weapon: " + weapon;
		return descr;
	}
	
	public String getName(){
		return "weapon";
	}
	public GameObject killCondition(){
		return null;
	}
	
	public GameObject getObject(){
		return null;
	}
}