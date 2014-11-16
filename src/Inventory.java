public class Inventory
{
	GameObject[] inv = new GameObject[5];
	int size;
	public Inventory(){
		size = 0;
	}

	public void addToInventory(GameObject s){
		if(size == 9){
			Game.println("Your inventory is too full. You will need to drop an item or two.");
		}else{
			inv[size] = s;
			size++;
		}

	}

	public void viewInventory(){
		if(size == 0)
			Game.println("You have nothing in your inventory.");
		else {
			Game.println("You are carrying the following:");
			for(int i = 0; i < size; i++)
				Game.println(inv[i].getDescription());
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
				if(inv[i].getName().equals(obj) || inv[i].getName().equals("a " + obj)){
					return inv[i];
				}
			}
			return null;
		}

		public boolean contains(String obj){
			for(int i = 0; i < size; i++){System.out.println(inv[i].getName() + ", " + obj);
			if(inv[i].getName().equals(obj) || inv[i].getName().equals("a " + obj)){
				return true;
			}
			}
			return false;
		}

		public void dropInventory(GameObject s){
			for(int i = 0; i < size; i++){
				if(inv[i] == s){
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