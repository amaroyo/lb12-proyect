package nonograma;

import java.util.LinkedList;
import java.util.ListIterator;

import interfaz.TableroCanvas;

public class Nonograma {

	TableroCanvas canvas;
	int[][] restriccionesFilas;
	int[][] restriccionesCols;
	int nfils,ncols;
	int[][] tablero;
	LinkedList<int[]> solFilas[];
	LinkedList<int[]> solCols[];
	int[][] solActual;
	int lineaActual;
	boolean primero = true;
	ListIterator<int[]> iterSolFilas[];
	ListIterator<int[]> iterSolCols[];
	
	
	
	public  Nonograma(TableroCanvas c, int[][] restF, int[][] restC){
		canvas = c;
		restriccionesFilas = restF;
		restriccionesCols = restC;
		nfils = c.getNfilas();
		ncols = c.getNcols();
		tablero = new int[nfils][ncols];
		for(int i = 0; i < nfils; i++)
			for(int j = 0; j < ncols; j++)
				tablero[i][j] = c.getValorPosTablero(i, j);
	}
	
	/******************************************************************************************************/

	public boolean nonograma(){
		if(primero){
			calcularSolFilas();
			calcularSolCols();
			solActual = new int[nfils][]; //para no malgastar la memoria
			primero = false;
			lineaActual = 0;
		}
		
		return busquedaBack();	
		
	}
	
	
	/******************************************************************************************************/

	public void calcularSolFilas(){
		
	}
	
	/******************************************************************************************************/

	public void calcularSolCols(){
		
	}
	
	/******************************************************************************************************/

	public boolean busquedaBack(){
		return false;
	}
	
	
	
}//fin de clase
