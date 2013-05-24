package grafo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class Floyd {
	
	
	/* 
	 * Dado un grafo dirigido con matriz de adyacencia determina la longitud del camino minimo para cada par de vertices
	 * En nuestro caso, marcamos un vertice fin
	 * 
	 * Dividir el problema en subproblemas pequeños (memorizacion)
	 * Resolver estos problemas de manera optima
	 * Utilizar las soluciones para una optima global
	 * 
	 * */
		private int[][] camino_min;
		private Nodo[][] matrizAuxRutas;
		private Grafo grafo;
		public HashSet<Arco> arcos;
		private static final Integer MAX_INT = 999999999;
		
		public Floyd(HashSet<Arco> arcos){
			this.arcos = arcos;
		}
		public Floyd(Grafo g,Nodo origen, Nodo destino){
			if (g != null && g.getNumNodos()>0){
				grafo = g;
				camino_min = new int[grafo.getNumNodos()][grafo.getNumNodos()];
				inicializarMatrizCamino();
				matrizAuxRutas = new Nodo[grafo.getNumNodos()][grafo.getNumNodos()];
				inicializarMatrizRuta();
				//Programacion dinamica, caso base simple
				// y luego llamadas recursivas de tamanyo menor que el original
				for (int k=0; k<grafo.getNumNodos();k++){
					for (int i=0; i<grafo.getNumNodos();i++){
						for (int j=0; j<grafo.getNumNodos();j++){
							// Calculamos el valor del camino de i a j pasando por k
							int suma = camino_min[i][k]+camino_min[k][j];
							//Comparamos si es mejor que ir de i a j sin pasar por k
							if(suma < camino_min[i][j]){
								camino_min[i][j]=suma;//Si es mejor pasar por k
								matrizAuxRutas[i][j]= grafo.nodoPos(k);//Anotamos el nodo k por el que hemos pasado para ir de i a j
							}
	                    }
	                }
	            }
				arcos = new HashSet<Arco>();
				anotarResultadoFloyd(origen,destino);
	        }
			else arcos = new HashSet<Arco>();
		}
		
        
			
		public void inicializarMatrizRuta(){
			//Inicialmente la matriz auxiliar esta a null ya que no hemos tomado ningun camino aun
			for (int i=0; i<grafo.getNumNodos();i++){   
					for (int j=0; j<grafo.getNumNodos();j++)
						matrizAuxRutas[i][j]=null;
			}
		}
		
		
		
		public void inicializarMatrizCamino(){
			//Inicializado camino minimo con las distancias directas
				camino_min = new int[grafo.getNumNodos()][grafo.getNumNodos()];
				Iterator<Nodo> it1 = grafo.iteratorNodos();
				for (int i=0; i<grafo.getNumNodos();i++){   
					Nodo nodo=it1.next();
					Iterator<Nodo> it2 = grafo.iteratorNodos();
					for (int j=0; j<grafo.getNumNodos();j++){	
						Nodo nodo2 = it2.next();
						Arco arco = grafo.existeArco(nodo, nodo2);
						if (arco != null)
							camino_min[i][j] = arco.getPeso();
						else if (nodo.equals(nodo2)) 
							camino_min[i][j] = 0;
						else camino_min[i][j] = MAX_INT;
						}
				}
		}
		
		public void anotarResultadoFloyd(Nodo origen, Nodo destino){
			if(matrizAuxRutas[grafo.posNodo(origen)][grafo.posNodo(destino)] == null ){ 
				Arco arco= grafo.existeArco(origen,destino);
				if (arco != null)
					arcos.add(arco);
				}
				else {    
				     Nodo nodo = grafo.nodoPos(grafo.posNodo(matrizAuxRutas[grafo.posNodo(origen)][grafo.posNodo(destino)]));
				     anotarResultadoFloyd(origen,nodo);
				     anotarResultadoFloyd(nodo,destino);
				     }  
	   }


		public HashSet<Arco> getArcos() {
			return arcos;
		}


		public void setArcos(HashSet<Arco> arcos) {
			this.arcos = arcos;
		}
		
		public boolean equals(Object o){
			Floyd c;
			if (!Floyd.class.isInstance(o))
				return false;
			else 
			 c = (Floyd) o;
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