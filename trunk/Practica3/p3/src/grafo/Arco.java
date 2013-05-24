package grafo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.geom.QuadCurve2D;

public class Arco implements Comparable<Arco>{
	private Nodo origen;
	private Nodo destino;
	private int peso;
	private boolean pintar;
	
	
	public Arco(Nodo origen, Nodo destino, int peso){
		this.origen = origen;
		this.destino = destino;
		this.peso = peso;
		this.pintar = false;
	}

	public boolean isPintar() {
		return pintar;
	}

	public void setPintar(boolean pintar) {
		this.pintar = pintar;
	}

	public Nodo getOrigen() {
		return origen;
	}

	public void setOrigen(Nodo origen) {
		this.origen = origen;
	}

	public Nodo getDestino() {
		return destino;
	}

	public void setDestino(Nodo destino) {
		this.destino = destino;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}
	
	public void dibujar(Graphics g){
		int x1= origen.getX();
		int x2= destino.getX();
		int y1= origen.getY();
		int y2= destino.getY();
		Color color = null;
		if (pintar == true){
			g.setColor(color.red);
			drawArrow(g,x1,y1,x2,y2);
			/*if (x1>x2)
				g.drawString(Integer.toString(peso),x1+(x2-x1)/2,y1+50);
			else g.drawString(Integer.toString(peso),x1+(x2-x1)/2,y1-50);*/
			if(Math.abs(x2-x1) > 50){
				if (x1>x2)
					g.drawString(Integer.toString(peso),x1+(x2-x1)/2,y1+50);
				else g.drawString(Integer.toString(peso),x1+(x2-x1)/2,y1-50);
			}
			else{
				if (y1>y2)
					g.drawString(Integer.toString(peso),x1+50,y1+(y2-y1)/2);
				else g.drawString(Integer.toString(peso),x1-50,y1+(y2-y1)/2);
			}
			pintar = false;
		}
		else {
			g.setColor(new Color(23, 70, 121));
			drawArrow(g,x1,y1,x2,y2);
			/*if (x1>x2)
				g.drawString(Integer.toString(peso),x1+(x2-x1)/2,y1+50);
			else g.drawString(Integer.toString(peso),x1+(x2-x1)/2,y1-50);*/
			if(Math.abs(x2-x1) > 50){
				if (x1>x2)
					g.drawString(Integer.toString(peso),x1+(x2-x1)/2,y1+50);
				else g.drawString(Integer.toString(peso),x1+(x2-x1)/2,y1-50);
			}
			else{
				if (y1>y2)
					g.drawString(Integer.toString(peso),x1+50,y1+(y2-y1)/2);
				else g.drawString(Integer.toString(peso),x1-50,y1+(y2-y1)/2);
			}
			
		}
		g.setColor(color.black);
	}
		
	
	public void drawArrow(Graphics g,int x0,int y0,int x1,int y1){
		
		Graphics2D g2D=(Graphics2D)g;
		g2D.setStroke (new BasicStroke(2f));
		QuadCurve2D.Double quad = new QuadCurve2D.Double();
		Point2D.Double inicio1 = new Point2D.Double();
		Point2D.Double fin1 = new Point2D.Double();
		Point2D.Double control1 = new Point2D.Double();
		
		inicio1.setLocation(x0,y0);
		fin1.setLocation(x1,y1);
		/*if (x0>x1)
		  control1.setLocation(x0+(x1-x0)/2,y0+50);
		else control1.setLocation(x0+(x1-x0)/2,y0-50);*/
		
		if(Math.abs(x1-x0) > 50){
			if (x0>x1)
				  control1.setLocation(x0+(x1-x0)/2,y0+50);
			else control1.setLocation(x0+(x1-x0)/2,y0-50);
		}
		else{
			if (y0>y1)
				  control1.setLocation(x0+50,y0+(y1-y0)/2);
			else control1.setLocation(x0-50,y0+(y1-y0)/2);
		}
			
		
		
		double alfa=Math.atan2(y1-y0,x1-x0);
		//g2D.drawLine(x0,y0,x1,y1); 
		quad.setCurve(inicio1,control1,fin1);
		g2D.draw(quad);
		int k=7;
		int xa=(int)(x1-k*Math.cos(alfa+1));
		int ya=(int)(y1-k*Math.sin(alfa+1));
		// Se dibuja un extremo de la direccion de la flecha.
		g2D.drawLine(xa,ya,x1,y1); 
		xa=(int)(x1-k*Math.cos(alfa-1));
		ya=(int)(y1-k*Math.sin(alfa-1));
		// Se dibuja el otro extremo de la direccion de la flecha.
		g2D.drawLine(xa,ya,x1,y1); 
	}

	@Override
	public int compareTo(Arco a) {
		if (peso > a.peso) return 1;
		else if(peso == a.peso) return 0;
		else return -1;
	}



}



