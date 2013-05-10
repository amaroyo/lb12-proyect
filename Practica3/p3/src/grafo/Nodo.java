package grafo;

import java.awt.Color;

public class Nodo {
	
	private int x;
	private int y;
	private String nombre;
	private Color color;
	
	/******************************************************************************************************/
	public Nodo(int x, int y, String n){//Constructora con Color por defecto
		this.x = x;
		this.y = y;		
		this.nombre = n;
		this.color = Color.PINK;
	}
	
	public Nodo(int x, int y, String n, Color c){//Constructora
		this.x = x;
		this.y = y;		
		this.nombre = n;
		this.color = c;
	}
	
	public Nodo(Nodo n){//Constructora por copia
		this.x = n.getX();
		this.y = n.getY();
		this.color = n.getColor();
		this.nombre = n.getNombre();
	}
	
	/******************************************************************************************************/
										/*GETTERS AND SETTERS*/
	/******************************************************************************************************/
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
