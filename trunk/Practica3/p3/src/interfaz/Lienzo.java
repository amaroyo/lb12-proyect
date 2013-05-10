package interfaz;

import grafo.Arco;
import grafo.Grafo;
import grafo.Nodo;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;

public class Lienzo extends Canvas implements MouseListener{
	
	private static final long serialVersionUID = 1L;
	private Interfaz inter;
	private boolean bien = false;
	private Nodo primerNodo;

	/******************************************************************************************************/
	public Lienzo(Interfaz i){
		this.inter=i;
	}
	
	/******************************************************************************************************/
	public void paint(Graphics g){
		if (inter.getGrafo() != null){
			Iterator<Nodo> iN = inter.getGrafo().dameNodos();
			Iterator<Arco> iA = inter.getGrafo().dameArcos();
			while (iN.hasNext()){
				Nodo auxNodo = iN.next();
				g.setColor(auxNodo.getColor());
				g.fillOval(auxNodo.getX(),auxNodo.getY(), 10, 10);
				g.setColor(Color.BLACK);
				g.drawString(auxNodo.getNombre(), auxNodo.getX(), auxNodo.getY());
			}
			while (iA.hasNext()){
				Arco auxArco = iA.next();
				g.setColor(auxArco.getColor());
				g.drawLine(auxArco.getOrig().getX(), auxArco.getOrig().getY(), auxArco.getDest().getX(), auxArco.getDest().getY());
				g.setColor(Color.BLACK);
				String c = Integer.toString(auxArco.getPeso());
				int x = ((auxArco.getOrig().getX() + auxArco.getDest().getX()) / 2) + 3;
				int y = ((auxArco.getOrig().getY() + auxArco.getDest().getY()) / 2) + 3; 
				g.drawString(c, x, y);
			}
		}
	}
	
	/******************************************************************************************************/
	private Nodo estaDentro(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

	/******************************************************************************************************/
	@Override
	public void mousePressed(MouseEvent e) {
		if (inter.getGrafo() == null){
			inter.setGrafo(new Grafo());
		}
		if (inter.getIntroduceVertice()){
			inter.getGrafo().addNodo(new Nodo(e.getX(),e.getY(),inter.getNombreVertice()));
		}
		else{
			Nodo segundoNodo = estaDentro(e.getX(),e.getY());
			if (segundoNodo != null){
				bien = true;
				if(inter.getCont() == 0){
					primerNodo = segundoNodo;
				}
				if(inter.getCont() == 1){
					inter.getGrafo().addArco(new Arco(primerNodo,segundoNodo,inter.getPesoArco()));
				}
			}
		}			
		repaint();
		
	}
	
	/******************************************************************************************************/
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
