public class Monster implements GameObject
{
	private String monster;
	private boolean dragon;
	private GameObject object;
	private GameObject loot;
	
	Monster(String s, boolean d, GameObject obj, GameObject l){
		monster = s;
		dragon = d;
		object = obj;
		loot = l;
	}
	
	public String getDescription(){
		String descr;
		if(dragon){
			descr = "A " + monster + " dragon!";
		}else{	
			descr = "A " + monster + " monster!";
		}
		return descr;
	}
	
	public GameObject killCondition(){
		return object;
	}
	
	public GameObject getObject(){
		return loot;
	}
	
	public String getName(){
		return "monster";
	}
}