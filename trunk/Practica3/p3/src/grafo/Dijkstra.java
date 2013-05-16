package grafo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;

public class Dijkstra {
	private HashMap<Nodo,Integer> camino;
	private HashMap<Nodo,Nodo> ruta;
	private ArrayList<Nodo> yaVisitados;
	private PriorityQueue<Nodo> aVisitar;
	private Grafo grafo;
	public HashSet<Arco> arcos;
	private static final Integer MAX_INT = 999999999;
	
	
	public Dijkstra(HashSet<Arco> arcos){
		this.arcos = arcos;
	}
	
	public Dijkstra(Grafo g, Nodo origen, Nodo destino){
		Comparator <Nodo> comp = new Comparator<Nodo>(){
			public int compare(Nodo nd1, Nodo nd2){
				if (camino.get(nd1) >camino.get(nd2)) return 1;
				if (camino.get(nd1)<camino.get(nd2)) return -1;
				return 0;
				}
		};
		if (g != null){
			this.grafo = g;
			camino = new HashMap<Nodo,Integer>();
			ruta = new HashMap<Nodo,Nodo>();
			yaVisitados = new ArrayList<Nodo>();
			aVisitar = new PriorityQueue<Nodo>(1,comp);
			inicializarCamino();
			camino.remove(origen);
			camino.put(origen, 0);
			inicializarRuta();		
			aVisitar.add(origen);
			Nodo aux;
			while (!aVisitar.isEmpty()){
				aux = aVisitar.remove();
				yaVisitados.add(aux);
				relajarVertice(aux);
			}
		anotarResultadoDijkstra(origen, destino);
		}
		else {arcos = new HashSet<Arco>();}
				
	}
	
	
	public void inicializarCamino(){
		Iterator<Nodo> it = grafo.iteratorNodos();
		Nodo nodo;
		while (it.hasNext()){
			nodo = it.next();
			camino.put(nodo, MAX_INT);
		}
	
	}
	
	public void inicializarRuta(){
		Iterator<Nodo> it = grafo.iteratorNodos();
		Nodo nodo;
		while (it.hasNext()){
			nodo = it.next();
			ruta.put(nodo,null);
		}
	}
	
	public void relajarVertice(Nodo nodo){
		
		Iterator<Nodo> it = grafo.iteratorNodos();
		Nodo nodo2;
		Arco arco;
		while (it.hasNext()){
			nodo2 = it.next();
			arco = grafo.existeArco(nodo,nodo2);
			if (arco != null){
				if (!estaVisitado(yaVisitados,nodo2))
					aVisitar.add(nodo2);
					if (camino.get(nodo2) > camino.get(nodo) + arco.getPeso()){
						camino.remove(nodo2);
						camino.put(nodo2,arco.getPeso()+camino.get(nodo));
						aVisitar.remove(nodo2);
						aVisitar.add(nodo2);
						ruta.remove(nodo2);
						ruta.put(nodo2, nodo);
					}
			}
		}	
	}
	
	public void anotarResultadoDijkstra(Nodo origen, Nodo destino){
		arcos = new HashSet<Arco>();
		Arco arco;
		Nodo aux = destino;
		Nodo aux2 = ruta.get(aux);
		while (aux2 != null){
			arco = grafo.existeArco(aux2, aux);
			if (arco != null)
				arcos.add(arco);
			aux = aux2;	
			aux2 = ruta.get(aux);
		}
	}
	
	public boolean estaVisitado(ArrayList<Nodo> listaNodos,Nodo nodo){
		if (listaNodos.contains(nodo)) return true;
		return false;
	}


	public HashMap<Nodo, Nodo> getRuta() {
		return ruta;
	}


	public void setRuta(HashMap<Nodo, Nodo> ruta) {
		this.ruta = ruta;
	}
		
	public boolean equals(Object o){
		Dijkstra c;
		if (!Dijkstra.class.isInstance(o))
			return false;
		else 
		 c = (Dijkstra) o;
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
