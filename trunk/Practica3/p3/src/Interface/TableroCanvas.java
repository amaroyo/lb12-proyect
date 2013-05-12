package Interface;

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
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import javax.swing.JOptionPane;

import Algorithm.Algoritmos;
import Grafos.Arco;
import Grafos.Nodo;


public class TableroCanvas extends Canvas implements MouseListener{

	private Interfaz myframe;
	private Nodo Orig;
	Nodo Dest;
	private Nodo ant;
	Map<Nodo,Nodo> solD;
	ArrayList<Nodo> solF;
	Set<Arco> solK;
	Algoritmos alg;
	boolean drawCaminoD;
	boolean drawCaminoF;
	boolean drawCaminoK;
	
	public TableroCanvas(Interfaz inter) {
		myframe = inter; 
		alg = new Algoritmos(myframe.getGrafo());
		solD = new HashMap<Nodo, Nodo>();
		solK = new HashSet<Arco>();
		Orig = null;
		Dest = null;
		ant = null;
		drawCaminoD = false;
		drawCaminoF = false;
		drawCaminoK = false;
		this.setBackground(Color.white);
		this.addMouseListener(this);
	}

	
	public void paint(Graphics g){	
		Iterator<Nodo> itNodo = myframe.getGrafo().dameNodos();
		Iterator<Arco> itArco = myframe.getGrafo().dameArcos();
		while (itNodo.hasNext()){
			pintarNodo(itNodo.next(),g);
		} 
		while (itArco.hasNext()){
			g.setColor(Color.BLUE);
			pintarArco(itArco.next(),g);
		}
		if (drawCaminoD){
			g.setColor(Color.RED);
			pintarCaminoDijkstra(g);
		}
		else if (drawCaminoF){
			g.setColor(Color.GREEN);
			pintarCaminoFloyd(g);
		}
		else if (drawCaminoK){
			g.setColor(Color.ORANGE);
			pintarCaminoKruskal(g);
		}
	}
	
	
	public void pintarNodo(Nodo nodoActual, Graphics g) {
		
			g.setColor(Color.BLUE);
			g.fillOval(nodoActual.getX(), nodoActual.getY(), 20, 20);
			g.drawString(nodoActual.getName(), nodoActual.getX()+5, nodoActual.getY()-5);
	}
	
	public void pintarCaminoDijkstra(Graphics g){
		while (ant != Orig){
			if(ant != null){
			ant = solD.get(ant);
				if (this.existeArco(ant, Dest)){
					Iterator<Arco> itarco = myframe.getGrafo().dameArcos();
					Arco aux2 = new Arco(ant,Dest,0);
					while(itarco.hasNext()){
						Arco aux =itarco.next();
						if (aux.getOrig() == ant && aux.getDest() == Dest){ 
							aux2.setPeso(aux.getPeso());
						}
					}
					pintarArco(aux2,g);
					Dest = ant;
				}
				else{ JOptionPane.showMessageDialog(null, "No hay camino entre los dos nodos"); ant = Orig;};
			}else{ JOptionPane.showMessageDialog(null, "No hay camino entre los dos nodos"); ant = Orig;};
		}	
		drawCaminoD = false;
	}

	public void pintarCaminoFloyd(Graphics g){
		Nodo aux1 = solF.get(0);
		for(int i=1;i<solF.size();i++){
			Nodo aux2 = solF.get(i);
			if (aux2 == null){JOptionPane.showMessageDialog(null, "Aviso : No hay camino"); i=solF.size(); } 
			Arco aux = new Arco(aux1,aux2,0);
			Iterator<Arco> itarco = myframe.getGrafo().dameArcos();
			while(itarco.hasNext()){
				Arco aux3 =itarco.next();
				if (aux3.getOrig() == aux1 && aux3.getDest() == aux2){ 
					aux.setPeso(aux3.getPeso());
				}
			}
			pintarArco(aux,g);
			aux1 = aux2;
		}
		drawCaminoD = false;
	}
	
