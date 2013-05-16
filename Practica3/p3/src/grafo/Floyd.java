package grafo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class Floyd {
		private int[][] matrizCamino;
		private Nodo[][] matrizRuta;
		private Grafo grafo;
		public HashSet<Arco> arcos;
		private static final Integer MAX_INT = 999999999;
		
		public Floyd(HashSet<Arco> arcos){
			this.arcos = arcos;
		}
		public Floyd(Grafo g,Nodo origen, Nodo destino){
			if (g != null && g.getNumNodos()>0){
				grafo = g;
				matrizCamino = new int[grafo.getNumNodos()][grafo.getNumNodos()];
				inicializarMatrizCamino();
				matrizRuta = new Nodo[grafo.getNumNodos()][grafo.getNumNodos()];
				inicializarMatrizRuta();
				for (int k=0; k<grafo.getNumNodos();k++){
					for (int i=0; i<grafo.getNumNodos();i++){
						for (int j=0; j<grafo.getNumNodos();j++){
							int suma = matrizCamino[i][k]+matrizCamino[k][j];
							if(suma < matrizCamino[i][j]){
								matrizCamino[i][j]=suma;
								matrizRuta[i][j]= grafo.nodoPos(k);
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
			for (int i=0; i<grafo.getNumNodos();i++){   
					for (int j=0; j<grafo.getNumNodos();j++)
						matrizRuta[i][j]=null;
			}
		}
		
		
		
		public void inicializarMatrizCamino(){
				matrizCamino = new int[grafo.getNumNodos()][grafo.getNumNodos()];
				Iterator<Nodo> it1 = grafo.iteratorNodos();
				for (int i=0; i<grafo.getNumNodos();i++){   
					Nodo nodo=it1.next();
					Iterator<Nodo> it2 = grafo.iteratorNodos();
					for (int j=0; j<grafo.getNumNodos();j++){	
						Nodo nodo2 = it2.next();
						Arco arco = grafo.existeArco(nodo, nodo2);
						if (arco != null)
							matrizCamino[i][j] = arco.getPeso();
						else if (nodo.equals(nodo2)) 
							matrizCamino[i][j] = 0;
						else matrizCamino[i][j] = MAX_INT;
						}
				}
		}
		
		public void anotarResultadoFloyd(Nodo origen, Nodo destino){
			if(matrizRuta[grafo.posNodo(origen)][grafo.posNodo(destino)] == null ){ 
				Arco arco= grafo.existeArco(origen,destino);
				if (arco != null)
					arcos.add(arco);
				}
				else {    
				     Nodo nodo = grafo.nodoPos(grafo.posNodo(matrizRuta[grafo.posNodo(origen)][grafo.posNodo(destino)]));
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