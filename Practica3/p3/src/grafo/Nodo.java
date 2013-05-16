package grafo;

import java.awt.Graphics;

public class Nodo {
	private int x;
	private int y;
	private String nombre;
	
	public Nodo(int x,int y,String nombre){
		this.x = x;
		this.y = y;
		this.nombre = nombre;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void dibujar(Graphics g){
		g.drawOval(x, y, 5, 5);
		g.drawString(nombre,x,y);
	}
	
	public boolean equals(Object o){
		if (o == this) 
			return true;
		if (o == null) 
			return false;		
		if (getClass() != o.getClass()) 
			return false;
		Nodo nodo = (Nodo) o;
		return (this.nombre.equals(nodo.nombre));
	}



	
}