	public void pintarCaminoKruskal(Graphics g){
		Iterator<Arco> itarco = solK.iterator();
		while(itarco.hasNext()){
			pintarArco(itarco.next(),g);
		}
		drawCaminoD = false;
	}
	
	public void pintarArco(Arco arcoActual, Graphics g) {
		int XOrig = arcoActual.getOrig().getX();
		int YOrig =arcoActual.getOrig().getY();
		int XDest = arcoActual.getDest().getX();
		int YDest = arcoActual.getDest().getY();
		int XPeso;
		int YPeso;		
			
			if (arcoActual.getOrig().getX() > arcoActual.getDest().getX()){
				XDest = arcoActual.getDest().getX()+20; 
				YDest = arcoActual.getDest().getY()+10;
				XPeso = arcoActual.getOrig().getX() - (arcoActual.getOrig().getX() - arcoActual.getDest().getX())/2;
			}else if (arcoActual.getOrig().getX() < arcoActual.getDest().getX()){
				XOrig = arcoActual.getOrig().getX()+20;
				YOrig = arcoActual.getOrig().getY()+10;
				XPeso = arcoActual.getDest().getX() - (arcoActual.getDest().getX() - arcoActual.getOrig().getX())/2;
			}else{ 
				drawArrow(g,arcoActual.getOrig().getX(), arcoActual.getOrig().getY()+10, arcoActual.getDest().getX()+10, arcoActual.getDest().getY()+10);
				XPeso = arcoActual.getOrig().getX() + 5;
			}
			
			if (arcoActual.getOrig().getY() > arcoActual.getDest().getY()){
				YDest = arcoActual.getDest().getY()+10;
				YPeso = arcoActual.getOrig().getY() - (arcoActual.getOrig().getY() - arcoActual.getDest().getY())/2;
			}else if (arcoActual.getOrig().getY() < arcoActual.getDest().getY()){ 
				YOrig = arcoActual.getOrig().getY()+20;
				YPeso = arcoActual.getDest().getY() - (arcoActual.getDest().getY() - arcoActual.getOrig().getY())/2;
			}else YPeso = arcoActual.getOrig().getY() + 5;
			
			
			/*if (!(drawCaminoD || drawCaminoF)){*/ drawArrow(g, XOrig, YOrig, XDest, YDest); g.drawString(""+arcoActual.getPeso(), XPeso, YPeso);//}
			//else drawArrow(g, XOrig, YOrig, XDest, YDest);
			
	}

	
	public void drawArrow(Graphics g,int x0,int y0,int x1,int y1){
		double alfa=Math.atan2(y1-y0,x1-x0);
		g.drawLine(x0,y0,x1,y1);
		int k=7;
		int xa=(int)(x1-k*Math.cos(alfa+1));
		int ya=(int)(y1-k*Math.sin(alfa+1));
		// Se dibuja un extremo de la dirección de la flecha.
		g.drawLine(xa,ya,x1,y1); 
		xa=(int)(x1-k*Math.cos(alfa-1));
		ya=(int)(y1-k*Math.sin(alfa-1));
		// Se dibuja el otro extremo de la dirección de la flecha.
		g.drawLine(xa,ya,x1,y1); 
	}
	
	public Nodo existeNodo(int x, int y){
		Nodo existe = null;
		Iterator<Nodo> itNodo = myframe.getGrafo().dameNodos();
		while (itNodo.hasNext()){
			Nodo n = itNodo.next();
			if  (((n.getX()-10 < x-10 && x-10 < n.getX()+10) || (n.getX()-10 < x+10  && x+10 < n.getX()+10)) && 
				((n.getY()-10 < y-10 && y-10 < n.getY()+10) || (n.getY()-10 < y+10  && y+10 < n.getY()+10))){existe = n;}	
		} 
		return existe;
	}
	
