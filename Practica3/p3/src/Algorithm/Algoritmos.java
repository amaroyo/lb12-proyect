package Algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import javax.swing.JOptionPane;

import Grafos.Arco;
import Grafos.Grafo;
import Grafos.Nodo;

public class Algoritmos {

	Grafo grafo;
	int mAdy[][];
	int mFloyd[][];
	ArrayList<Nodo> NodosAImprimir;
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
//DIJKSTRA
	
	public Algoritmos(Grafo g){
		this.grafo = g;
	}
	

public void inicMatrizAdy(){
	mAdy = new int [grafo.getNumNodos()+1][grafo.getNumNodos()+1];
	for (int i=0; i<=grafo.getNumNodos();i++){
		for (int j=0; j<=grafo.getNumNodos(); j++){
			if (i == j) mAdy[i][j]= 0;
			else mAdy[i][j]= Integer.MAX_VALUE;
		}
	}
}	
	
public void inicDijkstra(Map<Nodo,Integer> camino){
	inicMatrizAdy();
	Iterator<Arco> itarco = grafo.dameArcos();
	while(itarco.hasNext()){
		Arco arcux = itarco.next();
		 //Grafo empieza desde cero
		mAdy[arcux.getOrig().getID()-1][arcux.getDest().getID()-1] = arcux.getPeso();                                     
	}
	Iterator<Nodo> itnodo = grafo.dameNodos();
	while(itnodo.hasNext()){
		Nodo naux = itnodo.next();
		 //Grafo empieza desde cero
		camino.put(naux,Integer.MAX_VALUE);                                    
	}
}

public Map<Nodo,Nodo> getDijkstra(Nodo inic) {
	final Map<Nodo,Integer> camino = new HashMap<Nodo, Integer>();;
	Set<Nodo> yaVisitados = new HashSet<Nodo>();
	Map<Nodo,Nodo> ruta = new HashMap<Nodo, Nodo>();
	Comparator<Nodo> myComp = new Comparator<Nodo>(){
		public int compare(Nodo n1, Nodo n2) {
			if (camino.get(n1) > camino.get(n2))  return +1;
			else if (camino.get(n1) < camino.get(n2))   return -1;
			else  return 0;
			}
		};
	PriorityQueue<Nodo> aVisitar = new PriorityQueue<Nodo>(8, myComp);
	
	inicDijkstra(camino);
	yaVisitados.clear();
	
	camino.put(inic, 0);
	aVisitar.add(inic);
		while (!aVisitar.isEmpty()){
			Nodo node = aVisitar.poll(); //accede a la prioridad minima
			yaVisitados.add(node);
			relajarVertices(node, camino, yaVisitados, aVisitar, ruta);
		}
		
	return ruta;	
}

public void relajarVertices(Nodo node, Map<Nodo,Integer> camino, Set<Nodo> yaVisitados, PriorityQueue<Nodo> aVisitar , Map<Nodo,Nodo> ruta) {

	Iterator<Nodo> itnodo = grafo.dameNodos();

	while(itnodo.hasNext()){
		Nodo target = itnodo.next();
		if(node != target && mAdy[node.getID()-1][target.getID()-1] != Integer.MAX_VALUE && !(yaVisitados.contains(target))){
			if (camino.get(target) > camino.get(node) + mAdy[node.getID()-1][target.getID()-1]) {
				camino.put(target, camino.get(node) + mAdy[node.getID()-1][target.getID()-1]);
				ruta.put(target, node);
				aVisitar.remove(target);
				aVisitar.add(target);
			}
		}

	}

}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//   Floyd


public void inicFloyd(){
	inicMatrizAdy();
	mFloyd = new int [grafo.getNumNodos()+1][grafo.getNumNodos()+1];
	for (int i = 0; i <= grafo.getNumNodos(); i++){ 
		for (int j = 0; j <= grafo.getNumNodos(); j++){
			mFloyd[i][j] = 0;
		}
	}
	mAdy = new int [grafo.getNumNodos()+1][grafo.getNumNodos()+1];
	for (int i=0; i<=grafo.getNumNodos();i++){
		for (int j=0; j<=grafo.getNumNodos(); j++){
			if (i == j) mAdy[i][j]= 0;
			else mAdy[i][j]= 10000;
		}
	}
	Iterator<Arco> itarco = grafo.dameArcos();
	while(itarco.hasNext()){
		Arco arcux = itarco.next();
		mAdy[arcux.getOrig().getID()][arcux.getDest().getID()] = arcux.getPeso();                                     
	}
}

public void getFloyd(){
	inicFloyd();
	for (int k = 1; k <= grafo.getNumNodos(); k++){
		for (int i = 1; i <= grafo.getNumNodos(); i++){ 
			for (int j = 1; j <= grafo.getNumNodos(); j++) 
			{	  
					int aux = this.mAdy[i][k] + this.mAdy[k][j];
					if(aux < this.mAdy[i][j])
					{
						this.mAdy[i][j] = aux;
						this.mFloyd[i][j] = k;
						}
			}
		}
}

}

public ArrayList<Nodo> reconstruyeFloyd(Nodo Orig, Nodo Dest){
	NodosAImprimir = new ArrayList<Nodo>();	
	int aux = mAdy[Orig.getID()][Dest.getID()];
	if(aux != 10000){
			NodosAImprimir.add(Orig);
			reconstruyeFloydAux(Orig,Dest);
			NodosAImprimir.add(Dest);
	}
	else return null;
	return NodosAImprimir;
}


public void reconstruyeFloydAux(Nodo Orig, Nodo Dest){
	int aux = mFloyd[Orig.getID()][Dest.getID()];
	String o = Orig.getName();
	String d = Dest.getName();
	if (aux != 0){
		reconstruyeFloydAux(Orig,grafo.buscarNodo(aux));
		NodosAImprimir.add(grafo.buscarNodo(aux));
		reconstruyeFloydAux(grafo.buscarNodo(aux),Dest);
	}
	
}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///// Kruskal

public Set<Arco> getKruskal(){
       HashMap<Nodo,Grupo> tabla=new HashMap<Nodo, Grupo>();
       Comparator<Arco> myCompArco = new Comparator<Arco>(){
   		public int compare(Arco a1, Arco a2) {
   			if (a1.getPeso() > a2.getPeso())  return +1;
   			else if (a1.getPeso()< a2.getPeso())   return -1;
   			else  return 0;
   			}
   		};
   	   PriorityQueue<Arco> pq = new PriorityQueue<Arco>(8,myCompArco);
       for(int i=0;i<grafo.getNumNodos();i++){
           tabla.put(grafo.buscarNodo(i+1),new Grupo());
       }
       Iterator<Arco> itarco = grafo.dameArcos();
       while (itarco.hasNext()){
    	 Arco aux = itarco.next();
    	 pq.add(aux);
       }   
       Set<Arco> solK = new HashSet<Arco>();
       
       while(!pq.isEmpty()){
           Arco ar = pq.poll();
           Nodo n1 = ar.getOrig();
           Nodo n2 = ar.getDest();
           Grupo g1 = tabla.get(n1);
           Grupo g2 = tabla.get(n2);
           if(g1.dameGrupo()!= g2.dameGrupo()){
               g1.union(g2);
               solK.add(ar);
           }
       }
       return solK;
   }

}
	

