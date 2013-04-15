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
	
}
