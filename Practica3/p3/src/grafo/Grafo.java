package grafo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

import grafo.Arco;
import grafo.Nodo;

public class Grafo {
	private Set<Arco> arcos;
	private Set<Nodo> nodos;
	private int numArcos;
	private int numNodos;	
	private static final Integer MAX_INT = 999999999;
	
	public Grafo(){
		arcos= new HashSet<Arco>();
		nodos= new HashSet<Nodo>();
		numArcos=0;
		numNodos=0;		
	}
	
	public void inicializar(File f) throws FileNotFoundException{
		arcos= new HashSet<Arco>();
		nodos= new HashSet<Nodo>();
		numNodos = 0;
		numArcos = 0;
		
		Scanner sc = new Scanner(f);
		if (sc.hasNext()){	
			String linea = sc.nextLine();
			Scanner sl = new Scanner(linea);
			while(sl.hasNext()){
				String nombre = sl.next();
				int x=Integer.parseInt(sl.next()); 
				int y=Integer.parseInt(sl.next()); 
				crearNodo(x,y,nombre);
			}
		}
		while (sc.hasNext()){	
			String nombreO = sc.next();
			String nombreD =sc.next();
			int peso=Integer.parseInt(sc.next());
			Nodo o= buscaNodo(nombreO);
			Nodo d= buscaNodo (nombreD);
			crearArco(o,d,peso);
		}
	}

	public Set<Arco> getArcos() {
		return arcos;
	}

	public void setArcos(Set<Arco> arcos) {
		this.arcos = arcos;
	}

	public Set<Nodo> getNodos() {
		return nodos;
	}

	public void setNodos(Set<Nodo> nodos) {
		this.nodos = nodos;
	}

	public int getNumArcos() {
		return numArcos;
	}

	public void setNumArcos(int numArcos) {
		this.numArcos = numArcos;
	}

	public int getNumNodos() {
		return numNodos;
	}

	public void setNumNodos(int numNodos) {
		this.numNodos = numNodos;
	}
	
	public Iterator<Arco> iteratorArcos(){
		return arcos.iterator();
	} 
	
	public Iterator<Nodo> iteratorNodos(){
		return nodos.iterator();
	}
	
	public void crearNodo(int x,int y,String nombre){
		Nodo nodo = new Nodo(x,y,nombre);
		nodos.add(nodo);
		numNodos++;	
	}
	
	public void anyadirNodo(Nodo nodo){
		nodos.add(nodo);
		numNodos++;
	}
	
	public void anyadirArco(Arco arco){
		arcos.add(arco);
		numArcos++;
	}
	
	public void crearArco(Nodo origen,Nodo destino,int peso){
		Arco arco = new Arco(origen, destino,peso);
		arcos.add(arco);
		numArcos++;
	}
	
	public void borrarArco(Nodo origen,Nodo destino){
		Iterator<Arco> it = iteratorArcos();
		boolean exito = false;
		while (it.hasNext() && exito == false){	
			Arco arco=(Arco) it.next();
			if (arco.getOrigen().getNombre().equals(origen.getNombre())
					&& arco.getDestino().getNombre().equals(destino.getNombre())
					&& arco.getOrigen().getX() == origen.getX()
					&& arco.getOrigen().getY() == origen.getY()
					&& arco.getDestino().getX() == destino.getX()
					&& arco.getDestino().getY() == destino.getY()){
				exito=true;
				arcos.remove(arco);
				numArcos --;
			}
		}
	}
	
	public void borrarArco(Nodo nodo){
		ArrayList<Arco> aux = new ArrayList<Arco>();
		int a = 0;
		Iterator<Arco> it = iteratorArcos();
		while (it.hasNext()){	
			Arco arco=(Arco) it.next();
			if (arco.getOrigen() == nodo
				|| arco.getDestino() == nodo){
					aux.add(arco);
					a++;
			}
		}
			for(int i=0;i<a;i++){
				Arco arc = (Arco) aux.get(i);
				arcos.remove(arc);
				numArcos--;				
			}
		
	}
	
	public void borrarNodo(Nodo nodo){
		Iterator<Nodo> it = iteratorNodos();
		boolean exito = false;
		while (it.hasNext() && exito == false){
			Nodo nod = (Nodo) it.next();
			if (nod.getNombre().equals(nodo.getNombre()) 
					&& nod.getX() == nodo.getX()
					&& nod.getY() == nodo.getY()){
				borrarArco(nod);
				nodos.remove(nod);
				numNodos --;				
				exito = true;
			}
		}
	}

	public Arco existeArco(Nodo origen,Nodo destino){
		Iterator<Arco> iterator = iteratorArcos();
		Arco arco;
		while (iterator.hasNext()){
			arco = iterator.next();
			if (arco.getOrigen() == origen && arco.getDestino() == destino) 
				return arco;
		}
		return null;
	}
	
	public Nodo buscaNodo(String nom){
		Nodo nodo;
		Iterator<Nodo> it = iteratorNodos();
		while (it.hasNext()){
			nodo = it.next();
			String aux = nodo.getNombre();
			if (aux.equals(nom))
				return nodo;
		}
		return null;
	}
	
	public Nodo existeNodo(int x,int y){
		Nodo nodo;
		int x1,y1;
		Iterator<Nodo> it = iteratorNodos();
		while (it.hasNext()){
			nodo = it.next();
			x1 = nodo.getX();
			y1 = nodo.getY();
			if ( x > (x1-20) && x < (x1+20) &&  y > (y1-20) && y < (y1+20)){
				return nodo;
			}
		}
		return null;
	}

	public Nodo nodoPos(int pos){
		Iterator<Nodo> it = iteratorNodos();
		int i=0;
		while(it.hasNext()){
			Nodo nodo = it.next();
			if (i==pos)
				return nodo;
			i++;
		}
		return null;
	}
	
	public int posNodo(Nodo nodo){
		Iterator<Nodo> it = iteratorNodos();
		int i=0;
		while(it.hasNext()){
			Nodo aux = it.next();
			if (aux.equals(nodo))
				return i;
			i++;
		}
		return MAX_INT;
	}
	
	public void modificaArco(Arco arco){
		arcos.remove(arco);
		arco.setPintar(true);
		arcos.add(arco);
	}
	
	
	
	
		

}
