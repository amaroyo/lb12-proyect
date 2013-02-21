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
						matriz[i][j]=i-j;
					}
			}
	}
	
	public Matriz(int f, int c){
		
		filas = f;
		columnas = c;
		matriz = new int[filas][columnas];
	}
	
	

}
