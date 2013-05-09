package grafo;


import java.awt.Color;

public class Arco implements Comparable<Arco> {

	private Nodo orig;
	private Nodo dest;
	private int peso;
	private Color color;
	
	/******************************************************************************************************/
	public Arco(Nodo n1, Nodo n2, int p) {
		orig = n1;
		dest = n2;
		peso = p;
		color = Color.ORANGE;
	}
	
	/******************************************************************************************************/
	public Arco(Nodo n1, Nodo n2, int p, Color c) {
		orig = n1;
		dest = n2;
		peso = p;
		color = c;
	}
	
	/******************************************************************************************************/
	@Override
	public int compareTo(Arco a) {
		if (this.peso>a.peso) return 1;
		else if (this.peso == a.peso) return 0;
		else return -1;
	}
	
	/******************************************************************************************************/
										/*GETTERS AND SETTERS*/
	/******************************************************************************************************/
	public Nodo getOrig() {
		return orig;
	}

	public void setOrig(Nodo orig) {
		this.orig = orig;
	}

	public Nodo getDest() {
		return dest;
	}

	public void setDest(Nodo dest) {
		this.dest = dest;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
}
