package grafo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

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
		
		Graphics2D g2D=(Graphics2D)g;
		g2D.setStroke (new BasicStroke(10f));
		g2D.setColor(new Color(141, 0, 141));
		g2D.fillOval(x, y, 20, 20);
		g2D.drawString(nombre,x,y); 
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
