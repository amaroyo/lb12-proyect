package algortimos;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Dijkstra {

	private static int MAX_NODOS = 20;
	private Set<Vertice> yaVisitados;
	private PriorityQueue<Vertice> aVisitar;
	private Comparator<Vertice> miComparador;
	private Map<Vertice,Integer> caminos;
	private Map<Vertice,Vertice> rutas;
	
	/******************************************************************************************************/
	public Dijkstra(){
		caminos = new HashMap<Vertice,Integer>();
		rutas = new HashMap<Vertice,Vertice>();
		yaVisitados = new HashSet<Vertice>();
		miComparador = new Comparator<Vertice>(){
			@Override
			public int compare(Vertice n1, Vertice n2) {
				return 0;
			}
			
		};
		
		aVisitar = new PriorityQueue<Vertice>(MAX_NODOS,miComparador);
		
	}
	/******************************************************************************************************/
	public void Dijkstra(Vertice ini){
		while(!aVisitar.isEmpty()){
			Vertice u = extraerMinimo(aVisitar);
			yaVisitados.add(u);
			relajarVertices(u);
		}
	}
	
	/******************************************************************************************************/
	private Vertice extraerMinimo(PriorityQueue<Vertice> pq) {
		
		return null;
	}
	
	/******************************************************************************************************/
	public void relajarVertices(Vertice u){
		Vertice v = null;
		Integer aux = 0;
		//Para cada vertice v adyacente a u que no pertenezca a yaVisitados
		if (caminos.get(v)>caminos.get(u)+distanciaDirecta(u,v)){
			aux = caminos.get(u)+distanciaDirecta(u,v);
			caminos.put(u, aux);
		}
	}
	
	/******************************************************************************************************/
	private Integer distanciaDirecta(Vertice u, Vertice v) {
		// Calcular la distancia entre ambos
		return null;
	}
}
