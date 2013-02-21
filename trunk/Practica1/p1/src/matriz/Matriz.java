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
	
	public Matriz suma(Matriz m1, Matriz m2){
		Matriz resul = new Matriz(filas,columnas);
		
		for (int i = 0; i < filas; i++)
			for (int j = 0; j < columnas; j++)
				resul.setIJ(i, j, m1.getIJ(i, j) + m2.getIJ(i, j));
		
		return resul;
				
	}
	
	public Matriz resta(Matriz m1, Matriz m2){
		Matriz resul = new Matriz(filas,columnas);
		
		for (int i = 0; i < filas; i++)
			for (int j = 0; j < columnas; j++)
				resul.setIJ(i, j, m1.getIJ(i, j) - m2.getIJ(i, j));
		
		return resul;
				
	}
	
	public Matriz multiplica(Matriz m1, Matriz m2){
		Matriz resul = new Matriz(filas,columnas);
		int suma = 0;
		
		for(int i = 0; i < filas; i++){
			for(int j = 0; j < columnas; j++){
				suma = 0;
				for(int k = 0; k < columnas; k++){
					suma += m1.getIJ(i, k) * m2.getIJ(k, j);
				}
				resul.setIJ(i, j, suma);
			}
		}
		return resul;
		
	}
	
}
