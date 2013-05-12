package Grafos;


public class Arco implements Comparable<Arco>{

	Nodo orig;
	Nodo dest;
	int peso;
	
	public Arco(Nodo O, Nodo D, int P){
		this.dest = D;
		this.orig = O; 
		this.peso = P;
	}
	
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

	public int compareTo(Arco arg0) {
		if (this.peso > arg0.peso) return 1;
		else if (this.peso < arg0.peso) return -1;
		else return 0;
	}
	
	
	
}
