package nonograma;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Stack;

import interfaz.TableroCanvas;

public class Nonograma {

	private TableroCanvas canvas;
	private int[][] restriccionesFilas;
	private int[][] restriccionesCols;
	private int nfils,ncols;
	private int[][] tablero;
	private LinkedList<int[]> solFilas[];
	private LinkedList<int[]> solCols[];
	private int[][] solActual;
	private int lineaActual;
	private boolean primero = true;
	private boolean forzado = false;
	private ListIterator<int[]> iterSolFilas[];
	private ListIterator<int[]> iterSolCols[];
	private Stack<int[][]> pilaForzado = null;
	
	
	
	public  Nonograma(TableroCanvas c, int[][] restF, int[][] restC, boolean b){
		this.canvas = c;
		this.restriccionesFilas = restF;
		this.restriccionesCols = restC;
		this.nfils = c.getNfilas();
		this.ncols = c.getNcols();
		this.tablero = new int[nfils][ncols];
		for(int i = 0; i < nfils; i++)
			for(int j = 0; j < ncols; j++)
				tablero[i][j] = c.getValorPosTablero(i, j);
		
		forzado = b;
		pilaForzado = new Stack<int[][]>();
	}
	
	/******************************************************************************************************/

	public boolean nonograma(){
		if(primero){//La primera vez?
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
		solFilas = (LinkedList<int[]>[]) new LinkedList[nfils];
		iterSolFilas=(ListIterator<int[]>[])new ListIterator[nfils];
		for (int i = 0; i<nfils; i++){
			solFilas[i] = new LinkedList<int[]>();
			iterSolFilas[i] = null;
			int[] unaSol = new int[numeroRF(i)];
			calcularSolucionesDeFila(i,0,0,unaSol,solFilas[i]);
			
		}
	}
	
	/******************************************************************************************************/
	
	public int numeroRF(int i){
		int c = 0;
		for(int j=0; j<restriccionesFilas[i].length;j++){
			if(restriccionesFilas[i][j]>0) c++;
		}
		return c;
	}
	/******************************************************************************************************/
	public int numeroRC(int i){
		int c = 0;
		for(int j=0; j<restriccionesCols[i].length;j++){
			if(restriccionesCols[i][j]>0) c++;
		}
		return c;
	}
	/******************************************************************************************************/
	
	public void calcularSolCols(){
		solCols = (LinkedList<int[]>[]) new LinkedList[ncols];
		iterSolCols=(ListIterator<int[]>[])new ListIterator[ncols];
		for (int i = 0; i<ncols; i++){
			solCols[i] = new LinkedList<int[]>();
			iterSolCols[i] = null;
			int unaSol[] = new int[numeroRC(i)];
			calcularSolucionesDeColumna(0,i,0,unaSol,solCols[i]);
		}
	}
	
	/******************************************************************************************************/
	public int[] resSinF(int f){
		int[] respuesta = new int[numeroRF(f)];
		int k = 0;
		for(int j=0; j<restriccionesFilas[f].length;j++){
			if(restriccionesFilas[f][j]>0){
				respuesta[k]=restriccionesFilas[f][j];
				k++;
			}
		}
		return respuesta;
		
	}
	
	/******************************************************************************************************/
	public int[] resSinC(int f){
		int[] respuesta = new int[numeroRC(f)];
		int k = 0;
		for(int j=0; j<restriccionesCols[f].length;j++){
			if(restriccionesCols[f][j]>0){
				respuesta[k]=restriccionesCols[f][j];
				k++;
			}
		}
		return respuesta;
		
	}
	/******************************************************************************************************/

	void calcularSolucionesDeFila(int fila, int col, int cont, int sol[], LinkedList<int[]> lista){
		
		int[] aux = resSinF(fila);
		
		if(cont == sol.length){ //CASO BASE
			//anyadir la sol a la lista
			lista.add(sol.clone());
		}
		else{
			
			
			int miRestriccion = aux[cont];
				
			if((col+miRestriccion) <= ncols){
				sol[cont]=col;
				calcularSolucionesDeFila(fila,col+miRestriccion+1,cont+1,sol,lista);
				
				
				if(tablero[fila][col]>=0){//o gris o negro
					calcularSolucionesDeFila(fila,col+1,cont,sol,lista);
				}
			}
			/*				
			 * 		2.1)
			 * 			a)empezamos en col si es posible(si el tamanyo del bloke cont + la col
			 * 			  en la q estoy se sale del tamanyo -> return, se descarta
			 * 			b) SI a partir del col hay tamanyo de bloque posiciones libres
			 * 				*anotar en sol[cont] la columna en la q se situa col
			 * 				*calcularSolucionesDeFila(fila,col+restriccionesFIla[fila][cont]+1,cont+1,sol,lista)
			 * 			   ELSE return
			 * 		2.2) dejamos un espacio mas en clanco
			 * 			 SI (tablero[fila][col]<=0)
			 * 				calcularSolucionesDeFila(fila,col+1,cont,sol,lista)	
			 * 		
			 */
	
		}
	}
	/******************************************************************************************************/
	void calcularSolucionesDeColumna(int fila, int col, int cont, int sol[], LinkedList<int[]> lista){
		
		int[] aux = resSinC(col);
		
		if(cont == sol.length){ //CASO BASE
			//anyadir la sol a la lista
			lista.add(sol.clone());
		}
		else{
			
			int miRestriccion = aux[cont];
				
			if((fila+miRestriccion) <= nfils){
				sol[cont]=fila;
				calcularSolucionesDeColumna(fila+miRestriccion+1,col,cont+1,sol,lista);
				
				
				if(tablero[fila][col]>=0){//o gris o negro
					calcularSolucionesDeColumna(fila+1,col,cont,sol,lista);
				}
			}
		}
	}
	
	/******************************************************************************************************/
	public boolean conflicto(){
		for(int i = 0; i<ncols;i++)
			if (conflictoCol(i)) return true;
		return false;
	}
	
	/******************************************************************************************************/
	public boolean conflictoCol(int col){
		ListIterator<int[]> solsCols = solCols[col].listIterator();
		while(solsCols.hasNext())
			if (funcionaConUna(col,solsCols.next())) return false;
		return true;
	}
	
	/******************************************************************************************************/
	public boolean funcionaConUna(int col, int[] is){
		
		
		for (int i=0; i<nfils; i++){
			LinkedList<int[]> aux = solFilas[i];
			int problemas = 0;
			int nsoluciones = aux.size();
			
			for(int k = 0; k<aux.size();k++){
				int[] lista = aux.get(k);
				for(int j = 0; j<lista.length;j++){
					for(int x=0;x<is.length;x++){
						if(lista[j] <=col) {
							if (lista[j] != is[x]) problemas++;
						}
					}
				}
			}
			
			if(problemas>=nsoluciones) return false;
		}
			
		
		return true;
	}
	
	/******************************************************************************************************/

	public boolean busquedaBack(){
		if(lineaActual == nfils) lineaActual--;
		while(lineaActual > 0 && lineaActual < nfils){
			if(iterSolFilas[lineaActual] == null){
				iterSolFilas[lineaActual] = solFilas[lineaActual].listIterator();
				if(solFilas[lineaActual]!=null) quitarFilaTablero(lineaActual, solActual[lineaActual]);
				boolean aplicable = false;
				while(iterSolFilas[lineaActual].hasNext() && !aplicable){
					//PODA 1
					solActual[lineaActual] = iterSolFilas[lineaActual].next();
					if(esAplicable(lineaActual,solActual[lineaActual])) aplicable = true;
				}
				
				if(aplicable){
					ponerFilaTablero(lineaActual,solActual[lineaActual]);
					if((lineaActual < nfils-1 && !conflicto()
							|| lineaActual == nfils && esValida())){
						lineaActual++;
					}
				}//FIN APLICABLE
				//el else vacio es hacer backtracking al hermano
				
			}//if == null
			else { //BACKTRACKING HACIA ARRIBA
				iterSolFilas[lineaActual] = null;
				solActual[lineaActual] = null;
				lineaActual--;
				if(lineaActual >= 0){
					quitarFilaTablero(lineaActual, solActual[lineaActual]);
					solActual[lineaActual] = null;
				}
			}//FIN ELSE - FIN BACK
			
		}//while
		
		if(lineaActual < 0) return false;
		else return true; //ESTA GUARDADA EN SOLACTUAL
	}

	private boolean esValida() {
		// TODO Auto-generated method stub
		return false;
	}

	private void ponerFilaTablero(int lineaActual2, int[] is) {
		// TODO Auto-generated method stub
		
	}

	private boolean esAplicable(int lineaActual2, int[] is) {
		// TODO Auto-generated method stub
		
		//PONER UNA BLANCA/NEGRA
		return false;
	}

	private void quitarFilaTablero(int lineaActual2, int[] is) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}//fin de clase
