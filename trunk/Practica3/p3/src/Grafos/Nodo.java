package Grafos;

public class Nodo {

	int x;
	int y;
	String name;
	int id;
	
	public Nodo(int X, int Y, String NAME, int ID){
		this.x = X;
		this.y = Y;
		this.name = NAME;
		this.id = ID;  
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getID(){
		return this.id;
	}
	public void setID(int ID) {
		this.id = ID;
	}
	
	public boolean igual(Nodo n1) {
		if (this.name == null) {
			if (n1.name != null)
				return false;
		} else if (!(this.name == n1.name))
			return false;
		return true;
	}
	
}
