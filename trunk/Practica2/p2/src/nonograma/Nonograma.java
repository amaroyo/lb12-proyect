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
	int[][] tableroInicial;
	private LinkedList<int[]> solFilas[];
	private LinkedList<int[]> solCols[];
	private int[][] solActual;
	private int lineaActual;
	private boolean primero = true;
	private boolean forzado = false;
	private ListIterator<int[]> iterSolFilas[];
	private ListIterator<int[]> iterSolCols[];
	private Stack<int[][]> pilaForzado = null;
	private int max_rest_fil;
	private int max_rest_col;
	
	
	
	public  Nonograma(TableroCanvas c, int[][] restF, int[][] restC, boolean b){
		this.canvas = c;
		this.restriccionesFilas = restF;
		this.restriccionesCols = restC;
		this.nfils = c.getNfilas();
		this.ncols = c.getNcols();
		this.tablero = new int[nfils][ncols];
		this.max_rest_fil = ((ncols / 2) + (ncols % 2));
		this.max_rest_col = ((nfils / 2) + (nfils % 2));
		
		for(int i = 0; i < nfils; i++)
			for(int j = 0; j < ncols; j++)
				tablero[i][j] = c.getValorPosTablero(i, j);
	
		tableroInicial=clonar(canvas.getTablero());
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
		int[] solAux = new int[nfils];
		int[] aux = resSinC(col);
		for(int i=0; i<is.length; i++){
			for(int j=is[i]; j<(aux[i]+is[i]); j++)
				solAux[j]=1;
		}
		if(tablero[lineaActual][col]>0 && solAux[lineaActual]>0 ) return true;
		if(tablero[lineaActual][col]<=0 && solAux[lineaActual]<=0 ) return true;
		
		return false;
	}
	
	/******************************************************************************************************/
				    
	public boolean busquedaBack(){
		if(lineaActual == nfils) lineaActual--;
		while(lineaActual >= 0 && lineaActual < nfils){
			if(iterSolFilas[lineaActual] == null){
				if(forzado) aplicarForzado();
				iterSolFilas[lineaActual] = solFilas[lineaActual].listIterator();
			}	
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
							|| lineaActual == nfils-1 && esValida()))
						lineaActual++; //el else vacio es hacer backtracking al hermano
				
				}//FIN APLICABLE
				else { //BACKTRACKING HACIA ARRIBA
						iterSolFilas[lineaActual] = null;
						solActual[lineaActual] = null;
						lineaActual--;
						if(lineaActual >= 0){
							quitarFilaTablero(lineaActual, solActual[lineaActual]);
							solActual[lineaActual] = null;
							if(forzado) deshacerForzado();
						}
			}//FIN ELSE - FIN BACK
			
		}//while
		
		if(lineaActual < 0) return false;
		else return true; //ESTA GUARDADA EN SOLACTUAL
	}

	
	private boolean esValida() {
		int[][] rest_col = new int[ncols][]; 
		for(int i=0; i < ncols ; i++){
			int aux[] = new int[max_rest_col];
			int counter = 1;
			int pos=0;
			for(int j=0; j< nfils ; j++){
				if ( tablero[j][i] > 0 ) 
					if ( j == nfils-1){
						aux[pos]=counter;
						counter=1;
						pos++;}
						else if(tablero[j+1][i]<=0){
							aux[pos]=counter;
							counter=1;
							pos++;}
							else counter++; 
			}
			int [] restric=null;
			restric = new int[pos];
			for(int k=0; k<pos; k++){
				restric[k]=aux[k];
			}
			rest_col[i]=restric;
		}
		
		
		int[][] auxSinCeros = restSinCerosC();
		for(int i=0; i<ncols; i++){
			if (rest_col[i].length==auxSinCeros[i].length){
				for(int j=0; j<rest_col[i].length; j++)
					if (rest_col[i][j]!=auxSinCeros[i][j]) return false;
			} 
			else return false;
		}
		return true;
	}

	
	public int[][] restSinCerosC(){
		int[][] respuesta = new int[ncols][];
		for (int i = 0; i<ncols;i++)
			respuesta[i]=resSinC(i);
		
		return respuesta;
	}
	
	public int[][] restSinCerosF(){
		int[][] respuesta = new int[nfils][];
		for (int i = 0; i<nfils;i++)
			respuesta[i]=resSinF(i);
		
		return respuesta;
	}
	
	private void ponerFilaTablero(int fil, int[] is) {
		int[] aux = resSinF(fil);
		for(int i=0; i<is.length; i++){
			for(int j=is[i]; j<(aux[i]+is[i]); j++)
				tablero[fil][j]=1;
		}
		for(int i=0; i<ncols; i++)
			if(tablero[fil][i]!=1) tablero[fil][i]=-1;
		
	}
	
	private void quitarFilaTablero(int fil, int[] is) {
		tablero[fil]=clonaSol(tableroInicial[fil]);
		
	}

	private boolean esAplicable(int fil, int[] is) {
		
		int solAux[]= new int[ncols];// se genera inicializado a cero
		int[] aux = resSinF(fil);
		
		for(int i=0; i<is.length; i++){
			for(int j=is[i]; j<(aux[i]+is[i]); j++)
				solAux[j]=1;
		}
		
		//Comprueba hay conflicto entre lo que deberia ser y lo que hay 
		for(int i=0; i<ncols; i++){
		   if ((solAux[i]==-1 && (tablero[fil][i]>0)) || (solAux[i] == 1 && tablero[fil][i]<0))
				return false;
		}		
		return true;
	}

	
	
	private int[] clonaSol(int[] is) {
		int sol[] = new int[is.length];
		for (int i=0; i < is.length; i++){
			sol[i] = is[i];
		}
		return sol;
	}
	
	private int[][] clonar(int[][] tabla){
		int[][] sol = new int[nfils][ncols];
		for (int i=0; i < nfils; i++){
			for(int j=0; j<ncols; j++)
				sol[i][j]=tabla[i][j];
		}
		return sol;
	}

	public int[][] getTablero(){
		return tablero;
	}
	
	public int[][] getSolActual(){
		return solActual;
	}
	
	
	private void deshacerForzado() {
		int[][] forzado = pilaForzado.pop();//Coge la cima de la pila que tiene el ultimo forzado aplicado
		for(int i=0; i < nfils; i++)
			for(int j=0; j < ncols; j++){
				if(forzado[i][j] == 1)
					tablero[i][j] = 0;
				if(forzado[i][j] == -1)
					tablero[i][j] = 0;
			}
	}

	private void aplicarForzado() {
		int[][] forzadas = new int[nfils][ncols];
		boolean negrasForzado[] = new boolean[Math.max(nfils, ncols)];
		boolean blancasForzado[] = new boolean[Math.max(nfils,ncols)];
		
		//FILAS
		for(int i=lineaActual; i<nfils; i++){
			forzadoFila(i, negrasForzado, blancasForzado);
			for(int j=0; j<ncols; j++){
				if(negrasForzado[j]==true && tablero[i][j]==0)
					tablero[i][j]=forzadas[i][j]=1;
				if(blancasForzado[j]==true && tablero[i][j]==0)
					tablero[i][j]=forzadas[i][j]=-1;
			}
		}
		

		//COLUMNAS
		for(int i=0; i<ncols; i++){
			forzadoColumna(i, negrasForzado, blancasForzado);
			for(int j=lineaActual; j<nfils; j++){
				if(negrasForzado[j]==true && tablero[j][i]==0)
					tablero[j][i]=forzadas[j][i]=1;
				if(blancasForzado[j]==true && tablero[j][i]==0)
					tablero[j][i]=forzadas[j][i]=-1;
			}
		}
      
		pilaForzado.push(forzadas);
	}

	private void forzadoColumna(int i, boolean[] negrasForzado, boolean[] blancasForzado) {
				//Forzado negro
				for(int j=0; j<negrasForzado.length;j++)
					negrasForzado[j]=true;
				
				iterSolCols[i]=solCols[i].listIterator();
				while (iterSolCols[i].hasNext()){
					int[] solAux=iterSolCols[i].next();
					if(solAux.length>0 ){//&& esAplicable(i,solAux)){
						//a.1 hasta primer lugar en blanco
						for(int j=0;j<solAux[0];j++)
							negrasForzado[j]=false;
						//a.2 desde el ultimo bloque hasta el final
						int[][] auxSinCeros = restSinCerosC();
						int inicio=solAux[(solAux.length-1)]+auxSinCeros[i][(solAux.length-1)];
						for(int j=inicio; j<negrasForzado.length;j++)
							negrasForzado[j]=false;
						//a.3
						for(int j=0;j<(solAux.length-1);j++)
							for(int k=solAux[j];k<solAux[j+1];k++)
								negrasForzado[k]=false;
					}else for(int j=0; j<negrasForzado.length;j++)
						negrasForzado[j]=false;
				}
				iterSolCols[i]=null;
				//Forzado blanco
				for(int j=0; j<blancasForzado.length;j++)
					blancasForzado[j]=true;
				
				iterSolCols[i]=solCols[i].listIterator();
				while (iterSolCols[i].hasNext()){
					int[] solAux=iterSolCols[i].next();
					if(solAux.length>0){ // esAplicable(i,solAux)){
						//ponemos la solucion
						int[][] auxSinCeros = restSinCerosC();
						for(int j=0;j<solAux.length;j++)
							for(int k=solAux[j];k<solAux[j]+auxSinCeros[i][j];k++)
								blancasForzado[k]=false;		
					}
				}
				iterSolCols[i]=null;
	}

	private void forzadoFila(int i, boolean[] negrasForzado, boolean[] blancasForzado) {
		//Forzado negro
		for(int j=0; j<negrasForzado.length;j++)
			negrasForzado[j]=true;
		
		iterSolFilas[i]=solFilas[i].listIterator();
		while (iterSolFilas[i].hasNext()){
			int[] solAux=iterSolFilas[i].next();
			if(solAux.length>0){
			if(esAplicable(i,solAux)){
				//a.1 hasta primer lugar en blanco
				for(int j=0;j<solAux[0];j++)
					negrasForzado[j]=false;
				//a.2 desde el ultimo bloque hasta el final
				int[][] auxSinCeros = restSinCerosF();
				int inicio=solAux[(solAux.length-1)]+auxSinCeros[i][(solAux.length-1)];
				for(int j=inicio; j<ncols;j++)
					negrasForzado[j]=false;
				//a.3 blancos entre bloques 
				for(int j=0;j<(solAux.length-1);j++){
					int rest = auxSinCeros[i][j];
					int limit = solAux[j+1];
					for(int k=solAux[j]+rest;k<limit;k++)
						negrasForzado[k]=false;
				}
			}
			} else for(int j=0; j<negrasForzado.length;j++)
				negrasForzado[j]=false;
		}
		iterSolFilas[i]=null;
		
		//Forzado blanco
		for(int j=0; j<blancasForzado.length;j++)
			blancasForzado[j]=true;
		
		iterSolFilas[i]=solFilas[i].listIterator();
		while (iterSolFilas[i].hasNext()){
			int[] solAux=iterSolFilas[i].next();
			if(solAux.length>0){
			if(esAplicable(i,solAux)){
				//ponemos la solucion
				int[][] auxSinCeros = restSinCerosF();
				for(int j=0;j<solAux.length;j++)
					for(int k=solAux[j];k<solAux[j]+auxSinCeros[i][j];k++)
						blancasForzado[k]=false;		
			}
			} else for(int j=0; j<blancasForzado.length;j++)
			blancasForzado[j]=true;
		}
		iterSolFilas[i]=null;
	}
	
}//fin de clase
