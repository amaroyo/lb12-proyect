package grafo;

public class Grupo {
	private Grupo padre;
	private int prof;
	
	public Grupo(){
		padre = null;
		prof = 0;
	}
	
	public Grupo dameGrupo(){
		if (this.padre == null) return this;
		else return padre = padre.dameGrupo();
	}
	
	
	
	 public void union(Grupo g){
		 //cuando dos arboles distintos comparten una misma arista, los unimos
		if(this.dameGrupo() != g.dameGrupo()){//si no tienen el mismo padre (son arboles distintos)
			if (this.prof <= g.prof){
				//si el padre no es null, aprovecho para cambiar el puntero e ir minimizando la profundidad
				 this.dameGrupo().padre = g.dameGrupo();
				 this.prof = Math.max(this.prof, g.prof+1);
				}
			else if (this.prof > g.prof){
				g.dameGrupo().padre = this.dameGrupo();
				g.prof = Math.max(this.prof+1, g.prof);
				}
			
		}	
	}
	 
	public boolean equals(Object o){
		if (dameGrupo() == ((Grupo) o).dameGrupo()) return true;
		else return false;
	}
}

