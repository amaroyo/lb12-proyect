package grafo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;

public class Kruskal {
	
	/*
	 * Arbol de expansion minimo
	 * Con aristas no dirigidas
	 * Transformamos el grafo en un arbol minimo sin ciclos. Sus ramas tienen peso minimo
	 * Tienen que contener todos los vertices a menos que no esten conectados
	 * 
	 */
	
	
	private Map<Nodo,Grupo> bosqueF;
	private HashSet<Arco> solucion;
	private PriorityQueue<Arco> conjuntoS;
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
			
			bosqueF = new HashMap<Nodo,Grupo>();
			solucion = new HashSet<Arco>();
			conjuntoS = new PriorityQueue<Arco>(1,comp);
			//1. Crear un bosque (cjto de arboles. t.q. cada vertice es un arbol separado)
			inicializarBosque();
			//2. Creamos un cjto S con todas las aristas
			inicializarArcos();
			Arco aux;
			Nodo nO = null;
			Nodo nD = null;
			//mientras S no es vacio y F no es un arbol de expansion
			while (!conjuntoS.isEmpty() && !arbolExpansion()){
				//tomamos la arista con minimo peso de S
				aux = conjuntoS.remove();
				nO = aux.getOrigen();
				nD = aux.getDestino();
				//si la arista conecta a dos arboles de F distintos
				if (!bosqueF.get(nO).equals(bosqueF.get(nD))){
					//combinamos en un solo arbol y tomamos la arista
					bosqueF.get(nD).union(bosqueF.get(nO));
					//anyado la arista a las soluciones
					solucion.add(aux);
					
				}
				// si no descartamos la arista
			}
			anotarResultadoKruskal();
		}
		else arcos = new HashSet<Arco>();
	}
	
	public boolean arbolExpansion(){
		return (solucion.size() == grafo.getNumNodos()-1);
	}
	
	public HashSet<Arco> getSolucion() {
		return solucion;
	}

	public void setSolucion(HashSet<Arco> solucion) {
		this.solucion = solucion;
	}

	public void inicializarBosque(){
		Iterator<Nodo> it = grafo.iteratorNodos();
		Nodo nodo;
		while (it.hasNext()){
			nodo = it.next();
			Grupo g = new Grupo();
			bosqueF.put(nodo,g);
		}
	}
	
	public void inicializarArcos(){
		Iterator<Arco> it = grafo.iteratorArcos();
		Arco arco;
		while (it.hasNext()){
			arco = it.next();
			conjuntoS.add(arco);
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
