package interfaz;

import java.awt.Color;

public class Arco implements Comparable<Arco> {

	private Nodo orig;
	private Nodo dest;
	private int peso;
	private Color color;
	
	/******************************************************************************************************/
	@Override
	public int compareTo(Arco a) {
		if (this.peso>a.peso) return 1;
		else if (this.peso == a.peso) return 0;
		else return -1;
	}
	
}
