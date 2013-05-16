package interfaz;

import grafo.Dijkstra;
import grafo.Floyd;
import grafo.Grafo;
import grafo.Grupo;
import grafo.Kruskal;
import grafo.Nodo;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;

import javax.swing.JOptionPane;

import grafo.Arco;


public class TableroCanvas extends Canvas implements MouseListener {
	private static final Integer MAX_INT = 999999999;
	private Grafo grafo;
	private Interfaz interfaz;
	private boolean primerClick;
	private boolean segundoClick;
	private Nodo primerNodo;
	//Dijkstra
	private Map<Nodo,Integer> camino;
	private Map<Nodo,Nodo> ruta;
	private ArrayList<Nodo> yaVisitados;
	private PriorityQueue<Nodo> aVisitar;
	//Kruskal
	private Map<Nodo,Grupo> bosque;
	private ArrayList<Arco> solucion;
	private PriorityQueue<Arco> arcosPrioridad;
	//Floyd
	private HashMap<Nodo,HashMap<Nodo,Integer>> matrizCamino;
	private HashMap<Nodo,HashMap<Nodo,Nodo>> matrizRuta;
	private HashSet<Arco> solucionFloyd;
	
	public TableroCanvas(Grafo grafo, Interfaz interfaz)
	{
		this.grafo=grafo;
		this.interfaz=interfaz;
		primerClick=true;
		segundoClick=false;
	}
	
//METODOS PARA DIBUJAR************************************		
	public void paint(Graphics g){
		Iterator<Nodo> itNodos = grafo.iteratorNodos();
		Iterator<Arco> itArcos = grafo.iteratorArcos();
		while (itNodos.hasNext()){
			Nodo nodo = itNodos.next();
			nodo.dibujar(g);
		}
		while (itArcos.hasNext()){
			Arco arco = itArcos.next();
			arco.dibujar(g);
		}		
	}
	
	public void pintar(){
		repaint();
	}
	
	public void dibujarArco(Arco arco,Graphics g){
		int x1= arco.getOrigen().getX();
		int x2= arco.getDestino().getX();
		int y1= arco.getOrigen().getY();
		int y2= arco.getDestino().getY();
		Color color = null;
		if (arco.isPintar()){
			g.setColor(color.red);
			g.drawLine(x1,y1,x2,y2);
			g.drawString(Integer.toString(arco.getPeso()),(x1+x2)/2,(y1+y2)/2);
			arco.setPintar(false);
		}
		else {
			g.drawLine(x1,y1,x2,y2);
			g.drawString(Integer.toString(arco.getPeso()),(x1+x2)/2,(y1+y2)/2);
		}
		g.setColor(color.black);
	}
	
	public void dibujarNodo(Nodo nodo, Graphics g){
		int x = nodo.getX();
		int y = nodo.getY();
		String nombre = nodo.getNombre();
		g.drawOval(x, y, 10, 10);
		g.drawString(nombre,x,y);
	}
	
//METODOS 	

//METODOS DE EVENTOS DE RATON**********************************	

