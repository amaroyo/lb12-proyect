package matriz;

public class Matriz {
	
	int filas;
	int columnas;
	int matriz[][];
	
	public Matriz(int a){
		matriz = new int[5][5];
		if (a==1){
			for (int i=0; i<5; i++)
				for (int j=0;j<5;j++){
					matriz[i][j]=i+j;
				}
		}
		else { for (int i=0; i<5; i++)
					for (int j=0;j<5;j++){
						matriz[i][j]=2*i-j;
					}
			}
	}
	
	public Matriz(int f, int c){
		
		filas = f;
		columnas = c;
		matriz = new int[filas][columnas];
	}
	
	public int getIJ(int i, int j){
		return matriz[i][j];
	}

	public void setIJ(int i, int j, int a){
		matriz[i][j] = a;
	}
	
	public void suma(Matriz m1, Matriz m2){
		//Matriz resul = new Matriz(filas,columnas);
		
		for (int i = 0; i < filas; i++)
			for (int j = 0; j < columnas; j++)
				matriz[i][j]= m1.getIJ(i, j) + m2.getIJ(i, j);
				//resul.setIJ(i, j, m1.getIJ(i, j) + m2.getIJ(i, j));
		
				
	}
	
	public void resta(Matriz m1, Matriz m2){
		//Matriz resul = new Matriz(filas,columnas);
		
		for (int i = 0; i < filas; i++)
			for (int j = 0; j < columnas; j++)
				matriz[i][j]= m1.getIJ(i, j) - m2.getIJ(i, j);
				//resul.setIJ(i, j, m1.getIJ(i, j) - m2.getIJ(i, j));
		
				
	}
	
	public void multiplica(Matriz m1, Matriz m2){
		//Matriz resul = new Matriz(filas,columnas);
		int suma = 0;
		
		for(int i = 0; i < filas; i++){
			for(int j = 0; j < columnas; j++){
				suma = 0;
				for(int k = 0; k < columnas; k++){
					suma += m1.getIJ(i, k) * m2.getIJ(k, j);
				}
				//resul.setIJ(i, j, suma);
			}
		}
		//return resul;
		
	}

	public Matriz multiplicarStrassen(Matriz a, Matriz b) {
		 
		Matriz aux = new Matriz(filas,columnas);
		 if (filas%2 == 1) {aux.multiplica(a,b);
		 					return aux;
		 					}
		 else {
			 int posMedia = (filas/2);
			 
			 Matriz A11 = new Matriz(posMedia,posMedia);
			 A11.copiarValores(a,1,1,posMedia,posMedia);
			 Matriz A12 = new Matriz(posMedia,posMedia);
			 A12.copiarValores(a,1,posMedia+1,posMedia,columnas);
			 Matriz A21 = new Matriz(posMedia,posMedia);
			 A21.copiarValores(a,posMedia+1,1,columnas,posMedia);
			 Matriz A22 = new Matriz(posMedia,posMedia);
			 A22.copiarValores(a,posMedia+1,columnas,posMedia+1,columnas);
			 
			 
			 Matriz B11 = new Matriz(posMedia,posMedia);
			 B11.copiarValores(b,1,1,posMedia,posMedia);
			 Matriz B12 = new Matriz(posMedia,posMedia);
			 B12.copiarValores(b,1,posMedia+1,posMedia,columnas);
			 Matriz B21 = new Matriz(posMedia,posMedia);
			 B21.copiarValores(b,posMedia+1,1,columnas,posMedia);
			 Matriz B22 = new Matriz(posMedia,posMedia);
			 B22.copiarValores(b,posMedia+1,columnas,posMedia+1,columnas);
			 
			 Matriz S1 = new Matriz(posMedia,posMedia);
			 S1.suma(A11, A22);
			 Matriz S2 = new Matriz(posMedia,posMedia);
			 S2.suma(B11, B22);
			 Matriz m1 = new Matriz(posMedia,posMedia);
			 m1 = multiplicarStrassen(S1,S2);
			 //****************************************************
			 Matriz S3 = new Matriz(posMedia,posMedia);
			 S3.suma(A21, A22);
			 Matriz m2 = new Matriz(posMedia,posMedia);
			 m2 = multiplicarStrassen(S3,B11);
			 
			 Matriz S4 = new Matriz(posMedia,posMedia);
			 S1.resta(B12, B22);
			 Matriz m3 = new Matriz(posMedia,posMedia);
			 m3 = multiplicarStrassen(A11,S4);
			 
			 
			 
		 }
		
		
		return null;
		
		
	}
	
	public void copiarValores(Matriz m, int iniX, int iniY, int finX , int finY){
		
		for (int i = iniX; i<finX; i++)
			for (int j = iniY; j<finY; j++){
				matriz[i][j]=m.getIJ(i,j);
			}
				
	}
	
}
