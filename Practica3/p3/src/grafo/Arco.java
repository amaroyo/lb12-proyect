package grafo;

import java.awt.Color;
import java.awt.Graphics;

public class Arco {
	private Nodo origen;
	private Nodo destino;
	private int peso;
	private boolean pintar;
	
	public Arco(Nodo origen, Nodo destino, int peso){
		this.origen = origen;
		this.destino = destino;
		this.peso = peso;
		this.pintar = false;
	}

	public boolean isPintar() {
		return pintar;
	}

	public void setPintar(boolean pintar) {
		this.pintar = pintar;
	}

	public Nodo getOrigen() {
		return origen;
	}

	public void setOrigen(Nodo origen) {
		this.origen = origen;
	}

	public Nodo getDestino() {
		return destino;
	}

	public void setDestino(Nodo destino) {
		this.destino = destino;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}
	
	public void dibujar(Graphics g){
		int x1= origen.getX();
		int x2= destino.getX();
		int y1= origen.getY();
		int y2= destino.getY();
		Color color = null;
		if (pintar == true){
			g.setColor(color.red);
			g.drawLine(x1,y1,x2,y2);
			g.drawString(Integer.toString(peso),(x1+x2)/2,(y1+y2)/2);
			pintar = false;
		}
		else {
			g.drawLine(x1,y1,x2,y2);
			g.drawString(Integer.toString(peso),(x1+x2)/2,(y1+y2)/2);
		}
		g.setColor(color.black);
	}
		



}



