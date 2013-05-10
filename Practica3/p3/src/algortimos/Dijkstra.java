package algortimos;

import grafo.Nodo;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Dijkstra {

	private static int MAX_NODOS = 20;
	private Set<Nodo> yaVisitados;
	private PriorityQueue<Nodo> aVisitar;
	private Comparator<Nodo> miComparador;
	private Map<Nodo,Integer> caminos;
	private Map<Nodo,Nodo> rutas;
	
	/******************************************************************************************************/
	public Dijkstra(){
		caminos = new HashMap<Nodo,Integer>();
		rutas = new HashMap<Nodo,Nodo>();
		yaVisitados = new HashSet<Nodo>();
		miComparador = new Comparator<Nodo>(){
			@Override
			public int compare(Nodo n1, Nodo n2) {
				return 0;
			}
			
		};
		
		aVisitar = new PriorityQueue<Nodo>(MAX_NODOS,miComparador);
		
	}
	/******************************************************************************************************/
	public void Dijkstra(Nodo ini){
		while(!aVisitar.isEmpty()){
			Nodo u = extraerMinimo(aVisitar);
			yaVisitados.add(u);
			relajarVertices(u);
		}
	}
	
	/******************************************************************************************************/
	private Nodo extraerMinimo(PriorityQueue<Nodo> pq) {
		
		return null;
	}
	
	/******************************************************************************************************/
	public void relajarVertices(Nodo u){
		Nodo v = null;
		Integer aux = 0;
		//Para cada vertice v adyacente a u que no pertenezca a yaVisitados
		if (caminos.get(v)>caminos.get(u)+distanciaDirecta(u,v)){
			aux = caminos.get(u)+distanciaDirecta(u,v);
			caminos.put(u, aux);
		}
	}
	
	/******************************************************************************************************/
	private Integer distanciaDirecta(Nodo u, Nodo v) {
		// Calcular la distancia entre ambos
		return null;
	}
}
