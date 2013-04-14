package interfaz;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TableroCanvas extends Canvas implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int nfilas, ncols, max_rest_col, max_rest_fil;
	private int tablero[][];
	private int k=50;
	private int celda=40;
	private int restF[][];
	private int restC[][];
	
	/******************************************************************************************************/
	public TableroCanvas(int filas, int cols){
		nfilas=filas;
		ncols=cols;
		max_rest_fil = 3;
		max_rest_col = 3;
		tablero = new int[nfilas][ncols];
	}
	
	/******************************************************************************************************/
	public void paint(Graphics g){//Se ejecuta inicialmente. Se ejecuta cada vez que modificas.
		//Le asignamos color al contexto actual new Color(r,g,b)
		super.paint(g);
		this.setBackground(new Color(141, 0, 141));
		pintarRestricciones(g);
		pintarTablero(g);
		
	}
	
	/******************************************************************************************************/
	private void pintarRestricciones(Graphics g) {
		g.setColor(new Color(116, 176, 248));
		pintarCeldasRest(g); //El relleno
		pintarCuadriRest(g);//Los bordes
	}

	/******************************************************************************************************/
	private void pintarCeldasRest(Graphics g) {
		int x = k+max_rest_fil*celda;//El ancho
		int y = k;
		for(int i = 0; i < ncols; i++){
			pintaRestCols(i, g, x, y);
			x = x + celda;
		}
		x = k;
		y = k + max_rest_col*celda;
		for(int j = 0; j < max_rest_fil;j++){
			pintaRestFilas(j, g, x, y);
			x = x + celda; 
		}
	}
	
	/******************************************************************************************************/
	private void pintaRestCols(int fila, Graphics g, int x, int y){	
		int columna = 0;
		String valor;
		for(int i = y; i < y + max_rest_col*celda; i = i+celda){
			if(restC != null){
				if(restC[fila][columna] != 0){
					g.setColor(new Color(116, 176, 248));
					g.fillRect(x, i, celda, celda);
					valor = restC[fila][columna] + "";
					g.setColor(new Color(23, 6, 128));
					g.setFont(new Font("ComicSans", Font.BOLD, 16));
					g.drawString(valor, x+12, i+20);
				}
				else{
					g.setColor(new Color(141, 0, 141));
					g.fillRect(x, i, celda, celda);
				}
			}
			else{
				g.setColor(new Color(116, 176, 248));
				g.fillRect(x, i, celda, celda);
			}
			columna++;
		}
	}
 	
	/******************************************************************************************************/
	private void pintaRestFilas(int columna,Graphics g,int x,int y){
		int fila = 0;
		String valor;
		for(int i = y; i < y + nfilas*celda; i = i+celda){
			if(restF != null){
					if(restF[fila][columna] != 0){
						g.setColor(new Color(116, 176, 248));
						g.fillRect(x, i, celda, celda);
						valor = restF[fila][columna] + "";
						g.setColor(new Color(23, 6, 128));
						g.setFont(new Font("ComicSans", Font.BOLD, 16));
						g.drawString(valor, x+12, i+20);
					}
					else{
						g.setColor(new Color(141, 0, 141));
						g.fillRect(x, i, celda, celda);
					}
			}
			else{
				g.setColor(new Color(116, 176, 248));
				g.fillRect(x, i, celda, celda);
			}
			fila++;
		}
	}

	/******************************************************************************************************/
	private void pintarCuadriRest(Graphics g) {
		g.setColor(new Color(0,0,0));
		for(int cl = k; cl <= k+(max_rest_col*celda); cl = cl+celda){
			g.drawLine(k+(max_rest_fil*celda), cl, k+max_rest_fil*celda+ncols*celda, cl);
		}
		for(int cl = k+(max_rest_col*celda); cl <= k+(max_rest_col*celda)+(nfilas*celda); cl = cl+celda){
			g.drawLine(k, cl, k+max_rest_fil*celda, cl);
		}
		for(int fl = k+(max_rest_fil*celda); fl <= k+(max_rest_fil*celda)+(ncols*celda); fl = fl+celda){
			g.drawLine(fl, k, fl, k+(max_rest_col*celda));
		}
		for(int fl = k; fl <= k+(max_rest_fil*celda); fl = fl+celda){
			g.drawLine(fl, k+(max_rest_col*celda), fl, k+(max_rest_col*celda)+(nfilas*celda));
		}
	}
	
	/******************************************************************************************************/
	private void pintarTablero(Graphics g) {
		g.setColor(Color.GRAY);
		pintarCeldas(g);		
		pintarCuadriculas(g);
	}

	/******************************************************************************************************/
	public void pintarCeldas(Graphics g) {
		for(int i = 0; i < (nfilas*celda); i = i + celda){
			pintarFila(g, (k+(max_rest_fil*celda)), ((k+(max_rest_col*celda))+i));
		}
	}

	/******************************************************************************************************/
	private void pintarFila(Graphics g, int inix, int iniy) {
		int f = (iniy - (k + max_rest_col*celda)) / celda;
		for(int i = 0; i < ncols; i++){
			if(tablero[f][i] > 0){ //pintar negro
				g.setColor(Color.BLACK);
				g.fillRect(inix, iniy, celda, celda);
					
			} 
			else if(tablero[f][i] < 0){ //pintar blanco
				g.setColor(Color.WHITE);
				g.fillRect(inix, iniy, celda, celda);
					
			}
			else{ //pintar gris
				g.setColor(Color.GRAY);
				g.fillRect(inix, iniy, celda, celda);					
			}
			inix = inix + celda;
		}
	}

	/******************************************************************************************************/
	public void pintarCuadriculas(Graphics g) {
		g.setColor(new Color(0, 0, 0));
		//pinta las filas
		for(int cl = k+(max_rest_col*celda); cl <= k+(max_rest_col*celda)+nfilas * celda; cl = cl+celda){
			g.drawLine(k+(max_rest_fil*celda), cl, k+max_rest_fil*celda+ncols*celda, cl);
		}
		//pinta las lineas de las columnas
		for(int fl = k+(max_rest_fil*celda); fl <= k+(max_rest_fil*celda)+ncols * celda; fl = fl+celda){
			g.drawLine(fl, k+(max_rest_col*celda), fl, k+(max_rest_col*celda)+nfilas*celda);
		}
	}
	
	/******************************************************************************************************/
	private void asignarColorCasilla(int f, int c) {
		if(tablero[f][c] == 0){ // de gris a negro
			tablero[f][c] = 1;
		}
		else if (tablero[f][c] > 0){ // de negro a blanco
			tablero[f][c] = -1;
		}
		else{                  // de blanco a gris
			tablero[f][c] = 0;
		}
	}

	/******************************************************************************************************/
	private int queColumnaEs(int x) {
		int cont = 0;
		int aux;
		aux = k + max_rest_fil*celda; 
		while (x > aux){
			cont++;
			aux = aux + celda;
		}
		cont--;
		return cont;
	}

	/******************************************************************************************************/
	private int queFilaEs(int y) {
		int cont = 0;
		int aux;
		aux = k + max_rest_col*celda; 
		while (y > aux){
			cont++;
			aux = aux + celda;
		}
		cont--;
		return cont;
	}
	
	/******************************************************************************************************/
	public void actualizar(int[][] restriccionesFilas, int[][] restriccionesCols) {
		restF = restriccionesFilas;
		restC = restriccionesCols;		
	}

	public void setnCols(int nCols) {this.ncols = nCols;}
	public int getnCols() {return ncols;}
	public void setnFilas(int nFilas) {this.nfilas = nFilas;}
	public int getnFilas() {return nfilas;}
	public void setMaxCols(int maxCols) {this.max_rest_col = maxCols;}
	public int getMaxCols() {return max_rest_col;}
	public void setMaxFilas(int maxFilas) {this.max_rest_fil = maxFilas;}
	public int getMaxFilas() {return max_rest_fil;}
	public void setTablero(int tablero[][]) {this.tablero = tablero;}
	public int[][] getTablero() {return tablero;}
	public int getTablero(int f, int c){return tablero[f][c];}
	public void setEnTablero(int f, int c,int contenido) {tablero[f][c] = contenido;}
	public int getCelda(){return celda;}
	public int getK(){return k;}

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
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int y = e.getY();
		int x = e.getX();
		int f = queFila(x);
		int c = queCol(y);
		if ((f!=-1)&&(c!=-1)){
			asignarColorCasilla(f, c);
			repaint();
		}
		
	}

	private int queCol(int y) {
		// TODO Auto-generated method stub
		return 0;
	}

	private int queFila(int x) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void cambiaTamCelda(int i) {
		// TODO Auto-generated method stub
		celda=i;
	}

}
