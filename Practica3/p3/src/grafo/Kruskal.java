package grafo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;

public class Kruskal {
	
	private Map<Nodo,Grupo> bosque;
	private ArrayList<Arco> solucion;
	private PriorityQueue<Arco> arcosPrioridad;
	private Grafo grafo;
	public HashSet<Arco> arcos;
	
	
	public Kruskal(HashSet<Arco> arcos){
		this.arcos = arcos;
	}
	
	public Kruskal(Grafo g){
		Comparator <Arco> comp = new Comparator<Arco>(){
		public int compare(Arco arc1, Arco arc2){
			if (arc1.getPeso() > arc2.getPeso()) return 1;
			if (arc1.getPeso() < arc2.getPeso()) return -1;
			return 0;
			}
		};
		if (g != null){
			this.grafo = g;
			bosque = new HashMap<Nodo,Grupo>();
			solucion = new ArrayList<Arco>();
			arcosPrioridad = new PriorityQueue<Arco>(1,comp);
			inicializarBosque();
			inicializarArcos();
			Arco aux;
			Nodo nO = null;
			Nodo nD = null;
			while (!arcosPrioridad.isEmpty() && !arbolExpansion()){//en los apuntes y bosque no sea bosque de expansión:¿?
				aux = arcosPrioridad.remove();
				nO = aux.getOrigen();
				nD = aux.getDestino();
				if (!bosque.get(nO).equals(bosque.get(nD))){
					bosque.get(nD).union(bosque.get(nO));
					solucion.add(aux);
				}
			}
			anotarResultadoKruskal();
		}
		else arcos = new HashSet<Arco>();
	}
	
	public boolean arbolExpansion(){
		return (solucion.size() == grafo.getNumNodos()-1);
	}
	
	public ArrayList<Arco> getSolucion() {
		return solucion;
	}

	public void setSolucion(ArrayList<Arco> solucion) {
		this.solucion = solucion;
	}

	public void inicializarBosque(){
		Iterator<Nodo> it = grafo.iteratorNodos();
		Nodo nodo;
		while (it.hasNext()){
			nodo = it.next();
			Grupo g = new Grupo();
			bosque.put(nodo,g);
		}
	}
	
	public void inicializarArcos(){
		Iterator<Arco> it = grafo.iteratorArcos();
		Arco arco;
		while (it.hasNext()){
			arco = it.next();
			arcosPrioridad.add(arco);
		}
	}
	
	public void anotarResultadoKruskal(){
		Iterator<Arco> it = solucion.iterator();
		arcos = new HashSet<Arco>();
		Arco arco;
		while(it.hasNext()){
			arco = it.next();
			if (arco != null)
				arcos.add(arco);
		}
	}
	
	public boolean equals(Object o){
		Kruskal c;
		if (!Kruskal.class.isInstance(o))
			return false;
		else 
		 c = (Kruskal) o;
		boolean b = comparar(c.arcos,this.arcos);
		return b;
	}
	
	public boolean comparar(HashSet<Arco> a,HashSet<Arco> b){
		if (a != null && b != null){
		Iterator<Arco> it = a.iterator();
		while (it.hasNext()){
			Arco arco = (Arco) it.next();
			if(!b.contains(arco)) return false; 
		}
		return true;
		}
		else return false;
	}

}
