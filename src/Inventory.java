

public class Inventory
{
	GameObject[] inv = new GameObject[5];
	int size;
	public Inventory(){
		size = 0;
	}
	
	public void addToInventory(GameObject s){
		if(size == 9){
			Game.println("Inventory too big, need to drop items for bigger size");
		}else{
			inv[size] = s;
			size++;
		}
		
	}
	
	public void viewInventory(){
		for(int i = 0; i < size; i++){
			Game.println(inv[i].getDescription());
		}
		if(size == 0){
			Game.println("Nothing in inventory");
		}
	}
	
	public boolean contains(GameObject obj){
		for(int i = 0; i < size; i++){
			if(inv[i] == obj){
				return true;
			}
		}
		return false;
	}
	
	public GameObject get(String obj){
		for(int i = 0; i < size; i++){
			if(inv[i].getName().equals(obj)){
				return inv[i];
			}
		}
		return null;
	}
	
	public boolean contains(String obj){
		for(int i = 0; i < size; i++){
			if(inv[i].getName().equals(obj)){
				return true;
			}
		}
		return false;
	}
	
	public void dropInventory(GameObject s){
		for(int i = 0; i < size; i++){
			if(inv[i] == s){
				GameObject j = s;
				inv[i] = inv[size-1];
				inv[size-1] = s;
			}
		}
		inv[size-1] = null;
		size--;
	}
	
	public void deleteAll(){
		for(int i = size - 1; i > -1; i--){
			inv[i] = null;
		}
		size = 0;
		
	}
	
	
}