	@Override
	public void mousePressed(MouseEvent e) {
		int posX = e.getX();
		int posY = e.getY();
		//Si es crear nodo.
		if (interfaz.isRepintar()){
			repaint();
			interfaz.setRepintar(false);
		}
		else if (interfaz.isCrearNodoBool()){
			if (grafo.buscaNodo(interfaz.getNombreNodo()) == null){
			/*Ya ha comprobado si el nombre existe o no por lo que sabemos que no es repetido*/
			grafo.crearNodo(posX,posY,interfaz.getNombreNodo());
			interfaz.setCrearNodoBool(false);
			repaint();
			}
			else 
				JOptionPane.showMessageDialog(null,"El nombre ya existe");
		}
		//Si es crear arco.
		else if (interfaz.isCrearArcoBool()){
			if (primerClick == true){
				primerNodo = grafo.existeNodo(posX,posY);
				if (primerNodo != null){
					primerClick = false;
					segundoClick = true;
				}				
			}
			else if (segundoClick == true){
				Nodo aux = grafo.existeNodo(posX,posY);
				if (aux != null){
					Arco arc = grafo.existeArco(primerNodo, aux);
					if (arc == null){
					grafo.crearArco(primerNodo, aux, interfaz.getPesoArco());
					repaint();		
					}
				}
				primerClick = true;
				segundoClick = false;
				interfaz.setCrearArcoBool(false);
			}
		}
		else if (interfaz.isBorrarArcoBool()){	
			if (primerClick == true){
				primerNodo = grafo.existeNodo(posX,posY);
				if (primerNodo != null){
					primerClick = false;
					segundoClick = true;
				}				
			}
			else if (segundoClick == true){
				Nodo aux = grafo.existeNodo(posX,posY);
				if (aux != null){
					Arco arco = grafo.existeArco(primerNodo,aux);
					if (arco != null){
						grafo.borrarArco(primerNodo,aux);
						repaint();
					}
					primerClick = true;
					segundoClick = false;
					interfaz.setBorrarArcoBool(false);
				}
			}
		}
		else if (interfaz.isBorrarNodoBool()){
			Nodo aux = grafo.existeNodo(posX, posY);
			if (aux != null){
				grafo.borrarNodo(aux);
				repaint();
			}
			interfaz.setBorrarNodoBool(false);
		}
		else if (interfaz.isDijkstra()){
			if (primerClick == true){
				primerNodo = grafo.existeNodo(posX,posY);
				if (primerNodo != null){
					primerClick = false;
					segundoClick = true;
				}				
			}
			else if (segundoClick == true){
				Nodo aux = grafo.existeNodo(posX,posY);
				if (aux != null){
					Dijkstra dijkstra = new Dijkstra(grafo,primerNodo,aux);
					ruta = dijkstra.getRuta();
					anotarResultadoDijkstra(primerNodo,aux);
					}
					primerClick = true;
					segundoClick = false;
					interfaz.setDijkstra(false);
					interfaz.setRepintar(true);
				}
			}
		else if (interfaz.isKruskal()){
			if (primerClick == true){
				
					Kruskal kruskal = new Kruskal(grafo);
					solucion = kruskal.getSolucion();
					anotarResultadoKruskal();
				}
				interfaz.setKruskal(false);
				interfaz.setRepintar(true);
			
			}
		else if (interfaz.isFloyd()){
			if (primerClick == true){
				primerNodo = grafo.existeNodo(posX,posY);
				if (primerNodo != null){
					primerClick = false;
					segundoClick = true;
				}				
			}
			else if (segundoClick == true){
				Nodo aux = grafo.existeNodo(posX,posY);
				if (aux != null){
					Floyd floyd = new Floyd(grafo,primerNodo,aux);
					solucionFloyd= floyd.getArcos();
					anotarResultadoFloyd();
					}
					primerClick = true;
					segundoClick = false;
					interfaz.setFloyd(false);
					interfaz.setRepintar(true);
				}
			}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
//METODOS DE ALGORITMOS ************************************************************
	public void anotarResultadoDijkstra(Nodo origen, Nodo destino){
		Arco arco;
		Nodo aux = destino;
		Nodo aux2 = ruta.get(aux);
		while (aux2 != null){
			arco = grafo.existeArco(aux2, aux);
			if (arco != null)
				grafo.modificaArco(arco);
			aux = aux2;	
			aux2 = ruta.get(aux);
		}
		repaint();
	}

	//	KRUSKAL**********************************************************************
	public void anotarResultadoKruskal(){
		Iterator<Arco> it = solucion.iterator(); 
		Arco arco;
		while(it.hasNext()){
			arco = it.next();
			if (arco != null)
				grafo.modificaArco(arco);
		}
		repaint();
	}
	
	//FLOYD**************************************************************************
		public void anotarResultadoFloyd(){
			Iterator<Arco> it = solucionFloyd.iterator(); 
			Arco arco;
			while(it.hasNext()){
				arco = it.next();
				if (arco != null)
					grafo.modificaArco(arco);
			}
			repaint();
		}
}