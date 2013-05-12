package Grafos;



import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JOptionPane;


public class Grafo {

	private Set<Arco> arcos;
	private Set<Nodo> nodos;
	private int numArcos;
	private int numNodos;
	
	public Grafo(){
		arcos = new HashSet<Arco>();
		nodos = new HashSet<Nodo>();
		numArcos = 0;
		numNodos = 0;
	}
	
	public Iterator<Arco> dameArcos(){
		return arcos.iterator();
	}
	
	public Iterator<Nodo> dameNodos(){
		return nodos.iterator();
	}
	
	public void crearNodo(int X, int Y, String Nombre, int ID){
		Nodo N = new Nodo(X,Y,Nombre,ID);
		nodos.add(N);
		numNodos++;
	}
	
	public void crearArco(int peso, Nodo orig, Nodo dest){
		Arco A = new Arco(orig,dest,peso);
		arcos.add(A);
		numArcos++;	
	}

	public void eliminarNodo(Nodo aux1){
		Iterator<Arco> itarco = this.dameArcos();
		while(itarco.hasNext()){
			Arco aux2 = itarco.next();
			if (aux2.getOrig() == aux1){ eliminarArco(aux1,aux2.getDest()); itarco = this.dameArcos();}
			else if (aux2.getDest() == aux1){ eliminarArco(aux2.getOrig(),aux1); itarco = this.dameArcos();}
		}
		Iterator<Nodo> itnodo2 = this.dameNodos();
		while (itnodo2.hasNext()){
			Nodo aux3 = itnodo2.next();
			if (aux3.getID() > aux1.getID()) aux3.setID(aux3.getID()-1);
		}
		this.nodos.remove(aux1);
		numNodos--;
	}
	
	public void eliminarArco(Nodo orig, Nodo dest){
		Iterator<Arco> itarco = this.dameArcos();
		Arco aux = new Arco(new Nodo(0,0,"Empty",0),new Nodo(0,0,"Empty",0),0);
		while(itarco.hasNext() && !(aux.getOrig().igual(orig) && aux.getDest().igual(dest))){
			aux = itarco.next();
		}
		this.arcos.remove(aux);
		numArcos--;	
	}

//recordar q en eliminar nodo hay q restarle uno a todos los id
	
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
	
	public void guardarGrafo(String NombreFich){
		File f = new File(NombreFich);
		try {
			FileWriter file = new FileWriter(f);
			PrintWriter out = new PrintWriter(file);
			Iterator<Nodo> nodo = this.dameNodos();
			Iterator<Arco> arco = this.dameArcos();
			out.println("Nodos");
			while (nodo.hasNext()){
				Nodo aux1 = nodo.next();
				out.println(aux1.getName());
				out.println(aux1.getID());
				out.println(aux1.getX());
				out.println(aux1.getY());
			}
			out.println("Arcos");
			while (arco.hasNext()){
				Arco aux2 = arco.next();
				out.println(aux2.getOrig().getID());
				out.println(aux2.getDest().getID());
				out.println(aux2.getPeso());
			}
			out.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "El archivo no se encuentra");
		}
	}
	
	
	public void cargarGrafo(File f) throws Exception{
		try {
			
			Scanner sc = new Scanner(f);
			String t = sc.nextLine();
			if (t.equals("Nodos")){
				String name = sc.next();
				while (sc.hasNext() && !(name.equals("Arcos"))){ 
						int id = sc.nextInt();
						int x = sc.nextInt();
						int y = sc.nextInt();
						this.crearNodo(x, y, name,id);
						name = sc.next();
				}
				while(sc.hasNext()){	
						int orig = sc.nextInt();
						int dest = sc.nextInt();
						int peso = sc.nextInt();
						Nodo aux1 = buscarNodo(orig);
						Nodo aux2 = buscarNodo(dest);
 						this.crearArco(peso,aux1,aux2);
				}
			
			}
			else{
				JOptionPane.showMessageDialog(null, "El archivo no concuerda el tipo");
				//throw Errortipo;
			}
			
		}catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "El archivo no se encuentra");
			
		}
		
		
	}
	
	
	public Nodo buscarNodo(int ID){
		Iterator<Nodo> nodo = this.dameNodos();
		while(nodo.hasNext()){
			Nodo aux = nodo.next();
			if (aux.getID() == ID){; return aux;}
		}
		return null;
	}
	
	
}
