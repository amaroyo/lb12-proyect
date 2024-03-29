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
		this.canvas = c; //Le asignamos el tablero original. Modificamos este para ir viendo los cambios.
		this.restriccionesFilas = restF;
		this.restriccionesCols = restC;
		this.nfils = c.getNfilas();
		this.ncols = c.getNcols();
		this.tablero = new int[nfils][ncols];
		this.max_rest_fil = ((ncols / 2) + (ncols % 2));
		this.max_rest_col = ((nfils / 2) + (nfils % 2));
		//Hacemos una copia del tablero original asi podemos deshacer cambios y comparar.
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
			if (conflictoCol(i)) return true; //Si hay conflicto retorno TRUE
		return false;
	}
	
	/******************************************************************************************************/
	public boolean conflictoCol(int col){ //Conflicto solo puede valer TRUE cuano se produce un conflicto para esa columna
		if (funcionaConUna(col)) return false; //Mientras funcione para esta columna no hay conflicto
		return true;
	}
	
	/******************************************************************************************************/
	public boolean funcionaConUna(int col){//Pruebo con esta columna que no haya conflicto con las soluciones de las filas
		//Hasta la lineaActual de la que tengo solucion en el tablero del canvas
		int[][] resAux = new int[ncols][canvas.getMax_rest_col()];
		int cont = 0;//contador de casillas negras
		int color;
		int posFila = 0;
		int num;
		int total = 0;
		//Calculamos el numero de restricciones por Columna 
			num = numeroRC(col);
			
			if (num == 0){//Si no habia restricciones para esa columna
				total = 0;
			}
			else{
				total = canvas.getMax_rest_col()-num;//Las que me quedan KE ES TOTAL?? LAS Q ME QUEDAN??
			}
			
			for(int j = 0; j <= lineaActual; j++){
				color = canvas.getValorPosTablero(j,col);//Compruebo si la casilla es blanca o negra en esa fila
				if(color > 0){ //casilla negra
					cont++;
				}
				else{  //casilla blanca
					if(cont > 0){ //Si habia al menos una negra
						resAux[col][posFila] = cont; //Le asigno el numero de fichas negras consecutivas a nuestra matriz auxiliar
						cont = 0;//Reinicio el contador xq tengo una casilla blanca
						//Si no concuerdan los valores entonces hay CONFLICTO
						if((total > canvas.getMax_rest_col()-1) || (resAux[col][posFila] != restriccionesCols[col][total])){
							return false;
						}
						//De lo contrario si se cumple incrementamos para pasar a la siguiente columna
						posFila++;
						total++;
					}
				}
			}
			
			
			if(cont > 0){//Si he acabado en una casilla negra
				resAux[col][posFila] = cont; //Asigno el valor a nuestra matriz auxiliar
				cont = 0;//Reinicio el contador
				if((total > canvas.getMax_rest_col()-1) || (resAux[col][posFila] > restriccionesCols[col][total])){
					return false;
				}
			}
			posFila = 0;//Reinicio el punto de partida	
		return true;
	
	
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

	
	
	/******************************************************************************************************/
	public boolean esValida(){
		//Comprobamos que lo que hay se corresponde con lo que deberia ser
		int c = canvas.getNcols();
		int f = canvas.getNfilas();
		//Creamos una matriz de restricciones auxiliar para luego comparar con el original
		int[][] resAux = new int[c][canvas.getMax_rest_col()]; 
		int cont = 0;
		int color;
		int posFila = canvas.getMax_rest_col()-1;
		for(int i = c-1; i >= 0; i--){
			for(int j = f-1; j >= 0; j--){
				color = canvas.getValorPosTablero(j,i);
				if(color > 0){ //Ficha negra
					cont ++;
				}
				else{ //Ficha blanca
					if(cont > 0){
						resAux[i][posFila] = cont;
						cont = 0;
						posFila--;
					}
				}
			}
			
			if(cont > 0){
				resAux[i][posFila] = cont;
				cont = 0;
			}
			posFila = canvas.getMax_rest_col()-1;
		}
		if (cont > 0){
			resAux[c-1][posFila] = cont;
		}
		
		//Comparamos una a una que ambas matrices restricciones son iguales
		//De no serlo, decimos que no es valido y devolvemos FALSE
		for(int fil = 0; fil < canvas.getMax_rest_col(); fil++){
			for(int col = 0; col < ncols; col++){
				if(resAux[col][fil] != restriccionesCols[col][fil]){return false;}
			}
		}
		
		return true;
	}

	/******************************************************************************************************/
	public int[][] restSinCerosC(){
		int[][] respuesta = new int[ncols][];
		for (int i = 0; i<ncols;i++)
			respuesta[i]=resSinC(i);
		
		return respuesta;
	}
	
	/******************************************************************************************************/
	public int[][] restSinCerosF(){
		int[][] respuesta = new int[nfils][];
		for (int i = 0; i<nfils;i++)
			respuesta[i]=resSinF(i);
		
		return respuesta;
	}
	
	/******************************************************************************************************/
	private void ponerFilaTablero(int fil, int[] is) {
		for (int i = 0; i < ncols; i++){//Ponemos toda la fila a blanco
			canvas.setValorPosTablero(fil, i, -1);
		}
		
		//Pintamos las fichas negras
		int cuantas = is.length;
		int c = canvas.getMax_rest_fil() - cuantas;
		for(int i = 0; i < cuantas; i++){
			int aux = restriccionesFilas[fil][c+i];
			int posIni = is[i];
			for(int j = posIni; j < posIni+aux; j++){
				canvas.setValorPosTablero(fil, j, 1);
			}
		}
		canvas.pintarFichas(canvas.getGraphics());
		
	}
	
	/******************************************************************************************************/
	private void quitarFilaTablero(int fil, int[] is) {//Restablecemos los valores originales de esa fila
		for (int i = 0; i < ncols; i++){
			if(tablero[fil][i] == 0){
			canvas.setValorPosTablero(fil, i, 0);
			}
		}
		canvas.pintarFichas(canvas.getGraphics());
	}

	/******************************************************************************************************/
	private boolean esAplicable(int lA, int[] solA){
		int[] fila = new int[ncols];
		for(int i = 0; i < ncols; i++){ //Ponemos la fila totalmente en blanco
				fila[i] = -1;
				}
		
		int t = solA.length;
		for(int j = 0; j < t; j++){
			int posIni = solA[j];//La posicion desde donde deberia empezar a pintar
			int num = restriccionesFilas[lA][canvas.getMax_rest_fil()-(t-j)];
			for(int k = posIni; k < posIni+num; k++){ //Pintamos solo las casillas negras
				fila[k] = 1;
			}
		}
		
		//Comprueba si hay algun conflicto entre lo que hay y lo que deberia hacer para cada fila del tablero
		//Si no hay conflicto decimos que es Aplicable y retornamos  TRUE
		for(int x = 0; x < ncols; x++){
			if((tablero[lA][x] != 0) && (tablero[lA][x] != fila[x])){
				return false;
			}
		}
		return true;
	}
	

	/******************************************************************************************************/
	public int[][] getTablero(){
		return tablero;
	}
	/******************************************************************************************************/
	public int[][] getSolActual(){
		return solActual;
	}
	/******************************************************************************************************/
	
	private void aplicarForzado(){
		
		int[][] forzadas = new int[nfils][ncols];
		boolean negrasForzadas[] = new boolean[Math.max(nfils, ncols)];
		boolean blancasForzadas[] = new boolean[Math.max(nfils, ncols)];
		
		//FILAS
		for(int i = 0; i < nfils; i++){ 
			forzadoFila(i, negrasForzadas, blancasForzadas);
			for(int j = 0; j < ncols; j++){
				
				if(negrasForzadas[j] &&  (tablero[i][j] == 0)) {
					tablero[i][j]=forzadas[i][j]=1;
				}
				else{
					if((blancasForzadas[j]) &&  (tablero[i][j] == 0)){
						tablero[i][j]=forzadas[i][j]=-1;
					}
				}
			}
		}
		
		//COLUMNAS
		for(int x = 0; x < ncols; x++){ 
			forzadoColumna(x, negrasForzadas, blancasForzadas);
			for(int y = 0; y < nfils; y++){
				if(negrasForzadas[y] &&  tablero[y][x] == 0){
					tablero[y][x]=forzadas[y][x]=1;
				}
				else{
					if((blancasForzadas[y] && tablero[y][x] == 0)){
						tablero[y][x]=forzadas[y][x]=-1;
					}
				}
			}
		}
		pilaForzado.push(forzadas); //Coloco en la cima de la pila las forzadas
	}
	
	/******************************************************************************************************/
	private void forzadoFila(int in, boolean[] negroF, boolean[] blancoF){
		
		for (int i = 0; i < negroF.length; i++){
			negroF[i] = true; 
		}
		for (int j = 0; j < blancoF.length; j++){
			blancoF[j] = true; 
		}
		
		ListIterator<int[]> it = solFilas[in].listIterator();
		int[] sol;
		int numR = 0; //numero de restricciones
		int numNoR = 0; //numero de No restricciones, para saber la posicion
		
		while(it.hasNext()){
			numR = 0;
			
			for(int aux = 0; aux < canvas.getMax_rest_fil(); aux++){
				if(restriccionesFilas[in][aux] != 0){numR++;}
			}
			
			numR = canvas.getMax_rest_fil()-numR; //es como el  total de funciona con una
			numNoR = numR;
			sol = it.next();
			
			if(esAplicable(in,sol) && (sol.length != 0)){
				//forzado negras
				//a.1 hasta primer lugar en blanco
				for(int blanco = 0; blanco < sol[0]; blanco++){
					negroF[blanco] = false;
				}
				//a.2 desde el ultimo bloque hasta el final
				int lim = sol[sol.length-1] + restriccionesFilas[in][canvas.getMax_rest_fil() - 1];
				for(int bFinal = lim; bFinal < ncols; bFinal++){
					negroF[bFinal] = false;
				}
				int r;
				//a.3 blancos entre bloques
				for(int bCentro = 0; bCentro < sol.length-1; bCentro++){
					r = sol[bCentro] + restriccionesFilas[in][numR];
					for(int posFin = r; posFin < sol[bCentro+1]; posFin++){
						negroF[posFin] = false;
					}
					numR++;
				}
				//forzado blancas
				for(int b = 0; b < sol.length; b++){
					for(int k = sol[b]; k < (restriccionesFilas[in][numNoR]+sol[b]); k++){
						blancoF[k] = false;
					}
					numNoR++;
				}
			}
			
			
			if ((sol.length == 0)){
				for (int i = 0; i < blancoF.length; i++){
					blancoF[i] = true;
				}
				for (int i = 0; i < negroF.length; i++){
					negroF[i] = false;
				}
			}
		}
	}
	
	/******************************************************************************************************/
	private void forzadoColumna(int in, boolean[] negroF, boolean[] blancoF){
		
		for (int i = 0; i < negroF.length; i++){
			negroF[i] = true; 
		}
		
		for (int j = 0; j < blancoF.length; j++){
			blancoF[j] = true; 
		}
		
		ListIterator<int[]> it = solCols[in].listIterator();
		int[] sol;
		int numR = 0;
		int numNoR = 0;
		
		while(it.hasNext()){
			numR = 0;
			
			for(int aux = 0; aux < canvas.getMax_rest_col(); aux++){
				if(restriccionesCols[in][aux] != 0){numR ++;}
			}
			
			numR = canvas.getMax_rest_col()-numR;
			numNoR = numR;
			sol = it.next();
			
			if(esAplicableCol(in,sol) && (sol.length != 0)){
				//forzado negras
				for(int blanco = 0; blanco < sol[0]; blanco++){
					negroF[blanco] = false;
				}
				
				int lim = sol[sol.length-1] + restriccionesCols[in][canvas.getMax_rest_col() - 1];
				for(int bFinal = lim; bFinal < nfils; bFinal++){
					negroF[bFinal] = false;
				}
				int r;
				for(int bCentro = 0; bCentro < sol.length-1; bCentro++){
					r = sol[bCentro] + restriccionesCols[in][numR];
					for(int posFinal = r; posFinal < sol[bCentro+1]; posFinal++){
						negroF[posFinal] = false;
					}
					numR++;
				}
				//forzado blancas
				for(int b = 0; b < sol.length; b++){
					for(int k = sol[b]; k < (restriccionesCols[in][numNoR]+sol[b]); k++){
						blancoF[k] = false;
					}
					numNoR++;
				}
			}
			if ((sol.length == 0)){
				for (int i = 0; i < blancoF.length; i++){
					blancoF[i] = true;
				}
				for (int i = 0; i < negroF.length; i++){
					negroF[i] = false;
				}
			}
		}
	}
	
	/******************************************************************************************************/
	private void deshacerForzado() {
		int[][] forz = pilaForzado.pop(); //Quitamos el forzado aplicado de la pila
		for(int i = 0; i < nfils; i++){
			for(int j = 0; j < ncols; j++){
				if(forz[i][j] != 0){
					canvas.setValorPosTablero(i, j, 0);
				}
			}
		}
	}
	
	/******************************************************************************************************/
	private boolean esAplicableCol(int c, int[] solAct){//Lo mismo que esAplicable pero para cols
		
		int[] col = new int[nfils];
		for(int i = 0; i < nfils; i++){ //la ponemos a blanco entera
			col[i] = -1;
		}
		
		int t = solAct.length;
		for(int j = 0; j < t; j++){
			int pos = solAct[j];
			int num = restriccionesCols[c][canvas.getMax_rest_col()-(t-j)];
			for(int k = pos; k < pos+num; k++){ //pintamos las casillas negras
				col[k] = 1;
			}
		}
		
		for(int i = 0; i < nfils; i++){
			if((tablero[i][c] != 0) && (tablero[i][c] != col[i])){
				return false;
			}
		}
		return true;
	}
	
	/******************************************************************************************************/
	public TableroCanvas getCanvas() {
		return canvas;
	}

	/******************************************************************************************************/
	public void setCanvas(TableroCanvas canvas) {
		this.canvas = canvas;
	}
	
}//fin de clase