	public boolean existeArco(Nodo O, Nodo D){
		boolean existe = false;
		Iterator<Arco> itArco = myframe.getGrafo().dameArcos();
		while (itArco.hasNext()){
			Arco aux = itArco.next();
			if ((aux.getOrig() == O && aux.getDest() == D)){
				existe = true;
			}
		}
		return existe;
	}
	
	
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY(); 
		//caso agregar nodo
		if (myframe.isNodo()){
			if (existeNodo(x,y) == null){
				myframe.getGrafo().crearNodo(x,y,myframe.dameNombre(),myframe.getGrafo().getNumNodos()+1); 
				repaint();
				myframe.restartEsNodo();
				}
			else{myframe.sendError(1);}
		}
		//caso elimina nodo
		if (myframe.isElimNodo()){
			Orig = existeNodo(x,y);
			if (Orig != null){
				myframe.getGrafo().eliminarNodo(Orig); 
				repaint();
				myframe.restartEsElimNodo();
				}
		}
		//caso agregar arista
		else if (myframe.isArco()){
			if(myframe.isPrimerClick()){
				Orig = null;
				if (existeNodo(x,y) != null){
					Orig = existeNodo(x,y);
					myframe.setPrimClick(false);
				}
			}
			else if (!myframe.isPrimerClick()){
				Dest = existeNodo(x,y);
				if (Dest != null && Orig != Dest){
					if (!existeArco(Orig,Dest)){
						myframe.getGrafo().crearArco(myframe.damePeso(),Orig,Dest); 
						repaint();
						myframe.restartEsArco();
					}
					else{
						myframe.sendError(2);
					}
					myframe.setPrimClick(true);
				}
			}
		}
		//caso elimina arista
		else if (myframe.isElimArco()){
			if(myframe.isPrimerClick()){
				Orig = null;
				if (existeNodo(x,y) != null){
					Orig = existeNodo(x,y);
					myframe.setPrimClick(false);
				}
			}
			else if (!myframe.isPrimerClick()){
				Dest = existeNodo(x,y);
				if (Dest != null && Orig != Dest){
					if (existeArco(Orig,Dest)){
						myframe.getGrafo().eliminarArco(Orig,Dest); 
						repaint();
						myframe.restartEsElimArco();
					}
					else{
						myframe.sendError(4);
					}
					myframe.setPrimClick(true);
				}
			}
		}
		//caso dijkstra
		else if (myframe.isDijkstra()){
			if(myframe.isPrimerClick()){
				Orig = null;
				if (existeNodo(x,y) != null){
					Orig = existeNodo(x,y);
					alg = new Algoritmos(myframe.getGrafo()); //cuidado
					solD = alg.getDijkstra(Orig);
					myframe.setPrimClick(false);
				}
			}
			else if (!myframe.isPrimerClick()){
				Dest = existeNodo(x,y);
				if (Dest != null && Orig != Dest){
					//recorrer solD de atras para adelante para encontra ruta de dibujo
					ant = Dest;
			}
			drawCaminoD = true;
			repaint();
			myframe.setPrimClick(true);
			myframe.restartEsDijkstra();
			}
		}
		//caso floyd
				else if (myframe.isFloyd()){
					if(myframe.isPrimerClick()){
						Orig = null;
						if (existeNodo(x,y) != null){
							Orig = existeNodo(x,y);
							alg = new Algoritmos(myframe.getGrafo()); //cuidado
							alg.getFloyd();
							myframe.setPrimClick(false);
						}
					}
					else if (!myframe.isPrimerClick()){
						Dest = existeNodo(x,y);
						if (Dest != null && Orig != Dest){
							solF = alg.reconstruyeFloyd(Orig,Dest);
					}
					if (solF != null) drawCaminoF = true;
					else  JOptionPane.showMessageDialog(null, "Aviso : No hay camino");
					repaint();
					myframe.setPrimClick(true);
					myframe.restartEsFloyd();
					}
				}
		//caso floyd
				else if (myframe.isKruskal()){
					alg = new Algoritmos(myframe.getGrafo()); //cuidado
					solK = alg.getKruskal();
					drawCaminoK = true;
					repaint();
					myframe.restartEsKruskal();
				}
		
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	

}
