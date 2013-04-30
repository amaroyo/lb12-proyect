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
	private int posStringX = 15;
	private int posStringY = 25;
	
	
	/******************************************************************************************************/
	public TableroCanvas(int filas, int cols){
		this.nfilas=filas;
		this.ncols=cols;
		this.max_rest_fil = ((ncols / 2) + (ncols % 2));
		this.max_rest_col = ((nfilas / 2) + (nfilas % 2));
		this.tablero = new int[nfilas][ncols];
	}
	
	/******************************************************************************************************/
	public void paint(Graphics g){//Se ejecuta inicialmente. Se ejecuta cada vez que modificas.
		super.paint(g);
		this.setBackground(new Color(141, 0, 141));//Le asignamos color de fondo al frame actual new Color(r,g,b)
		pintarRest(g);
		pintarTablero(g);
		
	}
	
	/******************************************************************************************************/
	private void pintarRest(Graphics g) {
		pintarCeldasRest(g); //El relleno
		pintarCuadriRest(g);//Los bordes
	}

	/******************************************************************************************************/
	private void pintarCeldasRest(Graphics g) {
		//Las restricciones de la parte superior
		int x = k+max_rest_fil*celda;//Asigno donde debe empezar por la izqda
		int y = k;
		
		for(int i = 0; i < ncols; i++){//Voy pintando de forma horizontal
			pintaRestCols(i, g, x, y);
			x = x + celda;
		}
		
		//Las restricciones del lateral izquierdo
		x = k;
		y = k + max_rest_col*celda;
		
		for(int j = 0; j < max_rest_fil;j++){//Voy pintando de forma horizontal
			pintaRestFilas(j, g, x, y);
			x = x + celda; 
		}
	}
	
	/******************************************************************************************************/
	private void pintaRestCols(int fila, Graphics g, int x, int y){	
		int columna = 0;
		String valor;
		for(int i = y; i < y + max_rest_col*celda; i = i+celda){//Voy pintando de forma vertical
			if(restC != null){//Si hay restricciones
				if(restC[fila][columna] != 0){//La celda contiene un valor
					g.setColor(new Color(116, 176, 248));//(Azul cielo claro)
					g.fillRect(x, i, celda, celda); //Lo relleno del color de casilla ocupada
					valor = restC[fila][columna] + "";
					g.setColor(new Color(23, 6, 128));//(Azul oscuro)
					g.setFont(new Font("ComicSans", Font.BOLD, 16));
					g.drawString(valor, x+posStringX, i+posStringY);//Muestro el valor de la celda y la posicion respecto a la casilla
				}
				else{//La celda no contienen ningun valor
					g.setColor(new Color(75, 141, 221));
					g.fillRect(x, i, celda, celda); 
				}
			}
			else{//Si no hay nada
				g.setColor(new Color(116, 176, 248));//(Azul cielo claro)
				g.fillRect(x, i, celda, celda);
			}
			columna++;
		}
	}
 	

	/******************************************************************************************************/
	private void pintaRestFilas(int columna,Graphics g,int x,int y){
		int fila = 0;
		String valor;
		for(int i = y; i < y + nfilas*celda; i = i+celda){//Voy pintando de forma vertical
			if(restF != null){//Si hay restricciones
					if(restF[fila][columna] != 0){//La celda contiene un valor
						g.setColor(new Color(116, 176, 248));//(Azul cielo claro)
						g.fillRect(x, i, celda, celda);//Lo relleno del color de casilla ocupada
						valor = restF[fila][columna] + "";
						g.setColor(new Color(23, 6, 128));//(Azul oscuro)
						g.setFont(new Font("ComicSans", Font.BOLD, 16));
						g.drawString(valor, x+posStringX, i+posStringY);//Muestro el valor de la celda 
					}
					else{//La celda no contienen ningun valor
						g.setColor(new Color(75, 141, 221));
						g.fillRect(x, i, celda, celda);
					}
			}
			else{//Si no hay nada
				g.setColor(new Color(116, 176, 248));//(Azul cielo claro)
				g.fillRect(x, i, celda, celda);
			}
			fila++;
		}
	}

	/******************************************************************************************************/
	private void pintarCuadriRest(Graphics g) {
		g.setColor(new Color(0,0,0));//(Negro)
		
		//Pinto las lineas horizontales de las restricciones superiores
		for(int col = k; col <= k+(max_rest_col*celda); col = col+celda){
			g.drawLine(k+(max_rest_fil*celda), col, k+max_rest_fil*celda+ncols*celda, col);
		}
		
		//Pinto las lineas horizontales de las restricciones laterales
		for(int col = k+(max_rest_col*celda); col <= k+(max_rest_col*celda)+(nfilas*celda); col = col+celda){
			g.drawLine(k, col, k+max_rest_fil*celda, col);
		}
		
		//Pinto las lineas verticales de las restricciones superiores
		for(int fil = k+(max_rest_fil*celda); fil <= k+(max_rest_fil*celda)+(ncols*celda); fil = fil+celda){
			g.drawLine(fil, k, fil, k+(max_rest_col*celda));
		}
		
		//Pinto las lineas verticales de las restricciones laterales
		for(int fil = k; fil <= k+(max_rest_fil*celda); fil = fil+celda){
			g.drawLine(fil, k+(max_rest_col*celda), fil, k+(max_rest_col*celda)+(nfilas*celda));
		}
	}
	
	/******************************************************************************************************/
	private void pintarTablero(Graphics g) {
		//g.setColor(Color.GRAY); //Por defecto
		pintarFichas(g);//Relleno		
		pintarCuadri(g);
	}

	/******************************************************************************************************/
	public void pintarFichas(Graphics g) {
		for(int i = 0; i < (nfilas*celda); i = i + celda){ //Voy pintando verticalmente
			pintarFila(g, (k+(max_rest_fil*celda)), ((k+(max_rest_col*celda))+i));
		}
	}

	/******************************************************************************************************/
	private void pintarFila(Graphics g, int inix, int iniy) {
		int f = (iniy - (k + max_rest_col*celda)) / celda;
		
		//Parte desde la esquina superior izqda
		for(int i = 0; i < ncols; i++){ //Voy pintando horizontalmente
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
	public void pintarCuadri(Graphics g) {
		g.setColor(new Color(0, 0, 0));//(Negro)
		//Pinta las lineas horizontales
		for(int col = k+(max_rest_col*celda); col <= k+(max_rest_col*celda)+nfilas * celda; col = col+celda){
			g.drawLine(k+(max_rest_fil*celda), col, k+max_rest_fil*celda+ncols*celda, col);
		}
		//Pinta las lineas verticales
		for(int fil = k+(max_rest_fil*celda); fil <= k+(max_rest_fil*celda)+ncols * celda; fil = fil+celda){
			g.drawLine(fil, k+(max_rest_col*celda), fil, k+(max_rest_col*celda)+nfilas*celda);
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
	private int queCol(int x) {
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
	private int queFila(int y) {
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
	
	/******************************************************************************************************/

	
	/******************************************************************************************************/
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int y = e.getY();
		int x = e.getX();
		int f = queFila(y);	//SEGUN ELVIRA AL REVES, PERO ASI FUNCIONA
		int c = queCol(x);
		if ((f > -1) && (f < nfilas) && (c > -1) && (c < ncols)){
			asignarColorCasilla(f, c);
			//asignarColorCasilla(c,f);
			repaint();
		}
		
	}
	
	/******************************************************************************************************/
										/*GETTERS AND SETTERS*/
	/******************************************************************************************************/
	public int getNfilas() {
		return nfilas;
	}

	public void setNfilas(int nfilas) {
		this.nfilas = nfilas;
	}

	public int getNcols() {
		return ncols;
	}

	public void setNcols(int ncols) {
		this.ncols = ncols;
	}

	public int getMax_rest_col() {
		return max_rest_col;
	}

	public void setMax_rest_col(int max_rest_col) {
		this.max_rest_col = max_rest_col;
	}

	public int getMax_rest_fil() {
		return max_rest_fil;
	}

	public void setMax_rest_fil(int max_rest_fil) {
		this.max_rest_fil = max_rest_fil;
	}

	public int[][] getRestF() {
		return restF;
	}

	public void setRestF(int[][] restF) {
		this.restF = restF;
	}

	public int[][] getRestC() {
		return restC;
	}

	public void setRestC(int[][] restC) {
		this.restC = restC;
	}

	public void setCelda(int celda) {
		this.celda = celda;
	}
	
	public void setTablero(int tablero[][]) {
		this.tablero = tablero;
	}
	
	public int[][] getTablero() {
		return tablero;
	}
	
	public int getValorPosTablero(int f, int c){
		return tablero[f][c];
	}
	
	public void setValorPosTablero(int f, int c,int val) {
		tablero[f][c] = val;
	}
	
	public int getTamCelda(){
		return celda;
	}
	
	public void setTamCelda(int val) {
		this.celda=val;
	}
	
	public int getK(){
		return k;
	}
	
	public void setK(int k) {
		this.k = k;
	}
	
	public void setPosStringX(int posStringX) {
		this.posStringX = posStringX;
	}

	public void setPosStringY(int posStringY) {
		this.posStringY = posStringY;
	}
	/******************************************************************************************************/
										/*AUTO-GENERATED METHODS*/ 
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
