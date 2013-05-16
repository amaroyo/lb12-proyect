package grafo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;

import junit.framework.TestCase;

public class DijkstraTester extends TestCase {
	  public DijkstraTester(String sTestName) {
	    super(sTestName);
	  }
	  


public void testDijkstraEjemplo(){
	Grafo g = new Grafo();
	Nodo n1 = new Nodo(100, 100, "n1");
	Nodo n2 = new Nodo(200, 200, "n2");
	Nodo n3 = new Nodo(300, 300, "n3");
	Nodo n4 = new Nodo(400, 400, "n4");
	Nodo n5 = new Nodo(500, 500, "n5");
	Nodo n6 = new Nodo(600, 600, "n6");
	g.anyadirNodo(n1);
	g.anyadirNodo(n2);
	g.anyadirNodo(n3);
	g.anyadirNodo(n4);
	g.anyadirNodo(n5);
	g.anyadirNodo(n6);
	Arco a1 = new Arco(n1, n2, 7);
	Arco a2 = new Arco(n1, n3, 3);
	Arco a3 = new Arco(n2, n3, 1);
	Arco a4 = new Arco(n2, n4, 6);
	Arco a5 = new Arco(n3, n5, 8);
	Arco a6 = new Arco(n4, n3, 3);
	Arco a7 = new Arco(n4, n6, 2);
	Arco a8 = new Arco(n5, n4, 3);
	Arco a9 = new Arco(n5, n6, 8);
	g.anyadirArco(a1);
	g.anyadirArco(a2);
	g.anyadirArco(a3);
	g.anyadirArco(a4);
	g.anyadirArco(a5);
	g.anyadirArco(a6);
	g.anyadirArco(a7);
	g.anyadirArco(a8);
	g.anyadirArco(a9);
	HashSet<Arco> arc = new HashSet<Arco>();
	arc.add(a1);
	arc.add(a4);
	arc.add(a7);
	
	Dijkstra mano = new Dijkstra(arc);
	Dijkstra maquina = new Dijkstra(g,n1,n6);
	this.assertEquals(mano,maquina);
}

public void testDijkstraVacio(){
	Grafo g = null;
	Nodo n1 = new Nodo(100, 100, "n1");
	Nodo n2 = new Nodo(200, 200, "n2");
	Dijkstra maquina = new Dijkstra(g,n1,n2);
	HashSet<Arco> arc = new HashSet<Arco>();
	Dijkstra mano = new Dijkstra(arc);
	this.assertEquals(mano,maquina);
}

public void testDijkstraSinUnir(){
	Grafo g = new Grafo();
	Nodo n1 = new Nodo(100, 100, "n1");
	Nodo n2 = new Nodo(200, 200, "n2");
	Dijkstra maquina = new Dijkstra(g,n1,n2);
	HashSet<Arco> arc = new HashSet<Arco>();
	Dijkstra mano = new Dijkstra(arc);
	this.assertEquals(mano,maquina);
}

//GRAFO BASICO 
public void testDijkstraComplicado(){
	//Creo dijkstra por el algoritmo;
	Grafo g = new Grafo();
	Nodo n1 = new Nodo(100, 100, "n1");
	Nodo n2 = new Nodo(200, 200, "n2");
	Nodo n3 = new Nodo(300, 300, "n3");
	Nodo n4 = new Nodo(400, 400, "n4");
	g.anyadirNodo(n1);
	g.anyadirNodo(n2);
	g.anyadirNodo(n3);
	g.anyadirNodo(n4);
	Arco a1 = new Arco(n1, n2, 3);
	Arco a2 = new Arco(n1, n3, 7);
	Arco a3 = new Arco(n2, n3, 3);
	Arco a4 = new Arco(n2, n4, 9);
	Arco a5 = new Arco(n3, n4, 3);
	g.anyadirArco(a1);
	g.anyadirArco(a2);
	g.anyadirArco(a3);
	g.anyadirArco(a4);
	g.anyadirArco(a5);
	HashSet<Arco> arc = new HashSet<Arco>();
	arc.add(a1);
	arc.add(a3);
	arc.add(a5);
	Dijkstra mano = new Dijkstra(arc);
	Dijkstra maquina = new Dijkstra(g,n1,n4);
	//Creo dijkstra con el resultado correcto
	this.assertEquals(mano,maquina);
	}
}
