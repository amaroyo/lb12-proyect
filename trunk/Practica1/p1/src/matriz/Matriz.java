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
			filas = 5;
			columnas = 5;
		}
		else { for (int i=0; i<5; i++)
					for (int j=0;j<5;j++){
						matriz[i][j]=2*i-j;
					}
		filas = 5;
		columnas = 5;
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
	
	public int getFilas(){
		return filas;
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
	
	public Matriz multiplica(Matriz m1, Matriz m2){
		
		Matriz resul = new Matriz(m1.getFilas(),m1.getFilas());
		int suma = 0;
		
		for(int i = 0; i < m1.getFilas(); i++){
			for(int j = 0; j < m1.getFilas(); j++){
				suma = 0;
				for(int k = 0; k < m1.getFilas(); k++){
					suma += m1.getIJ(i, k) * m2.getIJ(k, j);
				}
				resul.setIJ(i, j, suma);
			}
		}
		return resul;
		
	}

	public Matriz multiplicarStrassen(Matriz a, Matriz b) {
		 
		Matriz resul = new Matriz(a.getFilas(),a.getFilas());
		
		 if (a.getFilas()%2 == 1) {
			 resul = multiplica(a,b);
		 	 return resul;
		 }
		 else {
			 int posMedia = (a.getFilas()/2);
			 
			 Matriz A11 = new Matriz(posMedia,posMedia);
			 A11.copiarValores(a,0,posMedia,0,posMedia);
			 Matriz A12 = new Matriz(posMedia,posMedia);
			 A12.copiarValores(a,0,posMedia,posMedia,a.getFilas());
			 Matriz A21 = new Matriz(posMedia,posMedia);
			 A21.copiarValores(a,posMedia,a.getFilas(),0,posMedia);
			 Matriz A22 = new Matriz(posMedia,posMedia);
			 A22.copiarValores(a,posMedia,a.getFilas(),posMedia,a.getFilas());
			 
			 
			 Matriz B11 = new Matriz(posMedia,posMedia);
			 B11.copiarValores(b,0,posMedia,0,posMedia);
			 Matriz B12 = new Matriz(posMedia,posMedia);
			 B12.copiarValores(b,0,posMedia,posMedia,a.getFilas());
			 Matriz B21 = new Matriz(posMedia,posMedia);
			 B21.copiarValores(b,posMedia,a.getFilas(),0,posMedia);
			 Matriz B22 = new Matriz(posMedia,posMedia);
			 B22.copiarValores(b,posMedia,a.getFilas(),posMedia,a.getFilas());
			 
			 Matriz S1 = new Matriz(posMedia,posMedia);
			 S1.suma(A11, A22);
			 Matriz S2 = new Matriz(posMedia,posMedia);
			 S2.suma(B11, B22);
			 Matriz m1 = new Matriz(posMedia,posMedia);
			 m1 = multiplicarStrassen(S1,S2);
			 
			
			 Matriz S3 = new Matriz(posMedia,posMedia);
			 S3.suma(A21, A22);
			 Matriz m2 = new Matriz(posMedia,posMedia);
			 m2 = multiplicarStrassen(S3,B11);
			 
			 
			 Matriz S4 = new Matriz(posMedia,posMedia);
			 S4.resta(B12, B22);
			 Matriz m3 = new Matriz(posMedia,posMedia);
			 m3 = multiplicarStrassen(A11,S4);
			 
			 Matriz S5 = new Matriz(posMedia,posMedia);
			 S5.resta(B21, B11);
			 Matriz m4 = new Matriz(posMedia,posMedia);
			 m4 = multiplicarStrassen(A22,S5);
			 
			 Matriz S6 = new Matriz(posMedia,posMedia);
			 S6.suma(A11, A12);
			 Matriz m5 = new Matriz(posMedia,posMedia);
			 m5 = multiplicarStrassen(S6,B22);
			 
			 Matriz S7 = new Matriz(posMedia,posMedia);
			 S7.resta(A21, A11);
			 Matriz S8 = new Matriz(posMedia,posMedia);
			 S8.suma(B11, B12);
			 Matriz m6 = new Matriz(posMedia,posMedia);
			 m6 = multiplicarStrassen(S7,S8);
			 
			 Matriz S9 = new Matriz(posMedia,posMedia);
			 S9.resta(A12, A22);
			 Matriz S10 = new Matriz(posMedia,posMedia);
			 S10.suma(B21,B22);
			 Matriz m7 = new Matriz(posMedia,posMedia);
			 m7 = multiplicarStrassen(S9,S10);
			 
			 //////////////////////////////////////////
			 
			 Matriz X1 = new Matriz(posMedia,posMedia);
			 X1.suma(m1, m7);
			 Matriz X2 = new Matriz(posMedia,posMedia);
			 X2.resta(m4, m5);
			 Matriz C11 = new Matriz(posMedia,posMedia);
			 C11.suma(X1, X2);
			 
			 Matriz C12 = new Matriz(posMedia,posMedia);
			 C12.suma(m3, m5);
			 
			 Matriz C21 = new Matriz(posMedia,posMedia);
			 C21.suma(m2, m4);
			 
			 Matriz X3 = new Matriz(posMedia,posMedia);
			 X3.suma(m1, m6);
			 Matriz X4 = new Matriz(posMedia,posMedia);
			 X4.resta(m3, m2);
			 Matriz C22 = new Matriz(posMedia,posMedia);
			 C22.suma(X3, X4);
			 
			 
			 resul.componerResultado(C11,C12,C21,C22, posMedia);
			 
			 return resul;
			 
		 }
		
	}
	
	public void componerResultado(Matriz c11, Matriz c12, Matriz c21, Matriz c22, int p){
		
		int k = 0;
		
		for(int i = 0; i < p; i++){
			int v = 0;
			for(int j = 0; j < p; j++){
				matriz[i][j]=c11.getIJ(k,v);
				v++;
			}
			k++;
		}
		
		int k1 = 0;
		
		for(int i = 0; i < p; i++){
			int v = 0;
			for(int j = p; j < columnas; j++){
				matriz[i][j]=c12.getIJ(k1,v);
				v++;
			}
			k1++;
		}
		
		int k2=0;
		
		for(int i = p; i < columnas; i++){
			int v = 0;
			for(int j = 0; j < p; j++){
				matriz[i][j]=c21.getIJ(k2,v);
				v++;
			}
			k2++;
		}
		
		int k3 = 0;
		for(int i = p; i < columnas; i++){
			int v = 0;
			for(int j = p; j < columnas; j++){
				matriz[i][j]=c22.getIJ(k3,v);
				v++;
			}
			k3++;
		}
	}
	
	public void copiarValores(Matriz m, int iniX, int finX , int iniY, int finY){
		
		int k = 0;
		
		for (int i = iniX; i<finX; i++){
			int v = 0;
			for (int j = iniY; j<finY; j++){
				matriz[k][v]=m.getIJ(i,j);
				v++;
			}
			k++;
		}		
	}
	
}
